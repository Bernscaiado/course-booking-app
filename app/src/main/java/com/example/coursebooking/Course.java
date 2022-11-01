package com.example.coursebooking;

public class Course {
    private int code;
    private String name;

    public Course(int code, String name) {
        this.code = code;
        this.name = name;
    }
    @Override
    public String toString() {
        return "Course [code=" + code + ", name=" + name + "]";
    }
    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }


}
