package com.uca;

import com.uca.core.ExchangeCore;
import com.uca.core.ExchangeWantedCore;
import com.uca.core.PokemonCore;
import com.uca.core.UserCore;
import com.uca.dao._Initializer;
import com.uca.entity.ExchangeEntity;
import com.uca.entity.ExchangeWantedEntity;
import com.uca.entity.PokemonEntity;
import com.uca.entity.UserEntity;
import com.uca.gui.*;
import spark.Request;
import spark.Response;

import java.sql.Timestamp;

import static spark.Spark.*;

public class StartServer{

    public static void isConnected(Request req, Response res){

        if(!req.cookies().containsKey("USER_ID")){

            res.redirect("/", 303);
        }
    }

    public static void doesUserExist(Request req, Response res){

        if(!UserCore.doesUserExist(Integer.parseInt(req.params("user_id")))){

            res.redirect(null, 404);
        }
    }

    public static void doesExchangeExist(Request req, Response res){

        if(!ExchangeCore.doesExchangeExist(Integer.parseInt(req.params("echange_id")))){

            res.redirect(null, 404);
        }
    }

    public static void doesPokemonExist(Request req, Response res){

        if(!PokemonCore.doesPokemonExist(Integer.parseInt(req.params("pokemon_id")))){

            res.redirect(null, 404);
        }
    }

