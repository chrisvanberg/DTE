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
package dte.javainterface.model;

import dte.javainterface.exceptions.EmptyHistoryException;
import dte.javainterface.exceptions.NoHistoryDataAvaliableException;
import dte.javainterface.exceptions.UnknowAlertLevelException;
import gnu.io.CommPortIdentifier;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Model of the App, contain all the data
 */
public class Model extends Observable {

    private int currentTemperature;
    private int thresholdTemperature;
    private int alertLevel;

    private LinkedHashMap<Date, Integer> temperaturesHistory;
    private LinkedHashMap<Date, Integer> alertLevelHistory;

    //Serial Communication
    private boolean connected;
    CommPortIdentifier serialPortId;
    private Enumeration enumComm;
    public static final int BAUDRATE = 9600;
    private static OutputStream uplink;
    private static PrintWriter uplinkWriter;
    
    private InputStream downlink;

    /**
     * Represent the smallest alertLevel : {@value #MIN_ALERT_LVL}
     */
    public static final int MIN_ALERT_LVL = 0;
    /**
     * Represent the biggest alertLevel : {@value #MAX_ALERT_LVL}
     */
    public static final int MAX_ALERT_LVL = 3;
    /**
     * The Cooling Alert Level : {@value #ALERT_COOLING}
     */
    public static final int ALERT_COOLING = 0;
    /**
     * The IDLE Alert Level : {@value #ALERT_IDLE}
     */
    public static final int ALERT_IDLE = 1;
    /**
     * The HEATING AlertLevel : {@value #ALERT_HEATING}
     */
    public static final int ALERT_HEATING = 2;
    /**
     * The OVERHEATING (When the temperature is over the threshold) AlertLevel:
     * {@value #ALERT_OVERHEATING}
     */
    public static final int ALERT_OVERHEATING = 3;

    /**
     * Empty Model constructor. Initialize the history and set all the instance
     * variables to -1000.
     */
    public Model() {
        this.currentTemperature = -1000;
        this.thresholdTemperature = 20;
        this.alertLevel = -1000;
        this.temperaturesHistory = new LinkedHashMap<Date, Integer>();
        this.alertLevelHistory = new LinkedHashMap<Date, Integer>();
        this.connected = false;
        this.enumComm = CommPortIdentifier.getPortIdentifiers();
    }

    /**
     * Full Model constructor
     *
     * @param currentTemperature Must be an int and be the current temperature
     * given by the sensors
     * @param thresholdTemperature Must be an int and be the temperature who
     * trigger the alert
     * @param alertLevel int Must be greater than {@value #MIN_ALERT_LVL} and
     * smaller than
     * {@value #MAX_ALERT_LVL} {{@value #ALERT_COOLING}, {@value #ALERT_IDLE}, {@value #ALERT_HEATING}, {@value #ALERT_OVERHEATING}}
     * @throws UnknowAlertLevelException When the alertLevel parametter is
     * strictly smaller than {@value #MIN_ALERT_LVL} and stritly bigger than
     * {@value #MAX_ALERT_LVL}
     */
    public Model(int currentTemperature, int thresholdTemperature, int alertLevel) throws UnknowAlertLevelException {
        //Instance variable initialization
        this.currentTemperature = currentTemperature;
        this.thresholdTemperature = thresholdTemperature;
        this.alertLevel = alertLevel;
        this.temperaturesHistory = new LinkedHashMap<Date, Integer>();
        this.alertLevelHistory = new LinkedHashMap<Date, Integer>();
        this.connected = false;

        //Update of the temperature & alertLevel
        this.addTemperatureToHistory(this.currentTemperature);
        this.addAlertLevelToHistory(this.alertLevel);
    }

