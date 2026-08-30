package com.movieFlix.entity.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

@Builder
public record CategoryRequest(
        @NotEmpty(message = "O nome da categoria é obrigatorio")
        String name
) {
}
