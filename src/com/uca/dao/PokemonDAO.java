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

    public int lvlUp(int id){  //>0: reussi  0:lvl max  -1:echec

        try {
            PreparedStatement preparedStatement = this.connect.prepareStatement("SELECT user_id FROM pokemon WHERE id=?;");
            preparedStatement.setInt(1, id);

            ResultSet resultSet =  preparedStatement.executeQuery();

            resultSet.next();

            if(UserCore.canLvlUp(resultSet.getInt("user_id"))){
                System.out.println("canLvl");
                preparedStatement = this.connect.prepareStatement("UPDATE pokemon set level=level+1 WHERE id=?;");
                preparedStatement.setInt(1, id);

                return preparedStatement.executeUpdate();
            }

            return 0;


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    public int lvlUp(int id, int user_id){  //>0: reussi  0:lvl max  -1:echec

        try{

            if(UserCore.canLvlUp(user_id)){
                System.out.println("canLvl");
                PreparedStatement preparedStatement = this.connect.prepareStatement("UPDATE pokemon set level=level+1 WHERE id=?;");
                preparedStatement.setInt(1, id);

                return preparedStatement.executeUpdate();
            }

            return 0;


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