    /**
     * Model constructor with current and threshold temperature. Automaticaly
     * calculate the AlertLevel
     *
     * @param currentTemperature
     * @param thresholdTemperature
     */
    public Model(int currentTemperature, int thresholdTemperature) {
        this.currentTemperature = currentTemperature;
        this.thresholdTemperature = thresholdTemperature;
        this.alertLevel = currentTemperature < thresholdTemperature ? 1 : 3;
        this.temperaturesHistory = new LinkedHashMap<Date, Integer>();
        this.alertLevelHistory = new LinkedHashMap<Date, Integer>();
        this.connected = false;

        try {
            this.addAlertLevelToHistory(this.alertLevel);
        } catch (UnknowAlertLevelException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public PrintWriter getUplinkWriter() {
        return uplinkWriter;
    }

    public void setUplinkWriter(PrintWriter uplinkWriter) {
        this.uplinkWriter = uplinkWriter;
    }

    
    
    public OutputStream getUplink() {
        return uplink;
    }

    public void setUplink(OutputStream uplink) {
        this.uplink = uplink;
    }

    public InputStream getDownlink() {
        return downlink;
    }

    public void setDownlink(InputStream downlink) {
        this.downlink = downlink;
    }

    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
        this.setChanged();
        this.notifyObservers();
    }

    public CommPortIdentifier getSerialPortId() {
        return serialPortId;
    }

    public void setSerialPortId(CommPortIdentifier serialPortId) {
        this.serialPortId = serialPortId;
    }

    public Enumeration getEnumComm() {
        return enumComm;
    }

    public void setEnumComm(Enumeration enumComm) {
        this.enumComm = enumComm;
    }

    /**
     * Get the currentTemperature
     *
     * @return int currentTemperature
     */
    public int getCurrentTemperature() {
        return currentTemperature;
    }

    /**
     * Set the currentTemperature and record it on the temperaturesHistory's
     * HashMap
     *
     * @param currentTemperature int The current temperature
     */
    public void setCurrentTemperature(int currentTemperature) {
        this.currentTemperature = currentTemperature;
        this.addTemperatureToHistory(currentTemperature);
        checkAlertLevel();
        this.setChanged();
        this.notifyObservers();
    }

    /**
     * Get the threshold temperature (Temperature when the alert will be
     * triggered)
     *
     * @return int The threshold temperature (Temperature when the alert will be
     * triggered)
     */
    public int getThresholdTemperature() {
        return thresholdTemperature;
    }

    /**
     * Set the threshold temperature (Temperature when the alert will be
     * triggered)
     *
     * @param thresholdTemperature int The threshold temperature (Temperature
     * when the alert will be triggered)
     */
    public void setThresholdTemperature(int thresholdTemperature) {
        this.thresholdTemperature = thresholdTemperature;
        this.checkAlertLevel();
        this.setChanged();
        this.notifyObservers();
        
    }

    public void checkAlertLevel(){
        int lastTemperatureRecorded = -1000;
        try {
            
            LinkedHashMap<Date, Integer> history = getTemperaturesHistory();
            Date theLastEntry = new ArrayList<>(history.keySet()).get(history.size() - 2);
            lastTemperatureRecorded = getTemperatureFromHistory(theLastEntry);
            
        } catch (EmptyHistoryException | NoHistoryDataAvaliableException | ArrayIndexOutOfBoundsException ex) {
            lastTemperatureRecorded = -1000;
        } finally {
        
        if(currentTemperature>=thresholdTemperature && currentTemperature!= -1000 && thresholdTemperature != -1000){
            try {
                setAlertLevel(ALERT_OVERHEATING);
            } catch (UnknowAlertLevelException ex) {
                Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if(currentTemperature<lastTemperatureRecorded && currentTemperature!= -1000 && thresholdTemperature != -1000){
            try {
                setAlertLevel(ALERT_COOLING);
            } catch (UnknowAlertLevelException ex) {
                Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if(currentTemperature>lastTemperatureRecorded && currentTemperature!= -1000 && thresholdTemperature != -1000){
            try {
                setAlertLevel(ALERT_HEATING);
            } catch (UnknowAlertLevelException ex) {
                Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if (currentTemperature!= -1000 && thresholdTemperature != -1000) {
            try {
                setAlertLevel(ALERT_IDLE);
            } catch (UnknowAlertLevelException ex) {
                Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
     
            
            }
        
            
    }
    
    /**
     * Get the current AlertLevel
     *
     * @return int greater than {@value #MIN_ALERT_LVL} and smaller than
     * {@value #MAX_ALERT_LVL} {{@value #ALERT_COOLING}, {@value #ALERT_IDLE}, {@value #ALERT_HEATING}, {@value #ALERT_OVERHEATING}}
     */
    public int getAlertLevel() {
        return alertLevel;
    }

    /**
     * Set the alertLevel and record it on the AlertLevelHistory HashMap
     *
     * @param alertLevel int int Must be greater than {@value #MIN_ALERT_LVL}
     * and smaller than
     * {@value #MAX_ALERT_LVL} {{@value #ALERT_COOLING}, {@value #ALERT_IDLE}, {@value #ALERT_HEATING}, {@value #ALERT_OVERHEATING}}
     * @throws UnknowAlertLevelException When the parametter is strictly smaller
     * than {@value #MIN_ALERT_LVL} and stritly bigger than
     * {@value #MAX_ALERT_LVL}
     */
    public void setAlertLevel(int alertLevel) throws UnknowAlertLevelException {
        if (isAlertLevelCorrect(alertLevel)) {
            this.alertLevel = alertLevel;
            addAlertLevelToHistory(alertLevel);
        } else {
            throw new UnknowAlertLevelException(alertLevel);
        }
        this.setChanged();
        this.notifyObservers();
    }

    /**
     * Get the alert level history
     *
     * @return HashMap(Date, Integer) AlertLevel history
     * @throws EmptyHistoryException when there is no AlertLevel recorded yet.
     */
    public LinkedHashMap<Date, Integer> getAlertLevelHistory() throws EmptyHistoryException {
        return alertLevelHistory;
    }

    /**
     * Add a record of alertLevel to the history at the current date
     *
     * @param alertLevel int int Must be greater than {@value #MIN_ALERT_LVL}
     * and smaller than
     * {@value #MAX_ALERT_LVL} {{@value #ALERT_COOLING}, {@value #ALERT_IDLE}, {@value #ALERT_HEATING}, {@value #ALERT_OVERHEATING}}
     * @throws UnknowAlertLevelException When the parametter is strictly smaller
     * than {@value #MIN_ALERT_LVL} and stritly bigger than
     * {@value #MAX_ALERT_LVL}
     */
    public final void addAlertLevelToHistory(int alertLevel) throws UnknowAlertLevelException {
        if (isAlertLevelCorrect(alertLevel)) {
            this.alertLevelHistory.put(new Date(), alertLevel);
        } else {
            throw new UnknowAlertLevelException(alertLevel);
        }
    }

    /**
     * Add a record of alertLevel to the history at the current date
     *
     * @param date Date when the alertLevel was set
     * @param alertLevel int Must be greater than {@value #MIN_ALERT_LVL} and
     * smaller than
     * {@value #MAX_ALERT_LVL} {{@value #ALERT_COOLING}, {@value #ALERT_IDLE}, {@value #ALERT_HEATING}, {@value #ALERT_OVERHEATING}}
     * @throws UnknowAlertLevelException When the parametter is strictly smaller
     * than {@value #MIN_ALERT_LVL} and stritly bigger than
     * {@value #MAX_ALERT_LVL}
     */
    public final void addAlertLevelToHistory(Date date, int alertLevel) throws UnknowAlertLevelException {
        if (isAlertLevelCorrect(alertLevel)) {

            this.alertLevelHistory.put(date, alertLevel);

        } else {
            throw new UnknowAlertLevelException(alertLevel);
        }
    }

    /**
     * getAlertLevelFromHistory return the AlertLevel at a date
     *
     * @param date Date Must be in the past
     * @return int The AlertLevel at the given date
     * @throws NoHistoryDataAvaliableException when there is no entry at the
     * given date
     */
    public int getAlertLevelFromHistory(Date date) throws NoHistoryDataAvaliableException {

        if (this.alertLevelHistory.get(date) != null) {

            return this.alertLevelHistory.get(date);
        } else {
            throw new NoHistoryDataAvaliableException(date);
        }
    }

    /**
     * Get the temperature history
     *
     * @return HashMap(date, Integer) temperatureHistory
     * @throws EmptyHistoryException when there is no temperature recorded yet
     */
    public LinkedHashMap<Date, Integer> getTemperaturesHistory() throws EmptyHistoryException {
        return temperaturesHistory;
    }

    /**
     * Get the temperature at a given date
     *
     * @param date Must be a date in the past
     * @return int The temperature at the given date
     * @throws NoHistoryDataAvaliableException when there is no temperature
     * recorded at the given date
     */
    public int getTemperatureFromHistory(Date date) throws NoHistoryDataAvaliableException {

        if (this.temperaturesHistory.get(date) != null) {
            return this.temperaturesHistory.get(date);
        } else {
            throw new NoHistoryDataAvaliableException(date);
        }

    }

    /**
     * Add a temperature to the temperature history
     *
     * @param currentTemperature int The currentTemperature
     */
    public final void addTemperatureToHistory(int currentTemperature) {
        this.temperaturesHistory.put(new Date(), currentTemperature);
    }

    /**
     * Add a temperature at the given date to the temperature history
     *
     * @param date Date when the temperature was set
     * @param temperature The Temperature at the date
     */
    public void addTemperatureToHistory(Date date, int temperature) {
        this.temperaturesHistory.put(date, temperature);
    }

    /**
     * Check if the given alertLevel is correct
     *
     * @param alertLevel int Must be greater than {@value #MIN_ALERT_LVL} and
     * smaller than
     * {@value #MAX_ALERT_LVL} {{@value #ALERT_COOLING}, {@value #ALERT_IDLE}, {@value #ALERT_HEATING}, {@value #ALERT_OVERHEATING}}
     * @return true if the given alertLevel is correct; False otherwise.
     */
    public boolean isAlertLevelCorrect(int alertLevel) {

        return MIN_ALERT_LVL <= alertLevel && alertLevel <= MAX_ALERT_LVL;

    }

    @Override
    public String toString() {
        return "Model{" + "currentTemperature=" + currentTemperature + ", thresholdTemperature=" + thresholdTemperature + ", alertLevel=" + alertLevel + ", temperaturesHistory=" + temperaturesHistory + ", alertLevelHistory=" + alertLevelHistory + '}';
    }

}
