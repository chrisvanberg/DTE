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
package be.chrisv.dte.model;

import be.chrisv.dte.model.Model;
import be.chrisv.dte.exceptions.EmptyHistoryException;
import be.chrisv.dte.exceptions.NoHistoryDataAvaliableException;
import be.chrisv.dte.exceptions.UnknowAlertLevelException;
import static be.chrisv.dte.model.Model.ALERT_IDLE;
import static be.chrisv.dte.model.Model.ALERT_OVERHEATING;
import static be.chrisv.dte.model.Model.MAX_ALERT_LVL;
import static be.chrisv.dte.model.Model.MIN_ALERT_LVL;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import junit.framework.TestCase;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

/**
 * Test class of Model
 *
 */
public class ModelTest extends TestCase {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

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
        Model instance = new Model(15, 20);
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
        Model instance = new Model(15, 20);
        instance.setCurrentTemperature(currentTemperature);

        int expResult = currentTemperature;
        int result = instance.getCurrentTemperature();
        assertEquals(expResult, result);

        try {
            LinkedHashMap<Date, Integer> history = instance.getTemperaturesHistory();
            Date theLastEntry = new ArrayList<>(history.keySet()).get(history.size() - 1);
            try {

                assertEquals(currentTemperature, instance.getTemperatureFromHistory(theLastEntry));
            } catch (NoHistoryDataAvaliableException ex) {
                fail("setCurrentTemperature should have filled the history (NoHistoryDataAvaliableException)");
            }
        } catch (EmptyHistoryException ex) {
            fail("setCurrentTemperature should have filled the history (EmptyHistoryException)");
        }

    }

    /**
     * Test of getThresholdTemperature method, of class Model.
     */
    public void testGetThresholdTemperature() {
        System.out.println("getThresholdTemperature");
        Model instance = new Model(15, 20);
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
        Model instance = new Model(15, 20);
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
        int alertLevel = 1;
        Model instance;
        try {
            instance = new Model(15, 20, alertLevel);
            int expResult = 1;
            int result = instance.getAlertLevel();
            assertEquals(expResult, result);
        } catch (UnknowAlertLevelException ex) {
            fail("The test AlertLevel is unknow: " + alertLevel);
        }

    }

    /**
     * Test of setAlertLevel method, of class Model.
     *
     * @throws be.chrisv.dte.exceptions.UnknowAlertLevelException
     */
    public void testSetAlertLevel() throws UnknowAlertLevelException {
        System.out.println("setAlertLevel");
        int alertLevel = ALERT_OVERHEATING;
        Model instance = new Model(15, 20);
        instance.setAlertLevel(alertLevel);

        int expResult = alertLevel;
        int result = instance.getAlertLevel();
        assertEquals(expResult, result);

        try {
            instance.setAlertLevel(MAX_ALERT_LVL + 1);
            fail("Should have throwned an UnknowAlertLevelException");
        } catch (UnknowAlertLevelException e) {
            assertTrue(e.getMessage().contains("Given Alert Level is not defined!"));
        }
    }

    /**
     * Test of getTemperaturesHistory method, of class Model.
     */
    public void testGetTemperaturesHistory() {
        System.out.println("getTemperaturesHistory");
        Model instance = new Model(15, 20);

        LinkedHashMap<Date, Integer> result;

        try {
            result = instance.getTemperaturesHistory();
            assertNotNull(result);
        } catch (EmptyHistoryException ex) {
            fail("Should have throwned an EmptyHistoryException");
        }

    }

    /**
     * Test of addTemperatureToHistory method, of class Model.
     */
    public void testAddTemperatureToHistory_int() {
        System.out.println("addTemperatureToHistory");
        int temperature = 12;
        Model instance = new Model(15, 20);
        instance.addTemperatureToHistory(temperature);

        try {
            LinkedHashMap<Date, Integer> history = instance.getTemperaturesHistory();
            Date theLastEntry = new ArrayList<>(history.keySet()).get(history.size() - 1);
            try {

                assertEquals(temperature, instance.getTemperatureFromHistory(theLastEntry));
            } catch (NoHistoryDataAvaliableException ex) {
                Logger.getLogger(ModelTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (EmptyHistoryException ex) {
            fail("GetTemperatureFromHistory have thrown an EmptyHistoryException. Unable to complete the test.");
        }
    }

    /**
     * Test of addTemperatureToHistory method, of class Model.
     */
    public void testAddTemperatureToHistory_Date_int() {
        System.out.println("addTemperatureToHistory");
        Date date = new Date();
        int temperature = 17;
        Model instance = new Model(15, 20);
        instance.addTemperatureToHistory(date, temperature);

        try {
            assertEquals(temperature, instance.getTemperatureFromHistory(date));
        } catch (NoHistoryDataAvaliableException ex) {
            fail("GetTemperatureFromHistory have thrown an EmptyHistoryException. Unable to complete the test.");
        }
    }

    /**
     * Test of getTemperatureFromHistory method, of class Model.
     */
    public void testGetTemperatureFromHistory() {
        System.out.println("getTemperatureFromHistory");
        Date date = new Date();
        Model instance = new Model(15, 20);

        instance.addTemperatureToHistory(date, 25);
        
        try {
           assertEquals(instance.getTemperatureFromHistory(date), 25);
        } catch (NoHistoryDataAvaliableException ex) {
            Logger.getLogger(ModelTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        instance = new Model();

        try {
            instance.getTemperatureFromHistory(date);
            fail("Should have throwned an NoTemperatureAvaliableException");
        } catch (NoHistoryDataAvaliableException ex) {
            assertTrue(ex.getMessage().contains("There is no data recorded at this date!"));
        }
    }

    /**
     * Test of getAlertLevelHistory method, of class Model.
     */
    public void testGetAlertLevelHistory() {
        System.out.println("getAlertLevelHistory");
        Model instance = new Model(15, 20);

        try {
            LinkedHashMap<Date, Integer> result = instance.getTemperaturesHistory();
            assertNotNull(result);
        } catch (EmptyHistoryException ex) {
            fail("Should have throwned an EmptyHistoryException");
        }

    }

    /**
     * Test of addAlertLevelToHistory method, of class Model.
     */
    public void testAddAlertLevelToHistory_int() {
        System.out.println("addAlertLevelToHistory");

        Model instance = new Model(15, 20);

        for (int i = -1; i < 10; i++) {
            try {
                instance.addAlertLevelToHistory(i);
            } catch (UnknowAlertLevelException ex) {
                if (instance.isAlertLevelCorrect(i)) {
                    fail("Should not have thrown an UnknowAlertLevelException"+"Message:"+i);
                }
            }
        }

    }

    /**
     * Test of addAlertLevelToHistory method, of class Model.
     */
    public void testAddAlertLevelToHistory_Date_int() {
        System.out.println("addAlertLevelToHistory");
        Date date = new Date();
        int alertLevel = 3;
        Model instance = new Model();
        try {
            instance.addAlertLevelToHistory(date, alertLevel);
        } catch (UnknowAlertLevelException ex) {
            fail("Should not have thrown an UnknowAlertLevelException");
        }

        try {
            instance.addAlertLevelToHistory(date, 6);
            fail("Should have thrown an UnknowAlertLevelException");
        } catch (UnknowAlertLevelException ex) {
            assertTrue(ex.getMessage().contains("Given Alert Level is not defined!"));
        }

        try {
            int result = instance.getAlertLevelFromHistory(date);

            assertEquals(alertLevel, result);
        } catch (NoHistoryDataAvaliableException ex) {
            fail("getAlertLevelFromHistory have thrown an NoHistoryDataAvaliableException. Unable to complete the test");
        }

    }

    /**
     * Test of isAlertLevelCorrect method, of class Model.
     */
    public void testIsAlertLevelCorrect() {
        System.out.println("isAlertLevelCorrect");
        Model instance = new Model();
        for (int i = -1; i < 10; i++) {
            if (MIN_ALERT_LVL <= i && i <= MAX_ALERT_LVL) {
                assertTrue(instance.isAlertLevelCorrect(i));
            } else {
                assertFalse(instance.isAlertLevelCorrect(i));
            }
        }

    }

    /**
     * Test of getAlertLevelFromHistory method, of class Model.
     */
    public void testGetAlertLevelFromHistory() {
        System.out.println("getAlertLevelFromHistory");

        Model instance = new Model(15, 20);

        int expResult = ALERT_IDLE;
        int result;
        try {
            instance.setAlertLevel(ALERT_IDLE);

            try {
                LinkedHashMap<Date, Integer> history = instance.getAlertLevelHistory();
               
                Date theLastEntry = null;
      for(Date key : history.keySet()){
        theLastEntry = key;
      }
                try{
                try {
                    result = instance.getAlertLevelFromHistory(theLastEntry);
                    assertEquals(expResult, result);
                } catch (NoHistoryDataAvaliableException ex) {
                    fail("Should not have thrown an NoHistoryDataAvaliableException");
                }}
                catch (NullPointerException ex){
                    fail("Should not have thrown a nullPointerException");
                }

                instance = new Model();

                try {
                    instance.getAlertLevelFromHistory(theLastEntry);
                    fail("Should have thrown an NoHistoryDataAvaliableException");
                } catch (NoHistoryDataAvaliableException ex) {
                    assertTrue(ex.getMessage().contains("There is no data recorded at this date!"));
                }

            } catch (EmptyHistoryException ex) {
                fail("getAlertLevelHistory have thrown an EmptyHistoryException. Unable to complete the test");
            }
        } catch (UnknowAlertLevelException ex) {
            fail("test AlertLevel is Unknow (UnknowAlertLevelException)");
        }

    }

}
