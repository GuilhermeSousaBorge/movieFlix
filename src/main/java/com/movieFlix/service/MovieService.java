package com.movieFlix.service;

import com.movieFlix.entity.Category;
import com.movieFlix.entity.Movie;
import com.movieFlix.entity.Streaming;
import com.movieFlix.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository repository;
    private final CategoryService categoryService;
    private final StreamingService streamingService;

    public Movie save(Movie request){
        request.setCategories(this.findCategories(request.getCategories()));
        request.setStreamings(this.findStreaming(request.getStreamings()));
        return repository.save(request);
    }

    public List<Movie> findAll(){
        return repository.findAll();
    }

    public Optional<Movie> findById(Long id){
        return  repository.findById(id);
    }

    public List<Movie> findByCategory(Long categoryId){
        return repository.findMoviesByCategories(List.of(Category.builder().id(categoryId).build()));
    }

    public Optional<Movie> update(Long id, Movie payload){
        Optional<Movie> movie = repository.findById(id);
        if(movie.isPresent()){

            List<Category> categories = this.findCategories(payload.getCategories());
            List<Streaming> streamings = this.findStreaming(payload.getStreamings());

            Movie newMovie = movie.get();
            newMovie.setTitle(payload.getTitle());
            newMovie.setDescription(payload.getDescription());
            newMovie.setRating(payload.getRating());
            newMovie.setReleaseDate(payload.getReleaseDate());
            newMovie.getCategories().clear();
            newMovie.getCategories().addAll(categories);
            newMovie.getStreamings().clear();
            newMovie.getStreamings().addAll(streamings);

            repository.save(newMovie);
        }
        return Optional.empty();
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

    private List<Category> findCategories(List<Category> categories){
        List<Category> findCategories = new ArrayList<>();

        categories.forEach(category -> categoryService.getCategoryById(category.getId()).ifPresent(findCategories::add));

        return findCategories;
    }

    private List<Streaming> findStreaming(List<Streaming> streamings){
        List<Streaming> findStreamings = new ArrayList<>();
        streamings.forEach(streaming -> streamingService.getById(streaming.getId()).ifPresent(findStreamings::add));
        return findStreamings;
    }
}
