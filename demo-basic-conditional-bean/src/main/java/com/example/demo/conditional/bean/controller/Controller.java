package com.example.demo.conditional.bean.controller;

import com.example.demo.conditional.bean.service.CacheService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class Controller {

	private final ApplicationContext applicationContext;

	@GetMapping
	public String getCurrentBeanName() {
		return applicationContext.getBeanNamesForType(CacheService.class)[0];
	}
}
