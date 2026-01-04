package com.jguarnizo.bibliotecarest.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfiguration {
    
    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
            .info(new Info()
                .title("Api Gestion Biblioteca")
                .version("1.0")
                .description("Servicio web que se encarga de gestionar la informacion de los libros para una    biblioteca")
                .contact(new Contact()
                    .name("Julian Eduardo Guarnizo Parra")
                    .email("julii2002@hotmail.com"))
                .license(new License().name("Apache 2.0").url("http://springdoc.org"))
            );
    }
}
