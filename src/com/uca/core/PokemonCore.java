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

        return new PokemonDAO().getPokemonFromAPIById(pokedexId);
    }

    public static PokemonEntity getPokemonFromAPIByName(String name) throws IOException {

        return new PokemonDAO().getPokemonFromAPIByName(name);
    }

    public static void isEvolving(int pokedex_id, int id, int level) throws IOException {

        new PokemonDAO().isEvolving(pokedex_id, id, level);
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

    public static void rename(int pokemonId, String newName){

        new PokemonDAO().rename(pokemonId, newName);
    }

    public static boolean doesPokemonExist(int pokemonId){

        return new PokemonDAO().getPokemonById(pokemonId) != null;
    }

    public static String getPokedexDescription(int pokedex_id) throws IOException {

        return new PokemonDAO().getPokedexDescription(pokedex_id);
    }

    public static LinkedList<PokemonEntity> getPokedex(LinkedList<PokemonEntity> userPokemons) throws IOException{

        return new PokemonDAO().getPokedex(userPokemons);
    }

}
