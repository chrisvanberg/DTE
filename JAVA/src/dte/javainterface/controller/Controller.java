package dte.javainterface.controller;

import dte.javainterface.model.Model;
import dte.javainterface.view.View;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controller {

    private final Model model;
    private View view;

    public Controller(Model model) {
        this.model = model;
    }

    public void addView(View view) {
        this.view = view;
    }

    public void startGame() throws InterruptedException {
        
        this.view.update(null, "App Started");
        
        Thread dteLoop = new Thread(new DTELoop());
        dteLoop.start();
    }
    
    private class DTELoop implements Runnable {

        public DTELoop() {
        }

        public void run() {

            while (true) {

                try {
                      System.out.println("Game Loop");
                    Thread.sleep(2000);
                } catch (NullPointerException e) {

                } catch (InterruptedException ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        }
    }

}
