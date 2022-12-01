package com.example.coursebooking;

import java.util.ArrayList;
import java.util.List;

public class Course {
    private String CourseName;
    private String CourseCode;
    private String Instructor;
    private List<String> Students = new ArrayList<>();

    @Override
    public String toString() {
        return "CourseName= '" + CourseName + '\'' +
                ",\tCourseCode= " + CourseCode;
    }

    public String getCourseName() {
        return CourseName;
    }

    public void setCourseName(String courseName) {
        CourseName = courseName;
    }

    public String getCourseCode() { return CourseCode; }

    public String getInstructor() {
        return Instructor;
    }

    public List<String> getStudents() {return Students;}

    public void setStudents(List<String> students) { this.Students = students; }

    public void addStudent(String student) { Students.add(student); }

    public void setCourseCode(String courseCode) {
        CourseCode = courseCode;
    }

    public void setInstructor(String instructor) {
        this.Instructor = instructor;
    }

    public Course(String courseName, String courseCode) {
        this.CourseName = courseName;
        this.CourseCode = courseCode;
    }

    public Course(String courseName, String courseCode, String instructor) {
        this.CourseName = courseName;
        this.CourseCode = courseCode;
        this.Instructor = instructor;

    }
}
