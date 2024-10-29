package com.aluracursos.pokemontcg.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties (ignoreUnknown = true)
public record DataCards(List<Cards> data) {
}
