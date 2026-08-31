package com.movieFlix.controller;

import com.movieFlix.config.TokenService;
import com.movieFlix.entity.User;
import com.movieFlix.entity.dto.LoginRequest;
import com.movieFlix.entity.dto.UserRequest;
import com.movieFlix.entity.dto.UserResponse;
import com.movieFlix.mapper.UserMapper;
import com.movieFlix.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Autenticação", description = "Registro e login de usuários")
public class AuthController {

    private UserService userService;
    private AuthenticationManager authenticationManager;
    private TokenService tokenService;

    @PostMapping("/register")
    @Operation(summary = "Registrar novo usuário", description = "Cria um novo usuário no sistema com as credenciais fornecidas")
    @ApiResponse(responseCode = "201", description = "Usuário registrado com sucesso")
    @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos")
    public ResponseEntity<UserResponse> register(@RequestBody UserRequest request){
        var newUser = UserMapper.toEntity(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.toUserResponse(newUser));
    }

    @PostMapping("/login")
    @Operation(summary = "Realizar login", description = "Autentica o usuário e retorna um token JWT")
    @ApiResponse(responseCode = "200", description = "Login realizado com sucesso, retorna token JWT")
    @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    public ResponseEntity<String> login(@RequestBody LoginRequest request){
        UsernamePasswordAuthenticationToken userAndPass = new UsernamePasswordAuthenticationToken(request.email(), request.password());
        Authentication authenticate = authenticationManager.authenticate(userAndPass);
        User user = (User)authenticate.getPrincipal();

        return ResponseEntity.ok(tokenService.generateToken(user));
    }
}
