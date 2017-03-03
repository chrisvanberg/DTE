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

import java.util.Date;
import java.util.HashMap;
import junit.framework.TestCase;

/**
 * Test class of Model
 * 
 */
public class ModelTest extends TestCase {
    
    public ModelTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of getCurrentTemperature method, of class Model.
     */
    public void testGetCurrentTemperature() {
        System.out.println("getCurrentTemperature");
        Model instance = new Model(15,20,0);
        int expResult = 15;
        int result = instance.getCurrentTemperature();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setCurrentTemperature method, of class Model.
     */
    public void testSetCurrentTemperature() {
        System.out.println("setCurrentTemperature");
        int currentTemperature = 13;
        Model instance = new Model(15,20,0);
        instance.setCurrentTemperature(currentTemperature);
        
        int expResult = currentTemperature;
        int result = instance.getCurrentTemperature();
        assertEquals(expResult, result);
    }

    /**
     * Test of getThresholdTemperature method, of class Model.
     */
    public void testGetThresholdTemperature() {
        System.out.println("getThresholdTemperature");
        Model instance = new Model(15,20,0);
        int expResult = 20;
        int result = instance.getThresholdTemperature();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setThresholdTemperature method, of class Model.
     */
    public void testSetThresholdTemperature() {
        System.out.println("setThresholdTemperature");
        int thresholdTemperature = 30;
        Model instance = new Model(15,20,0);
        instance.setThresholdTemperature(thresholdTemperature);
        
        int expResult = thresholdTemperature;
        int result = instance.getThresholdTemperature();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getAlertLevel method, of class Model.
     */
    public void testGetAlertLevel() {
        System.out.println("getAlertLevel");
        Model instance = new Model(15,20,1);
        int expResult = 1;
        int result = instance.getAlertLevel();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setAlertLevel method, of class Model.
     */
    public void testSetAlertLevel() throws Exception {
        System.out.println("setAlertLevel");
        int alertLevel = 3;
        Model instance = new Model(15,20,0);
        instance.setAlertLevel(alertLevel);
        
        int expResult = alertLevel;
        int result = instance.getAlertLevel();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTemperaturesHistory method, of class Model.
     */
    public void testGetTemperaturesHistory() {
        System.out.println("getTemperaturesHistory");
        Model instance = new Model(15,20,0);
        
        //HashMap<Date, Integer> result = instance.getTemperaturesHistory();
        //assertNotNull(result);
        
    }

    /**
     * Test of addTemperatureToHistory method, of class Model.
     */
    public void testAddTemperatureToHistory_int() {
        System.out.println("addTemperatureToHistory");
        int currentTemperature = 0;
        Model instance = null;
        instance.addTemperatureToHistory(currentTemperature);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addTemperatureToHistory method, of class Model.
     */
    public void testAddTemperatureToHistory_Date_int() {
        System.out.println("addTemperatureToHistory");
        Date date = null;
        int temperature = 0;
        Model instance = null;
        instance.addTemperatureToHistory(date, temperature);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTemperatureFromHistory method, of class Model.
     */
    public void testGetTemperatureFromHistory() throws Exception {
        System.out.println("getTemperatureFromHistory");
        Date date = null;
        Model instance = null;
        int expResult = 0;
        int result = instance.getTemperatureFromHistory(date);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
