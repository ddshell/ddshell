package com.ddshell.spring;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.client.RestTemplate;

@Configuration
@ComponentScan(basePackages = AppConfig.BASE_PACKAGE, excludeFilters = { @ComponentScan.Filter(type = FilterType.ANNOTATION, value = {
		Controller.class, ControllerAdvice.class }) })
@EnableJpaRepositories(basePackages = AppConfig.BASE_PACKAGE + ".**.repository")
public class AppConfig {

	public static final String BASE_PACKAGE = "com.example.quickstart";

	static {
		System.setProperty("app.base-package", BASE_PACKAGE);
	}

	@Bean
	public RestTemplate restTemplate() {
		RestTemplate restTemplate = new RestTemplate();

		List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
		converters.add(new MappingJackson2HttpMessageConverter());
		restTemplate.setMessageConverters(converters);

		return restTemplate;
	}
}
