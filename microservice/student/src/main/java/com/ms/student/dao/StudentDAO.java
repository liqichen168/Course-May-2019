package com.ms.student.dao;

import com.ms.student.domain.Student;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

@Repository
public class StudentDAO {


    public Set<Student> getAllStudents(){
        return studentDB;
    }

    public static Set<Student> studentDB;

    static{
        studentDB = new HashSet<Student>();
        studentDB.add(new Student(1, "Alice", "Physics"));
        studentDB.add(new Student(2, "Bruce", "Chemistry"));
        studentDB.add(new Student(3, "Cathy", "Mathematics"));
    }
}
