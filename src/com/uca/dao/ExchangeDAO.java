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


            statement = this.connect.prepareStatement("SELECT id FROM echange WHERE pokemon_id=? AND state=0;");

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

        try {
            PreparedStatement statement = this.connect.prepareStatement("DELETE FROM echange;");

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isPokemonExchangeable(int pokemon_id){

        try {
            PreparedStatement statement = this.connect.prepareStatement("SELECT id FROM echange WHERE pokemon_id=? AND state=0;");

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


            PreparedStatement statement = this.connect.prepareStatement("SELECT * FROM echange WHERE pokemon_id=? AND state=0;");
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

    public ExchangeEntity getExchangeFromId(int id){

        ExchangeEntity exchange = new ExchangeEntity();

        try {


            PreparedStatement statement = this.connect.prepareStatement("SELECT * FROM echange WHERE id=? AND state=0;");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            resultSet.next();

            exchange.setPokemon(PokemonCore.getPokemonById(resultSet.getInt("pokemon_id")));
            exchange.setId(id);
            exchange.setDate(resultSet.getTimestamp("date"));
            exchange.setState(resultSet.getInt("state"));
            exchange.setPokemonWanted(ExchangeWantedCore.getAllPokemonWanted(id));

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

                    PreparedStatement statement = this.connect.prepareStatement("SELECT * FROM echange WHERE pokemon_id=? AND state=0;");
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

            PreparedStatement statement = this.connect.prepareStatement("SELECT * FROM echange WHERE state=0;");
            ResultSet resultSet = statement.executeQuery();
            int i=0;
            while(resultSet.next()) {

                boolean available = true;

                for (PokemonEntity pokemon : pokemonList) {
                    System.out.println(pokemon.getId() + "/" + resultSet.getInt("pokemon_id"));
                    if (pokemon.getId() == resultSet.getInt("pokemon_id")) {
                        System.out.println("remove");
                        pokemonList.remove(pokemon);
                        available = false;
                    }
                }

                if (available) {

                    exchangeList.add(this.getExchangeFromPokemonId(resultSet.getInt("pokemon_id")));
                    System.out.println(i);
                    i++;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return exchangeList;
    }

    public int getUserIdFromExchange(int id){

        int userId = -1;

        try {


            PreparedStatement statement = this.connect.prepareStatement("SELECT pokemon_id FROM echange WHERE id=? AND state=0;");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            resultSet.next();

            userId = PokemonCore.getUserIdFromPokemon(resultSet.getInt("pokemon_id"));

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userId;
    }

    public int getPokemonIdFromExchange(int id){

        try {


            PreparedStatement statement = this.connect.prepareStatement("SELECT pokemon_id FROM echange WHERE id=? AND state=0;");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            resultSet.next();

            return resultSet.getInt("pokemon_id");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    public void cancelExchange(int id){

        try {


            PreparedStatement statement = this.connect.prepareStatement("UPDATE echange SET state=-1 WHERE id=?;");
            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void acceptExchange(int id, int userId, int pokemonId){

        try {

            PreparedStatement statement = this.connect.prepareStatement("SELECT pokemon_id FROM echange WHERE id=? AND state=0;");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            resultSet.next();

            int userId2 = PokemonCore.getUserIdFromPokemon(resultSet.getInt("pokemon_id"));
            System.out.println("userId2: " + userId2);
            System.out.println("pokemonId: " + pokemonId);
            PokemonCore.changeUser(resultSet.getInt("pokemon_id"), userId);
            PokemonCore.changeUser(pokemonId, userId2);

            statement = this.connect.prepareStatement("UPDATE echange SET state=1 WHERE id=?;");
            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
