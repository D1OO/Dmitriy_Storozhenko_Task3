package net.shvdy;

import java.util.ArrayList;

public class Model {

    public static final int MIN_RANGE = 0;
    public static final int MAX_RANGE = 100;
    private int correctAnswer;

    public class AttemptOutOfBoundsException extends Exception {

    }

    public int getCorrectAnswer(){
        return correctAnswer;
    }

    public Model(){
        correctAnswer = generateAnswer();
    }

    public static int generateAnswer(){
        return (int)(Math.random() * ((MAX_RANGE - MIN_RANGE + 1))) + MIN_RANGE;
    }

    public boolean checkAttempt (int attempt) throws AttemptOutOfBoundsException {
        if (attempt == correctAnswer) return true;
        else if (( MIN_RANGE > attempt) || ( attempt > MAX_RANGE)) {
            throw new AttemptOutOfBoundsException();
        }
        return false;
    }

    public String createSentence(String[] words){
        StringBuilder sentenceBuilder = new StringBuilder();
        for(String s : words) {
            sentenceBuilder.append(s + " ");
        }
        return sentenceBuilder.toString().trim();
    }
}
