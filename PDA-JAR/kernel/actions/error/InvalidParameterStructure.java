package com.infowave.pda.kernel.actions.error;

/**
 * Created by Alexey.Ogarkov.
 * User: Alexey.Ogarkov
 * Date: 11.03.2008
 * Time: 16:31:26
 * Paccage : com.infowave.pda.kernel.actions.error
 */
public class InvalidParameterStructure extends Exception {
    public InvalidParameterStructure() {super();}
    public InvalidParameterStructure(String msg) {super(msg);}
    public InvalidParameterStructure(String msg, Throwable t) {super(msg,t);}
    public InvalidParameterStructure(Throwable t) {super(t);}
}
