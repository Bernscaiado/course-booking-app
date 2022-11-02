package com.example.coursebooking;

public class Course {
    private String CourseName;
    private int CourseCode;

    @Override
    public String toString() {
        return "Course{" +
                "CourseName='" + CourseName + '\'' +
                ", CourseCode=" + CourseCode +
                '}';
    }

    public String getCourseName() {
        return CourseName;
    }

    public void setCourseName(String courseName) {
        CourseName = courseName;
    }

    public int getCourseCode() {
        return CourseCode;
    }

    public void setCourseCode(int courseCode) {
        CourseCode = courseCode;
    }

    public Course(String courseName, int courseCode) {
        CourseName = courseName;
        CourseCode = courseCode;
    }
}
