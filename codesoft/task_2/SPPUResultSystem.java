import java.util.Scanner;
public class SPPUResultSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] subjects = {
                "DATABASE MANAGEMENT SYSTEMS",
                "THEORY OF COMPUTATION",
                "SYSTEMS PROGRAMMING AND OPERATING SYSTEM",
                "COMPUTER NETWORKS AND SECURITY",
                "HUMAN COMPUTER INTERFACE",
                "DATABASE MANAGEMENT SYSTEMS LABORATORY",
                "COMPUTER NETWORKS AND SECURITY LABORATORY",
                "LABORATORY PRACTICE I",
                "SEMINAR AND TECHNICAL COMMUNICATION",
                "COMPUTATIONAL STATISTICS (HONOURS)",
                "LEARN NEW SKILLS"
        };
        int totalGradePoints = 0;
        boolean isFail = false;
        int totalSubjects = subjects.length;
        System.out.println("===== ENTER MARKS =====");
        for (int i = 0; i < totalSubjects; i++) {
            System.out.print("\nEnter marks for " + subjects[i] + ": ");
            int marks = sc.nextInt();
            // Validation
            while (marks < 0 || marks > 100) {
                System.out.print("Invalid! Enter marks between 0-100: ");
                marks = sc.nextInt();
            }
            // Fail condition
            if (marks < 40) {
                isFail = true;
            }
            int gradePoint = 0;
            String grade = "";
            if (marks >= 90) {
                gradePoint = 10;
                grade = "O";
            } else if (marks >= 75) {
                gradePoint = 9;
                grade = "A+";
            } else if (marks >= 60) {
                gradePoint = 8;
                grade = "A";
            } else if (marks >= 55) {
                gradePoint = 7;
                grade = "B+";
            } else if (marks >= 50) {
                gradePoint = 6;
                grade = "B";
            } else if (marks >= 45) {
                gradePoint = 5;
                grade = "C";
            } else if (marks >= 40) {
                gradePoint = 4;
                grade = "D";
            } else {
                gradePoint = 0;
                grade = "F";
            }
            totalGradePoints += gradePoint;

            System.out.println("Grade: " + grade + " | Pointer (Out of 10): " + gradePoint);
        }
        System.out.println("\n========== FINAL 5th Semester RESULT ==========");
        if (isFail) {
            System.out.println("Result: FAIL (One or more subjects below 40)");
            System.out.println("CGPA: Not Applicable");
            System.out.println("Percentage: Not Applicable");
        } else {

            double cgpa = (double) totalGradePoints / totalSubjects;

            double percentage;
            if (cgpa >= 8.25) {
                percentage = cgpa * 10;
            } else {
                percentage = (cgpa * 10) - 7.5;
            }

            String classAwarded;

            if (cgpa >= 9.50) {
                classAwarded = "Outstanding";
            } else if (cgpa >= 8.25) {
                classAwarded = "Excellent (A+)";
            } else if (cgpa >= 6.75) {
                classAwarded = "Very Good (A)";
            } else if (cgpa >= 5.75) {
                classAwarded = "Good (B+)";
            } else if (cgpa >= 5.25) {
                classAwarded = "Above Average (B)";
            } else if (cgpa >= 4.75) {
                classAwarded = "Average (C)";
            } else {
                classAwarded = "Pass (D)";
            }

            System.out.println("Total Grade Points: " + totalGradePoints);
            System.out.printf("CGPA (Pointer): %.2f\n", cgpa);
            System.out.printf("Percentage: %.2f%%\n", percentage);
            System.out.println("Class Awarded: " + classAwarded);
        }

        sc.close();
    }
}
