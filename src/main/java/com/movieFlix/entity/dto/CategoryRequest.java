package com.movieFlix.entity.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

@Builder
@Schema(description = "Dados de criação de categoria")
public record CategoryRequest(
        @NotEmpty(message = "O nome da categoria é obrigatorio")
        @Schema(description = "Nome da categoria", example = "Ação")
        String name
) {
}
