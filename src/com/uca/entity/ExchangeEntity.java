package com.uca.entity;

import java.sql.Timestamp;
import java.util.LinkedList;

public class ExchangeEntity {
    
    private int id;
    private PokemonEntity pokemon;
    private Timestamp date;
    private LinkedList<PokemonEntity> pokemonWanted;
    private int state; //0:en attente 1:accepté -1:annulé 

    public ExchangeEntity(){

        this.state = 0;
    }

    public int getId(){

        return this.id;
    }

    public void setId(int id){

        this.id = id;
    }

    public PokemonEntity getPokemon() {
        return pokemon;
    }

    public void setPokemon(PokemonEntity pokemon) {
        this.pokemon = pokemon;
    }

    public Timestamp getDate(){

        return this.date;
    }

    public void acceptExchange(){

        this.state = 1;
    }

    public void cancelExchange(){

        this.state = -1;
    }

    public int getState(){

        return this.state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public void addPokemonWanted(PokemonEntity pokemon){

        this.pokemonWanted.add(pokemon);
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public LinkedList<PokemonEntity> getPokemonWanted() {
        return pokemonWanted;
    }

    public void setPokemonWanted(LinkedList<PokemonEntity> pokemonWanted) {
        this.pokemonWanted = pokemonWanted;
    }
}
