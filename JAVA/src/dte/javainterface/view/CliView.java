package dte.javainterface.view;

import dte.javainterface.controller.Controller;
import dte.javainterface.model.Model;
import java.util.Observable;

public class CliView extends View {

    public CliView(Controller controller, Model model) {
        super(controller, model);
    }

    @Override
    public void update(Observable o, Object arg) {
        
        draw();
    }
    
    public void draw(){
        while(true){
        System.out.println("Hello World !");
        }
    }

}
