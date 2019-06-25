package com.ms.course.dao;

import com.ms.course.model.Course;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

@Repository
public class CourseDAO {

    public Set<Course> getAllCourses(){
        return courseDB;
    }


    public static Set<Course> courseDB;

    static{
        courseDB = new HashSet<>();
        courseDB.add(new Course("PHYS101", 3));
        courseDB.add(new Course("CHEM101", 2));
        courseDB.add(new Course("MATH101", 4));
    }
}
