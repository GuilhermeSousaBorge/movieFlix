package com.movieFlix.controller;

import com.movieFlix.config.TokenService;
import com.movieFlix.entity.User;
import com.movieFlix.entity.dto.LoginRequest;
import com.movieFlix.entity.dto.UserRequest;
import com.movieFlix.entity.dto.UserResponse;
import com.movieFlix.mapper.UserMapper;
import com.movieFlix.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movieflix/auth")
@RequiredArgsConstructor
public class AuthController {

    private UserService userService;
    private AuthenticationManager authenticationManager;
    private TokenService tokenService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody UserRequest request){
        var newUser = UserMapper.toEntity(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.toUserResponse(newUser));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request){
        UsernamePasswordAuthenticationToken userAndPass = new UsernamePasswordAuthenticationToken(request.email(), request.password());
        Authentication authenticate = authenticationManager.authenticate(userAndPass);
        User user = (User)authenticate.getPrincipal();

        return ResponseEntity.ok(tokenService.generateToken(user));
    }
}
