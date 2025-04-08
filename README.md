# ✨ WordReplacer - Java Capstone Project (COMS W3134)

This project is the **Final Capstone** for the course **COMS W3134: Data Structures in Java** at **Columbia University**.  
It is a command-line Java application that performs **word replacements in a text file**, using efficient data structures and graph-based cycle detection.

---

## 🔍 Features Overview

### ✅ Global Word Replacement
- Load word → replacement rules from file
- Supports **transitive replacement chains**
- Detects and rejects **replacement cycles**

### 🌳 Data Structure Options
- `BSTreeMap` – Binary Search Tree Map
- `RBTreeMap` – Red-Black Tree Map
- `MyHashMap` – Custom HashMap

### ⚙️ Command-Line Interface
- Validate all arguments and input files
- Use Java's `StringBuilder` for efficient output
- Exit gracefully with error messages on failure

---

## 📂 Usage Example

```bash
java WordReplacer inputs/input1.txt inputs/replacements1.txt bst
```

Where:
- `input1.txt`: the original text
- `replacements1.txt`: word replacement rules
- `bst`: the data structure (`bst`, `rbt`, or `hash`)

---

## 📄 Sample Input / Output

**Text:**
```
Alice will meet Bob at the supermarket.
```

**Rules:**
```
Alice -> Jennifer
supermarket -> diner
```

**Output:**
```
Jennifer will meet Bob at the diner.
```

---

## ⏱️ Performance Evaluation

Run with large input (e.g. `warandpeace.txt`):

```bash
time java WordReplacer warandpeace.txt warandpeace_replacements.txt hash > /dev/null
```

Repeat with all three data structures and record average timings.

---

## 📁 File Structure

```
Final/
├── src/               # Core source code and data structures
├── inputs/            # Test cases and replacement files
├── WordReplacer/      # Main executable class
├── sampledata/        # Large-scale test files
├── readme.txt         # Report with timing and analysis
├── Capstone-WordReplacer.pdf  # Project specification
```

---

## 🧑‍💻 Author

Developed by **Fan Yi (Sumio0)**  
Columbia University - Fall 2024  
