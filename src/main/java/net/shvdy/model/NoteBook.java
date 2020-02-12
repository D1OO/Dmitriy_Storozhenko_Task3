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

    public String getName() {
        return (String) properties.get("name");
    }

    public void setName(String name) {
        setProperty("name", name);
    }

    public String getOwner() {
        return (String) properties.get("owner");
    }

    public void setOwner(String owner) {
        setProperty("owner", owner);
    }

    public HashMap<BigInteger, Note> getNotes() {
        return (HashMap<BigInteger, Note>) properties.get("notes");
    }

    public void setNotes(HashMap<BigInteger, Note> notes) {
        setProperty("notes", notes);
    }

    public void addNote(Note note) {
        ((HashMap<BigInteger, Note>) properties.get("notes")).put(note.getId(), note);
    }

}
