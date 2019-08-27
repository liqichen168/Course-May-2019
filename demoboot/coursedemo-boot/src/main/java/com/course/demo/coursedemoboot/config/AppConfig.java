package com.course.demo.coursedemoboot.config;

import com.course.demo.coursedemoboot.service.StudentHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public StudentHelper studentHelper(){
        return new StudentHelper();
    }

    @Bean
    public StudentHelper anotherStudentHelper(){
        return new StudentHelper();
    }
}
