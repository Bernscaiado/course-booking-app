package com.example.coursebooking;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


import java.util.List;

class CourseTest {

    private Course course = new Course("seg","2105","Ali ben Salma");

    @Test
    void getCourseName() {
        assertEquals("seg", course.getCourseName());
    }

    @Test
    void setCourseName() {
        course.setCourseName("csi");
        assertEquals("csi", course.getCourseName());
    }

    @Test
    void getCourseCode() {
        assertEquals("2105", course.getCourseCode());
    }

    @Test
    void getInstructor() {
        assertEquals("Ali ben Salma", course.getInstructor());
    }

    @Test
    void getStudents() {
        course.addStudent("Jackson");
        List<String> list =  course.getStudents();
        assertEquals("Jackson", course.getStudents().get(0));
    }
}