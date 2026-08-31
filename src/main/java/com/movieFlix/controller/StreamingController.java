package com.movieFlix.controller;

import com.movieFlix.entity.Streaming;
import com.movieFlix.entity.dto.StreamingRequest;
import com.movieFlix.entity.dto.StreamingResponse;
import com.movieFlix.mapper.StreamingMapper;
import com.movieFlix.service.StreamingService;
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
@RequestMapping("/movieflix/streaming")
@RequiredArgsConstructor
@Tag(name = "Streamings", description = "Gerenciamento de plataformas de streaming")
@SecurityRequirement(name = "bearer-key")
public class StreamingController {

    private final StreamingService service;

    @PostMapping
    @Operation(summary = "Criar streaming", description = "Cria uma nova plataforma de streaming")
    @ApiResponse(responseCode = "201", description = "Streaming criado com sucesso")
    @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos")
    public ResponseEntity<StreamingResponse> addStreaming(@Valid @RequestBody StreamingRequest payload) {
        Streaming streaming = StreamingMapper.toEntity(payload);
        Streaming savedStreaming = service.create(streaming);
        StreamingResponse response = StreamingMapper.toStreamingResponse(savedStreaming);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    @Operation(summary = "Listar streamings", description = "Retorna todas as plataformas de streaming")
    @ApiResponse(responseCode = "200", description = "Lista de streamings retornada com sucesso")
    public ResponseEntity<List<StreamingResponse>> listAllStreamings(){
        List<Streaming> categories = service.listAll();
        return ResponseEntity.ok(categories.stream().map(StreamingMapper::toStreamingResponse).toList());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obter streaming por ID", description = "Retorna os detalhes de uma plataforma de streaming específica")
    @ApiResponse(responseCode = "200", description = "Streaming encontrado e retornado com sucesso")
    @ApiResponse(responseCode = "404", description = "Streaming não encontrado")
    public ResponseEntity<StreamingResponse> streamingDetails(@PathVariable Long id){
        return service
                .getById(id)
                .map(streaming -> ResponseEntity.ok(StreamingMapper.toStreamingResponse(streaming)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar streaming", description = "Atualiza uma plataforma de streaming existente")
    @ApiResponse(responseCode = "200", description = "Streaming atualizado com sucesso")
    @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos")
    @ApiResponse(responseCode = "404", description = "Streaming não encontrado")
    public ResponseEntity<Long> updateStreaming(@PathVariable Long id, @Valid @RequestBody StreamingRequest payload){
        Streaming streaming = StreamingMapper.toEntity(payload);
        return ResponseEntity.ok(service.update(id, streaming));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar streaming", description = "Remove uma plataforma de streaming do sistema")
    @ApiResponse(responseCode = "204", description = "Streaming deletado com sucesso")
    @ApiResponse(responseCode = "404", description = "Streaming não encontrado")
    public ResponseEntity<Void> deleteStreaming(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
