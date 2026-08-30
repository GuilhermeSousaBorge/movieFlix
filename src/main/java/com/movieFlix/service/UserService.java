package com.movieFlix.service;

import com.movieFlix.entity.User;
import com.movieFlix.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public User save(User payload){
        String password = payload.getPassword();
        payload.setPassword(passwordEncoder.encode(password));

        return repository.save(payload);
    }
}
