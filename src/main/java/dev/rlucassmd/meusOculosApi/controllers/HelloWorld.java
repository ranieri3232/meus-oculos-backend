package dev.rlucassmd.meusOculosApi.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/")
public class HelloWorld {
    @GetMapping
    public String HelloWorld(){
        return "Hello World!";
    }
}
