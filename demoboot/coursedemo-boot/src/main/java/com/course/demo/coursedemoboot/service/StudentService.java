package com.course.demo.coursedemoboot.service;

import com.course.demo.coursedemoboot.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    @Qualifier("anotherStudentHelper")
    StudentHelper helper;

    public List<Student> getAllStudents(){
        return helper.getAllStudents();
    }
}
