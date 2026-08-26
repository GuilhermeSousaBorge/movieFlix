package com.movieFlix.mapper;

import com.movieFlix.entity.Category;
import com.movieFlix.entity.dto.CategoryRequest;
import com.movieFlix.entity.dto.CategoryResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CategoryMapper {


    public Category toEntity(CategoryRequest categoryRequest){
        return Category.builder().name(categoryRequest.name()).build();
    }

    public CategoryResponse toCategoryResponse(Category category){
        return CategoryResponse.builder().id(category.getId()).name(category.getName()).build();
    }
}
