package com.example.demo.properties.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class BuildInProperties {

    @Value("${spring.application.name}")
    private String appName;
}
