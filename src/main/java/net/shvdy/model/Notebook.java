/**
 * NoteBook
 * <p>
 * version 2
 * <p>
 * 11.02.2020
 * <p>
 * Copyright(r) shvdy
 */
package net.shvdy.model;

import java.math.BigInteger;
import java.util.HashMap;

public class Notebook extends ModelObject {

    public Notebook() {
        super();
        fillProperties();
    }

    private void fillProperties() {
        getProperties().put("notes", new HashMap<BigInteger, Note>());
        HashMap<String, String> properties = getPropertiesStringsForUserInput();
        properties.put("name", "Default Notebook");
        properties.put("owner", "Default Owner");
    }

    public void addNote(Note note) {
        ((HashMap<BigInteger, Note>) getProperties().get("notes")).put(note.getId(), note);
    }
}
