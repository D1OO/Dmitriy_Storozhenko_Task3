package net.shvdy.controller;

import net.shvdy.view.View;

import java.util.ResourceBundle;
import java.util.Scanner;

public class UtilityController implements Regex {
    private View view;

    public UtilityController(View view) {
        this.view = view;
    }

    public String getVerifiedUserInput(Scanner scanner, ResourceBundle currentLocale, String property) {

        view.printMessage((new StringBuilder().append(currentLocale.getString("INPUT_VALUE_FOR"))
                .append(" ").append(currentLocale.getString(property))).toString());

        String result;
        switch (property) {
            case ("surname"): {
                while (!(scanner.hasNextLine() &&
                        ((result = scanner.nextLine()).matches(surnameEng)) || (result = scanner.nextLine()).matches(surnameUa))) {
                    view.printMessage(currentLocale.getString("WRONG_INPUT_MSG"));
                }
            }
            case ("nick"): {
                while (!(scanner.hasNextLine() &&
                        ((result = scanner.nextLine()).matches(nickEng)) || (result = scanner.nextLine()).matches(nickUa))) {
                    view.printMessage(currentLocale.getString("WRONG_INPUT_MSG"));
                }
            }
            default:
                result = scanner.nextLine();
                break;
        }

        return result;
    }
}

