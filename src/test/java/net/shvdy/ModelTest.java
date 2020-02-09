package net.shvdy;

import org.junit.Ignore;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ModelTest {


    @org.junit.jupiter.api.Test
    @Ignore
    void generateAnswer() {
        Model model = new Model();
        HashSet<Integer> answers = new HashSet<>();

        /*
        for (int i = 0; i < 1000; i++) {
            answers.add(model.generateAnswer());
        }
        */

        assertTrue(answers.contains(13));
        assertTrue(answers.contains(1));
        assertTrue(answers.contains(99));
        assertFalse(answers.contains(0));
        assertFalse(answers.contains(100));
        assertFalse(answers.contains(101));
        assertFalse(answers.contains(-1));
        assertFalse(answers.contains(666));
    }
}