/**
 * Controller
 *
 * version 2
 *
 * 13.02.2020
 *
 * Copyright(r) shvdy
 */
package net.shvdy.controller;

import net.shvdy.model.NoteBook;
import net.shvdy.view.View;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Controller {
    View view;
    Scanner sc = new Scanner(System.in);
    ResourcesBrowser resourcesBrowser = new ResourcesBrowser();
    UtilityController utilities;
    private NoteCreatorController noteCreator;

    public void processSession(View passedView) {
        view = passedView;
        try {
            resourcesBrowser.loadFromJarResources();
            view.setCurrentLocale(receiveAndSetLocale());
            utilities = new UtilityController(this);
            noteCreator = new NoteCreatorController(this);
        } catch (IOException | URISyntaxException e) {
            view.printMessage("Internal error \n");
        }

        view.printLocalisedMessage(view.WELCOME_MSG);

        NoteBook newNotebook = createNoteBook();
        noteCreator.createNotes(newNotebook);

        view.printLocalisedMessage(view.GOODBYE_MSG);
    }

    /**
     * Receives locales, loaded from jar file with {@link ResourcesBrowser},
     * returns one chosen by user
     *
     * @return ResourceBundle of locale chosen by user
     * @see ResourceBundle
     */
    private ResourceBundle receiveAndSetLocale() {
        List<Locale> availableLocales;
        int chosenLangCode;
        StringBuilder chooseLangMsg = new StringBuilder();

        availableLocales = resourcesBrowser.getAvailableLocales();

        chooseLangMsg.append("Choose your language/Виберіть мову:");
        for (int i = 0; i < availableLocales.size(); i++) {
            chooseLangMsg.append("\n\t").append(i + 1).append(". ").append(availableLocales.get(i).toString().toUpperCase());
        }

        while (true) {
            view.printMessage(chooseLangMsg.toString());
            try {
                chosenLangCode = sc.nextInt();
                sc.nextLine();
                if ((chosenLangCode > availableLocales.size() || (chosenLangCode < 1))) throw new Exception();
                break;
            } catch (Exception e) {
                view.printLocalisedMessage(View.WRONG_INPUT_MSG);
            }
        }

        return ResourceBundle.getBundle("controller/locale", availableLocales.get(chosenLangCode - 1));
    }

    private NoteBook createNoteBook() {
        return new NoteBook();
    }

}
