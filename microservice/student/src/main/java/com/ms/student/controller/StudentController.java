package com.ms.student.controller;

import com.ms.student.domain.Student;
import com.ms.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class StudentController {

    @Autowired
    StudentService service;


    @RequestMapping(path={"/","/allstudents"}, method = RequestMethod.GET)
    public Set<Student> getAllStudents(){
        return service.getAllStudents();
    }
}
