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
import net.shvdy.model.NoteBook;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;

class NoteCreatorController {
    private ViewController viewController;
    private UtilityController utilities;
    private Scanner sc;

    NoteCreatorController(Controller controller) {
        viewController = controller.viewController;
        utilities = controller.utilities;
        sc = controller.sc;
    }

    void createNotes(NoteBook notebook) {
        boolean keepMakingNotes = true;

        while (keepMakingNotes) {
            Note newNote = new Note();
            HashMap<String, Object> noteProperties = newNote.getParameters();
            boolean done = false;
            viewController.printLocalisedMessage(ViewController.INPUT_NEW_NOTE_MSG);

            for (Map.Entry<String, Object> property : noteProperties.entrySet()) {
                String propertyKey = property.getKey();
                if (!(propertyKey.equals("full_name") ||
                        propertyKey.equals("full_address") ||
                        propertyKey.equals("date_created") ||
                        propertyKey.equals("date_modified"))) {
                    if (propertyKey.equals("groups")) {
                        property.setValue(utilities.getVerifiedUserGroupsInput());
                    } else {
                        property.setValue(utilities.getVerifiedUserInput(property));
                    }
                }
            }

            noteProperties.put("full_name", concatenateString((String) noteProperties.get("surname"), " ",
                    ((String) noteProperties.get("name")).substring(0, 0), "."));
            noteProperties.put("full_address", concatenateString(
                    (String) noteProperties.get("index"), " ",
                    (String) noteProperties.get("city"), " ",
                    (String) noteProperties.get("street"), " ",
                    (String) noteProperties.get("home_number"), " ",
                    (String) noteProperties.get("apartment_number")));

            notebook.addNote(newNote);

            while (!done) {
                boolean viewCreatedNotes = false;
                viewController.printLocalisedMessage(ViewController.CHOOSE_ACTION_AFTER_NOTE_CREATED_MSG);
                try {
                    int actionCode = sc.nextInt();
                    sc.nextLine();
                    if (!((actionCode == 1) || (actionCode == 2) || (actionCode == 3))) throw new Exception();
                    switch (actionCode) {
                        case 1:
                            done = true;
                            break;
                        case 2:
                            viewCreatedNotes = true;
                            break;
                        case 3:
                            keepMakingNotes = false;
                            done = true;
                            break;
                    }
                } catch (Exception e) {
                    viewController.printLocalisedMessage(ViewController.WRONG_INPUT_MSG);
                }
                if (viewCreatedNotes) {
                    viewCreatedNotes(notebook);
                }
            }
        }
    }


    private void viewCreatedNotes(NoteBook notebook) {
        HashMap<BigInteger, Note> createdNotes = (HashMap<BigInteger, Note>) notebook.getParameters().get("notes");
        for (Map.Entry<BigInteger, Note> note : createdNotes.entrySet()) {

            HashMap<String, Object> properties = note.getValue().getParameters();

            viewController.printMessage("\n----------[", note.getKey().toString(), "]---------\n");
            for (Map.Entry<String, Object> prop : properties.entrySet()) {
                switch (prop.getKey()) {
                    case ("groups"):
                        StringBuilder groupsString = new StringBuilder();
                        for (Note.Groups group : (HashSet<Note.Groups>) prop.getValue()) {
                            groupsString.append(group.toString());
                        }
                        printNotePropertyString(prop.getKey(), groupsString.toString());
                        break;
                    default:
                        printNotePropertyString(prop.getKey(), prop.getValue().toString());
                }
            }
            viewController.printMessage("\n-------------------------------------\n");

        }
    }

    private void printNotePropertyString(String name, String value) {
        viewController.printMessage("\t", viewController.getPropertyLocalisation(name), ": ", value);
    }

    private String concatenateString(String... pieces) {
        StringBuilder sb = new StringBuilder();
        for (String p : pieces) {
            sb.append(p);
        }
        return sb.toString();
    }
}
