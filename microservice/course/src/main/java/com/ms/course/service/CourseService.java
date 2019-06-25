package com.ms.course.service;

import com.ms.course.dao.CourseDAO;
import com.ms.course.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CourseService {

    @Autowired
    CourseDAO dao;

    public Set<Course> getAllCourses(){
        return dao.getAllCourses();
    }
}
