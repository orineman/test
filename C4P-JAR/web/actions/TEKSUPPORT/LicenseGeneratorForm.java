package com.infowave.c4p.web.actions.teksupport;

import org.apache.struts.action.ActionForm;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;


public class LicenseGeneratorForm extends ActionForm
{
    private static Log m_log;

    static 
    {
        m_log = LogFactory.getLog(com.infowave.c4p.web.actions.teksupport.LicenseGeneratorForm.class);
    }

/*
  private String error = null;
  private boolean wasError = false;
  private boolean wasUpdated = false;
*/

  private String totalCount = "1000";
  private int currentCount = 0;
  private String days = "365";
  private String rc = "N";
  private String boxed = "N";
  private String emailAddress = null;
  private String emailText = null;
  private String maxUses = "1";
  private String type = null;
  private String reseller = null;
  private boolean isCleared = false;

  public String getReseller() { return reseller; }
  public void setReseller( String rs ) { reseller = rs; }
  public String getType() { return type; }
  public void setType( String t ) { type = t; }

  public boolean isRC() { 
	boolean bRc = false;
	if( rc.equals("Y") || rc.equals("y") ) bRc = true; 
	return bRc;
  }

  public boolean isSubscription() { 
	boolean bSubscription = false;
	if( boxed.equals("N") || boxed.equals("n") ) bSubscription = true; 
	return bSubscription;
  }

  public String getMaxUses() { return maxUses; }

  public String getRc() { return rc; }

  public String getBoxed() { return boxed; }

  public String getEmailAddress() { return emailAddress; }

  public String getEmailText() { return emailText; }

  public int getCurrentCount() { return currentCount; }

  public String getTotalCount() { return totalCount; }

  public String getDays() { return days; }

  public void setRc(String rc)
  {
    this.rc = rc.trim();
  }

  public void setMaxUses(String maxUses)
  {
    this.maxUses = maxUses.trim();
  }

  public void setEmailAddress(String emailAddress)
  {
    this.emailAddress = emailAddress.trim();
    this.emailAddress.replace( ';', ',' );
  }

  public void setEmailText(String emailText)
  {
    this.emailText = emailText.trim();
  }

  public void setBoxed(String boxed)
  {
    this.boxed= boxed.trim();
  }

  public void setTotalCount(String count)
  {
    this.totalCount = count;
  }

  public void setCurrentCount(int count)
  {
    this.currentCount = count;
  }

  public void setDays(String days)
  {
    this.days = days;
  }

  public void clear()
  {
	isCleared = true;
  }

  public boolean iscleared()
  {
	return isCleared;
  }

  }
