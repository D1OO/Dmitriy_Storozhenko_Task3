package net.shvdy;

import java.util.ArrayList;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ModelTest {

    @org.junit.jupiter.api.Test
    void generateAnswer() {
        Model model = new Model();
        HashSet<Integer> answers = new HashSet<>();

        for (int i=0; i<1000; i++) {
            answers.add(model.generateAnswer());
        }

        assertTrue(answers.contains(0));
        assertTrue(answers.contains(100));
        assertFalse(answers.contains(101));
        assertFalse(answers.contains(-1));
        assertFalse(answers.contains(666));
    }
}