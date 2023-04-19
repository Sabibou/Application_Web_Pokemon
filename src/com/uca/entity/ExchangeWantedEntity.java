package com.uca.entity;

public class ExchangeWantedEntity {

    private int id;
    private PokemonEntity pokemon;

    public ExchangeWantedEntity(int id, PokemonEntity pokemon){

        this.id = id;
        this.pokemon = pokemon;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PokemonEntity getPokemon() {
        return pokemon;
    }

    public void setPokemon(PokemonEntity pokemon) {
        this.pokemon = pokemon;
    }
}
