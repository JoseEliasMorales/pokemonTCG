package com.aluracursos.pokemontcg.services;

import com.aluracursos.pokemontcg.models.Cards;
import com.aluracursos.pokemontcg.models.DataCards;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsumoAPI {
    ApiKey ApiKey = new ApiKey();
    String API_KEY = ApiKey.getAPI_KEY();
    private final String URL = "https://api.pokemontcg.io/v2/";
    private HttpClient client;
    private HttpRequest request;
    HttpResponse<String> response = null;

    private String requestApi(){
        try{
            client = HttpClient.newHttpClient();
            response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        var json = response.body();
        return json;


    }

    public String obtenerCartasPorPropiedadYNombre( String propiedad, String nombre){
        request = HttpRequest.newBuilder()
                .uri(URI.create(URL  + "cards?q=" + propiedad + ":" + nombre))
                .header("X-Api-Key", API_KEY)
                .build();


        return requestApi();
    }

    public String obtenerCartasPorPropiedad( String nombre){
        String urlCompleta = URL  + "cards?q=" + nombre;
        request = HttpRequest.newBuilder()
                .uri(URI.create(urlCompleta))
                .header("X-Api-Key", API_KEY)
                .build();


        return requestApi();
    }


    public String obtenerTipoDeCartas(String propiedad){
        request = HttpRequest.newBuilder()
                .uri(URI.create(URL  + propiedad))
                .header("X-Api-Key", API_KEY)
                .build();


        return requestApi();
    }

    public String obtenerCartasPorId(String id){
        request = HttpRequest.newBuilder()
                .uri(URI.create(URL  + "cards/" + id))
                .header("X-Api-Key", API_KEY)
                .build();

        return requestApi();
    }

    public String obtenerTodasLasCartas(){
        request = HttpRequest.newBuilder()
                .uri(URI.create(URL + "cards" + "?orderBy=-cardmarket.prices.averageSellPrice"))
                .header("X-Api-Key", API_KEY)
                .build();

        return requestApi();
    }

}
