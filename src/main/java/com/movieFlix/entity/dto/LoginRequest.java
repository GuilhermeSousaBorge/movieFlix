package com.movieFlix.entity.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Dados de login do usuário")
public record LoginRequest(
        @Schema(description = "Email do usuário", example = "user@example.com")
        String email,
        @Schema(description = "Senha do usuário", example = "senha123")
        String password
) {
}
