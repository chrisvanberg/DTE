package dte.javainterface.controller;

import dte.javainterface.model.Model;
import dte.javainterface.view.View;

/**
 *
 * @author <a href="mailto:contact@chrisv.be">Christophe Van Waesberghe
 * (contact@chrisv.be)</a>
 */
public class Controller {
    Model model;
    View view;
    
    public Controller(Model model){
        this.model=model;
    }
    
    public void addView(View view){
        this.view=view;
    }
    
}
