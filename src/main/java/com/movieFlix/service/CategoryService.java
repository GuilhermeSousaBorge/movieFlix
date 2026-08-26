package com.movieFlix.service;

import com.movieFlix.entity.Category;
import com.movieFlix.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.InputMismatchException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository repository;

    public Category createCategory(Category payload){
        return repository.save(payload);
    }

    public List<Category> listAll(){
        return repository.findAll();
    }

    public Category getCategoryById(Long id){
        return repository.findById(id).orElseThrow(() -> new InputMismatchException("Categoria nao encontrada"));
    }

    public Long updateCategory(Long id, Category payload){
        Category category = getCategoryById(id);

        category.setName(payload.getName());

        return repository.save(category).getId();
    }

    public void deleteCategory(Long id){
        Category category = getCategoryById(id);

        repository.delete(category);
    }

}
