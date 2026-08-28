package com.movieFlix.mapper;

import com.movieFlix.entity.Category;
import com.movieFlix.entity.Movie;
import com.movieFlix.entity.Streaming;
import com.movieFlix.entity.dto.CategoryResponse;
import com.movieFlix.entity.dto.MovieRequest;
import com.movieFlix.entity.dto.MovieResponse;
import com.movieFlix.entity.dto.StreamingResponse;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class MovieMapper {

    public static Movie toEntity(MovieRequest request){

        List<Category> categories = request
                .categories()
                .stream()
                .map(categoryId -> Category.builder().id(categoryId).build())
                .toList();

        List<Streaming> streamings = request
                .streamings()
                .stream()
                .map(streamingId -> Streaming.builder().id(streamingId).build())
                .toList();

        return Movie.builder()
                .title(request.title())
                .description(request.description())
                .releaseDate(request.releaseDate())
                .rating(request.rating())
                .categories(categories)
                .streamings(streamings)
                .build();
    }

    public static MovieResponse toMovieResponse(Movie entity){

        List<CategoryResponse> categories = entity.getCategories()
                .stream()
                .map(CategoryMapper::toCategoryResponse)
                .toList();

        List<StreamingResponse> streamings = entity.getStreamings()
                .stream()
                .map(StreamingMapper::toStreamingResponse)
                .toList();

        return MovieResponse.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .releaseDate(entity.getReleaseDate())
                .rating(entity.getRating())
                .categories(categories)
                .streamings(streamings)
                .build();
    }
}
