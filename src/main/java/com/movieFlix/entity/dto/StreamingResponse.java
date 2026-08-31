package com.movieFlix.entity.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(description = "Dados da plataforma de streaming")
public record StreamingResponse(
        @Schema(description = "ID da plataforma de streaming", example = "1")
        Long id,
        @Schema(description = "Nome da plataforma de streaming", example = "Netflix")
        String name
) {
}
