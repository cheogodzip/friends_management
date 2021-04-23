package com.example.friends.controller;

import com.example.friends.exception.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
    @GetMapping(value = "/api/helloWorld")
    public String helloWorld(){
        return "HELLO WORLD";
    }

    @GetMapping(value = "/api/helloException")
    public String helloException(){
        throw new RuntimeException("Hello RuntimeException");
    }
}
