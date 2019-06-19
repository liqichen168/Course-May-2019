package com.course.demo.coursedemoboot.service;

import com.course.demo.coursedemoboot.dao.StudentDAO;
import com.course.demo.coursedemoboot.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class StudentHelper {

    StudentDAO dao;

    public List<Student> getAllStudents(){
        return dao.getAllStudents();
    }

    @Autowired
    public void setDao(StudentDAO dao) {
        this.dao = dao;
    }
}
