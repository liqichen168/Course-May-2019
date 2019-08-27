package com.course.demo.coursedemoboot.dao;


import com.course.demo.coursedemoboot.domain.Student;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentDAO {

    public List<Student> getAllStudents(){
        //TODO: fetch student db and return student with id = id
        System.out.println("Message from DAO");
        Student s = new Student(1, "Andy", "boss");
        List<Student> list = new ArrayList<>();
        list.add(s);
        return list;
    }



}
