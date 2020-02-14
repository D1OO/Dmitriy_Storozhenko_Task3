/**
 * UtilityController
 *
 * version 2
 *
 * 13.02.2020
 *
 * Copyright(r) shvdy
 */
package net.shvdy.controller;

import net.shvdy.model.Note;
import net.shvdy.view.View;

import java.util.*;

class UtilityController extends Controller {
    private Controller controller;
    private View view;
    private List<ResourceBundle> regexBundles;

    UtilityController(Controller controller) {
        this.controller = controller;
        this.view = controller.view;
        regexBundles = this.controller.resourcesBrowser.getAvailableRegex();
    }

    /**
     * Verifies user entered strings with the regular expressions, bundled in /resources folder,
     * an string match any language's regexp
     *
     */
    String getVerifiedUserInput(Map.Entry<String, Object> property) {
        String propertyName = property.getKey();

        while (true) {
            view.printMessage("\n", view.getLocalisation(View.INPUT_VALUE_FOR_MSG), " ",
                    view.getPropertyLocalisation(propertyName));

            String userInput = controller.sc.nextLine();

            for (ResourceBundle regex : regexBundles) {
                if (userInput.matches(regex.getString("regex." + propertyName)))
                    return userInput;
            }

            view.printLocalisedMessage(View.WRONG_INPUT_MSG);
        }

    }

    /**
     * Outputs all {@link Note.Groups} available groups for user to choose from,
     * accepts user's input in form of int values, corresponding to groups order in the output list, divided by spaces
     *
     */
    HashSet getVerifiedUserGroupsInput() {
        HashSet<Note.Groups> chosenGroups = new HashSet<>();
        List<Note.Groups> avaliableGroups = Arrays.asList(Note.Groups.values());

        while (true) {
            view.printMessage(view.getLocalisation(View.AVAILABLE_GROUPS_MSG), " ", avaliableGroups.toString(), "\n",
                    view.getLocalisation(View.CHOOSE_GROUPS_MSG), "1 - ",
                    String.valueOf(avaliableGroups.size()), "\n");

            String userInput = controller.sc.nextLine();
            try {
                int[] arr = Arrays.stream(userInput.split(" "))
                        .map(String::trim).mapToInt(Integer::parseInt).toArray();
                for (int i : arr) {
                    chosenGroups.add(avaliableGroups.get(i - 1));
                }
                return chosenGroups;
            } catch (Exception e) {

            }
            view.printMessage(View.WRONG_INPUT_MSG);
        }

    }
}

