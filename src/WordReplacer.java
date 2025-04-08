import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Class for the main program
 * Author Fan Yi
 * UNI:sy3194
 */
public class WordReplacer {

    public static void main(String[] args) {
        //Parsing Command Line Arguments
        parseCommandLineArg(args);
        String inputTextFile = args[0];
        String wordReplacementsFile = args[1];
        String dataStructure = args[2];
        //Creating the Map
        MyMap<String, String> map = createMyMap(dataStructure);

        //Parsing the Input Files
        loadReplacements(map,wordReplacementsFile);

        //read the input text file
        replaceInputFile(map,inputTextFile);

    }

    /**
     * read the input text file
     * @param map
     * @param inputTextFile
     */
    private static void replaceInputFile(MyMap<String, String> map, String inputTextFile) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(inputTextFile));
            StringBuilder builder = new StringBuilder();
            String line = null;
            while ((line = br.readLine()) != null){
                int i = 0;
                StringBuilder wordBuilder = new StringBuilder();
                while (i < line.length()){
                    char currentChar = line.charAt(i);
                    if(Character.isLetter(currentChar)){
                        wordBuilder.append(currentChar);
                    }else{
                        if(wordBuilder.length() > 0){
                            String word = wordBuilder.toString();
                            wordBuilder.setLength(0);
                            builder.append(replaceWithUpdate(word,map));
                        }
                        builder.append(currentChar);
                    }
                    i++;
                }
                if(wordBuilder.length() > 0){
                    String word = wordBuilder.toString();
                    wordBuilder.setLength(0);
                    builder.append(replaceWithUpdate(word,map));
                }
                builder.append("\n");
            }
            br.close();
            System.out.printf("%s\n",builder.substring(0,builder.length()-1));

        }catch (FileNotFoundException e){
            //verify that the input text file exists
            System.err.println("Error: Cannot open file '" + inputTextFile + "' for input.");
            System.exit(1);
        }catch (IOException e) {
            System.err.println("Error: An I/O error occurred reading '" + inputTextFile + "'.");
        }
    }

    /**
     *  replace word to replacement
     * @param word
     * @param map
     * @return
     */
    private static String replaceWithUpdate(String word, MyMap<String, String> map) {
        String finalNeighbour = getFinalNeighbour(word,map);
        if(finalNeighbour.equals(word)){
            return word;
        }
        map.put(word,finalNeighbour);
        return finalNeighbour;
    }

    /**
     * get the final replacement of word
     * @param word
     * @param map
     * @return
     */
    private static String getFinalNeighbour(String word,MyMap<String, String> map){
        String neighbour = map.get(word);
        if(neighbour != null){
            return getFinalNeighbour(neighbour,map);
        }else{
            return word;
        }
    }

    /**
     * the word replacements file
     * @param myMap
     * @param wordReplacementsFile
     */
    public static void loadReplacements(MyMap<String, String> myMap,String wordReplacementsFile){
        try {
            BufferedReader br = new BufferedReader(new FileReader(wordReplacementsFile));
            String line = null;
            while ((line = br.readLine()) != null){
                String[] arr = line.split("->");
                if(arr.length == 2){
                    String a = arr[0].trim();
                    String b = arr[1].trim();
                    //record the rule
                    myMap.put(a,b);

                    MyMap<String, Boolean> visitedMap = new MyHashMap<>();
                    MyMap<String, Boolean> onStackMap = new MyHashMap<>();
                    if (isCyclic(a,visitedMap,onStackMap,myMap)) {
                        System.err.println("Error: Cycle detected when trying to add replacement rule: " + a + " -> " + b);
                        System.exit(1);
                    }
                }
            }
            br.close();


        }catch (FileNotFoundException e){
            //verify that the word replacements file exists.
            System.err.println("Error: Cannot open file '" + wordReplacementsFile + "' for input.");
            System.exit(1);
        }catch (IOException e) {
            System.err.println("Error: An I/O error occurred reading '" + wordReplacementsFile + "'.");
        }
    }

    /**
     * detect there is cycle or not
     * this algorithm is DFS
     * @param node
     * @param visitedMap
     * @param onStackMap
     * @param replacements
     * @return
     */
    private static boolean isCyclic(String node,MyMap<String,Boolean>visitedMap,MyMap<String,Boolean>onStackMap,
                                    MyMap<String,String> replacements) {
        // Check whether the node is accessed
        if (visitedMap.get(node) != null) {
            return false;
        }
        visitedMap.put(node, true);
        onStackMap.put(node, true);

        String neighbor = replacements.get(node);
        if (neighbor!= null) {
            // Determines whether the neighbor node is in the recursive stack
            if (onStackMap.get(neighbor) != null) {
                return true;
            }
            // Determines whether the neighbor node is accessed, and makes a recursive call to detect the presence of a ring
            if (visitedMap.get(neighbor) == null && isCyclic(neighbor,visitedMap,onStackMap,replacements)) {
                return true;
            }
        }

        //Removes the node from the recursive stack
        onStackMap.remove(node);
        return false;
    }

    /**
     * instantiate MyMap
     * @param dataStructure
     * @return
     */
    private static MyMap createMyMap(String dataStructure){
        MyMap<String, String> map = null;
        if(dataStructure.equals("bst")){
            map = new BSTreeMap<>();
        }else if(dataStructure.equals("rbt")){
            map = new RBTreeMap<>();
        }else{
            map = new MyHashMap<>();
        }
        return map;
    }

    /**
     * Parsing Command Line Arguments
     * @param args
     */
    private static void parseCommandLineArg(String[] args){
        if(args == null || args.length != 3){
            System.err.println("Usage: java WordReplacer <input text file> <word replacements file> <bst|rbt|hash>");
            System.exit(1);
        }
        String dataStructure = args[2];
        //parse the command line for which data structure the user wishes to use
        if(!(dataStructure.equals("bst") || dataStructure.equals("rbt") || dataStructure.equals("hash"))){
            System.err.println("Error: Invalid data structure '" + dataStructure + "' received.");
            System.exit(1);
        }
    }
}
