package com.aluracursos.pokemontcg.principal;

import com.aluracursos.pokemontcg.models.*;
import com.aluracursos.pokemontcg.services.ConsumoAPI;
import com.aluracursos.pokemontcg.services.ConvierteDatosAPI;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public class TiposDeBusqueda {

    ConsumoAPI consumoAPI = new ConsumoAPI();
    ConvierteDatosAPI conversor = new ConvierteDatosAPI();
    Scanner teclado = new Scanner(System.in);


    public void buscarPorNombre(){

        System.out.println("Por favor, escribe el nombre de la carta:");
        var nombrePokemon = teclado.nextLine();

        try {
            var json = consumoAPI.obtenerCartasPorPropiedadYNombre("name", nombrePokemon);
            var datos = conversor.obtenerDatos(json, DataCards.class);
            if (datos.data().isEmpty()){
                System.out.println("No hemos encontrado ninguna carta con ese nombre\n");
            }
            listarDatos(datos, 100);
        }catch (IllegalArgumentException e){
            System.out.println("Por favor, escribe un solo nombre sin espacios o intenta buscar por ID.");
        }
    }

    public void busquedaPorId(){
        try {

            System.out.println("Por favor, escribe el ID de la carta: ");
            var idDeLaCarta = teclado.nextLine();

            var json = consumoAPI.obtenerCartasPorId(idDeLaCarta);
            var datos = conversor.obtenerDatos(json, DataOneCard.class);

            System.out.println("""
                Hemos encontrado tu carta! :
                
                Nombre: %s,
                Rareza: %s,
                Tipo de Carta: %s,
                Numero de Pokemon: %s,
                Tipo: %s,
                Cantidad de cartas: %s,
                Detalles del set: 
                    Serie: %s,
                    Nombre: %s,
                Precio de Mercado: $ %s,   
                Imagen: %s
                    
                """.formatted(datos.cards().nombre(),
                    datos.cards().rareza(),
                    datos.cards().tipoDeCarta(),
                    datos.cards().numeroPokedex(),
                    datos.cards().tipo(),
                    datos.cards().set().total(),
                    datos.cards().set().series(),
                    datos.cards().set().name(),
                    datos.cards().mercado().precios().precioMedio(),
                    datos.cards().imagenes().imagen()));
        }catch (NullPointerException e){
            System.out.println("No hemos encontrado ninguna carta con esa ID");
        }

    }

    public void buscarPorPokedex(){
        System.out.println("Por favor, introduce el numero de pokemon: ");
        try {
        Integer numeroDePokemon = teclado.nextInt();
        teclado.nextLine();

        if(numeroDePokemon > 1010){
            System.out.println("Lo siento, solo tenemos listados 1010 pokemon en la pokedex");
        } else if (numeroDePokemon < 1) {
            System.out.println("Por favor, introduce un numero entre 1 y 1010");
        }
            var json = consumoAPI.obtenerCartasPorPropiedadYNombre("nationalPokedexNumbers", String.valueOf(numeroDePokemon));
            var datos = conversor.obtenerDatos(json, DataCards.class);

            listarDatos(datos, 100);
        } catch (InputMismatchException e) {
            System.out.println("Por favor, introduce solo un numero");
        }
    }

    public void obtenerCartasPorPropiedad(String propiedad, String tipoPropiedad){


        List<String> rarezas = new ArrayList<>();
        try{
            var json = consumoAPI.obtenerTipoDeCartas(propiedad);
            var datos = conversor.obtenerDatos(json, DataTypes.class);

            System.out.println("Que tipo de carta buscas?");

            for (int i = 0; i < datos.data().size(); i++) {
                System.out.println( (i+1) + " " + datos.data().get(i));
                rarezas.add(datos.data().get(i));
            }

            System.out.println("Escoge una opcion de la lista por numero: ");

            //Despues de la lista de rarezas, se debe ingresar el numero de la lista, no el nombre
            var busqueda = teclado.nextInt();
            teclado.nextLine();

            String nombre = URLEncoder.encode(tipoPropiedad +":\""+ datos.data().get(busqueda-1)+"\"", StandardCharsets.UTF_8);
            if (busqueda <= datos.data().size() + 1){
                var jsonData = consumoAPI.obtenerCartasPorPropiedad(nombre);
                var datosCards = conversor.obtenerDatos(jsonData, DataCards.class);

                listarDatos(datosCards, 100);
            }
        }catch (InputMismatchException e){
            System.out.println("Introduce una opcion valida.");
        }catch (IndexOutOfBoundsException e){
            System.out.println("Por favor, elige dentro de las opciones generadas. " );
        }



    }

    public void topCartasValiosas(){
        try {
            System.out.println("Obteniendo cartas...\n");
            var json = consumoAPI.obtenerTodasLasCartas();
            var datos = conversor.obtenerDatos(json, DataCards.class);

            listarDatos(datos, 10);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    //Aqui listamos los datos para todas las busquedas que necesiten este formato.
    public void listarDatos(DataCards datos, Integer limite){
        datos.data().stream()
                .map(d-> new Card(d.mercado(), d))
                .limit(limite)
                .peek(System.out::println)
                .collect(Collectors.toList());



    }
}
