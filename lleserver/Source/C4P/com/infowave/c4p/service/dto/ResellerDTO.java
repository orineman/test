package com.infowave.c4p.service.dto;

import javax.servlet.jsp.JspException;
import java.util.Locale;
import java.util.List;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.io.Serializable;

/**
 * The Data Transfer Object for a Reseller.
 * Created by: Vincent Aumont
 * Copyright 2003 Infowave Software, Inc.
 */
public class ResellerDTO  implements Serializable
 {
  private String id;
  private String name;
  private String hostname;
  private String expiryUrl;
  private Locale defaultLocale;
  private int nbSecurityQuestions;
  private String licenseCodeFormat;
  private String passwordEncoder;
  /** The license code is broken up into several groups for usability reasons
   * (e.g. a 16-digit code could be presenetd to teh user as 4 4-digit groups.
   * The number of elements in teh list is teh number of groups, each element
   * contains the group's size. */
  private List licenseCodeGroups = null;
  private int licenseCodeLength = 0;
 private int nbLicenseCodeGroups;

  public String getId()
  {
    return id;
  }

  public void setId(String id)
  {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
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

  public void setNbSecurityQuestions(int nbSecurityQuestions) {
    this.nbSecurityQuestions = nbSecurityQuestions;
  }

  public String getLicenseCodeFormat() {
    return licenseCodeFormat;
  }

  public void setLicenseCodeFormat(String licenseCodeFormat) {
    this.licenseCodeFormat = licenseCodeFormat;
  }

  public String getPasswordEncoder()
  {
    return passwordEncoder;
  }

  public void setPasswordEncoder(String passwordEncoder)
  {
    this.passwordEncoder = passwordEncoder;
  }

  public List getLicenseCodeGroups() throws NumberFormatException
  {
    if (licenseCodeGroups == null) {
      licenseCodeGroups = new ArrayList(4);
      StringTokenizer tokenizer = new StringTokenizer(licenseCodeFormat,",");
      while (tokenizer.hasMoreTokens()) {
        String val = tokenizer.nextToken();
        int intVal = Integer.parseInt(val);
        licenseCodeLength += intVal;
        licenseCodeGroups.add(new Integer(intVal));
      }
    }
    return licenseCodeGroups;
  }

  public int getLicenseCodeLength()
  {
    getLicenseCodeGroups();
    return licenseCodeLength;
  }

  public int getNbLicenseCodeGroups()
  {
    getLicenseCodeGroups();
    return nbLicenseCodeGroups;
  }
}
