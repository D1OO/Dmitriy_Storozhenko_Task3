/**
 * ModelObject
 * <p>
 * version 1
 * <p>
 * 11.02.2020
 * <p>
 * Copyright(r) shvdy
 */
package net.shvdy.model;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Random;

public abstract class ModelObject {
    private BigInteger id;
    private HashMap<String, Object> properties = new HashMap<>();
    private LinkedHashMap<String, String> propertiesStringsForUserInput = new LinkedHashMap<>();

    ModelObject() {
        id = new BigInteger(3 + (LocalDateTime.now().format(DateTimeFormatter.ofPattern("ddMMuuHHmmssSSS")) +
                String.format("%02d", new Random().nextInt(99) + 1)));

        properties.put("date_created", LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.uu HH:mm:ss")));
        properties.put("date_modified", LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.uu HH:mm:ss")));
    }

    public BigInteger getId() {
        return id;
    }

    public HashMap<String, Object> getProperties() {
        return properties;
    }

    public LinkedHashMap<String, String> getPropertiesStringsForUserInput() {
        return propertiesStringsForUserInput;
    }
}