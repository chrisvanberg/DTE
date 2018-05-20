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
package be.chrisv.dte.exceptions;

import java.util.Date;

/**
 * NotTemperatureAvaliableException is triggered when you try to access to a date when there is no temperature recorded
 * 
 */
public class NoHistoryDataAvaliableException extends Exception {
 

    public NoHistoryDataAvaliableException() {
        super("There is no data recorded at this date!");
    }
    
    public NoHistoryDataAvaliableException(Date date){
        super("There is no data recorded at this date! :"+date.toString());
    }

    public NoHistoryDataAvaliableException(String message) {
        super(message);
    }

    public NoHistoryDataAvaliableException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoHistoryDataAvaliableException(Throwable cause) {
        super(cause);
    }
}
