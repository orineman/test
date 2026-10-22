/*
 * InvalidHostResponseXML.java
 *
 * Created on 20 Сентябрь 2007 г., 14:46
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
public class InvalidHostResponseXML extends IOException{
    
    public InvalidHostResponseXML() {super();}
    public InvalidHostResponseXML(String msg) {super(msg);}
    
}
