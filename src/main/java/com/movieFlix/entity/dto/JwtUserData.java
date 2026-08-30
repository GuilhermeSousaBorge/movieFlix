package com.movieFlix.entity.dto;

import lombok.Builder;

@Builder
public record JwtUserData (
        Long id,
        String email,
        String name
){
}
