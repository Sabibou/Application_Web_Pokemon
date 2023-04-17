package com.uca.core;


import com.uca.dao.ExchangeWantedDAO;
import com.uca.entity.ExchangeEntity;
import com.uca.entity.ExchangeWantedEntity;

public class ExchangeWantedCore {

    public static ExchangeWantedEntity create(ExchangeWantedEntity exchange){

        return new ExchangeWantedDAO().create(exchange);
    }
}
