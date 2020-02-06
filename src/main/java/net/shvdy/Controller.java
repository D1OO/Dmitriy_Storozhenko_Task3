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
        this.model  = model;
        this.view = view;
    }

    public void processSession(){
        view.printWelcomeMessage(model.MIN_RANGE, model.MAX_RANGE);
        ArrayList<HashMap<Boolean, ArrayList<Integer>>> gamesStats = new ArrayList();

        while (true){
            gamesStats.add(newGame());
            view.printStatsHeader();
            int k=1;
            for (HashMap<Boolean, ArrayList<Integer>> game : gamesStats){
                view.printStatsMessage(k++, (Boolean)game.keySet().toArray()[0], (ArrayList)game.get(game.keySet().toArray()[0]));
            }
        }
    }

    private HashMap<Boolean, ArrayList<Integer>> newGame(){
        HashMap<Boolean, ArrayList<Integer>> gameInfo = new HashMap<>();
        ArrayList allAttempts = new ArrayList();
        boolean correctAnswer = false;
        int attemptsCount = 1;

        while (!correctAnswer){
            view.printAttemptMessage(attemptsCount,allAttempts);
            int attempt=0;

            while (true){
                try {
                    String input = sc.nextLine();
                    if (input.equals("I give up")){
                        view.printGiveUpMessage(model.getCorrectAnswer());
                        correctAnswer = true;
                        gameInfo.put(false, allAttempts);
                    } else if  (input.equals("Exit")){
                        System.exit(0);
                    } else {
                        attempt = Integer.parseInt(input);
                        if (!allAttempts.contains(attempt)){
                            try {
                                if (model.checkAttempt(attempt)){
                                    view.printWinnerMessage();
                                    correctAnswer = true;
                                    gameInfo.put(true, allAttempts);
                                } else {
                                    view.printTryAgainMessage();
                                }
                                allAttempts.add(attempt);
                                attemptsCount++;
                            } catch (Model.AttemptOutOfBoundsException e) {
                                view.printRulesViolatedMessage();
                            }
                        } else {
                            view.printAlreadyUsedNumberMessage();
                        }
                    }
                    break;
                } catch (NumberFormatException e){
                    view.printWrongInputMessage();
                }
            }
        }
        return gameInfo;
    }

}
