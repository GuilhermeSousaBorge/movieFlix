package com.movieFlix.controller;

import com.movieFlix.entity.Movie;
import com.movieFlix.entity.dto.MovieRequest;
import com.movieFlix.entity.dto.MovieResponse;
import com.movieFlix.mapper.MovieMapper;
import com.movieFlix.service.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
@RequestMapping("/movieflix/movie")
@RequiredArgsConstructor
@Tag(name = "Filmes", description = "Gerenciamento de filmes")
@SecurityRequirement(name = "bearer-key")
public class MovieController {

    private final MovieService service;

    @PostMapping
    @Operation(summary = "Criar filme", description = "Cria um novo filme no sistema")
    @ApiResponse(responseCode = "201", description = "Filme criado com sucesso")
    @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos")
    public ResponseEntity<MovieResponse> save(@Valid @RequestBody MovieRequest payload){
        Movie movie = MovieMapper.toEntity(payload);
        Movie savedMovie = service.save(movie);
        MovieResponse response = MovieMapper.toMovieResponse(savedMovie);
       return ResponseEntity
               .status(HttpStatus.CREATED)
               .body(response);
    }

    @GetMapping
    @Operation(summary = "Listar filmes", description = "Retorna todos os filmes cadastrados")
    @ApiResponse(responseCode = "200", description = "Lista de filmes retornada com sucesso")
    public ResponseEntity<List<MovieResponse>> findAll(){
        List<MovieResponse> responseList = service.findAll().stream().map(MovieMapper::toMovieResponse).toList();
        return ResponseEntity.ok(responseList);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obter filme por ID", description = "Retorna os detalhes de um filme específico")
    @ApiResponse(responseCode = "200", description = "Filme encontrado e retornado com sucesso")
    @ApiResponse(responseCode = "404", description = "Filme não encontrado")
    public ResponseEntity<MovieResponse> findById(@PathVariable Long id){
        return service
                .findById(id)
                .map(movie -> ResponseEntity.ok(MovieMapper.toMovieResponse(movie)))
                .orElse(ResponseEntity.notFound().build());

    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar filme", description = "Atualiza um filme existente")
    @ApiResponse(responseCode = "200", description = "Filme atualizado com sucesso")
    @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos")
    @ApiResponse(responseCode = "404", description = "Filme não encontrado")
    public ResponseEntity<MovieResponse> update(@PathVariable Long id, @Valid @RequestBody MovieRequest payload){
        return service.update(id, MovieMapper.toEntity(payload))
                .map(movie -> ResponseEntity.ok(MovieMapper.toMovieResponse(movie)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar filme", description = "Remove um filme do sistema")
    @ApiResponse(responseCode = "204", description = "Filme deletado com sucesso")
    @ApiResponse(responseCode = "404", description = "Filme não encontrado")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/search")
    @Operation(summary = "Buscar filmes por categoria", description = "Retorna todos os filmes de uma categoria específica")
    @ApiResponse(responseCode = "200", description = "Filmes encontrados e retornados com sucesso")
    public ResponseEntity<List<MovieResponse>> search(
        @RequestParam
        @Parameter(description = "ID da categoria para filtrar filmes")
        Long category){
        return ResponseEntity.ok(service.findByCategory(category).stream().map(MovieMapper::toMovieResponse).toList());
    }
}
