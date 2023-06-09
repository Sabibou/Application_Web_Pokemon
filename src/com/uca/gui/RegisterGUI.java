package com.uca.gui;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class RegisterGUI{

    public static String getRegisterPage(boolean pseudoExists) throws IOException, TemplateException{
        Configuration configuration = _FreeMarkerInitializer.getContext();

        Map<String, Object> input = new HashMap<>();

        if(pseudoExists){

            input.put("pseudoExists", new Object());
        }

        Writer output = new StringWriter();
        Template template = configuration.getTemplate("views/register/register.ftl");
        template.setOutputEncoding("UTF-8");
        template.process(input, output);

        return output.toString();
    }
}
