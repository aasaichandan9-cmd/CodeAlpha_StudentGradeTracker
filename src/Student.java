import java.util.ArrayList;

public class Student {
    private int id;
    private String name;
    private ArrayList<Double> grades;

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
        this.grades = new ArrayList<>();
    }

    public Student(int id, String name, ArrayList<Double> grades) {
        this.id = id;
        this.name = name;
        this.grades = grades;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Double> getGrades() {
        return grades;
    }

    public void addGrade(double grade) {
        grades.add(grade);
    }

    public double getAverage() {
        if (grades.isEmpty()) return 0.0;
        double sum = 0;
        for (double g : grades) {
            sum += g;
        }
        return sum / grades.size();
    }

    public double getHighest() {
        if (grades.isEmpty()) return 0.0;
        double max = grades.get(0);
        for (double g : grades) {
            if (g > max) max = g;
        }
        return max;
    }

    public double getLowest() {
        if (grades.isEmpty()) return 0.0;
        double min = grades.get(0);
        for (double g : grades) {
            if (g < min) min = g;
        }
        return min;
    }

    public char getLetterGrade() {
        double avg = getAverage();
        if (avg >= 90) return 'A';
        else if (avg >= 80) return 'B';
        else if (avg >= 70) return 'C';
        else if (avg >= 60) return 'D';
        else return 'F';
    }

    @Override
    public String toString() {
        return String.format("ID: %d | Name: %s | Grades: %s | Avg: %.2f (%c)", id, name, grades, getAverage(), getLetterGrade());
    }

    public String toCSV() {
        StringBuilder sb = new StringBuilder();
        sb.append(id).append(",").append(name);
        for (double g : grades) {
            sb.append(",").append(g);
        }
        return sb.toString();
    }

    public static Student fromCSV(String csvLine) {
        String[] parts = csvLine.split(",");
        if (parts.length < 2) return null;
        int id = Integer.parseInt(parts[0].trim());
        String name = parts[1].trim();
        ArrayList<Double> grades = new ArrayList<>();
        for (int i = 2; i < parts.length; i++) {
            if (!parts[i].trim().isEmpty()) {
                grades.add(Double.parseDouble(parts[i].trim()));
            }
        }
        return new Student(id, name, grades);
    }
}
