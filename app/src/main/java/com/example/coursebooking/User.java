package com.example.coursebooking;

public abstract class  User {
    private String username;
    private String firstname;
    private String role;

    public User(String username, String firstname, String role) {
        this.username = username;
        this.firstname = firstname;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
