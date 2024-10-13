package org.example.sms;

import java.util.ArrayList;
import java.util.List;

public class StudentManagementSystem {

    // Student Class
    public static class Student {
        private String name;
        private int rollNumber;
        private String grade;
        private String branch;
        private String section;
        private int year;
        private String clearanceStatus;

        public Student(String name, int rollNumber, String grade, String branch, String section, int year, String clearanceStatus) {
            this.name = name;
            this.rollNumber = rollNumber;
            this.grade = grade;
            this.branch = branch;
            this.section = section;
            this.year = year;
            this.clearanceStatus = clearanceStatus;
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

        public String getBranch() {
            return branch;
        }

        public String getSection() {
            return section;
        }

        public int getYear() {
            return year;
        }

        public String getClearanceStatus() {
            return clearanceStatus;
        }

        // Setters for updating student details
        public void setName(String name) {
            this.name = name;
        }

        public void setGrade(String grade) {
            this.grade = grade;
        }

        public void setBranch(String branch) {
            this.branch = branch;
        }

        public void setSection(String section) {
            this.section = section;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public void setClearanceStatus(String clearanceStatus) {
            this.clearanceStatus = clearanceStatus;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "name='" + name + '\'' +
                    ", rollNumber=" + rollNumber +
                    ", grade='" + grade + '\'' +
                    ", branch='" + branch + '\'' +
                    ", section='" + section + '\'' +
                    ", year=" + year +
                    ", clearanceStatus='" + clearanceStatus + '\'' +
                    '}';
        }
    }

    // Management System to handle student operations
    private List<Student> students;

    public StudentManagementSystem() {
        students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void removeStudent(int rollNumber) {
        students.removeIf(student -> student.getRollNumber() == rollNumber);
    }

    public Student searchStudent(int rollNumber) {
        for (Student student : students) {
            if (student.getRollNumber() == rollNumber) {
                return student;
            }
        }
        return null;
    }

    public void editStudent(int rollNumber, Student updatedStudent) {
        for (Student student : students) {
            if (student.getRollNumber() == rollNumber) {
                student.setName(updatedStudent.getName());
                student.setGrade(updatedStudent.getGrade());
                student.setBranch(updatedStudent.getBranch());
                student.setSection(updatedStudent.getSection());
                student.setYear(updatedStudent.getYear());
                student.setClearanceStatus(updatedStudent.getClearanceStatus());
                return; // Exit the method after updating
            }
        }
        throw new IllegalArgumentException("Student with roll number " + rollNumber + " not found.");
    }

    public String displayAllStudents() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("%-20s %-15s %-10s %-10s %-10s %-10s %-15s\n",
                "Name", "Roll Number", "Grade", "Branch", "Section", "Year", "Clearance Status"));
        builder.append("--------------------------------------------------------------------------------\n");

        for (Student student : students) {
            builder.append(String.format("%-20s %-15d %-10s %-10s %-10s %-10d %-15s\n",
                    student.getName(),
                    student.getRollNumber(),
                    student.getGrade(),
                    student.getBranch(),
                    student.getSection(),
                    student.getYear(),
                    student.getClearanceStatus()));
        }

        return builder.toString();
    }

}
