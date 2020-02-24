/**
 * Model
 * <p>
 * version 1.0
 * <p>
 * 23.02.2020
 * <p>
 * Copyright(r) shvdy.net
 */

package net.shvdy.model;

import java.math.BigInteger;
import java.util.HashMap;

public class Model {

    public class NotUniqueDataException extends Exception {
        String key, value;

        public String getKey() {
            return key;
        }

        public String getValue() {
            return value;
        }

        NotUniqueDataException(String k, String v) {
            key = k;
            value = v;
        }
    }

    public void checkDataForUniqueness(Notebook nb, String key, String value) throws NotUniqueDataException {
        for (Note n : ((HashMap<BigInteger, Note>) nb.getProperties().get("notes")).values()) {
            if (n.getPropertiesStringsForUserInput().get(key).equals(value))
                throw new NotUniqueDataException(key, value);
        }
    }
}
