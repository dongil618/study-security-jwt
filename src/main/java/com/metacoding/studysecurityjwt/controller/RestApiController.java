package com.metacoding.studysecurityjwt.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestApiController {

    @GetMapping("/")
    public String home(){
        return "<h1>Home</h1>";
    }
}
