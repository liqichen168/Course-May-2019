package com.course.demo.coursedemoboot.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@Aspect
public class AopConfig {

    @Before("execution(* com.course.demo.coursedemoboot.service.StudentService.get*(..))")
    public void beforeAop(){
        System.out.println("*** this is before AOP method ***");
    }

    @Around("execution(* com.course.demo.coursedemoboot.service.StudentService.around*(..))")
    public void aroundAop(ProceedingJoinPoint pjp) throws Throwable{
        System.out.println("*** AOP around ::: before the around method ***");
        try {
            pjp.proceed();
        }catch (Exception e){}
        System.out.println("*** AOP around ::: after the around method ***");
    }

    @AfterThrowing("execution(* com.course.demo.coursedemoboot.service.StudentService.throw*(..))")
    public void throwAop(){
        System.out.println("*** this is throw AOP method ***");
    }

}
