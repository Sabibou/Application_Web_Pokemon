package com.uca.entity;

import java.sql.Timestamp;

public class PokemonEntity {
    private String name;
    private int id;
    private int pokedexId;
    private int level;
    private UserEntity user; // Composition
    private ExchangeEntity exchange;
    private String sprite;
    private int userId;

    public PokemonEntity(String name, int pokedexId, UserEntity user, String sprite) {
        this.name = name;
        this.pokedexId = pokedexId;
        this.user = user;
        this.sprite = sprite;
        this.level = 1;
    }
        
    public String getName(){
        return name;
    }
    
    public void setName(String name){
        this.name = name;    
    }

    public int getId(){

        return this.id;
    }

    public void setId(int id){

        this.id = id;
    }

    public int getPokedexId(){

        return this.pokedexId;
    }

    public void setPokedexId(int id){

        this.pokedexId = id;
    }

    public void setUserId(int id){

        this.userId = id;
    }

    public int getUserId(){

        return this.userId;
    }

    public void lvlUp(){

        if(this.level < 100){

            this.level++;
        }
    }
}