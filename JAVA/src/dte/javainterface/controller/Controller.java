/*
 * The MIT License
 *
 * Copyright 2017
 * Christophe Van Waesberghe <contact@chrisv.be>
 * Amélie Courtin <a.courtin@students.ephec.be>
 * Mathieu Rousseau <m.rousseau@students.ephec.be>
 * Nathan Dolinski <n.dolinski@students.ephec.be>
 * (Groupe 05, 2016/17)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package dte.javainterface.controller;

import dte.javainterface.model.Model;
import dte.javainterface.view.View;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controller {

    private final Model model;
    private View view;

    public Controller(Model model) {
        this.model = model;
    }

    public void addView(View view) {
        this.view = view;
    }

    public void startGame() throws InterruptedException {

        this.view.update(null, "App Started");

        Thread dteLoop = new Thread(new DTELoop());
        dteLoop.start();
    }

    private class DTELoop implements Runnable {

        public DTELoop() {
        }

        public void run() {

            while (true) {

                try {
                    System.out.println("Game Loop");
                    Thread.sleep(2000);
                } catch (NullPointerException e) {

                } catch (InterruptedException ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        }
    }

}
