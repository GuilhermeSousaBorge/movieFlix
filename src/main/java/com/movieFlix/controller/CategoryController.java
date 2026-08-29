package com.movieFlix.controller;

import com.movieFlix.entity.Category;
import com.movieFlix.entity.dto.CategoryResponse;
import com.movieFlix.mapper.CategoryMapper;
import com.movieFlix.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movieflix/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService service;

    @PostMapping
    public ResponseEntity<CategoryResponse> addCategory(@RequestBody Category category) {
        Category newCategory = service.createCategory(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(CategoryMapper.toCategoryResponse(newCategory));
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> listAllCategories(){
        List<Category> categories = service.listAll();
        return ResponseEntity.ok(categories.stream().map(CategoryMapper::toCategoryResponse).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> categoryDetails(@PathVariable Long id){
        return service
                .getCategoryById(id)
                .map(category -> ResponseEntity.ok(CategoryMapper.toCategoryResponse(category)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateCategory(@PathVariable Long id, @RequestBody Category category){
        return ResponseEntity.ok(service.updateCategory(id, category));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id){
        service.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }

}
