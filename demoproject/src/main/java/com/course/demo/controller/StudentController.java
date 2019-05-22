package com.course.demo.controller;

import com.course.demo.model.Student;
import com.course.demo.util.StudentHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.Iterator;

@Controller
public class StudentController {

    @GetMapping("/hello")
    public String test(){
        return "hello";
    }

    @GetMapping("/student/{id}")
    public ModelAndView getStudentById(@PathVariable int id){

        ModelAndView modelAndView = new ModelAndView("student");

        StudentHelper helper = new StudentHelper();

        /*Iterator<Student> it = StudentHelper.studentDB.iterator();
        while(it.hasNext()){
            Student temp = it.next();
            if(temp.getId() == id){
                modelAndView.addObject("student", temp);
            }
        }*/

        Student result = StudentHelper.studentDB.stream().filter(
                s -> s.getId() == id
        ).findFirst().orElse(null);

        modelAndView.addObject("student", result);

        return modelAndView;
    }


    //GET:: all student
    //update: one student
    //insert: one student
    //delete: one student

    //REST CONTROLLER
}
