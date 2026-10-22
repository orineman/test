package com.infowave.c4p.util;

import java.security.MessageDigest;
import java.util.Random;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * This class is responsible for generating unique Ids.
 */
public class IdGenerator
{
  private static MessageDigest md;
  private static Random r = new Random(System.currentTimeMillis());
  private static Log m_log = LogFactory.getLog(IdGenerator.class);

  static {
    try {
      md = MessageDigest.getInstance("MD5");
    }
    catch (Exception e) {
    }
  }

  /**
   *  Generates a unique Id.
   * 
   * @return
   */
  public static synchronized String getId() {
    StringBuffer str = new StringBuffer(256);
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

  public static synchronized String getPassword() {
    StringBuffer str = new StringBuffer(256);
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
    String s = asPassword(md.digest());

    return s;
  }

  public static synchronized String getCid() {
    StringBuffer str = new StringBuffer(256);
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
    String s = asCid(md.digest());

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

  private static final String CIDCHARS_ALLOWED = "abcdefghijklmnopqrstuvwxyz1234567890";
  private static String asCid (byte hash[]) {
    StringBuffer buf = new StringBuffer(hash.length);
    char ch;

    for (int i = 0; i < hash.length; i++) {
	  ch = (char)hash[i];
      if( CIDCHARS_ALLOWED.indexOf( ch ) != -1 ) {
        buf.append(ch);
	  }

    }

    return buf.toString();
  }

  private static final String PWDCHARS_ALLOWED = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890~!@$^*()-_=|<>,.?:";
  private static String asPassword (byte hash[]) {
    StringBuffer buf = new StringBuffer(hash.length);
    char ch;

    for (int i = 0; i < hash.length; i++) {
	  ch = (char)hash[i];
      if( PWDCHARS_ALLOWED.indexOf( ch ) != -1 ) {
        buf.append(ch);
	  }

    }

    return buf.toString();
  }

  public static void main(String[] args) {
    String str;

    System.out.println("Test:\n-----\n\n");
    for (int i = 0;i<10;i++) {
      str = getId();
      System.out.println(str);
    }
  }

}
