package com.uca;

import com.uca.core.PokemonCore;
import com.uca.core.UserCore;
import com.uca.dao._Initializer;
import com.uca.entity.UserEntity;
import com.uca.gui.*;

import java.sql.Timestamp;

import static spark.Spark.*;

public class StartServer{

    public static void main(String[] args) {

        //staticFiles.location("../resources/views");

        //Configure Spark
        staticFiles.location("/static/");
        port(8081);


        _Initializer.Init();

        //Defining our routes
        get("/users", (req, res) -> {
            return UserGUI.getAllUsers();
        });

        get("/register", (req, res) -> {

            res.redirect("register.html");
            return null;
        });

        post("/register", (req, res) -> {

            UserEntity user = UserCore.createNewUser(req.queryParams("username"), req.queryParams("userpwd"), new Timestamp(System.currentTimeMillis()));
            PokemonCore.createNewPokemon(1 + (int)(Math.random() * ((1000 - 1))), user.getId());

            return UserGUI.getUserById(user.getId());
        });

        get("/login", (req, res) -> {

            res.redirect("login.html");
            return null;
        });

        post("/login", (req, res) -> {

            UserEntity user = UserCore.getUserByPseudo(req.queryParams("username"));
            if(user == null){

                return "Mauvais nom d'utilisateur";
            }
            else{
                if(UserCore.verifyPassword(user.getPassword(), req.queryParams("userpwd"))){

                    Timestamp newT = new Timestamp(System.currentTimeMillis());

                    if(UserCore.isOtherDay(newT, user.getId())){

                        PokemonCore.createNewPokemon(1 + (int)(Math.random() * ((1000 - 1))), user.getId());
                        UserCore.resetNbXp(user.getId());
                    }

                    UserCore.setNewConnection(newT, user.getId());

                    return UserGUI.getUser(user);
                }
                else{

                    return "Mauvais mdp";
                }
            }
        });

        get("/:user_id", (req, res) -> {

            System.out.println("a" + Integer.parseInt(req.params("user_id")));
            return UserGUI.getUserById(Integer.parseInt(req.params("user_id")));
        });

        post("/:pokemon_id/lvl_up", (req, res) -> {
            int user_id = PokemonCore.getUserIdFromPokemon(Integer.parseInt(req.params("pokemon_id")));
            System.out.println("pok_id=" + req.params("pokemon_id"));
            if(PokemonCore.lvlUp(Integer.parseInt(req.params(":pokemon_id")), user_id) > 0){
                System.out.println("lvlup");
                UserCore.lvlUp(user_id);
            }

            return UserGUI.getUserById(user_id);
        });

    }
}