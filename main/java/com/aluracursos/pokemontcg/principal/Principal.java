package com.aluracursos.pokemontcg.principal;

import com.aluracursos.pokemontcg.models.Cards;
import com.aluracursos.pokemontcg.models.DataCards;
import com.aluracursos.pokemontcg.services.ConsumoAPI;
import com.aluracursos.pokemontcg.services.ConvierteDatosAPI;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {
    ConvierteDatosAPI conversor = new ConvierteDatosAPI();
    ConsumoAPI consumoAPI = new ConsumoAPI();
    Scanner teclado = new Scanner(System.in);
    TiposDeBusqueda busqueda = new TiposDeBusqueda();

    public void muestraElMenu(){
        System.out.println("""
                Bienvenido a nuestra app de cartas Pokemon!
                
                Por favor, dime el tipo de busqueda que desees hacer:
                
                1 - Por nombre
                2 - Por id
                3 - Por pokedex
                4 - Por rareza
                5 - Por tipo
                6 - Las 10 cartas vas valiosas
                
                """);
        System.out.println("Tu opcion: ");
        String opcionElegida = teclado.nextLine();


        switch (opcionElegida){
            case "1":
                busqueda.buscarPorNombre();
                break;
            case "2":
                busqueda.busquedaPorId();
                break;
            case "3":
                busqueda.buscarPorPokedex();
                break;
            case "4":
                busqueda.obtenerCartasPorPropiedad("rarities", "rarity");
                break;
            case "5":
                busqueda.obtenerCartasPorPropiedad("types", "types");
                break;
            case "6":
                busqueda.topCartasValiosas();
                break;
            default:
                System.out.println("Opcion no disponible");
                break;
        }

    }

}
