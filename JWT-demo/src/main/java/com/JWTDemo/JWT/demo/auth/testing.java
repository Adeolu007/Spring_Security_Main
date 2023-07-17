package com.JWTDemo.JWT.demo.auth;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/testing")
public class testing {
    @GetMapping("/hello")
    public String helloWorld (){
        return "Hello my people";
    }
    @GetMapping("/next")
    public String nextWorld (){
        return "Next!!!!!!!!!! people";
    }
}
