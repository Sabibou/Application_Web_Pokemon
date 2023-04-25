package com.uca.gui;

import com.uca.core.ExchangeCore;
import com.uca.core.UserCore;
import com.uca.entity.UserEntity;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class UserGUI {

    public static String getAllUsersExceptMainUser(int userId) throws IOException, TemplateException {
        Configuration configuration = _FreeMarkerInitializer.getContext();

        Map<String, Object> input = new HashMap<>();

        input.put("users", UserCore.getAllUsersExceptMainUser(userId));
        input.put("main_user", UserCore.getUserById(userId));

        Writer output = new StringWriter();
        Template template = configuration.getTemplate("views/users/users.ftl");
        template.setOutputEncoding("UTF-8");
        template.process(input, output);

        return output.toString();
    }

    public static String getUser(UserEntity user) throws IOException, TemplateException {
        Configuration configuration = _FreeMarkerInitializer.getContext();

        Map<String, Object> input = new HashMap<>();


        input.put("users", UserCore.getAllUsersExceptMainUser(user.getId()));
        input.put("main_user", user);
        input.put("pokemons", user.getPokemon());

        Writer output = new StringWriter();
        Template template = configuration.getTemplate("views/users/users.ftl");
        template.setOutputEncoding("UTF-8");
        template.process(input, output);

        return output.toString();
    }

    public static String getUserById(int mainId, int id) throws IOException, TemplateException {
        Configuration configuration = _FreeMarkerInitializer.getContext();

        Map<String, Object> input = new HashMap<>();

        UserEntity user = UserCore.getUserById(id);

        if(user == null){

            System.out.println("null");
        }

        input.put("users", UserCore.getAllUsersExceptMainUser(mainId));
        input.put("main_user", UserCore.getUserById(mainId));
        input.put("user_page", user);
        input.put("pokemons", user.getPokemon());
        input.put("exchanges", ExchangeCore.getAllExchangesStartedByUser(user.getPokemon()));

        Writer output = new StringWriter();
        Template template = configuration.getTemplate("views/users/user.ftl");
        template.setOutputEncoding("UTF-8");
        template.process(input, output);

        return output.toString();
    }
}
