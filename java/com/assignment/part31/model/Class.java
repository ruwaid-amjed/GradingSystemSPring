package com.assignment.part31.model;

public class Class {
    private String courseName;
    private String instructorName;
    private String studentName;

    public Class(String courseName, String instructorName, String studentName) {
        this.courseName = courseName;
        this.instructorName = instructorName;
        this.studentName = studentName;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public String getStudentName() {
        return studentName;
    }
}
