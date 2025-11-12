package com.example.demo_jwt.Demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor

public class DemoController {
    @GetMapping(value = "path")
    public String welcome() {
        return "welcome to secured endpoint";
    }
    

}
