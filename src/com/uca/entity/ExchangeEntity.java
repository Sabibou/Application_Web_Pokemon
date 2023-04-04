package com.uca.entity;

import java.sql.Timestamp;
import java.util.LinkedList;

public class ExchangeEntity {
    
    private int id;
    private int pokemonId;
    private Timestamp date;
    private LinkedList<Integer> pokemonWanted;
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

    public int getPokemonxId(){

        return this.pokemonId;
    }

    public void setPokemonId(int id){

        this.pokemonId = id;
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
}
