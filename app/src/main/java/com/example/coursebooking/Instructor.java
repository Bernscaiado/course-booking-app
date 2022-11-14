package com.example.coursebooking;

import java.util.List;

public class Instructor extends User {
    List<Course> courses;
    public Instructor(String username, String firstname, String role, String password, List<Course> courses) {
        super(username, firstname, role, password);
    }

    public boolean assignCourse(Course c) {
        courses.add(c);
        return true;
    }

    public boolean unAssignCourse(Course c) {
        courses.remove(c);
        return true;
    }
}
