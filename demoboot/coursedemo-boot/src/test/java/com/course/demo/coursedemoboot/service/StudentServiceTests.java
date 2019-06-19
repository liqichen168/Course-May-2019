package com.course.demo.coursedemoboot.service;

import com.course.demo.coursedemoboot.domain.Student;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.util.List;

public class StudentServiceTests {

    @Autowired
    StudentService service;

    @Before
    public void init(){
        //TODO
    }

    @Test
    public void TestGetAllStudents(){
        List<Student> list = service.getAllStudents();
        Student s = list.get(0);
        Assert.isTrue(s.getId() == 1);
    }

    @After
    public void cleanup() {
        //TODO
    }

}
