package com.course.demo.util;

import com.course.demo.model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentHelper {

    public static List<Student> studentDB = new ArrayList<Student>();

    static {
        studentDB.add(new Student(1, "Jon Snow", "King", "Throne Training"));
        studentDB.add(new Student(2, "Night King", "King", "Walking"));
    }

    public void hello(){
        System.out.println("in student helper");
    }
}
