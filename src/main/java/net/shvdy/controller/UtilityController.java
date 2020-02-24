/**
 * UtilityController
 * <p>
 * version 2
 * <p>
 * 13.02.2020
 * <p>
 * Copyright(r) shvdy
 */
package net.shvdy.controller;

import net.shvdy.model.Model;
import net.shvdy.model.Note;
import net.shvdy.model.Notebook;

import java.math.BigInteger;
import java.util.*;

class UtilityController {
    private Model model;
    private ViewController viewController;
    private Scanner sc;
    private List<ResourceBundle> regexBundles;

    UtilityController(Controller controller) {
        model = controller.getModel();
        viewController = controller.getViewController();
        sc = controller.getSc();
        regexBundles = controller.getResourcesBrowser().getAvailableRegex();
    }

    /**
     * Verifies user entered strings with the regular expressions, bundled in /resources folder,
     * an string match any language's regexp
     */
    String getVerifiedUserInput(Map.Entry<String, String> property) {
        String propertyName = property.getKey();

        while (true) {
            viewController.printMessage("\n", viewController.getMessageLocalisation(ViewController.INPUT_VALUE_FOR_MSG), " ",
                    viewController.getPropertyLocalisation(propertyName));

            String userInput = sc.nextLine();

            for (ResourceBundle regex : regexBundles) {
                if (userInput.matches(regex.getString("regex." + propertyName)))
                    return userInput;
            }

            viewController.printLocalisedMessage(ViewController.WRONG_INPUT_MSG);
        }
    }

    /**
     * Outputs all {@link Note.Groups} available groups for user to choose from,
     * accepts user's input in form of int values, corresponding to groups order in the output list, divided by spaces
     */
    HashSet<Note.Groups> getVerifiedUserGroupsInput() {
        HashSet<Note.Groups> chosenGroups = new HashSet<>();
        Note.Groups[] availableGroups = Note.Groups.values();

        while (true) {
            viewController.printMessage(viewController.getMessageLocalisation(ViewController.AVAILABLE_GROUPS_MSG), " ",
                    getGroupsString(Arrays.asList(availableGroups)), "\n",
                    viewController.getMessageLocalisation(ViewController.CHOOSE_GROUPS_MSG), "1 - ",
                    String.valueOf(availableGroups.length), "\n");

            String userInput = sc.nextLine();
            int[] inputToArr;
            try {
                inputToArr = Arrays.stream(userInput.split(" "))
                        .map(String::trim).mapToInt(Integer::parseInt).toArray();
            } catch (Exception e) {
                viewController.printMessage(ViewController.WRONG_INPUT_MSG);
                continue;
            }

            for (int i : inputToArr) {
                chosenGroups.add(availableGroups[i - 1]);
            }
            return chosenGroups;
        }
    }

    void getUniqueInput(Notebook notebook, Map.Entry<String, String> property) {
        while (true) {
            String value = getVerifiedUserInput(property);
            try {
                model.checkDataForUniqueness(notebook, property.getKey(), value);
            } catch (Model.NotUniqueDataException e) {
                viewController.printMessage(e.getValue(), ": ",
                        viewController.getMessageLocalisation(ViewController.EXISTING_VALUE_MSG),
                        " '", viewController.getPropertyLocalisation(e.getKey()), "'");
                continue;
            }
            property.setValue(value);
            break;
        }
    }

    void viewCreatedNotes(Notebook notebook) {
        HashMap<BigInteger, Note> createdNotes = (HashMap<BigInteger, Note>) notebook.getProperties().get("notes");
        for (Map.Entry<BigInteger, Note> note : createdNotes.entrySet()) {
            HashMap<String, String> properties = note.getValue().getPropertiesStringsForUserInput();
            viewController.printMessage("\n----------[", note.getKey().toString(), "]---------\n");

            for (Map.Entry<String, String> prop : properties.entrySet()) {
                viewController.printNotePropertyString(prop.getKey(), prop.getValue());
            }

            for (Map.Entry<String, Object> prop : note.getValue().getProperties().entrySet()) {
                if (prop.getValue().getClass().equals(HashSet.class)) {
                    viewController.printNotePropertyString("groups",
                            getGroupsString(Arrays.asList(((HashSet) prop.getValue()).toArray())));
                } else if (prop.getValue().getClass().equals(String.class))
                    viewController.printNotePropertyString(prop.getKey(), (String) prop.getValue());
            }
        }
        viewController.printMessage("\n-------------------------------------\n");
    }

    String buildString(String... pieces) {
        StringBuilder sb = new StringBuilder();
        for (String p : pieces) {
            sb.append(p);
        }
        return sb.toString();
    }

    String getGroupsString(List<Object> g) {
        StringBuilder s = new StringBuilder();
        for (Object group : g) {
            s.append(buildString(
                    "[", viewController.getPropertyLocalisation("groups." + group.toString().toLowerCase())
                    , "] "));
        }
        return s.toString();
    }
}

