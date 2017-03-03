/*
 * GNU General Public License v3 (GPL-3)
 *
 * Copyright 2017
 * Christophe Van Waesberghe <contact@chrisv.be>
 * Amélie Courtin <a.courtin@students.ephec.be>
 * Mathieu Rousseau <m.rousseau@students.ephec.be>
 * Nathan Dolinsky <n.dolinsky@students.ephec.be>
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
        
    }

}
