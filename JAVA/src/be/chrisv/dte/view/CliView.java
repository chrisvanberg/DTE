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
package be.chrisv.dte.view;

import be.chrisv.dte.controller.Controller;
import be.chrisv.dte.model.Model;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Observable;

/**
 * ConsoleLine View of the App
 */
public class CliView extends View {

    static int nbUpdate = 1;
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
     * Draw the view on the standart output. For the moment draw the Model as a String with an update counter
     */
    public void draw() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        System.out.println(sdf.format(cal.getTime())+" CLI Update triggered ("+nbUpdate+") ! : "+super.model.toString());
        nbUpdate += 1;
    }

    @Override
    public void printAlertLevel() {
        //Not implemented yet
    }

}
