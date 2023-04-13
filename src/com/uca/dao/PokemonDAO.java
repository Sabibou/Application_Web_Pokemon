package com.uca.dao;

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

                entities.add(entity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return entities;
    }

    @Override
    public PokemonEntity create(PokemonEntity pokemon){

        try {
            PreparedStatement statement = this.connect.prepareStatement("INSERT INTO pokemon (id_pokedex, name, sprite, user_id) VALUES (?, ?, ?, ?);");

            statement.setInt(1, pokemon.getPokedexId());
            statement.setString(2, pokemon.getName());
            statement.setString(3, pokemon.getSprite());
            statement.setInt(4, pokemon.getUserId());

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
