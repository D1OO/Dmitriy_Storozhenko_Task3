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

public class NoteBook extends ModelObject {

    public NoteBook() {
        super();
        fillProperties();
    }

    private void fillProperties() {
        properties.put("name", "Default Notebook");
        properties.put("owner", "Default Owner");
        properties.put("notes", new HashMap<BigInteger, Note>());
    }

    public void addNote(Note note) {
        ((HashMap<BigInteger, Note>) properties.get("notes")).put(note.getId(), note);
    }

}
