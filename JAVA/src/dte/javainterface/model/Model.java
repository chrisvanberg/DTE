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
package dte.javainterface.model;

import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Observable;

/**
 * Model of the App, contain all the data.a
 */
public class Model extends Observable {

    private int currentTemperature;
    private int thresholdTemperature;
    private int alertLevel;
    
    private HashMap<Date, Integer> temperatures;

    /**
     * Model's constructor without parametters
     */
    public Model() {
    }

    /**
     * Full Model constructor
     *
     * @param currentTemperature Must be an int and be the current temperature
     * given by the sensors
     * @param thresholdTemperature Must be an int and be the temperature who
     * trigger the alert
     * @param alertLevel Must be an int>0 who represent the current lever of
     * alert (0:Cooling,1:OK,2:heating,3:alert)
     */
    public Model(int currentTemperature, int thresholdTemperature, int alertLevel) {
        //Instance variable initialization
        this.currentTemperature = currentTemperature;
        this.thresholdTemperature = thresholdTemperature;
        this.alertLevel = alertLevel;
        this.temperatures = new HashMap<Date, Integer>();

        //Update of the temperature
        this.temperatures.put(new Date(), currentTemperature);

    }
    
    
}
