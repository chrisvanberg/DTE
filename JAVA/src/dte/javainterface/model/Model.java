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

import dte.javainterface.exceptions.EmptyHistoryException;
import dte.javainterface.exceptions.NoHistoryDataAvaliableException;
import dte.javainterface.exceptions.UnknowAlertLevelException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Model of the App, contain all the data.a
 */
public class Model extends Observable {

    private int currentTemperature;
    private int thresholdTemperature;
    private int alertLevel;
    
    private LinkedHashMap<Date, Integer> temperaturesHistory;
    private LinkedHashMap<Date, Integer> alertLevelHistory;
    
    /**
     * Represent the smallest alertLevel : {@value #MIN_ALERT_LVL}
     */
    public static final int MIN_ALERT_LVL=0;
    /**
     * Represent the biggest alertLevel : {@value #MAX_ALERT_LVL}
     */
    public static final int MAX_ALERT_LVL=3;
    /**
     * The AlertLevel 0 : {@value #ALERT_LVL_0}
     */
    public static final String ALERT_LVL_0="COOLING";
    /**
     * The AlertLevel 1: {@value #ALERT_LVL_1}
     */
    public static final String ALERT_LVL_1="OK";
    /**
     * The AlertLevel 2: {@value #ALERT_LVL_2}
     */
    public static final String ALERT_LVL_2="HEATING";
    /**
     * The AlertLevel 3: {@value #ALERT_LVL_3}
     */
    public static final String ALERT_LVL_3="ALERT";
    

    /**
     * Empty Model constructor. Initialize the history and set all the instance variables to -1000.
     */
    public Model(){
        this.currentTemperature= -1000;
        this.thresholdTemperature = -1000;
        this.alertLevel = -1000;
        this.temperaturesHistory = new LinkedHashMap<Date, Integer>();
        this.alertLevelHistory = new LinkedHashMap<Date, Integer>();
    }
    
