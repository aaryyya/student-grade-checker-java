import java.io.*;
import java.util.*;

class Student {
    int code;
    String surname;
    String name;
    char sex;
    int year;
    float grade;

    Student(int code, String surname, String name, char sex, int year, float grade) {
        this.code = code;
        this.surname = surname;
        this.name = name;
        this.sex = sex;
        this.year = year;
        this.grade = grade;
    }

    @Override
    public String toString() {
        return code + ", " + surname + ", " + name + ", " + sex + ", " + year + ", " + grade;
    }
}

class BSTNode {
    int code;       // student code (key)
    int recNo;      // record number (line in file)
    BSTNode left, right;

    BSTNode(int code, int recNo) {
        this.code = code;
        this.recNo = recNo;
        this.left = null;
        this.right = null;
    }
}

public class StudentManagement {
    private static BSTNode root = null;
    private static final String FILE_NAME = "students_sample.txt";

    // ===== BST FUNCTIONS =====
    private static void insertBST(int code, int recNo) {
        root = insertRec(root, code, recNo);
    }

    private static BSTNode insertRec(BSTNode node, int code, int recNo) {
        if (node == null) return new BSTNode(code, recNo);
        if (code < node.code) node.left = insertRec(node.left, code, recNo);
        else if (code > node.code) node.right = insertRec(node.right, code, recNo);
        else System.out.println("This code already exists!");
        return node;
    }

    private static BSTNode searchBST(BSTNode node, int code) {
        if (node == null) return null;
        if (code == node.code) return node;
        if (code < node.code) return searchBST(node.left, code);
        return searchBST(node.right, code);
    }

    private static void inorder(BSTNode node) {
        if (node != null) {
            inorder(node.left);
            System.out.print("(" + node.code + ", " + node.recNo + "), ");
            inorder(node.right);
        }
    }

    // ===== FILE FUNCTIONS =====
    private static int buildBSTFromFile() {
        int size = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",\\s*");
                if (parts.length != 6) continue;
                int code = Integer.parseInt(parts[0]);
                insertBST(code, size);
                size++;
            }
        } catch (IOException e) {
            System.out.println("File not found, starting with empty database.");
        }
        return size;
    }

    private static void appendStudentToFile(Student s) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            bw.write(s.toString());
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error writing file.");
        }
    }

    private static void printStudent(int recNo) {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            int count = 0;
            while ((line = br.readLine()) != null) {
                if (count == recNo) {
                    System.out.println(line);
                    return;
                }
                count++;
            }
        } catch (IOException e) {
            System.out.println("Error reading file.");
        }
    }

    private static void printStudentsWithGrade(float minGrade) {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",\\s*");
                if (parts.length != 6) continue;
                float grade = Float.parseFloat(parts[5]);
                if (grade >= minGrade) {
                    System.out.println(line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file.");
        }
    }

    // ===== MAIN MENU =====
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int size = buildBSTFromFile();
        int choice;

        do {
            System.out.println("\n========= MENU =========");
            System.out.println("1. Insert new student");
            System.out.println("2. Search for a student");
            System.out.println("3. Print all students (Inorder)");
            System.out.println("4. Print students with >= grade");
            System.out.println("5. Quit");
            System.out.print("Choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter student code: ");
                    int code = sc.nextInt();
                    sc.nextLine();
                    if (searchBST(root, code) != null) {
                        System.out.println("Student already exists!");
                        break;
                    }
                    System.out.print("Enter surname: ");
                    String surname = sc.nextLine();
                    System.out.print("Enter name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter sex (M/F): ");
                    char sex = sc.next().charAt(0);
                    System.out.print("Enter year: ");
                    int year = sc.nextInt();
                    System.out.print("Enter grade: ");
                    float grade = sc.nextFloat();
                    sc.nextLine();

                    Student s = new Student(code, surname, name, sex, year, grade);
                    appendStudentToFile(s);
                    insertBST(code, size);
                    size++;
                }
                case 2 -> {
                    System.out.print("Enter student code: ");
                    int code = sc.nextInt();
                    BSTNode found = searchBST(root, code);
                    if (found != null) {
                        printStudent(found.recNo);
                    } else {
                        System.out.println("Student not found!");
                    }
                }
                case 3 -> {
                    System.out.println("Students (code, recNo): ");
                    inorder(root);
                    System.out.println();
                }
                case 4 -> {
                    System.out.print("Enter minimum grade: ");
                    float g = sc.nextFloat();
                    printStudentsWithGrade(g);
                }
                case 5 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice!");
            }

        } while (choice != 5);

        sc.close();
    }
}
