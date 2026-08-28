package com.movieFlix.service;

import com.movieFlix.entity.Movie;
import com.movieFlix.entity.dto.MovieRequest;
import com.movieFlix.mapper.MovieMapper;
import com.movieFlix.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository repository;


    public Movie save(MovieRequest request){
        return repository.save(MovieMapper.toEntity(request));
    }

    public List<Movie> findAll(){
        return repository.findAll();
    }
}
