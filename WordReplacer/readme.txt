===================================== Q1 =======================================
===bst===:
real    0m2.400s
user    0m3.500s
sys     0m0.193s

===rbt===:
real    0m1.358s
user    0m2.593s
sys     0m0.228s

===hash===:
real    0m0.937s
user    0m2.045s
sys     0m0.272s



===================================== Q2 =======================================
===What data structure do you expect to have the best (fastest) performance? Which one
do you expect to be the slowest?===
hash is the fastest and bst the slowest.

===Do the results of timing your program’s execution
match your expectations?===
matched

===briefly explain the correlation===

HashMap is implemented based on a hash table. Ideally, the time complexity of find, delete, insert, etc. is close to O(1). It takes up more space, and when you run out of memory, it takes up more time.

In the equilibrium state of BSTree, the time complexity of search or delete operations is O(log n). It gradually finds the target node in the tree by comparing the size of the node value with the target value. But in the worst case (the tree degenerates into a linked list), the time complexity degenerates to O(n). It takes up the least amount of space, and when memory runs out,BSTree is faster.

RBTree uses a self-balancing mechanism to ensure that the height of the tree is relatively stable, and the operation time complexity is always O(log n). It takes up less space than hash, but more space than bstree, so it's neither the fastest nor the slowest



================================================================================