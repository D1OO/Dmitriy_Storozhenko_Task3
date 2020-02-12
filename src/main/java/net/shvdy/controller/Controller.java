package net.shvdy.controller;

import net.shvdy.model.NoteBook;
import net.shvdy.view.View;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.CodeSource;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;


public class Controller {
    private View view;
    private Scanner sc = new Scanner(System.in);

    public void processSession(View view) {
        this.view = view;
        ResourceBundle currentLocale = receiveAndSetLocale();
        NoteCreatorController noteCreator = new NoteCreatorController(this.view, sc);
        NoteBook newNotebook = createNoteBook();

        noteCreator.createNotes(currentLocale, newNotebook);

        view.printMessage(currentLocale.getString("GOODBYE_MSG"));
    }

    private ResourceBundle receiveAndSetLocale() {
        List<Locale> avaliableLocales = null;

        try {
            avaliableLocales = getAvailableLocales();
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        int chosenLangCode;
        StringBuilder chooseLangMsg = new StringBuilder();

        chooseLangMsg.append("Choose your language/Виберіть мову:");
        for (int i = 0; i < avaliableLocales.size(); i++) {
            chooseLangMsg.append("\n\t").append(i + 1).append(". ").append(avaliableLocales.get(i).toString().toUpperCase());
        }

        while (true) {
            view.printMessage(chooseLangMsg.toString());
            try {
                chosenLangCode = sc.nextInt();
                if ((chosenLangCode > avaliableLocales.size() || (chosenLangCode < 0))) throw new Exception();
                break;
            } catch (Exception e) {
                view.printMessage("Wrong input, try again");
            }
        }

        return ResourceBundle.getBundle("controller/locale", avaliableLocales.get(chosenLangCode - 1));
    }

    private List<Locale> getAvailableLocales() throws IOException, URISyntaxException {
        List<Locale> availableLocales = new ArrayList<>();
        CodeSource src = Controller.class.getProtectionDomain().getCodeSource();

        if (src != null) {
            URI jj = new URI(src.getLocation().toString());

            URL jar = jj.toURL();
            ZipInputStream zip = new ZipInputStream(jar.openStream());
            ZipEntry ze = null;

            while ((ze = zip.getNextEntry()) != null) {
                String entryName = ze.getName();
                if (entryName.startsWith("controller/locale_") && entryName.endsWith(".properties")) {
                    availableLocales.add(new Locale(entryName.replaceFirst("controller/locale_", "").replaceFirst(".properties", "")));
                }
            }
        }
        return availableLocales;
    }

    private NoteBook createNoteBook() {
        return new NoteBook();
    }

}
