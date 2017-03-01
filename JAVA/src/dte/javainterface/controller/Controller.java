package dte.javainterface.controller;

import dte.javainterface.model.Model;
import dte.javainterface.view.View;

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
