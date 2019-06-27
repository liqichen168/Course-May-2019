package com.ms.course.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class CourseConfiguration {

    @Bean
    @LoadBalanced
    //without this annotation,
    //the resttemplate will report cannot find student service error
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
