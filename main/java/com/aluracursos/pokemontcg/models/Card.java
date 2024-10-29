package com.aluracursos.pokemontcg.models;

import java.lang.reflect.Array;
import java.util.List;

public class Card {
    private String nombre;
    private String tipoDeCarta;
    private String id;
    private String nivel;
    private String setName;
    private String setSeries;
    private Integer totalCartas;
    private List<Integer> numeroPokedex;
    private List<String> tipo;
    private String rareza;
    private double precioDeMercado;

    public Card(Cards cards){

    }

    public double getPrecioDeMercado() {
        return precioDeMercado;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipoDeCarta() {
        return tipoDeCarta;
    }

    public String getId() {
        return id;
    }

    public String getNivel() {
        return nivel;
    }

    public String getSetName() {
        return setName;
    }

    public String getSetSeries() {
        return setSeries;
    }

    public Integer getTotalCartas() {
        return totalCartas;
    }

    public List<Integer> getNumeroPokedex() {
        return numeroPokedex;
    }

    public List<String> getTipo() {
        return tipo;
    }

    public String getRareza() {
        return rareza;
    }

    public Card(CardMarket mercado, Cards d) {
        this.nombre = d.nombre();
        this.tipoDeCarta = d.tipoDeCarta();
        this.id = d.id();
        try{
            if(this.nivel.isEmpty()){
                this.nivel = "-";
            }
        }catch (NullPointerException e){
            this.nivel = "-";
        }


        this.numeroPokedex = d.numeroPokedex();
        this.setName = d.set().name();
        this.setSeries = d.set().series();
        this.totalCartas = d.set().total();
        this.tipo = d.tipo();
        this.rareza = d.rareza();
        try {
            this.precioDeMercado = Double.parseDouble(d.mercado().precios().precioMedio());
        }catch (NullPointerException e){
            this.precioDeMercado= 0.0;
        }

    }

    @Override
    public String toString() {
        return """
                Nombre: %s
                id: %s
                Rareza: %s
                Precio: $ %.2f
                Nivel: %s
                Serie: %s
                Nombre del Set: %s
                Total de cartas impresas: %s
                Pokedex: %s
                Tipo: %s
                """.formatted(this.nombre, this.id, this.rareza, this.precioDeMercado, this.nivel, this.setSeries, this.setName, this.totalCartas, this.numeroPokedex, this.tipo);
    }
}
