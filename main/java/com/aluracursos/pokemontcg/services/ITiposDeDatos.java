package com.aluracursos.pokemontcg.services;

public interface ITiposDeDatos {
    <T> T obtenerDatos(String json, Class<T> clase);
}
