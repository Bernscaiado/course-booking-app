package com.example.coursebooking;

public class Course {
    private String CourseName;
    private String CourseCode;
    private String Instructor;

    @Override
    public String toString() {
        return "Course{" +
                "CourseName= '" + CourseName + '\'' +
                ", CourseCode= " + CourseCode +
                '}';
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
