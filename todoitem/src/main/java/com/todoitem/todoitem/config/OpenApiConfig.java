package com.todoitem.todoitem.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {

@Bean
 public OpenAPI customOpenAPI(@Value("${springdoc.version}") String appVersion) {
	        return new OpenAPI()
	                .info(new Info().title("TodoItem API Service")
	                .version(appVersion)
	                 .contact(new Contact().name(" ")
	                         .url(" "))
	                .description("This is a sample todoitem service application using spring data")
	                .termsOfService("http://swagger.io/terms/")
	                .license(new License().name("Apache 2.0").url("http://springdoc.org")));
	    }

}
