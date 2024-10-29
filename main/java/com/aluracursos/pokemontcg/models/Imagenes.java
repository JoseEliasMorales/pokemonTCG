package com.aluracursos.pokemontcg.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Imagenes(
        @JsonAlias("large") String imagen
) {
}
