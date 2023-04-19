package com.uca.core;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uca.dao.PokemonDAO;
import com.uca.entity.PokemonEntity;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;

public class PokemonCore {

    public static LinkedList<PokemonEntity> getAllPokemonByUser(int user_id){

        return new PokemonDAO().getAllPokemonByUser(user_id);
    }

    private static PokemonEntity getPokemonFromAPI(int pokedexId) throws IOException {

        URL url = new URL("https://pokeapi.co/api/v2/pokemon-form/" + pokedexId);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = mapper.readTree(url);

        PokemonEntity pokemon = new PokemonEntity();

        pokemon.setSprite(String.valueOf(json.get("sprites").get("front_default")));
        pokemon.setPokedexId(pokedexId);
        pokemon.setName(String.valueOf(json.get("name")));

        return pokemon;
    }
    public static void createNewPokemon(int pokedexId, int user_id) throws IOException {

        PokemonEntity pokemon = PokemonCore.getPokemonFromAPI(pokedexId);
        pokemon.setUserId(user_id);

        new PokemonDAO().create(pokemon);
    }

    public static PokemonEntity getPokemonByPokedexId(int pokedexId) throws IOException {

        return PokemonCore.getPokemonFromAPI(pokedexId);
    }

    public static PokemonEntity getPokemonById(int id){

        return new PokemonDAO().getPokemonById(id);
    }

    public static int getUserIdFromPokemon(int id){

        return new PokemonDAO().getUserIdFromPokemon(id);
    }

    public static int lvlUp(int id, int user_id){

        return new PokemonDAO().lvlUp(id, user_id);
    }


}
