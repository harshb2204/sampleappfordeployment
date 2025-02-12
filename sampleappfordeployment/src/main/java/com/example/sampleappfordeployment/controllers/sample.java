package com.example.sampleappfordeployment.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class sample {




    @GetMapping
    public String get(){
        return "Hello World";
    }
}
