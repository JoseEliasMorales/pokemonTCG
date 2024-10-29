package com.aluracursos.pokemontcg.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record SetDetails(
        String series,
        String name,
        Integer total
) {
}
