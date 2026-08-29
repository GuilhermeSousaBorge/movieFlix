package com.movieFlix.service;

import com.movieFlix.entity.Category;
import com.movieFlix.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;

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

    public Optional<Category> getCategoryById(Long id){
        return repository.findById(id);
    }

    public Long updateCategory(Long id, Category payload){
        return repository.save(payload).getId();
    }

    public void deleteCategory(Long id){
        repository.deleteById(id);
    }

}
