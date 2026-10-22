package com.infowave.pda.kernel.objects.errors;

/**
 * Created by IntelliJ IDEA.
 * User: Alexey.Ogarkov
 * Date: 24.09.2007
 * Time: 15:10:19
 * To change this template use File | Settings | File Templates.
 */
public class InvalidNumberOfParams extends Exception {
    public InvalidNumberOfParams() {super();}
    public InvalidNumberOfParams(String msg) {super(msg);}
    public InvalidNumberOfParams(String msg, Throwable t) {super(msg,t);}
    public InvalidNumberOfParams(Throwable t) {super(t);}
}
