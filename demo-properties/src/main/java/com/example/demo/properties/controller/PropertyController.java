package com.example.demo.properties.controller;

import cn.hutool.core.lang.Dict;
import com.example.demo.properties.config.BuildInProperties;
import com.example.demo.properties.config.CustomProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PropertyController {

    private final BuildInProperties buildInProperties;

    private final CustomProperties customProperties;

    @GetMapping("/property")
    public Dict getProperties() {
        return Dict.create()
            .set("build-in-properties", buildInProperties)
            .set("custom-properties", customProperties);
    }
}
