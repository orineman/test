package llremoteassist;

public class WayBadException extends Exception
{
  public WayBadException (String msg) {super(msg);}
  public WayBadException (String msg, Throwable t) {super(msg,t);}
  public WayBadException (Throwable t) {super(t);}

}
