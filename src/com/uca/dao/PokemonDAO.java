package com.uca.dao;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uca.core.PokemonCore;
import com.uca.core.UserCore;
import com.uca.entity.PokemonEntity;

import java.io.IOException;
import java.net.URL;
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
                entity.setLevel(resultSet.getInt("level"));

                entities.add(entity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return entities;
    }

    public PokemonEntity getPokemonById (int id) {

        try {

            PreparedStatement preparedStatement = this.connect.prepareStatement("SELECT * FROM pokemon WHERE id=?;");
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();

            PokemonEntity pokemon = new PokemonEntity();

            pokemon.setId(resultSet.getInt("id"));
            pokemon.setUserId(resultSet.getInt("user_id"));
            pokemon.setPokedexId(resultSet.getInt("id_pokedex"));
            pokemon.setName(resultSet.getString("name"));
            pokemon.setSprite(resultSet.getString("sprite"));
            pokemon.setLevel(resultSet.getInt("level"));

            return pokemon;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public int lvlUp(int id, int user_id){  //>0: reussi  0:lvl max  -1:echec

        try{

            if(UserCore.canLvlUp(user_id)){

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

    public void changeUser(int pokemonId, int newUserId){

        try {
            PreparedStatement statement = this.connect.prepareStatement("UPDATE pokemon SET user_id=? WHERE id=?;");

            statement.setInt(1, newUserId);
            statement.setInt(2, pokemonId);

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void rename(int pokemonId, String newName){

        try {
            PreparedStatement statement = this.connect.prepareStatement("UPDATE pokemon SET name=? WHERE id=?;");

            statement.setString(1, newName);
            statement.setInt(2, pokemonId);

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public PokemonEntity getPokemonFromAPIById(int pokedexId) throws IOException {

        URL url = new URL("https://pokeapi.co/api/v2/pokemon-form/" + pokedexId);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = mapper.readTree(url);

        PokemonEntity pokemon = new PokemonEntity();

        pokemon.setSprite(String.valueOf(json.get("sprites").get("front_default")));
        pokemon.setPokedexId(pokedexId);
        pokemon.setName(String.valueOf(json.get("name")));

        return pokemon;
    }

    public PokemonEntity getPokemonFromAPIByName(String name) throws IOException {

        URL url = new URL("https://pokeapi.co/api/v2/pokemon-form/" + name);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = mapper.readTree(url);

        PokemonEntity pokemon = new PokemonEntity();

        pokemon.setSprite(String.valueOf(json.get("sprites").get("front_default")));
        pokemon.setPokedexId(Integer.parseInt(String.valueOf(json.get("id"))));
        pokemon.setName(name);

        return pokemon;
    }

    public void isEvolving(int pokedex_id, int id, int level) throws IOException {

        URL url = new URL("https://pokeapi.co/api/v2/pokemon-species/" + (pokedex_id+1));
        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = mapper.readTree(url);

        PokemonEntity pokemon = PokemonCore.getPokemonFromAPIById(pokedex_id);

        int levelToGet = pokemon.getLevel() >= 14 ? 30 : 15;

        if(String.valueOf(json.get("evolves_from_species").get("name")).equals(pokemon.getName()) && level >= levelToGet){

            pokemon = PokemonCore.getPokemonFromAPIById(pokedex_id+1);
            new PokemonDAO().evolve(pokemon, id);
        }
    }
}
