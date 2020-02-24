/**
 * Note
 * <p>
 * version 2
 * <p>
 * 11.02.2020
 * <p>
 * Copyright(r) shvdy
 */
package net.shvdy.model;

import java.util.HashSet;
import java.util.LinkedHashMap;

public class Note extends ModelObject {

    public Note() {
        super();
        fillProperties();
    }

    public enum Groups {
        FRIENDS,
        RELATIVES,
        WORK
    }

    private void fillProperties() {
        getProperties().put("groups", new HashSet<Groups>());
        LinkedHashMap<String, String> properties = getPropertiesStringsForUserInput();
        properties.put("surname", "Default");
        properties.put("name", "Default");
        properties.put("login", "Default");
        properties.put("patronymic", "Default");
        properties.put("phone_number_home", "Default");
        properties.put("phone_number_mobile", "Default");
        properties.put("phone_number_mobile_second", "Default");
        properties.put("email", "Default");
        properties.put("skype", "Default");
        properties.put("index", "Default");
        properties.put("city", "Default");
        properties.put("street", "Default");
        properties.put("home_number", "Default");
        properties.put("apartment_number", "Default");
        properties.put("comment", "Default");
    }
}
