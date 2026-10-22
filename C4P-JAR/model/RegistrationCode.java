package com.infowave.c4p.model;

/**
 * This class reprsents a Reg Code.
 * 
 * Created by: Vincent Aumont
 * Copyright 2003 Infowave Software, Inc.
 */
public class RegistrationCode
{

  private String id = null;
  private String code;
  private int trialDuration;
  private String description;
  private int maxUses =1;
  private int uses = 0;
  private Reseller reseller;


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public int getTrialDuration() {
    return trialDuration;
  }

  public void setTrialDuration(int trialDuration) {
    this.trialDuration = trialDuration;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public int getMaxUses() {
    return maxUses;
  }

  public void setMaxUses(int maxUses) {
    this.maxUses = maxUses;
  }

  public int getUses() {
    return uses;
  }

  public void setUses(int nbUses) {
    this.uses = nbUses;
  }

  public Reseller getReseller() {
    return reseller;
  }

  public void setReseller(Reseller reseller) {
    this.reseller = reseller;
  }

  public String toString()
  {
    StringBuffer sb = new StringBuffer();
    sb.append("Reg Code - ");
    sb.append("code: ").append(code).append(" ");
    sb.append("duration: ").append(trialDuration).append(" ");
    sb.append("max uses: ").append(maxUses).append(" ");
    sb.append("uses: ").append(uses).append(" ");

    return sb.toString();
  }

}
