package net.shvdy.model;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Random;

public abstract class ModelObject<T extends ModelObject> {
    private BigInteger id;
    protected HashMap<String, Object> properties = new HashMap<>();

    protected ModelObject() {
        id = new BigInteger(3 + (LocalDateTime.now().format(DateTimeFormatter.ofPattern("ddMMuuHHmmssSSS")) +
                String.format("%02d", new Random().nextInt(99) + 1)));
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public HashMap<String, Object> getParameters() {
        return properties;
    }

    protected void setProperty(String key, Object value) {
        properties.put("key", value);
    }
}