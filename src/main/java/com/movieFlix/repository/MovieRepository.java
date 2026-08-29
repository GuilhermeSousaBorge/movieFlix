package com.movieFlix.repository;

import com.movieFlix.entity.Category;
import com.movieFlix.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    List<Movie> findMoviesByCategories(List<Category> categoryId);
}
