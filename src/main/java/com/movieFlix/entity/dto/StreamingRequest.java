package com.movieFlix.entity.dto;

import lombok.Builder;

@Builder
public record StreamingRequest(
        String name
) {
}
