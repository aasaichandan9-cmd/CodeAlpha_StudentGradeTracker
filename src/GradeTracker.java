import java.util.ArrayList;

public class GradeTracker {
    private ArrayList<Student> students;

    public GradeTracker(ArrayList<Student> students) {
        this.students = students != null ? students : new ArrayList<>();
    }

    public GradeTracker() {
        this.students = new ArrayList<>();
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public int getNextId() {
        int maxId = 0;
        for (Student s : students) {
            if (s.getId() > maxId) {
                maxId = s.getId();
            }
        }
        return maxId + 1;
    }

    public Student addStudent(String name) {
        Student s = new Student(getNextId(), name);
        students.add(s);
        return s;
    }

    public boolean removeStudent(int id) {
        Student s = findStudent(id);
        if (s != null) {
            students.remove(s);
            return true;
        }
        return false;
    }

    public Student findStudent(int id) {
        for (Student s : students) {
            if (s.getId() == id) {
                return s;
            }
        }
        return null;
    }

    public void listAll() {
        System.out.println("--------------------------------------------------");
        System.out.println("                 ALL STUDENTS                     ");
        System.out.println("--------------------------------------------------");
        if (students.isEmpty()) {
            System.out.println("No students found.");
        } else {
            for (Student s : students) {
                System.out.println(s.toString());
            }
        }
        System.out.println("--------------------------------------------------");
    }

    public void printSummaryReport() {
        System.out.println("==================================================");
        System.out.println("                 SUMMARY REPORT                   ");
        System.out.println("==================================================");
        if (students.isEmpty()) {
            System.out.println("No data available.");
            System.out.println("==================================================");
            return;
        }
        
        int totalStudents = students.size();
        double classTotal = 0;
        double highestInClass = -1.0;
        double lowestInClass = Double.MAX_VALUE;
        String topStudent = "";
        String bottomStudent = "";

        for (Student s : students) {
            double avg = s.getAverage();
            classTotal += avg;
            if (avg > highestInClass) {
                highestInClass = avg;
                topStudent = s.getName();
            }
            if (avg < lowestInClass) {
                lowestInClass = avg;
                bottomStudent = s.getName();
            }
        }

        double classAvg = classTotal / totalStudents;
        
        System.out.println("Total Students: " + totalStudents);
        System.out.printf("Class Average: %.2f\n", classAvg);
        System.out.printf("Highest Average: %.2f (%s)\n", highestInClass, topStudent);
        System.out.printf("Lowest Average: %.2f (%s)\n", lowestInClass, bottomStudent);
        System.out.println("==================================================");
    }
}
