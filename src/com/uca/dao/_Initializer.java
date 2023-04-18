package com.uca.dao;

import java.sql.*;

public class _Initializer {

    public static void Init(){
        Connection connection = _Connector.getInstance();

        try {
            PreparedStatement statement;

            //Init articles table
            statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS joueur (id int primary key auto_increment, pseudo varchar(64) unique, password varchar(255), last_connection timestamp, nb_pok_xp int); ");
            statement.executeUpdate();
            statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS pokemon (id int primary key auto_increment, id_pokedex int, level int, name varchar(64), sprite varchar(255), user_id int not null, foreign key (user_id) references joueur(id)); ");
            statement.executeUpdate();
            statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS echange (id int primary key auto_increment, date timestamp DEFAULT CURRENT_TIMESTAMP, state int DEFAULT 0, pokemon_id int not null, foreign key (pokemon_id) references pokemon(id)); ");
            statement.executeUpdate();
            statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS echangeVoulu (id_echange int, id_pokedex int, primary key(id_echange, id_pokedex), foreign key (id_echange) references echange(id)); ");
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
