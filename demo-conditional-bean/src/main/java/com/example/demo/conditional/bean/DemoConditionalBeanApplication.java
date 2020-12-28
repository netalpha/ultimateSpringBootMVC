package com.example.demo.conditional.bean;

import com.example.demo.conditional.bean.service.CacheService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class DemoConditionalBeanApplication {

    public static void main(String[] args) {
        var app = SpringApplication.run(DemoConditionalBeanApplication.class, args);
        var name = app.getBeanNamesForType(CacheService.class);
        System.out.println(Arrays.asList(name));
    }

}
