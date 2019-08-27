package com.course.demo.coursedemoboot.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionAdvice {

    @ExceptionHandler(IndexOutOfBoundsException.class)
    public void handleException(){
        System.out.println("**** index out of bound in controller **");
    }
}
