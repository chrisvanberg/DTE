package dte.javainterface.controller;

import dte.javainterface.view.View;
import junit.framework.TestCase;

public class ControllerTest extends TestCase {
    
    public ControllerTest(String testName) {
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
     * Test of addView method, of class Controller.
     */
    public void testAddView() {
        System.out.println("addView");
        View view = null;
        Controller instance = null;
        instance.addView(view);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of startGame method, of class Controller.
     */
    public void testStartGame() throws Exception {
        System.out.println("startGame");
        Controller instance = null;
        instance.startGame();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
