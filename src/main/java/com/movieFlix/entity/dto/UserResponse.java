package com.movieFlix.entity.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(description = "Dados do usuário registrado")
public record UserResponse(
        @Schema(description = "ID do usuário", example = "1")
        Long id,
        @Schema(description = "Nome completo do usuário", example = "João Silva")
        String name,
        @Schema(description = "Email do usuário", example = "joao@example.com")
        String email
) {
}
