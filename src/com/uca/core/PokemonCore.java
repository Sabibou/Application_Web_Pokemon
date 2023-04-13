package com.uca.core;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uca.dao.PokemonDAO;
import com.uca.entity.PokemonEntity;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class PokemonCore {

    public static void createNewPokemon(int id, int user_id) throws IOException {

        URL url = new URL("https://pokeapi.co/api/v2/pokemon-form/" + id);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = mapper.readTree(url);

        PokemonEntity pokemon = new PokemonEntity();

        pokemon.setSprite(String.valueOf(json.get("front_default")));
        pokemon.setPokedexId(id);
        pokemon.setName(String.valueOf(json.get("name")));
        pokemon.setUserId(user_id);

        new PokemonDAO().create(pokemon);
    }
}
