package com.uca.entity;

import com.uca.core.PokemonCore;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    public LinkedList<PokemonEntity> getPokemonWantedPossessed(LinkedList<PokemonEntity> userPokemonList){

        LinkedList<PokemonEntity> pokemonList = new LinkedList<>();

        for(PokemonEntity pokemon : this.pokemonWanted){

            for(PokemonEntity userPokemon : userPokemonList){

                if(pokemon.getPokedexId() == userPokemon.getPokedexId()){

                    pokemon.setId(userPokemon.getId());
                    break;
                }
                else{

                    pokemon.setId(-1);
                }
            }
            pokemonList.add(pokemon);
        }

        return pokemonList;
    }
}
