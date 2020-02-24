/**
 * NoteCreatorController
 * <p>
 * version 2
 * <p>
 * 13.02.2020
 * <p>
 * Copyright(r) shvdy
 */
package net.shvdy.controller;

import net.shvdy.model.Note;
import net.shvdy.model.Notebook;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

class NoteCreatorController {
    private ViewController viewController;
    private UtilityController utilities;
    private Scanner sc;

    NoteCreatorController(Controller controller) {
        viewController = controller.getViewController();
        utilities = controller.getUtilities();
        sc = controller.getSc();
    }

    void createNotes(Notebook notebook) {
        boolean keepMakingNotes = true;
        while (keepMakingNotes) {
            Note newNote = new Note();
            LinkedHashMap<String, String> noteProperties = newNote.getPropertiesStringsForUserInput();
            viewController.printLocalisedMessage(ViewController.INPUT_NEW_NOTE_MSG);

            for (Map.Entry<String, String> property : noteProperties.entrySet()) {
                if (property.getKey().equals("login"))
                    utilities.getUniqueInput(notebook, property);
                else
                    property.setValue(utilities.getVerifiedUserInput(property));
            }
            noteProperties.put("full_name", utilities.buildString(noteProperties.get("surname"), " ",
                    noteProperties.get("name").substring(0, 1), "."));
            noteProperties.put("full_address", utilities.buildString(
                    noteProperties.get("index"), " ", noteProperties.get("city"), " ",
                    noteProperties.get("street"), " ", noteProperties.get("home_number"), " ",
                    noteProperties.get("apartment_number")));

            newNote.getProperties().put("groups", utilities.getVerifiedUserGroupsInput());
            notebook.addNote(newNote);

            boolean createNewNote = false;
            while (!createNewNote) {
                boolean viewCreatedNotes = false;
                int actionCode;
                viewController.printLocalisedMessage(ViewController.CHOOSE_ACTION_AFTER_NOTE_CREATED_MSG);
                actionCode = sc.nextInt();
                sc.nextLine();
                switch (actionCode) {
                    case 1:
                        createNewNote = true;
                        break;
                    case 2:
                        viewCreatedNotes = true;
                        break;
                    case 3:
                        keepMakingNotes = false;
                        createNewNote = true;
                        break;
                    default:
                        viewController.printLocalisedMessage(ViewController.WRONG_INPUT_MSG);
                }
                if (viewCreatedNotes)
                    utilities.viewCreatedNotes(notebook);
            }
        }
    }
}
