package com.uca.dao;

import com.uca.core.ExchangeWantedCore;
import com.uca.core.PokemonCore;
import com.uca.entity.ExchangeEntity;
import com.uca.entity.PokemonEntity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class ExchangeDAO extends _Generic<ExchangeEntity>{

    @Override
    public ExchangeEntity create(ExchangeEntity exchange) {

        try {
            PreparedStatement statement = this.connect.prepareStatement("INSERT INTO echange (pokemon_id) VALUES (?);");

            statement.setInt(1, exchange.getPokemon().getId());

            statement.executeUpdate();


            statement = this.connect.prepareStatement("SELECT id FROM echange WHERE pokemon_id=?;");

            statement.setInt(1, exchange.getPokemon().getId());

            ResultSet resultSet = statement.executeQuery();

            resultSet.next();
            System.out.println(resultSet.getInt("id"));
            exchange.setId(resultSet.getInt("id"));

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return exchange;
    }

    @Override
    public void delete(ExchangeEntity obj) {

    }

    public boolean isPokemonExchangeable(int pokemon_id){

        try {
            PreparedStatement statement = this.connect.prepareStatement("SELECT id FROM echange WHERE pokemon_id=?;");

            statement.setInt(1, pokemon_id);

            ResultSet resultSet = statement.executeQuery();


            return resultSet.next();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public ExchangeEntity getExchangeFromPokemonId(int pokemon_id){

        ExchangeEntity exchange = new ExchangeEntity();

        try {


            PreparedStatement statement = this.connect.prepareStatement("SELECT * FROM echange WHERE pokemon_id=?;");
            statement.setInt(1, pokemon_id);
            ResultSet resultSet = statement.executeQuery();

            resultSet.next();

            exchange.setPokemon(PokemonCore.getPokemonById(pokemon_id));
            exchange.setId(resultSet.getInt("id"));
            exchange.setDate(resultSet.getTimestamp("date"));
            exchange.setState(resultSet.getInt("state"));
            exchange.setPokemonWanted(ExchangeWantedCore.getAllPokemonWanted(resultSet.getInt("id")));

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return exchange;
    }

    public LinkedList<ExchangeEntity> getAllExchangesStartedByUser(LinkedList<PokemonEntity> pokemonList){

        LinkedList<ExchangeEntity> exchangeList = new LinkedList<>();

        try {

            for(PokemonEntity pokemon : pokemonList){

                if(this.isPokemonExchangeable(pokemon.getId())){

                    PreparedStatement statement = this.connect.prepareStatement("SELECT * FROM echange WHERE pokemon_id=?;");
                    statement.setInt(1, pokemon.getId());
                    ResultSet resultSet = statement.executeQuery();

                    resultSet.next();

                    ExchangeEntity exchange = this.getExchangeFromPokemonId(pokemon.getId());
                    exchangeList.add(exchange);
                }
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return exchangeList;
    }

    public LinkedList<ExchangeEntity> getAllExchangesAvailableForUser(LinkedList<PokemonEntity> pokemonList){

        LinkedList<ExchangeEntity> exchangeList = new LinkedList<>();

        try {

            PreparedStatement statement = this.connect.prepareStatement("SELECT * FROM echange;");
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()) {

                boolean available = true;

                for (PokemonEntity pokemon : pokemonList) {

                    if (pokemon.getId() == resultSet.getInt("pokemon_id")) {

                        pokemonList.remove(pokemon);
                        available = false;
                    }
                }

                if (available) {

                    exchangeList.add(this.getExchangeFromPokemonId(resultSet.getInt("pokemon_id")));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return exchangeList;
    }
}
