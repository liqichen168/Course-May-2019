package com.ms.course.controller;


import com.ms.course.model.Course;
import com.ms.course.model.Student;
import com.ms.course.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

@RestController
public class CourseController {


    @Autowired
    CourseService service;

    @GetMapping({"/","/allcourses"})
    public Set<Course> getAllCourses(){
        return service.getAllCourses();
    }


    @GetMapping("/register/{courseName}/{studentId}")
    public Course registerStudent(@PathVariable("courseName") String courseName,
                                  @PathVariable("studentId") int studentId){
        return service.registerStudent(courseName, studentId);
    }

    @GetMapping("/ribbonClient")
    public String getRibbonResponseFromStudentService(){
        return service.getRibbonResponseFromStudentService();
    }
}
