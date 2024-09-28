import java.util.Scanner;

public class StudentGradeCalculator {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Welcome to the Student Grade Calculator");
        System.out.println("---------------------------------------");

        System.out.print("Enter the number of subjects: ");
        int numSubjects = sc.nextInt();
        
        int totalMarks = 0;
        
        for (int i = 0; i < numSubjects; i++) {
            System.out.print("Enter marks for subject " + (i + 1) + ": ");
            totalMarks += sc.nextInt();
        }

        double averagePercentage = (double) totalMarks / numSubjects;

        System.out.println("\nTotal Marks: " + totalMarks);
        System.out.println("Average Percentage: " + averagePercentage + "%");

        char grade;
        if (averagePercentage >= 90) {
            grade = 'A';
        } else if (averagePercentage >= 80) {
            grade = 'B';
        } else if (averagePercentage >= 70) {
            grade = 'C';
        } else if (averagePercentage >= 60) {
            grade = 'D';
        } else {
            grade = 'F';
        }

        System.out.println("Grade: " + grade);
        System.out.println("---------------------------------------");
        System.out.println("Thank you for using the calculator!");

        sc.close();
    }
}
