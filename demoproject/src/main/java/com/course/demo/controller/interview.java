package com.course.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class interview {

 /*   @PostMapping("/test/ta/{B}/et")
    public ResponseEntity<Result> createNew(
            @PathVariable String b,
            @RequestBody Model model){

        Result result = new Result();
        try {
            service.createNew(b, model);
            result.setStatus("success");
            return new ResponseEntity<Model>(result,HttpStatus.OK);
        }catch (Exception e){
            result.setStatus("not found")
        }
    }*/
}


//do we need to define the model to parse the json format?