    public static void main(String[] args) {

        //Configure Spark
        staticFiles.location("/static/");
        port(8081);


        _Initializer.Init();


        //Defining our routes

        get("/", (req, res) -> {

            return IndexGUI.getIndexPage();
        });

        get("/users", (req, res) -> {

            StartServer.isConnected(req, res);

            return UserGUI.getAllUsersExceptMainUser(Integer.parseInt(req.cookie("USER_ID")));
        });

        get("/register", (req, res) -> {

            return RegisterGUI.getRegisterPage();
        });

        post("/register", (req, res) -> {

            UserEntity user = UserCore.createNewUser(req.queryParams("username"), req.queryParams("userpwd"), new Timestamp(System.currentTimeMillis()));
            PokemonCore.createNewPokemon(1 + (int)(Math.random() * ((1000 - 1))), user.getId());

            res.redirect("/" + user.getId());
            return null;
        });

        get("/login", (req, res) -> {

            return LoginGUI.getLoginPage(true, true);
        });

        post("/login", (req, res) -> {

            UserEntity user = UserCore.getUserByPseudo(req.queryParams("username"));

            if(user == null){

                return LoginGUI.getLoginPage(false, true);
            }
            else{
                if(UserCore.verifyPassword(user.getPassword(), req.queryParams("userpwd"))){

                    Timestamp newT = new Timestamp(System.currentTimeMillis());

                    if(UserCore.isOtherDay(newT, user.getId())){

                        PokemonCore.createNewPokemon(1 + (int)(Math.random() * ((1000 - 1))), user.getId());
                        UserCore.resetNbXp(user.getId());
                    }

                    UserCore.setNewConnection(newT, user.getId());

                    res.cookie("USER_ID", Integer.toString(user.getId()),10000, true);
                    res.redirect("/users/" + user.getId(), 303);
                    return null;
                }
                else{

                    return LoginGUI.getLoginPage(true, false);
                }
            }
        });

        get("/users/:user_id", (req, res) -> {

            StartServer.isConnected(req, res);

            int userId = Integer.parseInt(req.params("user_id"));

            StartServer.doesUserExist(req, res);

            return UserGUI.getUserById(Integer.parseInt(req.cookie("USER_ID")), userId);
        });

        post("/pokemon/:pokemon_id/lvl_up", (req, res) -> {

            StartServer.isConnected(req, res);

            StartServer.doesPokemonExist(req, res);

            PokemonEntity pokemon = PokemonCore.getPokemonById(Integer.parseInt(req.params("pokemon_id")));

            if(PokemonCore.lvlUp(pokemon.getId(), Integer.parseInt(req.cookie("USER_ID"))) > 0){

                    UserCore.lvlUp(pokemon.getUserId());
                    PokemonCore.isEvolving(pokemon.getPokedexId(), pokemon.getId(), pokemon.getLevel());

            }

            return UserGUI.getUserById(Integer.parseInt(req.cookie("USER_ID")), pokemon.getUserId());
        });

        get("/pokemon/:pokemon_id", (req, res) -> {

            StartServer.isConnected(req, res);

            StartServer.doesPokemonExist(req, res);

            return PokemonGUI.getPokemonById(Integer.parseInt(req.cookie("USER_ID")), Integer.parseInt(req.params("pokemon_id")));
        });

        post("/echanges/add/:pokemon_id", (req, res) -> {

            StartServer.isConnected(req, res);

            ExchangeEntity exchange = new ExchangeEntity();

            exchange.setPokemon(PokemonCore.getPokemonById(Integer.parseInt(req.params("pokemon_id"))));
            exchange.setDate(new Timestamp(System.currentTimeMillis()));

            exchange = ExchangeCore.create(exchange);

            ExchangeWantedEntity e = new ExchangeWantedEntity(exchange.getId(), PokemonCore.getPokemonFromAPIByName(req.queryParams("pokemonName")));

            ExchangeWantedCore.create(e);

            res.redirect("/pokemon/" + req.params("pokemon_id"));
            return null;
        });

        get("/echanges/all", (req, res) -> {

            StartServer.isConnected(req, res);
            return ExchangeGUI.getAllExchanges(Integer.parseInt(req.cookie("USER_ID")));
        });

        get("/echanges/:echange_id", (req, res) -> {

            StartServer.isConnected(req, res);

            StartServer.doesExchangeExist(req, res);

            return ExchangeGUI.getExchangeById(Integer.parseInt(req.cookie("USER_ID")), Integer.parseInt(req.params("echange_id")));
        });

        get("/echanges/delete/all", (req, res) -> {

            ExchangeWantedCore.delete(null);
            ExchangeCore.delete(null);
            return null;
        });

        get("/disconnect", (req, res) -> {
            res.removeCookie("USER_ID");
            res.redirect("/", 301);
            return "";
        });

        post("/echanges/:echange_id/cancel", (req, res) -> {

            StartServer.isConnected(req, res);

            ExchangeCore.cancelExchange(Integer.parseInt(req.params("echange_id")));

            res.redirect("/users/" + req.cookie("USER_ID"));
            return null;
        });

        post("/echanges/:echange_id/add", (req, res) -> {

            StartServer.isConnected(req, res);

            ExchangeWantedEntity e = new ExchangeWantedEntity(Integer.parseInt(req.params("echange_id")), PokemonCore.getPokemonFromAPIByName(req.queryParams("pokemonName")));

            ExchangeWantedCore.create(e);

            res.redirect("/echanges/" + req.params("echange_id"));
            return null;
        });

        post("/echanges/:echange_id/accept/:pokemon_id", (req, res) -> {

            StartServer.isConnected(req, res);

            int echangeId = Integer.parseInt(req.params("echange_id"));

            ExchangeCore.acceptExchange(echangeId, Integer.parseInt(req.cookie("USER_ID")), Integer.parseInt(req.params("pokemon_id")));

            res.redirect("/users/" + req.cookie("USER_ID"));
            return null;
        });

        post("/pokemons/:pokemon_id/rename", (req, res) -> {

            StartServer.isConnected(req, res);

            PokemonCore.rename(Integer.parseInt(req.params("pokemon_id")), req.queryParams("newName"));

            res.redirect("/pokemon/" + Integer.parseInt(req.params("pokemon_id")));
            return null;
        });

        get("/pokedex", (req, res) -> {

            return PokemonGUI.getPokedex(Integer.parseInt(req.cookie("USER_ID")));
        });

    }
}