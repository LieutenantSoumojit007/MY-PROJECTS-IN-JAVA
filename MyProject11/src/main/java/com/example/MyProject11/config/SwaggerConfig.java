package com.example.MyProject11.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {

        return new OpenAPI()

                .info(new Info()

                        .title("Product Management API")

                        .version("1.0")

                        .description("Spring Boot Product Management System"))

                .addSecurityItem(new SecurityRequirement()

                        .addList("basicAuth"))

                .components(new Components()

                        .addSecuritySchemes(

                                "basicAuth",

                                new SecurityScheme()

                                        .type(SecurityScheme.Type.HTTP)

                                        .scheme("basic")));

    }

}