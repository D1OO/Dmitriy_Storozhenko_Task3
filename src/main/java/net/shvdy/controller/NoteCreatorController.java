package net.shvdy.controller;

import net.shvdy.model.Note;
import net.shvdy.model.NoteBook;
import net.shvdy.view.View;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.Scanner;

public class NoteCreatorController {
    private View view;
    private Scanner sc;
    private UtilityController uc;

    public NoteCreatorController(View view, Scanner sc) {
        this.view = view;
        this.sc = sc;
        uc = new UtilityController(view);
    }

    public void createNotes(ResourceBundle currentLocale, NoteBook notebook) {
        boolean terminateMakingNotes = false;

        while (!terminateMakingNotes) {

            view.printMessage(currentLocale.getString("NEW_NOTE_CREATION_MSG"));
            Note newNote = new Note();
            HashMap<String, Object> noteProperties = newNote.getParameters();

            for (String property : noteProperties.keySet()) {
                noteProperties.put(property, uc.getVerifiedUserInput(sc, currentLocale, property));
            }

            notebook.addNote(newNote);

            boolean done = false;
            while (!done) {
                boolean viewCreatedNotes = false;
                view.printMessage(currentLocale.getString("CREATE_ANOTHER_NOTE_OR_VIEW_CREATED_OR_EXIT_MSG"));
                try {
                    int actionCode = sc.nextInt();
                    if ((actionCode != 1) || (actionCode != 2) || (actionCode != 3)) throw new Exception();
                    switch (actionCode) {
                        case 1:
                            done = true;
                        case 2:
                            viewCreatedNotes = true;
                        case 3:
                            terminateMakingNotes = true;
                    }
                } catch (Exception e) {
                    view.printMessage(currentLocale.getString("WRONG_INPUT_MSG"));
                }
                if (viewCreatedNotes) {
                    HashMap<BigInteger, Note> createdNotes = notebook.getNotes();
                    for (BigInteger id : createdNotes.keySet()) {
                        Note note = createdNotes.get(id);
                        StringBuilder sb = new StringBuilder();
                        sb.append(note.getId()).append(" ")
                                .append(note.getSurname()).append(" ")
                                .append(note.getNick())
                                .append("\n\t");
                    }
                }
            }
        }
    }
}
