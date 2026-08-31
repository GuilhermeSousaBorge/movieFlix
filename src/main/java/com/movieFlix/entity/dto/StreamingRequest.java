package com.movieFlix.entity.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

@Builder
@Schema(description = "Dados de criação de plataforma de streaming")
public record StreamingRequest(
        @NotEmpty(message = "O nome do streaming é obrigatorio")
        @Schema(description = "Nome da plataforma de streaming", example = "Netflix")
        String name
) {
}
