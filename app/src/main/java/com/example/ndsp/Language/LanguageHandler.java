package com.example.ndsp.Language;

import android.content.res.Configuration;
import android.content.res.Resources;

import java.util.Locale;

public class LanguageHandler {

    public static void changeLocale(Resources res, String locale) {

        Configuration config;
        config = new Configuration(res.getConfiguration());

        switch (locale) {
            case "e":
                config.locale = Locale.ENGLISH;
                break;
            //Locale file for zawgyi create
            case "z":
                config.locale = new Locale("my");
                break;
            //Locale file for unicode create
            case "u":
                config.locale=new Locale("kw");
                break;
            default:
                config.locale = Locale.ENGLISH;
                break;
        }
        res.updateConfiguration(config, res.getDisplayMetrics());
    }

}
