package com.infowave.c4p.service.dto;

import java.io.Serializable;

import java.util.Locale;

/**
 * The Data Transfer Object for a User Registration.
 * 
 * Created by: Vincent Aumont
 * Copyright 2003 Infowave Software, Inc.
 */
public class UserRegistrationDTO  implements Serializable
{
  private String username;
  private String password;
  private String email;
  private String firstname;
  private String lastname;
  private int securityQuestion;
  private String securityAnswer;
  private String did;
  private String resellerId;
  private String pcName;
  private String countryCode;
  private Locale locale = null;

  public Locale getLocale() {
     return locale;
  }

  public void setLocale( Locale locale ) {
     this.locale = locale;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

   public String getFirstname() {
    return firstname;
  }

  public String getLastname() {
    return lastname;
  }

   public String getCountryCode() {
    return countryCode;
  }

  public void setCountryCode(String code) {
    this.countryCode= code;
  }

 public void setFirstname(String firstname) {
    this.firstname= firstname;
  }

  public void setLastname(String lastname) {
    this.lastname= lastname;
  }

 public void setEmail(String email) {
    this.email = email;
  }

  public int getSecurityQuestion() {
    return securityQuestion;
  }

  public void setSecurityQuestion(int securityQuestion) {
    this.securityQuestion = securityQuestion;
  }

  public String getSecurityAnswer() {
    return securityAnswer;
  }

  public void setSecurityAnswer(String securityAnswer) {
    this.securityAnswer = securityAnswer;
  }

  public String getDid() {
    return did;
  }

  public void setDid(String did) {
    this.did = did;
  }

  public String getResellerId() {
    return resellerId;
  }

  public void setResellerId(String resellerId) {
    this.resellerId = resellerId;
  }

  public String getPcName() {
    return pcName;
  }

  public void setPcName(String pcName) {
    this.pcName = pcName;
  }
}
