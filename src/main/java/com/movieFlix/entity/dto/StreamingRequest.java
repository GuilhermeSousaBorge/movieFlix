package com.movieFlix.entity.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

@Builder
public record StreamingRequest(
        @NotEmpty(message = "O nome do streaming é obrigatorio")
        String name
) {
}
