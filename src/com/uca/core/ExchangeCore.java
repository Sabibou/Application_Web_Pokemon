package com.uca.core;

import com.uca.dao.ExchangeDAO;
import com.uca.entity.ExchangeEntity;

public class ExchangeCore {

    public static ExchangeEntity create(ExchangeEntity exchange){

        return new ExchangeDAO().create(exchange);
    }
}
