package com.course.demo.model;

public class Student {

    private int id;
    private String name;
    private String major;
    private String course;

    public Student() {
    }

    public Student(int id, String name, String major, String course) {
        this.id = id;
        this.name = name;
        this.major = major;
        this.course = course;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }
}
