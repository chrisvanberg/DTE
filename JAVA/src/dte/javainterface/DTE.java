/*
 * GNU General Public License v3 (GPL-3)
 *
 * Copyright 2017
 * Christophe Van Waesberghe <contact@chrisv.be>
 * Amélie Courtin <a.courtin@students.ephec.be>
 * Mathieu Rousseau <m.rousseau@students.ephec.be>
 * Nathan Dolinsky <n.dolinski@students.ephec.be>
 * (Groupe 05, 2016/17)
 * 
 * The DTE Java Interface is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * The DTE Java Interface is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with the DTE Java Interface and the whole DTE Project.  If not, see <http://www.gnu.org/licenses/>.
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

        //cli.draw();
        gui.setVisible(true);
        gui.update(null, null);

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
