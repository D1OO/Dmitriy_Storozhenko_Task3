/**
 * Main
 * <p>
 * version 1
 * <p>
 * 11.02.2020
 * <p>
 * Copyright(r) shvdy
 */
package net.shvdy;

import net.shvdy.controller.Controller;
import net.shvdy.model.Model;
import net.shvdy.view.View;

public class Main {

    public static void main(String[] args) {
        Controller controller = new Controller();
        controller.processSession(new View(), new Model());
    }
}
