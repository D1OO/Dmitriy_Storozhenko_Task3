package net.shvdy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 *
 */
public class Model {

    private int minRange, maxRange, correctAnswer;
    private ArrayList<HashMap<Boolean, ArrayList<Integer>>> sessionStats = new ArrayList();
    private HashMap<Boolean, ArrayList<Integer>> gameStats = new HashMap<>();

    public class AttemptOutOfBoundsException extends Exception {
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public int getMinRange() {
        return minRange;
    }

    public int getMaxRange() {
        return maxRange;
    }

    public void setForNewGame() {
        minRange = 1;
        maxRange = 99;
        correctAnswer = generateAnswer();
    }

    public void addGameStats(HashMap<Boolean, ArrayList<Integer>> gameStats) {
        sessionStats.add(gameStats);
    }

    public ArrayList<HashMap<Boolean, ArrayList<Integer>>> getSessionStats() {
        return sessionStats;
    }

    public Model() {
        setForNewGame();
    }


    /**
     * Passed all tests
     */
    private int generateAnswer() {
        Random rnd = new Random();
        return rnd.nextInt(maxRange) + 1;
    }

    public void changeBoundaries(int attempt) {
        if (attempt < correctAnswer) minRange = attempt + 1;
        else maxRange = attempt - 1;
    }


    public boolean checkAttempt(int attempt) throws AttemptOutOfBoundsException {
        if (attempt == correctAnswer) return true;
        else if ((minRange > attempt) || (attempt > maxRange)) {
            throw new AttemptOutOfBoundsException();
        }
        changeBoundaries(attempt);
        return false;
    }
}
