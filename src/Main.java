import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static final String FILE_NAME = "students.csv";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Student> loadedStudents = FileManager.loadStudents(FILE_NAME);
        GradeTracker tracker = new GradeTracker(loadedStudents);
        
        System.out.println("Welcome to the Student Grade Tracker!");
        
        boolean running = true;
        while (running) {
            printMenu();
            int choice = getIntInput(scanner, "Enter your choice: ");
            
            switch (choice) {
                case 1:
                    System.out.print("Enter student name: ");
                    String name = scanner.nextLine().trim();
                    if (!name.isEmpty()) {
                        Student s = tracker.addStudent(name);
                        System.out.println("Added student: " + s.getName() + " with ID: " + s.getId());
                    } else {
                        System.out.println("Name cannot be empty.");
                    }
                    break;
                case 2:
                    int idToGrade = getIntInput(scanner, "Enter student ID: ");
                    Student studentToGrade = tracker.findStudent(idToGrade);
                    if (studentToGrade != null) {
                        double grade = getDoubleInput(scanner, "Enter grade: ");
                        if (grade >= 0 && grade <= 100) {
                            studentToGrade.addGrade(grade);
                            System.out.println("Grade added successfully.");
                        } else {
                            System.out.println("Invalid grade. Please enter a value between 0 and 100.");
                        }
                    } else {
                        System.out.println("Student not found with ID: " + idToGrade);
                    }
                    break;
                case 3:
                    int idToView = getIntInput(scanner, "Enter student ID: ");
                    Student studentToView = tracker.findStudent(idToView);
                    if (studentToView != null) {
                        System.out.println("\n--- Student Details ---");
                        System.out.println(studentToView.toString());
                        if (!studentToView.getGrades().isEmpty()) {
                            System.out.printf("Highest Grade: %.2f\n", studentToView.getHighest());
                            System.out.printf("Lowest Grade: %.2f\n", studentToView.getLowest());
                        }
                        System.out.println("-----------------------");
                    } else {
                        System.out.println("Student not found with ID: " + idToView);
                    }
                    break;
                case 4:
                    tracker.listAll();
                    break;
                case 5:
                    tracker.printSummaryReport();
                    break;
                case 6:
                    int idToRemove = getIntInput(scanner, "Enter student ID to remove: ");
                    if (tracker.removeStudent(idToRemove)) {
                        System.out.println("Student removed successfully.");
                    } else {
                        System.out.println("Student not found with ID: " + idToRemove);
                    }
                    break;
                case 7:
                    FileManager.saveStudents(tracker.getStudents(), FILE_NAME);
                    System.out.println("Data saved to " + FILE_NAME + ". Exiting...");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please select an option from 1 to 7.");
            }
        }
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n+--------------------------------+");
        System.out.println("|      STUDENT GRADE TRACKER     |");
        System.out.println("+--------------------------------+");
        System.out.println("| 1. Add Student                 |");
        System.out.println("| 2. Add Grade to Student        |");
        System.out.println("| 3. View Student Details        |");
        System.out.println("| 4. View All Students           |");
        System.out.println("| 5. Print Summary Report        |");
        System.out.println("| 6. Remove Student              |");
        System.out.println("| 7. Save & Exit                 |");
        System.out.println("+--------------------------------+");
    }

    private static int getIntInput(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }
    }

    private static double getDoubleInput(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }
}
