package com.ms.course.controller;


import com.ms.course.model.Course;
import com.ms.course.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class CourseController {


    @Autowired
    CourseService service;

    @GetMapping({"/","/allcourses"})
    public Set<Course> getAllCourses(){
        return service.getAllCourses();
    }
}
