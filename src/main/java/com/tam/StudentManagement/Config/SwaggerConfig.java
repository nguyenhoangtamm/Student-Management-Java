package com.tam.StudentManagement.Config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI myOpenAPI() {
        Server devServer = new Server()
                .url("http://localhost:8080")
                .description("Development server");

        Contact contact = new Contact()
                .name("Student Management API Support")
                .email("support@example.com");

        Info info = new Info()
                .title("Student Management API")
                .version("1.0")
                .contact(contact)
                .description("This API exposes endpoints to manage students.")
                .termsOfService("http://swagger.io/terms/")
                .license(new License().name("Apache 2.0").url("http://springdoc.org"));

        return new OpenAPI()
                .info(info)
                .servers(List.of(devServer));
    }
}