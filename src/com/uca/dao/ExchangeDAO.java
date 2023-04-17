package com.uca.dao;

import com.uca.entity.ExchangeEntity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ExchangeDAO extends _Generic<ExchangeEntity>{

    @Override
    public ExchangeEntity create(ExchangeEntity exchange) {

        try {
            PreparedStatement statement = this.connect.prepareStatement("INSERT INTO echange (pokemon_id) VALUES (?);");

            statement.setInt(1, exchange.getPokemonId());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void delete(ExchangeEntity obj) {

    }
}
