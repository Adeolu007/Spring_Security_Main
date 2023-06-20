package com.emperor.Security.jwt.controller;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/testing")
public class Testing {
@GetMapping
    public String am(){
        return "welcome";
    }

    @GetMapping("/hidden")
    public String hiddenDetails(){
    return "Only for authenticated users";
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'CEO')")
    @GetMapping("/admin")
    public String adminEntry(){
    return "for only admin";
    }
}
