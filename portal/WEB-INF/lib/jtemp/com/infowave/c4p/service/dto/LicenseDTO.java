package com.infowave.c4p.service.dto;

import java.io.Serializable;

/**
 * The Data Transfer Object for a License. 
 */
public class LicenseDTO implements Serializable
{
  private String code;
  private int numAvailableUses;
  private int remainingDays;
  private boolean fixedTerm;
  private int licenseType;

  public String getCode()
  {
    return code;
  }

  public void setCode(String code)
  {
    this.code = code;
  }

  public int getLicenseType()
  {
    return licenseType;
  }

  public void setLicenseType(int licenseType)
  {
    this.licenseType = licenseType;
  }

  public int getRemainingDays()
  {
    return remainingDays;
  }

  public void setRemainingDays(int remainingDays)
  {
    this.remainingDays = remainingDays;
  }

  public boolean isFixedTerm()
  {
    return fixedTerm;
  }

  public void setFixedTerm(boolean fixedTerm)
  {
    this.fixedTerm = fixedTerm;
  }

  public int getNumAvailableUses() {
    return numAvailableUses;
  }

  public void setNumAvailableUses(int numAvailableUses) {
    this.numAvailableUses = numAvailableUses;
  }
}
