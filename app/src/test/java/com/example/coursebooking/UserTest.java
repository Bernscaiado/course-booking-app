package com.example.coursebooking;

import junit.framework.TestCase;

public class UserTest extends TestCase {

    private User student = new Student("username", "firstname","student","password");

    public void testGetUsername() {
        assertEquals("username", student.getUsername());
    }

    public void testSetUsername() {
        String newUsername = "newUserName";
        student.setUsername(newUsername);
        assertEquals(newUsername, student.getUsername());
    }

    public void testGetFirstname() {
        assertEquals("firstname", student.getFirstname());
    }

    public void testSetFirstname() {
        String newFirstName = "newFirstName";
        student.setUsername(newFirstName);
        assertEquals(newFirstName, student.getUsername());
    }

    public void testGetRole() {
        assertEquals("student", student.getRole());
    }
}