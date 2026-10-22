/*
 * InvalidInpitParameters.java
 *
 * Created on 21 Сентябрь 2007 г., 16:26
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.infowave.pda.kernel.hostConnector.errors;

import java.io.IOException;

/**
 *
 * @author Alexey.Ogarkov
 */
public class InvalidInputParameters extends IOException{
    
    /** Creates a new instance of InvalidInpitParameters */
    public InvalidInputParameters() {super();}
    public InvalidInputParameters(String msg) {super(msg);}
    
}
