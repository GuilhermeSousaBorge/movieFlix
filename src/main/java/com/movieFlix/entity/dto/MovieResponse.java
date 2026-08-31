package com.movieFlix.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
@Schema(description = "Dados do filme")
public record MovieResponse(
        @Schema(description = "ID do filme", example = "1")
        Long id,
        @Schema(description = "Título do filme", example = "Homem Aranha")
        String title,
        @Schema(description = "Descrição do filme", example = "Um jovem ganha poderes de aranha")
        String description,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
        @Schema(description = "Data de lançamento (formato: dd/MM/yyyy)", example = "25/12/2023")
        LocalDate releaseDate,
        @Schema(description = "Nota do filme (0-10)", example = "8.5")
        Double rating,
        @Schema(description = "Categorias do filme")
        List<CategoryResponse> categories,
        @Schema(description = "Plataformas de streaming onde o filme está disponível")
        List<StreamingResponse> streamings
) {
}
