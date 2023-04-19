package com.uca.dao;

import com.uca.core.PokemonCore;
import com.uca.entity.ExchangeWantedEntity;
import com.uca.entity.PokemonEntity;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class ExchangeWantedDAO extends _Generic<ExchangeWantedEntity>{
    @Override
    public ExchangeWantedEntity create(ExchangeWantedEntity obj) {

        try {

            PreparedStatement statement = this.connect.prepareStatement("INSERT INTO echangeVoulu (id_echange, id_pokedex) VALUES (?, ?);");

            statement.setInt(1, obj.getId());
            statement.setInt(2, obj.getPokemon().getPokedexId());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public LinkedList<PokemonEntity> getAllPokemonWanted(int exchange_id){

        LinkedList<PokemonEntity> pokemonList = new LinkedList<>();

        try {

            PreparedStatement statement = this.connect.prepareStatement("SELECT id_pokedex FROM echangeVoulu WHERE id_echange=?;");
            statement.setInt(1, exchange_id);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){

                PokemonEntity pokemon = PokemonCore.getPokemonFromAPIById(resultSet.getInt("id_pokedex"));
                pokemonList.add(pokemon);
            }

        } catch (SQLException e) {
            e.printStackTrace();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return pokemonList;
    }

    @Override
    public void delete(ExchangeWantedEntity obj) {

        try {
            PreparedStatement statement = this.connect.prepareStatement("DELETE FROM echangeVoulu;");

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
