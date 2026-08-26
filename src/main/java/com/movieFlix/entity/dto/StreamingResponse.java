package com.movieFlix.entity.dto;

import lombok.Builder;

@Builder
public record StreamingResponse(
        Long id,
        String name
) {
}
