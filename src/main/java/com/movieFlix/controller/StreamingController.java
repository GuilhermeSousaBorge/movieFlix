package com.movieFlix.controller;

import com.movieFlix.entity.Streaming;
import com.movieFlix.entity.dto.StreamingRequest;
import com.movieFlix.entity.dto.StreamingResponse;
import com.movieFlix.mapper.StreamingMapper;
import com.movieFlix.service.StreamingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movieflix/streaming")
@RequiredArgsConstructor
public class StreamingController {

    private final StreamingService service;

    @PostMapping
    public ResponseEntity<StreamingResponse> addStreaming(@RequestBody StreamingRequest streamingRequest) {
        Streaming newCategory = service.create(streamingRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(StreamingMapper.toStreamingResponse(newCategory));
    }

    @GetMapping
    public ResponseEntity<List<StreamingResponse>> listAllStreamings(){
        List<Streaming> categories = service.listAll();
        return ResponseEntity.ok(categories.stream().map(StreamingMapper::toStreamingResponse).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StreamingResponse> streamingDetails(@PathVariable Long id){
        return ResponseEntity.ok(StreamingMapper.toStreamingResponse(service.getById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateStreaming(@PathVariable Long id, @RequestBody StreamingRequest streamingRequest){
        return ResponseEntity.ok(service.update(id, streamingRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStreaming(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
