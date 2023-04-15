package com.uca.entity;

import java.sql.Timestamp;
import java.util.LinkedList;

import com.uca.entity.PokemonEntity;

public class UserEntity {
    private int id;
    private String pseudo;
    private String password;
    private Timestamp lastConnection;
    private int nbPokemonXP;
    private LinkedList<ExchangeEntity> exchange;
    private LinkedList<PokemonEntity> pokemon;

    public UserEntity() {

        this.nbPokemonXP = 0;
        this.exchange = new LinkedList<>();
        this.pokemon = new LinkedList<>();
    }

    public UserEntity(String pseudo, String pwd, Timestamp t){

        this();
        this.pseudo = pseudo;
        this.password = pwd;
        this.lastConnection = t;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getLastConnection(){

        return this.lastConnection;
    }

    public void setLastConnection(Timestamp t){

        this.lastConnection = t;
    }

    public void setPokemon(LinkedList<PokemonEntity> pokemon){

        this.pokemon = pokemon;
    }

    public void setExchange(LinkedList<ExchangeEntity> exchange){

        this.exchange = exchange;
    }

    public ExchangeEntity getExchange(int index){

        return this.exchange.get(index);
    }

    public PokemonEntity getPokemon(int index){

        return this.pokemon.get(index);
    }

    public LinkedList<PokemonEntity> getPokemon(){

        return this.pokemon;
    }

    public int getNbPokemonXP() {
        return nbPokemonXP;
    }

    public void setNbPokemonXP(int nbPokemonXP) {
        this.nbPokemonXP = nbPokemonXP;
    }
}
