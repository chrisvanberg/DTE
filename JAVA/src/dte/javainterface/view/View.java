package dte.javainterface.view;

import dte.javainterface.controller.Controller;
import dte.javainterface.model.Model;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author <a href="mailto:contact@chrisv.be">Christophe Van Waesberghe
 * (contact@chrisv.be)</a>
 */
public abstract class View implements Observer {

    Model model;
    Controller controller;

    public View(Controller controller, Model model) {
        this.model = model;
        this.controller = controller;
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
