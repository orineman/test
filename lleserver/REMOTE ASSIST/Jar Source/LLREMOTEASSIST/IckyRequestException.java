package llremoteassist;

public class IckyRequestException extends Exception
{
  public IckyRequestException (String msg) {super(msg);}
  public IckyRequestException (String msg, Throwable t) {super(msg,t);}
  public IckyRequestException (Throwable t) {super(t);}

}
