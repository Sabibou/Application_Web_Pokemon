package com.uca;

import com.uca.core.UserCore;
import com.uca.dao._Initializer;
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

            UserCore.createNewUser(req.queryParams("username"), req.queryParams("userpwd"), new Timestamp(System.currentTimeMillis()));
            return null;
        });

        get("/login", (req, res) -> {

            res.redirect("login.html");
            return null;
        });

        post("/login", (req, res) -> {

            int id = UserCore.getIdFromPseudo(req.queryParams("username"));
            return UserGUI.getUserById(id);
        });

        get("/user-:id", (req, res) -> {

            return UserGUI.getUserById(Integer.parseInt(req.params(":id")));
        });
    }
}