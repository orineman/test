/*
 * InvalidProtocolData.java
 *
 * Created on 20 Сентябрь 2007 г., 14:10
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.infowave.pda.kernel.hostConnector.errors;

/**
 *
 * @author Alexey.Ogarkov
 */
public class InvalidProtocolData extends Exception{
    public InvalidProtocolData() {super();}
    public InvalidProtocolData(String msg) {super(msg);}
    public InvalidProtocolData(String msg, Throwable t) {super(msg,t);}
    public InvalidProtocolData(Throwable t) {super(t);}
    
}
