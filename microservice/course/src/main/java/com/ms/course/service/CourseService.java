package com.ms.course.service;

import com.ms.course.dao.CourseDAO;
import com.ms.course.model.Course;
import com.ms.course.model.Student;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashSet;
import java.util.Set;

@Service
public class CourseService {

    @Autowired
    CourseDAO dao;

    @Autowired
    RestTemplate restTemplate;

    public Set<Course> getAllCourses(){
        return dao.getAllCourses();
    }

    public Course registerStudent(String courseName, int studentId) {
        //step1: find the course
        Set<Course> allCourses = this.getAllCourses();

        Course course = null;

        // lambda
        course = allCourses.stream().filter(
                c -> c.getName().equalsIgnoreCase(courseName)
        ).findAny().orElse(null);

        if(course == null){
            return null;
        }

        //step 2: get the student
        Student student = this.getStudentRestTemplate(studentId);
        if(student == null){
            return course;
        }

        //step 3: register the student into the course
        if(course.getRegisteredStudents() == null){
            course.setRegisteredStudents(new HashSet<Student>());
            course.getRegisteredStudents().add(student);
        }else{
            if(!course.getRegisteredStudents().contains(student)){
                course.getRegisteredStudents().add(student);
            }
        }

        return course;
    }

    @HystrixCommand(fallbackMethod = "getStudentById_fallback")
    public Student getStudentRestTemplate(int studentId){
        String url = "http://studentservice/student/" + studentId;
        Student student = this.restTemplate.getForObject(url, Student.class);
        return student;
    }

    //fallback method
    public Student getStudentById_fallback(int sid){
        return new Student(sid, "Cannot find student with sid = " + sid, "Not available");
    }

    public String getRibbonResponseFromStudentService() {
        String url = "http://studentservice/ribbonServer";
        String result = this.restTemplate.getForObject(url, String.class);
        return result;
    }
}
