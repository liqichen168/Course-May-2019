//package com.course.demo.controller;
//
//import com.course.demo.model.Student;
//import com.course.demo.util.StudentHelper;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.servlet.ModelAndView;
//
//import java.util.Iterator;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Controller
//public class StudentController {
//
//    @GetMapping("/hello")
//    public String test(){
//        return "hello";
//    }
//
//    @GetMapping("/student/{id}")
//    public ModelAndView getStudentById(@PathVariable int id){
//
//        ModelAndView modelAndView = new ModelAndView("student");
//        Student result = StudentHelper.studentDB.stream().filter(
//                s -> s.getId() == id
//        ).findFirst().orElse(null);
//
//        modelAndView.addObject("student", result);
//        return modelAndView;
//    }
//
//    @RequestMapping(value = "/student/all", method = RequestMethod.GET)
//    public String getAllStudents(Model model){
//        model.addAttribute("studentsList", StudentHelper.studentDB);
//        return "students";
//    }
//
//    @PostMapping("/student/update")
//    public ModelAndView updateStudentById(@RequestParam("studentId") int id,@RequestParam("major") String newMajor){
//        /*String newMajor = "test";*/
//        List<Student> list = StudentHelper.studentDB.stream().map(
//                student -> {
//                    if(student.getId() == id){
//                        student.setMajor(newMajor);
//                    }
//                    return student;
//                }
//        ).collect(Collectors.toList());
//        ModelAndView modelAndView = new ModelAndView("students");
//        modelAndView.addObject("studentsList", list);
//        return modelAndView;
//    }
//
//
//    @DeleteMapping("student/delete/{id}")
//    public ModelAndView deleteStudentById(@PathVariable int id){
//        Iterator<Student> it = StudentHelper.studentDB.iterator();
//        while (it.hasNext()){
//            Student s = it.next();
//            if(s.getId() == id){
//                it.remove();
//            }
//        }
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("students");
//        modelAndView.addObject("studentList", StudentHelper.studentDB);
//        return modelAndView;
//    }
//
//}
