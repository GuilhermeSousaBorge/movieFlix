package com.movieFlix.entity.dto;

import lombok.Builder;

@Builder
public record CategoryRequest(
        String name
) {
}
