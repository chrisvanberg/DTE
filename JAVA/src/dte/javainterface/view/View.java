package dte.javainterface.view;

import dte.javainterface.controller.Controller;
import dte.javainterface.model.Model;
import java.util.Observable;
import java.util.Observer;

public abstract class View extends javax.swing.JFrame implements Observer {

    Model model;
    Controller controller;

    public View(Controller controller, Model model) {
        this.model = model;
        this.controller = controller;
    }
    
    public View(){
        
    }

    /**
     * Add an observable object
     *
     * @param observable Observable object
     */
    public void addObservable(Observable observable) {
        observable.addObserver(this);
    }
}
