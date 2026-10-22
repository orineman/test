package com.infowave.pda.kernel.hostConnector.errors;

/**
 * Created by IntelliJ IDEA.
 * User: Alexey.Ogarkov
 * Date: 24.09.2007
 * Time: 15:10:19
 * To change this template use File | Settings | File Templates.
 */
public class CannotFindDesktop extends Exception {
    public CannotFindDesktop() {super();}
    public CannotFindDesktop(String msg) {super(msg);}
    public CannotFindDesktop(String msg, Throwable t) {super(msg,t);}
    public CannotFindDesktop(Throwable t) {super(t);}
}
