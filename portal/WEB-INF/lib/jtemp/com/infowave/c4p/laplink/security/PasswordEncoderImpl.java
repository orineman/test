package com.infowave.c4p.laplink.security;

import com.infowave.c4p.util.security.PasswordEncoder;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;

import java.security.MessageDigest;

import sun.security.provider.MD5;




/**
 * Created by: Vincent Aumont
 * Copyright 2003 Infowave Software, Inc.
 */
public class PasswordEncoderImpl implements PasswordEncoder
{
  /** The class's logger */
  private static Log m_log = LogFactory.getLog(PasswordEncoderImpl.class);

  // MD5 digester
  private static MessageDigest m_md5;

  // Whether the password is case sensitive.
  static private final boolean CASE_SENSITIVE = false;

  static {
    try {
      m_md5 = MessageDigest.getInstance("MD5");
    }
    catch (Exception e) {
      System.out.println("ERROR: Cannot get a MD5 digester");
    }
  }

  /**
   * Compares a password in clear with an ancoded password.
   *
   * @param clear - the password in clear.
   * @param encoded - the encoded password.
   * @return true if the password in clear, once encoded, matches the encoded
   * password.
   */
  public boolean match(String clear, String encoded)
  {
    String clearEncoded = encode(clear);
    return (clearEncoded.equals(encoded));
  }

  /**
   * Encodes a password.
   * @param password the password to encode.
   * @return the encoded password.
   */
  public String encode(String password)
  {
    //return encodeMD5(password);
    return hash(password,CASE_SENSITIVE);
  }

  /**
   * MD5-encode a string
   * @param input
   * @return the encode string
   */
  private static String encodeMD5(String input)
  {
    m_md5.update(input.getBytes());
    String md5 = asHex(m_md5.digest());
    return md5;

  }
  /**
   * Converts an array of bytes a a string odf hex.
   * @param hash
   * @return
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


  /**
   * Lalplink's encoding algorithm.
   * @param password
   * @param caseSensitive
   * @return
   */
  protected String hash(String password, boolean caseSensitive)
  {
    if(!caseSensitive)
      password = password.toLowerCase();
    int len = Math.max(password.length(), 8);
    String md5h = encodeMD5("36126823628:" + password);
    String h = md5h;
    if(h.length() > 12)
      h = h.substring(0, 12);
    return "_hs_" + h;

  }

  // ------------------------------------------------------------- test methods
  public static void main(String args[])
  {
    PasswordEncoderImpl enc = new PasswordEncoderImpl();
    String password = "infowave";
    System.out.println(enc.encode(password));
  }


}
