package com.delivery.springBoot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/")
    public String HelloMessage(){
        return "Hello, Estou na Dio Estudando!! ";
    }
}
