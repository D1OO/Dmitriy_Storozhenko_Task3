package net.shvdy;

import java.util.ArrayList;

public class View {

    public void printWelcomeMessage(int rulesMin, int rulesMax) {
        System.out.println("\"-----------Guess The Number----------\"\n\t" +
                "Welcome!\n-----------Rules:\n\t" +
                "1.You have to guess the number from " + rulesMin + " to " + rulesMax + ";\n\t" +
                "2. Range of the secret number is shortened after each unlucky try;\n\t" +
                "3.Enter \"I give up\" to see the right answer;\n\t" +
                "4.Enter \"Exit\" to close.\n\t");

    }

    public void printAttemptMessage(int minNumber, int maxNumber, int num, ArrayList prevTries) {
        System.out.println("\t\n" + "Write your guess #" + num + " in range between " +
                minNumber + " and " + maxNumber + " (Previously tried - " +
                prevTries.toString().replace("[", "").replace("]", "") + "): ");
    }

    public void printWinnerMessage() {
        System.out.println("Correct answer");
    }

    public void printStatsMessage(int id, boolean result, ArrayList<Integer> attempts) {
        String results = result ? "Win" : "Lose";
        System.out.println("#" + id + (result ? " Win" : " Lose ") + " with " + attempts.size() + " attempts: " + attempts.toString());
    }

    public void printTryAgainMessage(String whatBoundaryChanged) {
        System.out.println("Your guess is " + whatBoundaryChanged + " than the secret number, try again");
    }

    public void printRulesViolatedMessage() {
        System.out.println("Yor guess is out of range, check the rules");
    }

    public void printWrongInputMessage() {
        System.out.println("Wrong input, please enter correct number value");
    }

    public void printGiveUpMessage(int answer) {
        System.out.println("The right answer was: " + answer);
    }

    public void printStatsHeader() {
        System.out.println("\t\nSession stats:");
    }

}
