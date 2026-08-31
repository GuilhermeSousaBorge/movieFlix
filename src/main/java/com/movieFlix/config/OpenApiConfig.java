package com.movieFlix.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI movieFlixOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("MovieFlix API")
                .description("REST API para gerenciamento de filmes, categorias e streamings")
                .version("1.0.0")
                .contact(new Contact().name("MovieFlix Team")))
            .components(new Components()
                .addSecuritySchemes("bearer-key",
                    new SecurityScheme()
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer")
                        .bearerFormat("JWT")
                        .description("Informe o token JWT obtido no endpoint /auth/login")));
    }
}
