package com.course.demo.service;

import com.course.demo.dao.StudentDAO;
import com.course.demo.model.Student;
import com.course.demo.util.StudentHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    private StudentDAO dao;

    @Autowired
    private StudentHelper studentHelper;

    public Student getStudentById(int id){
        studentHelper.hello();
        return dao.getStudentById(id);
    }

}
