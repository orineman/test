package com.infowave.c4p.model;

import java.util.Locale;
import java.util.Date;

/**
 * This class represents a Reseller.
 * 
 * Created by: Vincent Aumont
 * Copyright 2003 Infowave Software, Inc.
 */
public class Reseller {

  private static final long NB_MILLI_SECONDS_IN_DAY = 24 * 3600 *1000;
  private String id = null;
  private String code;
  private String name;
  /** the locale we use if we don't have a locale for the user */
  private Locale defaultLocale = null;
  /** The hostname the portal uses for this reseller's urls */
  private String hostname;
  /** Ecomm site  URL*/
  private String expiryUrl = null;
  /** The number of security questions for this reseller */
  private int nbSecurityQuestions;
  /** The license code format. This string specifies the number of character in
   * the license code, as well as the number of groups and and their length.
   * The format as the form <bn char>, <nb char in group1,... <nb char in group2>
   * For instance, if a reseller has 16-character license codes, and they want
   * their user to enter license codes in 4-character groups, the format will
   * be: 16,4,4,4,4
   */
  private String licenseCodeFormat;
  /** Number of days a demo account is valid */
  private int demoDuration;
  /** name of the class we use to encode the users' passwords */
  private String passwordEncoder;

  // ---------------------------------------------------------- Getters/Setters
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String resellerCode) {
    this.code = resellerCode;
  }

  public String getName() {
    return name;
  }

  public void setName(String resellerName) {
    this.name = resellerName;
  }

  public String getHostname() {
    return hostname;
  }

  public void setHostname(String hostname) {
    this.hostname = hostname;
  }

  public String getExpiryUrl() {
    return expiryUrl;
  }

  public void setExpiryUrl(String expiryUrl) {
    this.expiryUrl = expiryUrl;
  }

  public Locale getDefaultLocale() {
    return defaultLocale;
  }

  public void setDefaultLocale(Locale defaultLocale) {
    this.defaultLocale = defaultLocale;
  }

  public int getNbSecurityQuestions() {
    return nbSecurityQuestions;
  }

  public void setNbSecurityQuestions(int nbSecurityQuetions) {
    this.nbSecurityQuestions = nbSecurityQuetions;
  }

  public String getLicenseCodeFormat() {
    return licenseCodeFormat;
  }

  public void setLicenseCodeFormat(String licenseCodeFormat) {
    this.licenseCodeFormat = licenseCodeFormat;
  }

  public int getDemoDuration() {
    return demoDuration;
  }

  public void setDemoDuration(int demoDuration) {
    this.demoDuration = demoDuration;
  }
  public String getPasswordEncoder()
  {
    return passwordEncoder;
  }

  public void setPasswordEncoder(String passwordEncoder)
  {
    this.passwordEncoder = passwordEncoder;
  }
  // ---------------------------------------------------------- Public methods

  /**
   * Given a start date, returns when a demo account will expire.
   * @param startDate - the demo account's start date.
   * @return Date at which the demo account will expire.
   */
  public Date getDemoExpiryDate(Date startDate)
  {
    return new Date(startDate.getTime() + (demoDuration * NB_MILLI_SECONDS_IN_DAY));
  }

  /**
   * For debugging purposes.
   * @return
   */
  public String toString()
  {
    StringBuffer sb = new StringBuffer();
    sb.append("Reseller - ");
    sb.append("code: ").append(code).append(" ");
    return sb.toString();
  }
}
