package com.movieFlix.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.movieFlix.entity.User;
import com.movieFlix.entity.dto.JwtUserData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Optional;

@Component
public class TokenService {

    @Value("${movieFlix.security.secret")
    private String secret;

    public String generateToken(User user){

        return JWT.create()
                .withSubject(user.getEmail())
                .withClaim("userId", user.getId())
                .withClaim("name", user.getName())
                .withExpiresAt(Instant.now().plusSeconds(86400))
                .withIssuedAt(Instant.now())
                .withIssuer("Api movie-flix")
                .sign(Algorithm.HMAC256(secret));
    }

    public Optional<JwtUserData> validate(String token) {

        try {
            DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256(secret)).build().verify(token);

            JwtUserData jwtUserData = JwtUserData.builder()
                    .id(decodedJWT.getClaim("userId").asLong())
                    .email(decodedJWT.getSubject())
                    .name(decodedJWT.getClaim("name").asString())
                    .build();
            return Optional.of(jwtUserData);
        } catch (JWTVerificationException ex){
            return Optional.empty();
        }
    }
}
