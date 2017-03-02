package dte.javainterface.view;

import java.util.Observable;
import junit.framework.TestCase;

public class CliViewTest extends TestCase {
    
    public CliViewTest(String testName) {
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
     * Test of update method, of class CliView.
     */
    public void testUpdate() {
        System.out.println("update");
        Observable o = null;
        Object arg = null;
        CliView instance = null;
        instance.update(o, arg);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of draw method, of class CliView.
     */
    public void testDraw() {
        System.out.println("draw");
        CliView instance = null;
        instance.draw();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
