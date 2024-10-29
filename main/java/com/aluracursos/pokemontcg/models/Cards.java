package com.aluracursos.pokemontcg.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.lang.reflect.Array;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Cards(
        @JsonAlias("name") String nombre,
        @JsonAlias("supertype") String tipoDeCarta,
        String id,
        @JsonAlias("nationalPokedexNumbers") List<Integer> numeroPokedex,
        @JsonAlias("level") String nivel,
        @JsonAlias("types") List<String> tipo,
        SetDetails set,
        @JsonAlias("rarity") String rareza,
        @JsonAlias("cardmarket") CardMarket mercado,
        @JsonAlias("images") Imagenes imagenes
){

}