    /**
     * Full Model constructor
     *
     * @param currentTemperature Must be an int and be the current temperature
     * given by the sensors
     * @param thresholdTemperature Must be an int and be the temperature who
     * trigger the alert
     * @param alertLevel int Must be greater than {@value #MIN_ALERT_LVL} and smaller than {@value #MAX_ALERT_LVL} {{@value #ALERT_LVL_0}, {@value #ALERT_LVL_1}, {@value #ALERT_LVL_2}, {@value #ALERT_LVL_3}}
     */
    public Model(int currentTemperature, int thresholdTemperature, int alertLevel) {
        //Instance variable initialization
        this.currentTemperature = currentTemperature;
        this.thresholdTemperature = thresholdTemperature;
        this.alertLevel = alertLevel;
        this.temperaturesHistory = new LinkedHashMap<Date, Integer>();
        this.alertLevelHistory = new LinkedHashMap<Date, Integer>();

        //Update of the temperature & alertLevel
        this.addTemperatureToHistory(currentTemperature);
        try {
            this.addAlertLevelToHistory(alertLevel);
        } catch (UnknowAlertLevelException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Get the currentTemperature
     * @return int currentTemperature
     */
    public int getCurrentTemperature() {
        return currentTemperature;
    }

    /**
     * Set the currentTemperature and record it on the temperaturesHistory's HashMap
     * @param currentTemperature  int The current temperature
     */
    public void setCurrentTemperature(int currentTemperature) {
        this.currentTemperature = currentTemperature;
        this.addTemperatureToHistory(currentTemperature);
    }

    /**
     * Get the threshold temperature (Temperature when the alert will be triggered)
     * @return int The threshold temperature (Temperature when the alert will be triggered)
     */
    public int getThresholdTemperature() {
        return thresholdTemperature;
    }

    /**
     * Set the threshold temperature (Temperature when the alert will be triggered)
     * @param thresholdTemperature int The threshold temperature (Temperature when the alert will be triggered)
     */
    public void setThresholdTemperature(int thresholdTemperature) {
        this.thresholdTemperature = thresholdTemperature;
    }

    /**
     * Get the current AlertLevel
     * @return int greater than {@value #MIN_ALERT_LVL} and smaller than {@value #MAX_ALERT_LVL} {{@value #ALERT_LVL_0}, {@value #ALERT_LVL_1}, {@value #ALERT_LVL_2}, {@value #ALERT_LVL_3}}
     */
    public int getAlertLevel() {
        return alertLevel;
    }

    /**
     * Set the alertLevel and record it on the AlertLevelHistory HashMap
     * @param alertLevel int int Must be greater than {@value #MIN_ALERT_LVL} and smaller than {@value #MAX_ALERT_LVL} {{@value #ALERT_LVL_0}, {@value #ALERT_LVL_1}, {@value #ALERT_LVL_2}, {@value #ALERT_LVL_3}}
     * @throws UnknowAlertLevelException When the parametter is strictly smaller than 0 and stritly bigger than 3
     */
    public void setAlertLevel(int alertLevel) throws UnknowAlertLevelException{
        
        this.alertLevel = alertLevel;
    }

    /**
     * Get the alert level history
     * @return HashMap(Date, Integer) AlertLevel history
     * @throws EmptyHistoryException when there is no AlertLevel recorded yet.
     */
    public LinkedHashMap<Date, Integer> getAlertLevelHistory() throws EmptyHistoryException {
        return alertLevelHistory;
    }

    /**
     * Add a record of alertLevel to the history at the current date
     * @param alertLevel int int Must be greater than {@value #MIN_ALERT_LVL} and smaller than {@value #MAX_ALERT_LVL} {{@value #ALERT_LVL_0}, {@value #ALERT_LVL_1}, {@value #ALERT_LVL_2}, {@value #ALERT_LVL_3}}
     * @throws UnknowAlertLevelException When the parametter is strictly smaller than 0 and stritly bigger than 3
     */
    public final void addAlertLevelToHistory(int alertLevel) throws UnknowAlertLevelException {
        this.temperaturesHistory.put(new Date(), alertLevel);
    }
    
    /**
     * Add a record of alertLevel to the history at the current date
     * @param date Date when the alertLevel was set
     * @param alertLevel int Must be greater than {@value #MIN_ALERT_LVL} and smaller than {@value #MAX_ALERT_LVL} {{@value #ALERT_LVL_0}, {@value #ALERT_LVL_1}, {@value #ALERT_LVL_2}, {@value #ALERT_LVL_3}}
     * @throws UnknowAlertLevelException When the parametter is strictly smaller than 0 and stritly bigger than 3
     */
    public final void addAlertLevelToHistory(Date date,int alertLevel) throws UnknowAlertLevelException {
        this.temperaturesHistory.put(date, alertLevel);
    }
    
    /**
     * getAlertLevelFromHistory return the AlertLevel at a date
     * @param date Date Must be in the past
     * @return int The AlertLevel at the given date
     * @throws NoHistoryDataAvaliableException when there is no entry at the given date
     */
    public int getAlertLevelFromHistory(Date date) throws NoHistoryDataAvaliableException{
        return -1;
    }
        
    /**
     * Get the temperature history
     * @return HashMap(date, Integer) temperatureHistory
     * @throws EmptyHistoryException when there is no temperature recorded yet
     */
    public LinkedHashMap<Date, Integer> getTemperaturesHistory() throws EmptyHistoryException{
        return temperaturesHistory;
    }
    
    /**
     * Get the temperature at a given date
     * @param date Must be a date in the past
     * @return int The temperature at the given date
     * @throws NoHistoryDataAvaliableException when there is no temperature recorded at the given date
     */
    public int getTemperatureFromHistory(Date date) throws NoHistoryDataAvaliableException{
        return -1;
    }
    
    /**
     * Add a temperature to the temperature history
     * @param currentTemperature int The currentTemperature
     */
    public final void addTemperatureToHistory(int currentTemperature) {
        this.temperaturesHistory.put(new Date(), currentTemperature);
    }
    
    /**
     * Add a temperature at the given date to the temperature history
     * @param date Date when the temperature was set
     * @param temperature  The Temperature at the date
     */
    public void addTemperatureToHistory(Date date, int temperature){
        this.temperaturesHistory.put(date, temperature);
    }
    
    /**
     * Check if the given alertLevel is correct
     * @param alertLevel int Must be greater than {@value #MIN_ALERT_LVL} and smaller than {@value #MAX_ALERT_LVL} {{@value #ALERT_LVL_0}, {@value #ALERT_LVL_1}, {@value #ALERT_LVL_2}, {@value #ALERT_LVL_3}}
     * @return true if the given alertLevel is correct; False otherwise.
     */
    public boolean isAlertLevelCorrect(int alertLevel){
        
        
        return MIN_ALERT_LVL<=alertLevel && alertLevel<=MAX_ALERT_LVL;
        
    }
}


