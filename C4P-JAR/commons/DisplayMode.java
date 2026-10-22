package com.infowave.c4p.commons;

import java.security.InvalidParameterException;
import java.io.Serializable;

/**
 * Created by: Vincent Aumont
 * Copyright 2003 Infowave Software, Inc.
 */
/**
 * This enum represents the available display modes for the portal application.
 */
public class DisplayMode implements Comparable, Serializable
 {
   private static final String CODE_LARGE = "L";
  private static final String CODE_MEDIUM = "M";
  private static final String CODE_SMALL = "S";
  private static final String CODE_XML = "X";

  /** PC View */
  public static final DisplayMode PC = new DisplayMode (0,"pc",CODE_LARGE);
  /** PDA View */
  public static final DisplayMode PDA = new DisplayMode (1,"pda",CODE_MEDIUM);
  /* Phone View */
  public static final DisplayMode PHONE = new DisplayMode (2,"phone",CODE_SMALL);
  /* XML View */
  public static final DisplayMode XML = new DisplayMode (3,"xml",CODE_XML);


  /** Unique value */
  private int value;
  /* a descriptive name */
  private String name;
  /** The code the use in the urls (L,M, X, or S) */
  private String code;

/**
 * Private constructor.
 * @param value
 * @param name
 * @param code
 */
  private DisplayMode(int value, String name, String code)
  {
    this.value = value;
    this.name = name;
    this.code = code;
  }

  /**
   * Returns the display mode's code.
   */
  public String getCode()
  {
    return code;
  }
  /**
   * Returns the display mode's name.
   */
  public String getName()
  {
    return name;
  }

  /**
   * Returns all available display modes.
   *
   * @return
   */
  public static DisplayMode[] getAll()
  {
    DisplayMode[] modes = new DisplayMode[4];
    modes[0] = PC;
    modes[1] = PDA;
    modes[2] = PHONE;
    modes[3] = XML;
    return modes;
  }
  /**
   * Gets the mode from a code.
   *
   * @param code - a code.
   * @return the display mode for that code.
   */
  public static DisplayMode getFromCode(String code)
  {
    DisplayMode mode = null;
    if (CODE_LARGE.equals(code)) {
      mode = PC;
    } else if (CODE_MEDIUM.equals(code)) {
      mode = PDA;
    } else if (CODE_SMALL.equals(code)){
      mode = PHONE;
    } else if (CODE_XML.equals(code)){
      mode = XML;
    } else {
      throw new InvalidParameterException("getFromCode - Invalid code: " + code);
    }
    return mode;
  }

  public boolean equals(Object o)
  {
    DisplayMode mode = (DisplayMode) o;
    return (mode.value == this.value);
  }

  /**
   * Compares this object with the specified object for order.  Returns a
   * negative integer, zero, or a positive integer as this object is less
   * than, equal to, or greater than the specified object.<p>
   *
   * @param   o the Object to be compared.
   * @return  a negative integer, zero, or a positive integer as this object
   *		is less than, equal to, or greater than the specified object.
   *
   * @throws ClassCastException if the specified object's type prevents it
   *         from being compared to this Object.
   */
  public int compareTo(Object o)
  {
    DisplayMode mode = (DisplayMode) o;
    // We base is on the fact that the highest display mode has the lowest value
    return (this.value - mode.value);
  }

  /**
   * Converts the object to a string.
   */
  public String toString() {
    return code;
  }
}
