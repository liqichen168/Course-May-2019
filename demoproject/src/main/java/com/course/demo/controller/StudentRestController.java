package com.course.demo.controller;

import com.course.demo.model.Student;
import com.course.demo.util.StudentHelper;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest")
public class StudentRestController {

    @GetMapping("/student/{id}")
    public Student getStudentById(@PathVariable int id){
        Student result = StudentHelper.studentDB.stream().filter(
                s -> s.getId() == id
        ).findFirst().orElse(null);
        return result;
    }

    @RequestMapping(value = "/student/all", method = RequestMethod.GET)
    public List<Student> getAllStudents(){
        return StudentHelper.studentDB;
    }

    @PutMapping("/student/update/{id}")
    public List<Student> updateStudentById(
            @PathVariable int id,
            @RequestParam("major") String newMajor
    ){
        List<Student> list = StudentHelper.studentDB.stream().map(
                student -> {
                    if(student.getId() == 1){
                        student.setMajor(newMajor);
                    }
                    return student;
                }
        ).collect(Collectors.toList());

        return list;
    }

    @PostMapping("/student/new")
    public List<Student> addNewStudent(@RequestBody Student newStudent){
        Student s = new Student();
        s.setId(StudentHelper.studentDB.size() + 1);
        s.setName(newStudent.getName());
        s.setMajor(newStudent.getMajor());
        s.setCourse(newStudent.getCourse());
        StudentHelper.studentDB.add(s);
        return StudentHelper.studentDB;
    }

    @DeleteMapping("/student/delete/{id}")
    public List<Student> deleteStudentById(@PathVariable int id){
        Iterator<Student> it = StudentHelper.studentDB.iterator();
        while (it.hasNext()){
            Student s = it.next();
            if(s.getId() == id){
                it.remove();
            }
        }
        return StudentHelper.studentDB;
    }
}
