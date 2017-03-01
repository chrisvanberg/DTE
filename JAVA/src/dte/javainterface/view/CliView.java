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
package dte.javainterface.view;

import dte.javainterface.controller.Controller;
import dte.javainterface.model.Model;
import java.util.Observable;

/**
 * ConsoleLine View of the App
 */
public class CliView extends View {

    /**
     * View's constructor with a Controller and a Model
     *
     * @param controller Controller of this view
     * @param model Model of the app
     */
    public CliView(Controller controller, Model model) {
        super(controller, model);
    }

    /**
     * Update the view. Triggered when the Model change
     *
     * @param o Observable Object
     * @param arg Object the argument
     */
    @Override
    public void update(Observable o, Object arg) {
        draw();
    }

    /**
     * Draw the view on the standart output
     */
    public void draw() {
        System.out.println("Welcome to DTE !");
    }

}
