package com.uca.gui;

import com.uca.core.UserCore;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class LoginGUI {

    public static String getLoginPage(boolean username, boolean mdp) throws IOException, TemplateException {
        Configuration configuration = _FreeMarkerInitializer.getContext();

        Map<String, Object> input = new HashMap<>();

        if(!username){

            input.put("username", new Object());
        }
        if(!mdp){

            input.put("mdp", new Object());
        }


        Writer output = new StringWriter();
        Template template = configuration.getTemplate("views/login/login.ftl");
        template.setOutputEncoding("UTF-8");
        template.process(input, output);


        return output.toString();
    }
}
