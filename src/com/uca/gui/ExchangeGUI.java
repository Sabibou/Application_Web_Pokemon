package com.uca.gui;

import com.uca.core.ExchangeCore;
import com.uca.core.PokemonCore;
import com.uca.core.UserCore;
import com.uca.entity.ExchangeEntity;
import com.uca.entity.PokemonEntity;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class ExchangeGUI{

    public static String getExchangeById(int id) throws IOException, TemplateException {
        Configuration configuration = _FreeMarkerInitializer.getContext();

        Map<String, Object> input = new HashMap<>();

        ExchangeEntity exchange = ExchangeCore.getExchangeFromId(id);

        input.put("main_user", UserCore.getUserById(exchange.getPokemon().getUserId()));
        input.put("exchange", exchange);
        input.put("pokemon", exchange.getPokemon());
        input.put("pokemonsWanted", exchange.getPokemonWanted());

        Writer output = new StringWriter();
        Template template = configuration.getTemplate("exchanges/exchange.ftl");
        template.setOutputEncoding("UTF-8");
        template.process(input, output);

        return output.toString();
    }
}
