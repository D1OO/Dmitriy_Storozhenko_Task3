/**
 * ResourceBundleContainer
 * <p>
 * version 1.0
 * <p>
 * 20.02.2020
 * <p>
 * Copyright(r) shvdy.net
 */

package net.shvdy.controller;

import net.shvdy.view.View;

import java.util.ResourceBundle;

public class ViewController {
    public static final String WELCOME_MSG = "message.welcome";
    public static final String WRONG_INPUT_MSG = "message.input.wrong.data";
    public static final String INPUT_NEW_NOTE_MSG = "message.input.new.note";
    public static final String CHOOSE_ACTION_AFTER_NOTE_CREATED_MSG = "message.input.choose.action.note.created";
    public static final String INPUT_VALUE_FOR_MSG = "message.input.value.for";
    public static final String GOODBYE_MSG = "message.goodbye";
    public static final String AVAILABLE_GROUPS_MSG = "message.available.groups";
    public static final String CHOOSE_GROUPS_MSG = "message.choose.groups";
    public static final String EXISTING_VALUE_MSG = "message.existing_value";

    public ResourceBundle currentLocale;
    private View view;

    ViewController(Controller c) {
        this.view = c.getView();
    }

    void setLocale(ResourceBundle rb) {
        currentLocale = rb;
    }

    public void printMessage(String message) {
        view.printMessage(message);
    }

    public void printMessage(String... message) {
        view.printMessage(concatenateString(message));
    }

    public void printLocalisedMessage(String message) {
        view.printMessage(currentLocale.getString(message));
    }

    public String getPropertyLocalisation(String property) {
        return currentLocale.getString("data." + property);
    }

    public String getMessageLocalisation(String key) {
        return currentLocale.getString(key);
    }

    public String concatenateString(String... message) {
        StringBuilder concatString = new StringBuilder();
        for (String v : message) {
            concatString.append(v);
        }
        return new String(concatString);
    }

    public void printNotePropertyString(String name, String value) {
        printMessage("\t", getPropertyLocalisation(name), ": ", value);
    }
}
