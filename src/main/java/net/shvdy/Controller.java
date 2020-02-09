package net.shvdy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Controller {

    private Model model;
    private View view;
    Scanner sc;

    public Controller(Model model, View view) {
        sc = new Scanner(System.in);
        this.model = model;
        this.view = view;
    }

    public void processSession() {
        view.printWelcomeMessage(model.getMinRange(), model.getMaxRange());
        while (true) {
            model.setForNewGame();
            newGame();
            view.printStatsHeader();
            int k = 1;
            for (HashMap<Boolean, ArrayList<Integer>> game : model.getSessionStats()) {
                view.printStatsMessage(k++, (Boolean) game.keySet().toArray()[0], (ArrayList) game.get(game.keySet().toArray()[0]));
            }
        }
    }

    private void newGame() {
        ArrayList<Integer> allAttempts = new ArrayList();
        boolean correctAnswer = false;
        int attemptsCount = 1;

        while (!correctAnswer) {
            view.printAttemptMessage(model.getMinRange(), model.getMaxRange(), attemptsCount, allAttempts);
            int attempt = 0;

            while (true) {
                try {
                    String input = sc.nextLine();
                    if (input.equals("I give up")) {
                        view.printGiveUpMessage(model.getCorrectAnswer());
                        correctAnswer = true;
                        addGameStatsToModel(false, allAttempts);
                    } else if (input.equals("Exit")) {
                        System.exit(0);
                    } else {
                        attempt = Integer.parseInt(input);
                        try {
                            if (model.checkAttempt(attempt)) {
                                view.printWinnerMessage();
                                correctAnswer = true;
                                addGameStatsToModel(true, allAttempts);
                            } else {
                                view.printTryAgainMessage(new StringBuilder((attempt < model.getCorrectAnswer()) ? "lesser" : "bigger").toString());
                            }
                            allAttempts.add(attempt);
                            attemptsCount++;
                        } catch (Model.AttemptOutOfBoundsException e) {
                            view.printRulesViolatedMessage();
                        }
                    }
                    break;
                } catch (NumberFormatException e) {
                    view.printWrongInputMessage();
                }
            }
        }
    }

    private void addGameStatsToModel(boolean result, ArrayList<Integer> allAttempts) {
        HashMap<Boolean, ArrayList<Integer>> gameStats = new HashMap<>();
        gameStats.put(result, allAttempts);
        model.addGameStats(gameStats);
    }

}
