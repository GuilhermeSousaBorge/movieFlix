package com.movieFlix.service;

import com.movieFlix.entity.Streaming;
import com.movieFlix.entity.dto.StreamingRequest;
import com.movieFlix.mapper.StreamingMapper;
import com.movieFlix.repository.StreamingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.InputMismatchException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StreamingService {

    private final StreamingRepository repository;

    public Streaming create(StreamingRequest payload){
        Streaming streaming = StreamingMapper.toEntity(payload);
        return repository.save(streaming);
    }

    public List<Streaming> listAll(){
        return repository.findAll();
    }

    public Streaming getById(Long id){
        return repository.findById(id).orElseThrow(() -> new InputMismatchException("Categoria nao encontrada"));
    }

    public Long update(Long id, StreamingRequest payload){
        Streaming streaming = getById(id);

        streaming.setName(payload.name());

        return repository.save(streaming).getId();
    }

    public void delete(Long id){
        Streaming streaming = getById(id);

        repository.delete(streaming);
    }
}
