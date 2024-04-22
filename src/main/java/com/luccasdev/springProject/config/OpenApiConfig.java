package com.luccasdev.springProject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {

    @Bean
    OpenAPI customAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("RestFull API - Java and Spring boot")
						.version("v1")
						.description(" ")
						.termsOfService("https://luccasdev.com.br/rest")
						.license(new License().name("Apache 2.0")
								.url("https://luccasdev.com.br/rest")));
	}

}
