package com.movieFlix.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
@Schema(description = "Dados de criação/atualização de filme")
public record MovieRequest(
        @NotEmpty(message = "O titulo do filme é obrigatorio")
        @Schema(description = "Título do filme", example = "Homem Aranha")
        String title,
        @Schema(description = "Descrição do filme", example = "Um jovem ganha poderes de aranha")
        String description,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
        @Schema(description = "Data de lançamento (formato: dd/MM/yyyy)", example = "25/12/2023")
        LocalDate releaseDate,
        @Schema(description = "Nota do filme (0-10)", example = "8.5")
        Double rating,
        @Schema(description = "IDs das categorias do filme", example = "[1, 2]")
        List<Long> categories,
        @Schema(description = "IDs das plataformas de streaming", example = "[1, 3]")
        List<Long> streamings
) {
}
