package com.uca.dao;

import java.sql.*;

public class _Initializer {

    public static void Init(){
        Connection connection = _Connector.getInstance();

        try {
            PreparedStatement statement;

            //Init articles table
            statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS joueur (id int primary key auto_increment, pseudo varchar(64) unique, password varchar(255), last_connection timestamp, nb_pok_xp int); ");
            statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS pokemon (id int primary key auto_increment, if_pokedex int, level int, name varchar(64), sprite varchar(255), user_id references joueur.id NOT NULL); ");
            statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS echange (id int primary key auto_increment, pokemon_id int references pokemon.id NOT NULL, date timestamp DEFAULT NOW(), state int DEFAULT O); ");
            statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS echange_voulu (id_echange int references echange.id, id_pokedex int); ");
            statement.executeUpdate();

            //Todo Remove me !
            //statement = connection.prepareStatement("INSERT INTO users(firstname, lastname) VALUES(?, ?);");
            //statement.setString(1, "Theodore");
            //statement.setString(2, "Muillerez");
            //statement.executeUpdate();

        } catch (Exception e){
            System.out.println(e.toString());
            throw new RuntimeException("could not create database !");
        }
    }
}
