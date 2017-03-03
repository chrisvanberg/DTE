/*
 * The MIT License
 *
 * Copyright 2017
 * Christophe Van Waesberghe <contact@chrisv.be>
 * Amélie Courtin <a.courtin@students.ephec.be>
 * Mathieu Rousseau <m.rousseau@students.ephec.be>
 * Nathan Dolinsky <n.dolinsky@students.ephec.be>
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
package dte.javainterface;

import dte.javainterface.controller.Controller;
import dte.javainterface.model.Model;
import dte.javainterface.view.CliView;
import dte.javainterface.view.GuiView;

/**
 * DTE Main class, initialize and start both CLI and GUI view
 */
public class DTE {

    private final Model model;
    private final Controller guiController;
    private final Controller cliController;
    private final CliView cli;
    private final GuiView gui;

    /**
     * Setup the Model, the controller, the view and start the app.
     */
    public DTE() {
        model = new Model();
        guiController = new Controller(model);
        cliController = new Controller(model);

        gui = new GuiView(guiController, model);
        cli = new CliView(cliController, model);

        guiController.addView(gui);
        cliController.addView(cli);

        gui.addObservable(model);
        cli.addObservable(model);

        cli.draw();
        gui.setVisible(true);

    }

    /**
     * Launch the App.
     *
     * @param args Not used for the moment (Will probably be used to pass some
     * settings)
     */
    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(DTE::new);

    }

}
