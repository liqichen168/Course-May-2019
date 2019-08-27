package com.course.demo.coursedemoboot.service;

import com.course.demo.coursedemoboot.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    @Qualifier("anotherStudentHelper")
    StudentHelper helper;

    public List<Student> getAllStudents(){
        return helper.getAllStudents();
    }

    public String aroundMethod(){
        try {
            System.out.println("*** In Service, AroundMethod started*** ");
            Thread.sleep(2000);
            System.out.println("*** In Service, AroundMethod ended*** ");
        }catch(InterruptedException e){
            System.out.println("Thread is interrupted!");
        }
        return "";
    }


    public String throwMethod(){
        System.out.println("*** In service.throwMethod ***");
        throw new IndexOutOfBoundsException();
    }
}
