package com.movieFlix.controller;

import com.movieFlix.entity.Movie;
import com.movieFlix.entity.dto.MovieRequest;
import com.movieFlix.entity.dto.MovieResponse;
import com.movieFlix.mapper.MovieMapper;
import com.movieFlix.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/movieflix/movie")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService service;

    @PostMapping
    public ResponseEntity<MovieResponse> save(MovieRequest payload){
       return ResponseEntity.status(HttpStatus.CREATED).body(MovieMapper.toMovieResponse(service.save(payload)));
    }

    @GetMapping
    public ResponseEntity<List<MovieResponse>> findAll(){
        return ResponseEntity.ok(service.findAll().stream().map(MovieMapper::toMovieResponse).toList());
    }
}
