package com.uca.dao;

import com.uca.entity.PokemonEntity;
import com.uca.entity.UserEntity;

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
    public PokemonEntity create(PokemonEntity pokemon) {

        return null;
    }

    @Override
    public void delete(PokemonEntity obj) {
        //TODO !
    }
}
