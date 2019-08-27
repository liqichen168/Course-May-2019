package com.course.demo.dao;

import com.course.demo.model.Student;
import org.springframework.stereotype.Repository;

@Repository
public class StudentDAO {

    public Student getStudentById(int id){
        //TODO: fetch student db and return student with id = id
        System.out.println("Message from DAO");
        return new Student();


    }

}
