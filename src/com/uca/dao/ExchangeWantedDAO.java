package com.uca.dao;

import com.uca.entity.ExchangeWantedEntity;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ExchangeWantedDAO extends _Generic<ExchangeWantedEntity>{
    @Override
    public ExchangeWantedEntity create(ExchangeWantedEntity obj) {

        try {

            PreparedStatement statement = this.connect.prepareStatement("INSERT INTO echangeVoulu (id_echange, id_pokedex) VALUES (?, ?);");

            statement.setInt(1, obj.getId());
            statement.setInt(2, obj.getPokedexId());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void delete(ExchangeWantedEntity obj) {

    }
}
