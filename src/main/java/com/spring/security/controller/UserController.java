package com.spring.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class UserController {

    @GetMapping("/health")
    public String health(){
        return "HEALTHY";
    }

    @GetMapping("/userDetails")
    public String userDetails(){
        return "WELCOME TO Spring Security Demo, Sandeep!";
    }

}
