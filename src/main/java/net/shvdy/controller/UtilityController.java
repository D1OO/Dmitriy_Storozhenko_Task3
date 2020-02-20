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

import net.shvdy.model.Note;

import java.util.*;

class UtilityController {
    private ViewController viewController;
    private Scanner sc;
    private List<ResourceBundle> regexBundles;

    UtilityController(Controller controller) {
        this.viewController = controller.viewController;
        this.sc = controller.sc;
        regexBundles = controller.resourcesBrowser.getAvailableRegex();
    }

    /**
     * Verifies user entered strings with the regular expressions, bundled in /resources folder,
     * an string match any language's regexp
     */
    String getVerifiedUserInput(Map.Entry<String, Object> property) {
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
        List<Note.Groups> availableGroups = Arrays.asList(Note.Groups.values());

        while (true) {
            viewController.printMessage(viewController.getMessageLocalisation(ViewController.AVAILABLE_GROUPS_MSG), " ", availableGroups.toString(), "\n",
                    viewController.getMessageLocalisation(ViewController.CHOOSE_GROUPS_MSG), "1 - ",
                    String.valueOf(availableGroups.size()), "\n");

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
                chosenGroups.add(availableGroups.get(i - 1));
            }
            return chosenGroups;
        }
    }
}

