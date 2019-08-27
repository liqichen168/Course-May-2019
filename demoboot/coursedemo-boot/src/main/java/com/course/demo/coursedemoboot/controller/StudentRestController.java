package com.course.demo.coursedemoboot.controller;

import com.course.demo.coursedemoboot.domain.Student;
import com.course.demo.coursedemoboot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentRestController {

    @Autowired
    StudentService service;

    @GetMapping({"/", "/hello"})
    public String greeting(){
        return "hello";
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Student> getAllStudents(){
        return service.getAllStudents();
    }


    @GetMapping("/around")
    public String aroundMethod(){
        return service.aroundMethod();
    }

    @GetMapping("/throw")
    public String throwMethod(){
        return service.throwMethod();
    }


}
