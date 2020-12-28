package com.example.demo.properties.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Data
@ConfigurationProperties(prefix = "custom")
@Component //WARN: Can't use @Configuration
public class CustomProperties {

    private String stringValue;
    private String stringDefaultValue; //null

    private List<String> listValues;

    private int intValue;

    private int intDefaultValue; // 0

    private Map<String, String> mapOfStringValues;

    private Map<String, List<String>> mapOfListValues;

    private Map<String, Credentials> mapOfObjectValues;

    @Data
    public static class Credentials {

        private String username;
        private String password;
    }
}
