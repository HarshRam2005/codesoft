import java.io.*;
import java.util.*;

class Student {

    private String name;
    private int rollNumber;
    private String grade;

    public Student(String name, int rollNumber, String grade) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public String getGrade() {
        return grade;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return rollNumber + "," + name + "," + grade;
    }
}

public class StudentManagementSystem {

    private static List<Student> students = new ArrayList<>();
    private static final String FILE_NAME = "students.txt";

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        loadFromFile();

        int choice;

        do {
            System.out.println("\n===== STUDENT MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Search Student");
            System.out.println("4. Edit Student");
            System.out.println("5. Display All Students");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();

            switch (choice) {
                case 1: addStudent(sc); break;
                case 2: removeStudent(sc); break;
                case 3: searchStudent(sc); break;
                case 4: editStudent(sc); break;
                case 5: displayStudents(); break;
                case 6:
                    saveToFile();
                    System.out.println("Data saved. Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 6);

        sc.close();
    }

    private static void addStudent(Scanner sc) {

        sc.nextLine();
        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Roll Number: ");
        int roll = sc.nextInt();

        sc.nextLine();
        System.out.print("Enter Grade: ");
        String grade = sc.nextLine();

        students.add(new Student(name, roll, grade));
        System.out.println("Student added successfully!");
    }

    private static void removeStudent(Scanner sc) {

        System.out.print("Enter Roll Number to remove: ");
        int roll = sc.nextInt();

        boolean removed = students.removeIf(s -> s.getRollNumber() == roll);

        if (removed)
            System.out.println("Student removed.");
        else
            System.out.println("Student not found.");
    }

    private static void searchStudent(Scanner sc) {

        System.out.print("Enter Roll Number to search: ");
        int roll = sc.nextInt();

        for (Student s : students) {
            if (s.getRollNumber() == roll) {
                System.out.println("Found: " + s.getRollNumber() + " | " + s.getName() + " | " + s.getGrade());
                return;
            }
        }
        System.out.println("Student not found.");
    }

    private static void editStudent(Scanner sc) {

        System.out.print("Enter Roll Number to edit: ");
        int roll = sc.nextInt();
        sc.nextLine();

        for (Student s : students) {
            if (s.getRollNumber() == roll) {

                System.out.print("Enter New Name: ");
                s.setName(sc.nextLine());

                System.out.print("Enter New Grade: ");
                s.setGrade(sc.nextLine());

                System.out.println("Student updated.");
                return;
            }
        }

        System.out.println("Student not found.");
    }

    private static void displayStudents() {

        if (students.isEmpty()) {
            System.out.println("No students available.");
            return;
        }

        System.out.println("\nRoll | Name | Grade");
        System.out.println("-----------------------");

        for (Student s : students) {
            System.out.println(s.getRollNumber() + " | " +
                    s.getName() + " | " +
                    s.getGrade());
        }
    }

    private static void saveToFile() {

        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Student s : students) {
                pw.println(s);
            }
        } catch (IOException e) {
            System.out.println("Error saving file.");
        }
    }

    private static void loadFromFile() {

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {

            String line;

            while ((line = br.readLine()) != null) {

                String[] data = line.split(",");

                if (data.length == 3) {
                    students.add(new Student(
                            data[1],
                            Integer.parseInt(data[0]),
                            data[2]
                    ));
                }
            }

        } catch (IOException e) {
            System.out.println("No previous data found.");
        }
    }
}
