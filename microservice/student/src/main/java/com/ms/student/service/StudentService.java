package com.ms.student.service;

import com.ms.student.dao.StudentDAO;
import com.ms.student.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class StudentService {

    @Autowired
    StudentDAO dao;


    public Set<Student> getAllStudents(){
        return dao.getAllStudents();
    }
}
