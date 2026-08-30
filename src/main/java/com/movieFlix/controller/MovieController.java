package com.movieFlix.controller;

import com.movieFlix.entity.Movie;
import com.movieFlix.entity.dto.MovieRequest;
import com.movieFlix.entity.dto.MovieResponse;
import com.movieFlix.mapper.MovieMapper;
import com.movieFlix.service.MovieService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movieflix/movie")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService service;

    @PostMapping
    public ResponseEntity<MovieResponse> save(@Valid @RequestBody MovieRequest payload){
        Movie movie = MovieMapper.toEntity(payload);
        Movie savedMovie = service.save(movie);
        MovieResponse response = MovieMapper.toMovieResponse(savedMovie);
       return ResponseEntity
               .status(HttpStatus.CREATED)
               .body(response);
    }

    @GetMapping
    public ResponseEntity<List<MovieResponse>> findAll(){
        List<MovieResponse> responseList = service.findAll().stream().map(MovieMapper::toMovieResponse).toList();
        return ResponseEntity.ok(responseList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieResponse> findById(@PathVariable Long id){
        return service
                .findById(id)
                .map(movie -> ResponseEntity.ok(MovieMapper.toMovieResponse(movie)))
                .orElse(ResponseEntity.notFound().build());

    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieResponse> update(@PathVariable Long id, @Valid @RequestBody MovieRequest payload){
        return service.update(id, MovieMapper.toEntity(payload))
                .map(movie -> ResponseEntity.ok(MovieMapper.toMovieResponse(movie)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<MovieResponse>> search(@RequestParam Long category){
        return ResponseEntity.ok(service.findByCategory(category).stream().map(MovieMapper::toMovieResponse).toList());
    }
}
