/*
 * CouldNotFindProtocolJSP.java
 *
 * Created on 20 Сентябрь 2007 г., 14:02
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
public class CouldNotFindProtocolJSP extends IOException{
    public CouldNotFindProtocolJSP() {super();}
    public CouldNotFindProtocolJSP(String msg) {super(msg);}
}
