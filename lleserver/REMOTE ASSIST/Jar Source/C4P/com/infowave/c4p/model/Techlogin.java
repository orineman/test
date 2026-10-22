package com.infowave.c4p.model;

import java.util.Date;
import java.util.Calendar;

import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;

/**
 * This class represents a Techlogin.
 *
 */
public class Techlogin
{

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


  private static Log m_log = LogFactory.getLog(Techlogin.class);

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

// ------------------------------------------------
  public boolean isActive()
  {
	boolean isActive = (enddatetime.after( epoch ) == false);

	return isActive;
  }



}