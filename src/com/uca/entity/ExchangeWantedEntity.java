package com.uca.entity;

public class ExchangeWantedEntity {

    private int id;
    private int pokedexId;

    public ExchangeWantedEntity(int id, int pokedexId){

        this.id = id;
        this.pokedexId = pokedexId;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPokedexId() {
        return pokedexId;
    }

    public void setPokedexId(int pokedexId) {
        this.pokedexId = pokedexId;
    }
}
