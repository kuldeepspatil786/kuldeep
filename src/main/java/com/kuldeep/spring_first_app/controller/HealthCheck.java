package com.kuldeep.spring_first_app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheck {

    @GetMapping("/health")

    public String healthChk()
    {
        return "Ok";
    }
}
