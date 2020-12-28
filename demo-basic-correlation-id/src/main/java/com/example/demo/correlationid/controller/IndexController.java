package com.example.demo.correlationid.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @GetMapping("/api/v1/index")
    public String index() {
        return "OK";
    }
}
