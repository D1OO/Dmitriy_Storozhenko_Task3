/**
 * View
 *
 * version 2
 *
 * 11.02.2020
 *
 * Copyright(r) shvdy
 */
package net.shvdy.view;

import java.util.ResourceBundle;

public class View implements TextConstant {
    ResourceBundle currentLocale;

    public void setCurrentLocale(ResourceBundle currentLocale) {
        this.currentLocale = currentLocale;
    }

    public void printMessage(String message) {
        System.out.println(message);
    }

    public void printLocalisedMessage(String message) {
        System.out.println(currentLocale.getString(message));
    }

    public void printMessage(String... message) {
        System.out.println(concatenationString(message));
    }

    public String concatenationString(String... message) {
        StringBuilder concatString = new StringBuilder();
        for (String v : message) {
            concatString.append(v);
        }
        return new String(concatString);
    }

    public String getPropertyLocalisation(String property) {
        return currentLocale.getString("data." + property);
    }

    public String getLocalisation(String key) {
        return currentLocale.getString(key);
    }

}
