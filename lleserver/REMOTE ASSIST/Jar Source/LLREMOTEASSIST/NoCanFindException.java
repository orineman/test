package llremoteassist;

public class NoCanFindException extends Exception
{
  public NoCanFindException (String msg) {super(msg);}
  public NoCanFindException (String msg, Throwable t) {super(msg,t);}
  public NoCanFindException (Throwable t) {super(t);}

}
