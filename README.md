# Student Grade Tracker 🎓

A Java console application built as part of the **CodeAlpha Java Programming Internship**.

## Features
- Add and manage students with multiple subject grades
- Auto-calculates **average, highest, and lowest** scores
- Assigns **letter grades** (A/B/C/D/F)
- Prints a full **summary report** for all students
- Saves and loads data via **CSV file persistence**

## Tech Stack
- Language: **Java**
- Concepts: OOP, ArrayList, File I/O, Exception Handling

## Project Structure
```
src/
├── Student.java        # Student entity with grade statistics
├── GradeTracker.java   # Core CRUD logic and summary report
├── FileManager.java    # CSV save/load
└── Main.java           # Console menu UI
```

## How to Run
```bash
# Compile
javac -d out src/*.java

# Run
java -cp out Main
```

## Author
Developed for **CodeAlpha Java Programming Internship**
