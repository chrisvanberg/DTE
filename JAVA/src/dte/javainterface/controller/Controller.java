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
package dte.javainterface.controller;

import dte.javainterface.model.Model;
import dte.javainterface.view.View;
import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

//Constant
import static dte.javainterface.model.Model.BAUDRATE;

/**
 * Controller of the App. Manage all the functionalities
 */
public class Controller {

    private final Model model;
    private View view;

    /**
     * Controller's constructor with a Model in parametter
     *
     * @param model Model to link to this controller
     */
    public Controller(Model model) {
        this.model = model;
    }

    /**
     * Connect a view to this controller
     *
     * @param view View to link to this controller
     */
    public void addView(View view) {
        this.view = view;
    }

    /**
     * Send the new threshold temparature to the connected COM port
     */
    public void sendThreshold() {
        if (this.model.isConnected()) {
            //new Thread(new SerialWriter(this.model, String.valueOf(this.model.getThresholdTemperature()))).start();
            this.model.getUplinkWriter().write(this.model.getThresholdTemperature() + '\r');

            this.model.getUplinkWriter().flush();
        }
    }

    /**
     * Connect the interface to the selected COM port
     *
     * @param selectedPortIdentifier The name of the target COM port
     * @throws PortInUseException When the target COM port is already used
     */
    public void connect(String selectedPortIdentifier) throws PortInUseException {
        try {
            CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(selectedPortIdentifier);
            SerialPort serialPort = (SerialPort) portIdentifier.open("DTE", 2500);
            serialPort.setSerialPortParams(BAUDRATE, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
            serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN | SerialPort.FLOWCONTROL_RTSCTS_OUT);
            this.model.setDownlink(serialPort.getInputStream());
            this.model.setUplink(serialPort.getOutputStream());
            this.model.setUplinkWriter(new PrintWriter(this.model.getUplink()));

            this.model.setConnected(true);

            new Thread(new SerialReader(this.model.getDownlink(), this.model)).start();

        } catch (NoSuchPortException | UnsupportedCommOperationException | IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * 
     */
    private class SerialWriter implements Runnable {

        Model model;
        String message;

        public SerialWriter(Model model, String message) {
            this.model = model;
            this.message = message;
        }

        @Override
        public void run() {
            //System.setProperty("line.separator", "\r");

            this.model.getUplinkWriter().write(message + '\r');

            this.model.getUplinkWriter().flush();

        }

    }

    private class SerialReader implements Runnable {

        private BufferedReader downlinkBuffer;

        public SerialReader(InputStream in, Model model) {
            this.downlinkBuffer = new BufferedReader(new InputStreamReader(in));

        }

        public void run() {
            while (true) {
                String message;
                while (true) {
                    try {
                        message = downlinkBuffer.readLine();

                    } catch (IOException e) {

                        break;
                    }
                    //Print the line read
                    if (message.length() != 0 && message.contains("DTE.")) {

                        String[] messageSplitted = message.split(":");

                        if (messageSplitted[0].equals("DTE.tresh")) {
                            model.setThresholdTemperature(Integer.parseInt(messageSplitted[1]));
                        } else if (messageSplitted[0].equals("DTE.temp")) {
                            model.setCurrentTemperature(Integer.parseInt(messageSplitted[1]));
                        }

                    }

                }
            }

        }

    }
}
