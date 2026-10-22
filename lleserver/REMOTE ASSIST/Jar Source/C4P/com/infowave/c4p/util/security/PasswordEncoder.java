package com.infowave.c4p.util.security;

/**
 * Interface that must be implemented by concrete password encoders.
 *
 * Created by: Vincent Aumont
 * Copyright 2003 Infowave Software, Inc.
 */
public interface PasswordEncoder
{
  /**
   * Compares a password in clear with an ancoded password.
   * The method encode the clear-text password with its specific algorithm, and
   * compares it with the passed encoded password.
   * 
   * @param clear - the password in clear.
   * @param encoded - the encoded password.
   * @return true if the password in clear, once encoded, matches the encoded
   * password.
   */
  public boolean match(String clear, String encoded);

  /**
   * Encodes a password.
   * @param password the password to encode.
   * @return the encoded password.
   */
  public String encode(String password);

}
