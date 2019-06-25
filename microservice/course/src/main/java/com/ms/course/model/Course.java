package com.ms.course.model;

import java.util.Set;

public class Course {

    private String name;
    private int credits;
    private Set<Student> registeredStudents;

    public Course() {
    }

    public Course(String name, int credits) {
        this.name = name;
        this.credits = credits;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public Set<Student> getRegisteredStudents() {
        return registeredStudents;
    }

    public void setRegisteredStudents(Set<Student> registeredStudents) {
        this.registeredStudents = registeredStudents;
    }
}
