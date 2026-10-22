package com.infowave.c4p.service.dto;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Date;
import java.util.Calendar;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;

/**
 * The Data Transfer Object for a Techlogin.
 *
 */
/**
 * This class represents a techlogin
 */
public class TechloginDTO implements Serializable
{   

  public static final double FORCED_LOGOUT_FLAG = 1;

  private static Calendar cal;
  private static Date epoch;
  static {
	cal = Calendar.getInstance();
	if( cal != null ) {
		cal.set(1970, 1, 1, 0, 0, 0);
		epoch = cal.getTime();
	}
  }

  private String id = null;
  private String companyid = null;
  private String techcid = null;

  private double flags = 0;
  private double loginid = 0;

  private Date logindatetime = null;
  private Date enddatetime = null;
	
  private String m_loginidStr = null;

  private static Log m_log = LogFactory.getLog(TechloginDTO.class);

  // ------------------------------------------------------- setters and getters
  public String getId()
  {
    return id;
  }

  public void setId(String id)
  {
    this.id = id;
  }

  public String getCompanyid()
  {
    return companyid;
  }

  public void setCompanyid(String id)
  {
    companyid = id;
  }

  public String getTechcid() {
    return techcid;
  }

  public void setTechcid(String techcid) {
    this.techcid = techcid;
  }

  public double getFlags() {
    return flags;
  }

  public void setFlags(double flags) {
    this.flags = flags;
  }

  public double getLoginid() {
    return loginid;
  }

  public void setLoginid(double loginid) {
    this.loginid = loginid;
  }

  public Date getLogindatetime() {
    return logindatetime;
  }

  public void setLogindatetime(Date logindatetime) {
    this.logindatetime = logindatetime;
  }

  public Date getEnddatetime() {
    return enddatetime;
  }

  public void setEnddatetime(Date enddatetime) {
    this.enddatetime = enddatetime;
  }

  // ------------------------------------------------------- 

  public boolean doLoginidsMatch( String lid )
  {
    boolean matches = false;

	Double dblId = new Double( lid );

	matches = dblId.equals( new Double(loginid) );

	return matches; 
  }

  public String getLoginidString()
  {	
	m_loginidStr = Double.toString( loginid );

	return m_loginidStr;
  }

  public static String loginidToString(double loginid)
  {	
	return Double.toString( loginid );
  }

  public static double getLoginDbl( String id ) throws NumberFormatException
  {
	double dblId = Double.parseDouble( id );

	return dblId;
  }

  public boolean isActive()
  {
	boolean isActive = (enddatetime.after( epoch ) == false);

	return isActive;
  }


}

