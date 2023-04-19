package com.uca.gui;

import com.uca.core.ExchangeCore;
import com.uca.core.PokemonCore;
import com.uca.core.UserCore;
import com.uca.entity.PokemonEntity;
import com.uca.entity.UserEntity;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class PokemonGUI {

    public static String getPokemonById(int mainUserId, int id) throws IOException, TemplateException {
        Configuration configuration = _FreeMarkerInitializer.getContext();

        Map<String, Object> input = new HashMap<>();

        PokemonEntity pokemon = PokemonCore.getPokemonById(id);

        if(pokemon == null){

            System.out.println("null");
        }

        input.put("users", UserCore.getAllUsers());
        input.put("main_user", UserCore.getUserById(mainUserId));
        input.put("pokemon", pokemon);
        input.put("isExchangeable", ExchangeCore.isPokemonExchangeable(pokemon.getId()));

        Writer output = new StringWriter();
        Template template = configuration.getTemplate("pokemons/pokemon.ftl");
        template.setOutputEncoding("UTF-8");
        template.process(input, output);

        return output.toString();
    }
}
