package com.uca.core;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uca.dao.PokemonDAO;
import com.uca.entity.PokemonEntity;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;

public class PokemonCore {

    public static LinkedList<PokemonEntity> getAllPokemonByUser(int user_id){

        return new PokemonDAO().getAllPokemonByUser(user_id);
    }

    public static PokemonEntity getPokemonFromAPIById(int pokedexId) throws IOException {

        URL url = new URL("https://pokeapi.co/api/v2/pokemon-form/" + pokedexId);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = mapper.readTree(url);

        PokemonEntity pokemon = new PokemonEntity();

        pokemon.setSprite(String.valueOf(json.get("sprites").get("front_default")));
        pokemon.setPokedexId(pokedexId);
        pokemon.setName(String.valueOf(json.get("name")));

        return pokemon;
    }

    public static PokemonEntity getPokemonFromAPIByName(String name) throws IOException {

        URL url = new URL("https://pokeapi.co/api/v2/pokemon-form/" + name);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = mapper.readTree(url);

        PokemonEntity pokemon = new PokemonEntity();

        pokemon.setSprite(String.valueOf(json.get("sprites").get("front_default")));
        pokemon.setPokedexId(Integer.parseInt(String.valueOf(json.get("id"))));
        pokemon.setName(name);

        return pokemon;
    }

    public static void isEvolving(int pokedex_id, int id, int level) throws IOException {

        URL url = new URL("https://pokeapi.co/api/v2/pokemon-species/" + (pokedex_id+1));
        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = mapper.readTree(url);

        PokemonEntity pokemon = PokemonCore.getPokemonFromAPIById(pokedex_id);

        int levelToGet = pokemon.getLevel() > 15 ? 30 : 15;

        if(String.valueOf(json.get("evolves_from_species").get("name")).equals(pokemon.getName()) && level >= levelToGet){

            pokemon = PokemonCore.getPokemonFromAPIById(pokedex_id+1);
            new PokemonDAO().evolve(pokemon, id);
        }
    }
    public static void createNewPokemon(int pokedexId, int user_id) throws IOException {

        PokemonEntity pokemon = PokemonCore.getPokemonFromAPIById(pokedexId);
        pokemon.setUserId(user_id);

        new PokemonDAO().create(pokemon);
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

    public static void changeUser(int pokemonId, int newUserId){

        new PokemonDAO().changeUser(pokemonId, newUserId);
    }


}
