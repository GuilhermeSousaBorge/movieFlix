package com.movieFlix.controller;

import com.movieFlix.entity.Category;
import com.movieFlix.entity.dto.CategoryResponse;
import com.movieFlix.mapper.CategoryMapper;
import com.movieFlix.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movieflix/category")
@RequiredArgsConstructor
@Tag(name = "Categorias", description = "Gerenciamento de categorias de filmes")
@SecurityRequirement(name = "bearer-key")
public class CategoryController {

    private final CategoryService service;

    @PostMapping
    @Operation(summary = "Criar categoria", description = "Cria uma nova categoria de filme")
    @ApiResponse(responseCode = "201", description = "Categoria criada com sucesso")
    @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos")
    public ResponseEntity<CategoryResponse> addCategory(@Valid @RequestBody Category category) {
        Category newCategory = service.createCategory(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(CategoryMapper.toCategoryResponse(newCategory));
    }

    @GetMapping
    @Operation(summary = "Listar categorias", description = "Retorna todas as categorias de filmes")
    @ApiResponse(responseCode = "200", description = "Lista de categorias retornada com sucesso")
    public ResponseEntity<List<CategoryResponse>> listAllCategories(){
        List<Category> categories = service.listAll();
        return ResponseEntity.ok(categories.stream().map(CategoryMapper::toCategoryResponse).toList());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obter categoria por ID", description = "Retorna os detalhes de uma categoria específica")
    @ApiResponse(responseCode = "200", description = "Categoria encontrada e retornada com sucesso")
    @ApiResponse(responseCode = "404", description = "Categoria não encontrada")
    public ResponseEntity<CategoryResponse> categoryDetails(@PathVariable Long id){
        return service
                .getCategoryById(id)
                .map(category -> ResponseEntity.ok(CategoryMapper.toCategoryResponse(category)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar categoria", description = "Atualiza uma categoria existente")
    @ApiResponse(responseCode = "200", description = "Categoria atualizada com sucesso")
    @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos")
    @ApiResponse(responseCode = "404", description = "Categoria não encontrada")
    public ResponseEntity<Long> updateCategory(@PathVariable Long id, @Valid @RequestBody Category category){
        return ResponseEntity.ok(service.updateCategory(id, category));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar categoria", description = "Remove uma categoria do sistema")
    @ApiResponse(responseCode = "204", description = "Categoria deletada com sucesso")
    @ApiResponse(responseCode = "404", description = "Categoria não encontrada")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id){
        service.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }

}
