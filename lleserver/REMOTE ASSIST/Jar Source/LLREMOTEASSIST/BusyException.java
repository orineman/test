package llremoteassist;

public class BusyException extends Exception
{
  public BusyException (String msg) {super(msg);}
  public BusyException (String msg, Throwable t) {super(msg,t);}
  public BusyException (Throwable t) {super(t);}

}
