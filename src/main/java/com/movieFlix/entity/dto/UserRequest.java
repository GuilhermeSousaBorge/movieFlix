package com.movieFlix.entity.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(description = "Dados de registro do novo usuário")
public record UserRequest(
        @Schema(description = "Nome completo do usuário", example = "João Silva")
        String name,
        @Schema(description = "Email do usuário", example = "joao@example.com")
        String email,
        @Schema(description = "Senha do usuário", example = "senha123")
        String password
) {
}
