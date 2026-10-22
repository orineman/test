package com.infowave.c4p.service.dto;

import java.util.Locale;
import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.infowave.c4p.util.StringManager;

/**
 * The Data Transfer Object for a User. 
 *
 * Created by: Vincent Aumont
 * Copyright 2003 Infowave Software, Inc.
 */
/**
 * This clas represents a user.
  */
public class UserDTO  implements Serializable
{
  private static Log m_log = LogFactory.getLog(UserDTO.class);

  private String id = null;
  private String firstName = null;
  private String lastName = null;
  private String email = null;
  private String regionalSettings = null;
  private Locale locale = null;
  private String username = null;
  private int securityQuestion = 0;
  private String resellerName = null;

  public String getLocaleLanguage()
  {
    String country = "";
    if( locale != null ) {
        country = locale.getLanguage();
    }
    return country;
  }

  public String getId()
  {
    return id;
  }

  public void setId(String id)
  {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getHtmlencodedFirstName()
  {
	return StringManager.htmlencode( (firstName==null?"":firstName) );
  }

  public void setHtmlencodedFirstName( String firstName )
  {
  }

  public String getLinkencodedFirstName()
  {
	return StringManager.linkencode( (firstName==null?"":firstName) );
  }

  public void setLinkencodedFirstName( String firstName )
  {
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName ) {
    this.lastName = lastName;
  }

  public String getHtmlencodedLastName()
  {
	return StringManager.htmlencode( (lastName==null?"":lastName) );
  }

  public void setHtmlencodedLastName( String lastName )
  {
  }

  public String getLinkencodedLastName()
  {
	return StringManager.linkencode( (lastName==null?"":lastName) );
  }

  public void setLinkencodedLastName( String lastName )
  {
  }


  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getHtmlencodedEmail()
  {
	return StringManager.htmlencode( (email==null?"":email) );
  }

  public void setHtmlencodedEmail( String email )
  {
  }

  public String getLinkencodedEmail()
  {
	return StringManager.linkencode( (email==null?"":email) );
  }

  public void setLinkencodedEmail( String email )
  {
  }


  public String getRegionalSettings() {
    return regionalSettings;
  }

  public void setRegionalSettings(String regionalSettings) {
    this.regionalSettings = regionalSettings;
  }

  public Locale getLocale() {
    return locale;
  }

  public void setLocale(Locale locale) {
    this.locale = locale;
  }

  public String getHtmlencodedUsername()
  {
	return StringManager.htmlencode( (username==null?"":username) );
  }

  public void setHtmlencodedUsername( String name )
  {
  }

  public String getLinkencodedUsername()
  {
	return StringManager.linkencode( (username==null?"":username) );
  }

  public void setLinkencodedUsername( String name )
  {
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public int getSecurityQuestion() {
    return securityQuestion;
  }

  public void setSecurityQuestion(int securityQuestion) {
    this.securityQuestion = securityQuestion;
  }

  public String getResellerName()
  {
    return resellerName;
  }

  public void setResellerName(String resellerName)
  {
    this.resellerName = resellerName;
  }


  }
