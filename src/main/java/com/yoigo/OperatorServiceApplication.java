/*******************************************************************************
 * Copyright (C) 2017 Yoigo & Masmovil - All Rights Reserved
 *
 * This file is a part of proprietary and confidential software.
 * Unauthorized copying of this file, via any medium is strictly prohibited
 *******************************************************************************/
package com.yoigo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Spring Boot Application Main Class
 *
 */
@SpringBootApplication
@EnableSwagger2
public class OperatorServiceApplication {

    @Value("${endpoints.cors.allowed-origins}")
    private String allowedOrigins;

    @Value("${endpoints.cors.allowed-methods}")
    private String allowedMethods;

    @Value("${endpoints.cors.allowed-headers}")
    private String allowedHeaders;

    /**
     * Application main method This method runs the spring boot application
     * 
     * @param args
     */
    public static void main(String[] args) {
	SpringApplication.run(OperatorServiceApplication.class, args);
    }

    /**
     * RestTemplate Bean declared to use it with Autowired annotation
     * 
     * @param RestTemplateBuilder
     *            builder
     * @return a RestTemplate bean
     */
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
	return builder.build();
    }

    /**
     * WebMvcConfigurer bean declared to configure CORS
     * 
     * @return WebMvcConfigurer bean
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
	return new WebMvcConfigurerAdapter() {
	    @Override
	    public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins(allowedOrigins).allowedHeaders(allowedHeaders)
			.allowedMethods(allowedMethods);
	    }
	};
    }

    @Bean
    public Docket simpleDiffServiceApi() {
	return new Docket(DocumentationType.SWAGGER_2).groupName("yoigo").apiInfo(apiInfo()).select()
		.apis(RequestHandlerSelectors.any()).paths(PathSelectors.regex("/operator.*")).build().pathMapping("/");

    }

    private ApiInfo apiInfo() {
	return new ApiInfoBuilder().title("Operator API").description("Rest Api documentation for Operator Service")
		.contact(new Contact("Yoigo", "http://www.yoigo.com/", "support@yoigo.com")).version("0.0.1-SNAPSHOT")
		.build();
    }
}
