package com.movieFlix.mapper;

import com.movieFlix.entity.Streaming;
import com.movieFlix.entity.dto.StreamingRequest;
import com.movieFlix.entity.dto.StreamingResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class StreamingMapper {

    public Streaming toEntity(StreamingRequest streamingRequest){
        return Streaming.builder().name(streamingRequest.name()).build();
    }

    public StreamingResponse toStreamingResponse(Streaming streaming){
        return  StreamingResponse.builder().name(streaming.getName()).build();
    }
}
