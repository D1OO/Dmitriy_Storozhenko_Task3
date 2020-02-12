package net.shvdy.model;

public class Note extends ModelObject {

    public Note() {
        super();
        fillProperties();
    }

    private void fillProperties() {
        properties.put("surname", "Default Surname");
        properties.put("nick", "Default Nick");
    }

    public void setNick(String nick) {
        setProperty("nick", nick);
    }

    public String getNick() {
        return (String) properties.get("nick");
    }

    public void setOwner(String surname) {
        setProperty("surname", surname);
    }

    public String getSurname() {
        return (String) properties.get("surname");
    }

}
