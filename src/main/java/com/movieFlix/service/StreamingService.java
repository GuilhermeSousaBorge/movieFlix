package com.movieFlix.service;

import com.movieFlix.entity.Streaming;
import com.movieFlix.entity.dto.StreamingRequest;
import com.movieFlix.mapper.StreamingMapper;
import com.movieFlix.repository.StreamingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StreamingService {

    private final StreamingRepository repository;

    public Streaming create(Streaming payload){
        return repository.save(payload);
    }

    public List<Streaming> listAll(){
        return repository.findAll();
    }

    public Optional<Streaming> getById(Long id){
        return repository.findById(id);
    }

    public Long update(Long id, Streaming payload){
        return repository.save(payload).getId();
    }

    public void delete(Long id){
        repository.deleteById(id);
    }
}
