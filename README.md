# Student Management System (Java)

## 📌 Overview

This project is a **Student Management System** written in Java. It allows you to manage student records using a **Binary Search Tree (BST)** for fast searching and indexing. The student data is stored persistently in a **text file (`students_sample.txt`)**, so records remain available even after the program is closed.

Each student record contains:

* **Code** (unique student ID, used as the BST key)
* **Surname**
* **Name**
* **Sex (M/F)**
* **Year** (enrollment year)
* **Grade** (float)

## ⚙️ Features

1. **Insert a new student**

   * Add a new student record.
   * Automatically stored in the `students_sample.txt` file.
   * BST is updated with the new record.

2. **Search for a student by code**

   * Uses the BST to quickly find the record.
   * Displays the full student details from the file.

3. **Print all students (inorder traversal)**

   * Displays the BST contents in ascending order of student codes.
   * Shows `(code, record number)` pairs.

4. **Print students with grade ≥ input**

   * Scans the file and prints students meeting the grade criteria.

5. **Persistent storage in text file**

   * All data is stored in `students_sample.txt` as text.
   * Each line of the file corresponds to one student record, formatted as:

     ```
     code, surname, name, sex, year, grade
     ```

## 📂 Example of `students_sample.txt`

```
101, Smith, John, M, 2021, 8.5
102, Brown, Alice, F, 2020, 9.1
103, Wilson, Mark, M, 2022, 7.4
```

## ▶️ How to Run

1. Compile the program:

   ```bash
   javac StudentManagement.java
   ```
2. Run the program:

   ```bash
   java StudentManagement
   ```
3. Follow the menu options to add, search, or list students.

## 🔑 Key Points

* **BST is used for searching by student code** (efficient lookup).
* **File (`students_sample.txt`) is the permanent storage**, ensuring records persist across sessions.
* Inorder traversal of BST gives students sorted by code.
