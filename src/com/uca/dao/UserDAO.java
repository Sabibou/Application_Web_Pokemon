package com.uca.dao;

import com.uca.entity.UserEntity;

import java.sql.*;
import java.util.ArrayList;

public class UserDAO extends _Generic<UserEntity> {

    public ArrayList<UserEntity> getAllUsers() {
        ArrayList<UserEntity> entities = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = this.connect.prepareStatement("SELECT * FROM joueur ORDER BY id ASC;");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                UserEntity entity = new UserEntity();
                entity.setId(resultSet.getInt("id"));
                entity.setPseudo(resultSet.getString("pseudo"));
                entity.setPassword(resultSet.getString("password"));

                entities.add(entity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return entities;
    }

    public UserEntity getUserById(int id){

        UserEntity user = new UserEntity();

        try {
            PreparedStatement preparedStatement = this.connect.prepareStatement("SELECT * FROM joueur WHERE id = ?;");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();

            user.setId(resultSet.getInt("id"));
            user.setPokemon(new PokemonDAO().getAllPokemonByUser(id));
            user.setPseudo(resultSet.getString("pseudo"));
            user.setLastConnection(new Timestamp(System.currentTimeMillis()));
            user.setPassword(resultSet.getString("password"));

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public int getIdFromPseudo(String pseudo){

        try {
            PreparedStatement preparedStatement = this.connect.prepareStatement("SELECT id FROM joueur WHERE pseudo=?");
            preparedStatement.setString(1, pseudo);
            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();

            return resultSet.getInt("id");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    @Override
    public UserEntity create(UserEntity user) {

        try {
            PreparedStatement statement = this.connect.prepareStatement("INSERT INTO joueur (pseudo, password, last_connection, nb_pok_xp) VALUES (?, ?, ?, ?);");

            statement.setString(1, user.getPseudo());
            statement.setString(2, user.getPassword());
            statement.setTimestamp(3, user.getLastConnection());
            statement.setInt(4, 0);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void delete(UserEntity obj) {
        //TODO !
    }
}
