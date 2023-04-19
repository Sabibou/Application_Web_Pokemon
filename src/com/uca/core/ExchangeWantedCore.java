package com.uca.core;


import com.uca.dao.ExchangeWantedDAO;
import com.uca.entity.ExchangeEntity;
import com.uca.entity.ExchangeWantedEntity;
import com.uca.entity.PokemonEntity;

import java.util.LinkedList;

public class ExchangeWantedCore {

    public static ExchangeWantedEntity create(ExchangeWantedEntity exchange){

        return new ExchangeWantedDAO().create(exchange);
    }

    public static LinkedList<PokemonEntity> getAllPokemonWanted(int exchange_id){

        return new ExchangeWantedDAO().getAllPokemonWanted(exchange_id);
    }

    public static void delete(ExchangeWantedEntity obj){

        new ExchangeWantedDAO().delete(obj);
    }
}
