package dte.javainterface.view;

import dte.javainterface.controller.Controller;
import dte.javainterface.model.Model;
import java.util.Observable;

/**
 *
 * @author <a href="mailto:contact@chrisv.be">Christophe Van Waesberghe
 * (contact@chrisv.be)</a>
 */
public class GuiView extends View {

    Model model;
    Controller controller;

    public GuiView(Controller controller, Model model) {
        super(controller, model);
    }

    @Override
    public void update(Observable o, Object arg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
