package com.uca.entity;

import java.sql.Timestamp;

public class UserEntity {
    private int id;
    private String pseudo;
    private String password;
    private Date lastConnection;
    private int nbPokemonXP;
    private ExchangeEntity exchange;
    private PokemonEntity pokemon;

    public UserEntity() {
        //Ignored !
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
}
