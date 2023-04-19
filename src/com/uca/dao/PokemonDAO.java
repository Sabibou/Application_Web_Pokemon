package com.uca.dao;

import com.uca.core.PokemonCore;
import com.uca.core.UserCore;
import com.uca.entity.PokemonEntity;

import java.sql.*;
import java.util.LinkedList;

public class PokemonDAO extends _Generic<PokemonEntity>{

    public LinkedList<PokemonEntity> getAllPokemonByUser(int user_id) {

        LinkedList<PokemonEntity> entities = new LinkedList<>();
        try {

            PreparedStatement preparedStatement = this.connect.prepareStatement("SELECT * FROM pokemon WHERE user_id=?;");
            preparedStatement.setInt(1, user_id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                PokemonEntity entity = new PokemonEntity();
                entity.setId(resultSet.getInt("id"));
                entity.setUserId(user_id);
                entity.setPokedexId(resultSet.getInt("id_pokedex"));
                entity.setName(resultSet.getString("name"));
                entity.setSprite(resultSet.getString("sprite"));
                System.out.println("level = " + resultSet.getInt("level"));
                entity.setLevel(resultSet.getInt("level"));

                entities.add(entity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return entities;
    }

    public PokemonEntity getPokemonById (int id) {

        PokemonEntity pokemon = new PokemonEntity();
        try {

            PreparedStatement preparedStatement = this.connect.prepareStatement("SELECT * FROM pokemon WHERE id=?;");
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();

            pokemon.setId(resultSet.getInt("id"));
            pokemon.setUserId(resultSet.getInt("user_id"));
            pokemon.setPokedexId(resultSet.getInt("id_pokedex"));
            pokemon.setName(resultSet.getString("name"));
            pokemon.setSprite(resultSet.getString("sprite"));
            pokemon.setLevel(resultSet.getInt("level"));

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pokemon;
    }

    public int lvlUp(int id, int user_id){  //>0: reussi  0:lvl max  -1:echec

        try{

            if(UserCore.canLvlUp(user_id)){

                System.out.println("canLvl");
                PreparedStatement preparedStatement = this.connect.prepareStatement("UPDATE pokemon SET level=level+1 WHERE id=?;");
                preparedStatement.setInt(1, id);

                return preparedStatement.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    public int getUserIdFromPokemon(int id){

        try {
            PreparedStatement preparedStatement = this.connect.prepareStatement("SELECT user_id FROM pokemon WHERE id=?;");
            preparedStatement.setInt(1, id);

            ResultSet resultSet =  preparedStatement.executeQuery();

            resultSet.next();
            System.out.println(resultSet.getInt("user_id"));
            return resultSet.getInt("user_id");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    public void evolve(PokemonEntity pokemon, int pokemon_id){

        try {
            PreparedStatement preparedStatement = this.connect.prepareStatement("UPDATE pokemon SET name=?, id_pokedex=?, sprite=? WHERE id=?;");
            preparedStatement.setString(1, pokemon.getName());
            preparedStatement.setInt(2, pokemon.getPokedexId());
            preparedStatement.setString(3, pokemon.getSprite());
            preparedStatement.setInt(4, pokemon_id);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public PokemonEntity create(PokemonEntity pokemon){

        try {
            PreparedStatement statement = this.connect.prepareStatement("INSERT INTO pokemon (id_pokedex, name, sprite, user_id, level) VALUES (?, ?, ?, ?, ?);");

            statement.setInt(1, pokemon.getPokedexId());
            statement.setString(2, pokemon.getName());
            statement.setString(3, pokemon.getSprite());
            statement.setInt(4, pokemon.getUserId());
            statement.setInt(5, 0);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void delete(PokemonEntity obj) {
        //TODO !
    }
}
