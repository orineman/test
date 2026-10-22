package test;

import java.net.URLEncoder;

/**
 * Created by IntelliJ IDEA.
 * User: vincent
 * Date: Dec 9, 2003
 * Time: 12:03:57 PM
 * To change this template use Options | File Templates.
 */
public class Test
{
  private static void testUrlEncoder() throws Exception
  {
    String url = "60's";
    System.out.println("encoded: "  + URLEncoder.encode(url,"UTF-8"));
  }

  public static void main(String[] args)
  {
    try {
      testUrlEncoder();
    }
    catch(Exception e){e.printStackTrace();}


  }
}
