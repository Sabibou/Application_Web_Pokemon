package com.uca.dao;

import com.uca.core.PokemonCore;
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
                entity.setLastConnection(resultSet.getTimestamp("last_connection"));
                entity.setNbPokemonXP(resultSet.getInt("nb_pok_xp"));

                entities.add(entity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return entities;
    }

    public UserEntity getUserById(int id){

        try {
            PreparedStatement preparedStatement = this.connect.prepareStatement("SELECT * FROM joueur WHERE id=?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();

            UserEntity user = new UserEntity();

            user.setId(resultSet.getInt("id"));
            user.setPokemon(PokemonCore.getAllPokemonByUser(id));
            user.setPseudo(resultSet.getString("pseudo"));
            user.setLastConnection(new Timestamp(System.currentTimeMillis()));
            user.setPassword(resultSet.getString("password"));

            return user;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public UserEntity getUserByPseudo(String pseudo){

        try {
            PreparedStatement preparedStatement = this.connect.prepareStatement("SELECT * FROM joueur WHERE pseudo=?");
            preparedStatement.setString(1, pseudo);
            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();

            UserEntity user = new UserEntity();

            user.setId(resultSet.getInt("id"));
            user.setPokemon(PokemonCore.getAllPokemonByUser(resultSet.getInt("id")));
            user.setPseudo(resultSet.getString("pseudo"));
            user.setLastConnection(new Timestamp(System.currentTimeMillis()));
            user.setPassword(resultSet.getString("password"));
            user.setNbPokemonXP(resultSet.getInt("nb_pok_xp"));

            return user;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean verifyPassword(String password1, String password2){

        return password1.equals(password2);
    }

    public boolean canLvlUp(int user_id){

        try {
            PreparedStatement preparedStatement = this.connect.prepareStatement("SELECT nb_pok_xp FROM joueur WHERE id=?");
            preparedStatement.setInt(1, user_id);
            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();
            System.out.println(resultSet.getInt("nb_pok_xp"));
            if(resultSet.getInt("nb_pok_xp") < 5){
                System.out.println(resultSet.getInt("nb_pok_xp"));
                return true;

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public int lvlUp(int user_id){

        try {
            PreparedStatement preparedStatement = this.connect.prepareStatement("UPDATE joueur SET nb_pok_xp=nb_pok_xp+1 WHERE id=?");
            preparedStatement.setInt(1, user_id);
            return preparedStatement.executeUpdate();

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

            PreparedStatement preparedStatement = this.connect.prepareStatement("SELECT id FROM joueur WHERE pseudo=?");
            preparedStatement.setString(1, user.getPseudo());
            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();
            user.setId(resultSet.getInt("id"));

            return user;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean isOtherDay(Timestamp newT, int user_id){

        try {
            PreparedStatement preparedStatement = this.connect.prepareStatement("SELECT last_connection FROM joueur WHERE id=?");
            preparedStatement.setInt(1, user_id);
            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();

            Timestamp oldT = resultSet.getTimestamp("last_connection");
            System.out.println("new:" + newT);
            System.out.println("old:" + oldT);
            System.out.println(newT.getTime() - oldT.getTime());
            if(newT.getTime() - oldT.getTime() > 8640000){  //24h en millisecondes

                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public void setNewConnection(Timestamp newT, int user_id){

        try {
            PreparedStatement preparedStatement = this.connect.prepareStatement("UPDATE joueur SET last_connection=? WHERE id=?");
            preparedStatement.setTimestamp(1, newT);
            preparedStatement.setInt(2, user_id);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void resetNbXp(int user_id){

        try {
            PreparedStatement preparedStatement = this.connect.prepareStatement("UPDATE joueur SET nb_pok_xp=0 WHERE id=?");
            preparedStatement.setInt(1, user_id);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(UserEntity obj) {
        //TODO !
    }
}
