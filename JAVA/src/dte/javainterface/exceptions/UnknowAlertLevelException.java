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
package dte.javainterface.exceptions;

/**
 * UnknowAlertLevelException is triggered when you try to use an AlertLevel that is not implemented
 * 
 */
public class UnknowAlertLevelException extends Exception {

    public UnknowAlertLevelException() {
        super("Given Alert Level is not defined!");
    }

    public UnknowAlertLevelException(int alertLevel){
        super("Given Alert Level is not defined! : "+alertLevel);
    }
    
    public UnknowAlertLevelException(String message) {
        super(message);
    }

    public UnknowAlertLevelException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnknowAlertLevelException(Throwable cause) {
        super(cause);
    }
}
