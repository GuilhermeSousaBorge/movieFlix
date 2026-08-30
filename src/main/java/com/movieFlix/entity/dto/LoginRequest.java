package com.movieFlix.entity.dto;

public record LoginRequest(
        String email,
        String password
) {
}
