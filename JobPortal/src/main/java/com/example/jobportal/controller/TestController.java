package com.example.jobportal.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test.htm")
    public String handleGet() {
        return "Test controller response";
    }
}
