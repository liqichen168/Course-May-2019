package com.course.demo.util;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public class Config {

    @Bean
    public StudentHelper studentHelper(){
        return new StudentHelper();
    }





}
