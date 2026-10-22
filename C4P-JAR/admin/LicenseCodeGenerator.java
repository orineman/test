package com.infowave.c4p.admin;

import java.util.Random;
import java.security.MessageDigest;

public class LicenseCodeGenerator
{
  /** The size of random string we generate */
  private static int  RANDOM_STRING_SIZE = 32;

  /** numbers of characters in the license  code */
  private int m_size;

  /** How many random number do we need to generate a license code? */
  private int m_nbRandomNumbersRequired;
  /** Our digester */
  private static MessageDigest md;

  /** Random generator */
  private static Random r = new Random(System.currentTimeMillis());

    static {
    try {
      md = MessageDigest.getInstance("MD5");
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    }


  public LicenseCodeGenerator(int size)
  {
    m_size = size;
    // if teh license is 34-character long, we'll need 2 23-character random nbs.
    m_nbRandomNumbersRequired = (size % RANDOM_STRING_SIZE) + 1;
  }


  /**
   * generates a license code.
   * @return
   */
  public String getCode()
  {
    StringBuffer code = new StringBuffer();
    for (int i = 0;i <m_nbRandomNumbersRequired;i++) {
      code.append(getId());
    }
    return code.substring(0,m_size);
  }

  /**
   * Generates a 32-character random string.
   *
   * @return
   */
  public static synchronized String getId() {
    StringBuffer str = new StringBuffer(256);

    // need to put something together that can generate UNIQUE hashes
    str.append(System.currentTimeMillis());
    md.update(str.toString().getBytes());
    try{
      Thread.currentThread().sleep(5);
    }
    catch(Exception e){
    }
    str.append(r.nextDouble());
    str.append(System.currentTimeMillis());
    md.update(str.toString().getBytes());
    // get the id
    String s = asHex(md.digest());

    return s;
  }

  /**
   * Turns array of bytes into string representing each byte as
   * unsigned hex number.
   *
   * @param hash        Array of bytes to convert to hex-string
   * @return    Generated hex string
   */
  private static String asHex (byte hash[]) {
    StringBuffer buf = new StringBuffer(hash.length * 2);
    int i;

    for (i = 0; i < hash.length; i++) {
      if (((int) hash[i] & 0xff) < 0x10)
        buf.append("0");

      buf.append(Long.toString((int) hash[i] & 0xff, 16));
    }

    return buf.toString();
  }

 // ------------------------------------------------------------ test functions
  public static void main (String[] args)
 {
   int size = 33;
   LicenseCodeGenerator lg = new LicenseCodeGenerator(size);
   String code = lg.getCode();
   System.out.println(code + " (" + code.length()+ " chars)");
 }

}
