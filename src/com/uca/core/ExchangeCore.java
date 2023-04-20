package com.uca.core;

import com.uca.dao.ExchangeDAO;
import com.uca.dao.PokemonDAO;
import com.uca.entity.ExchangeEntity;
import com.uca.entity.PokemonEntity;

import java.util.LinkedList;

public class ExchangeCore {

    public static ExchangeEntity create(ExchangeEntity exchange){

        return new ExchangeDAO().create(exchange);
    }


    public static ExchangeEntity getExchangeFromPokemonId(int pokemon_id){

        return new ExchangeDAO().getExchangeFromPokemonId(pokemon_id);
    }

    public static ExchangeEntity getExchangeFromId(int id){

        return new ExchangeDAO().getExchangeFromId(id);
    }

    public static LinkedList<ExchangeEntity> getAllExchangesStartedByUser(LinkedList<PokemonEntity> pokemonList){

        return new ExchangeDAO().getAllExchangesStartedByUser(pokemonList);
    }

    public static LinkedList<ExchangeEntity> getAllExchangesAvailableForUser(LinkedList<PokemonEntity> pokemonList){

        return new ExchangeDAO().getAllExchangesAvailableForUser(pokemonList);
    }

    public static boolean isPokemonExchangeable(int pokemonId){

        return new ExchangeDAO().isPokemonExchangeable(pokemonId);
    }

    public static void delete(ExchangeEntity obj){

        new ExchangeDAO().delete(obj);
    }

    public static void cancelExchange(int id){

        new ExchangeDAO().cancelExchange(id);
    }

    public static int getUserIdFromExchange(int id){

        return new ExchangeDAO().getUserIdFromExchange(id);
    }

    public static int getPokemonIdFromExchange(int id){

        return new ExchangeDAO().getPokemonIdFromExchange(id);
    }

    public static void acceptExchange(int id, int userId, int pokemonId){

        new ExchangeDAO().acceptExchange(id, userId, pokemonId);
    }
}
