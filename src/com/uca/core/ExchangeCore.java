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
}
