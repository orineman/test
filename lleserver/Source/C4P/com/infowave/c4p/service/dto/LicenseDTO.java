package com.infowave.c4p.service.dto;

import java.io.Serializable;
import com.infowave.c4p.model.License;

import java.util.Date;

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
  private int duration;

  private String rc = null;
  private Date firstUseDate = null;
  private int numUses = 0;
  private int maxUses = 0;
  private boolean revoked = true;

  public String getCode()
  {
    return code;
  }

  public void setCode(String code)
  {
    this.code = code;
  }

  public boolean isRevoked()
  {
    return revoked;
  }

  public boolean getRevoked()
  {
    return revoked;
  }

  public void setRevoked(boolean revoked)
  {
    this.revoked = revoked;
  }

  public String getRc()
  {
    return rc;
  }

  public void setRc(String Rc)
  {
    this.rc = Rc;
  }

  public Date getFirstUseDate()
  {
    return firstUseDate;
  }

  public void setFirstUseDate(Date firstUseDate)
  {
    this.firstUseDate = firstUseDate;
  }

  public int getNumUses()
  {
    return numUses;
  }

  public void setNumUses(int numUses)
  {
    this.numUses = numUses;
  }

  public int getMaxUses()
  {
    return maxUses;
  }

  public void setMaxUses(int maxUses)
  {
    this.maxUses = maxUses;
  }

  public int getLicenseType()
  {
    return licenseType;
  }

  public void setLicenseType(int licenseType)
  {
    this.licenseType = licenseType;
  }

  public int getDuration()
  {
    return duration;
  }

  public void setDuration(int duration)
  {
    this.duration = duration;
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

  public String getCorporate()
  {
	return Integer.toString(maxUses);
  }

  public void setCorporate(String data)
  {
  }

  public String getCorporateUsed()
  {
	return Integer.toString(numUses);
  }

  public void setCorporateUsed(String data)
  {
  }

  public String getRevokedStr()
  {
	return revoked==true?"Y":"N";
  }

  public void setRevokedStr(String data)
  {
  }

  public String getType()
  {
	// For now only two types
	return licenseType==License.BOXED_TYPE?License.BOXED_TYPE_STRING:License.SUBSCRIPTION_STRING;
  }

  public void setType(String data)
  {
  }

  public Date getExpires()
  {
    Date expiry = null;
	if( firstUseDate != null ) {
		Date from = firstUseDate;
		expiry = new Date(from.getTime() + (long) duration * License.NB_MILLISECONDS_IN_A_DAY);
	}
    return expiry;
  }

  public void setExpires(Date data)
  {
  }

  public String getRemainingDaysStr()
  {
    return Integer.toString(remainingDays);
  }

  public void setRemainingDaysStr(String data)
  {
  }

  public boolean getIsRcLinked()
  {
	boolean isrclinked = false;
	if( rc != null && (rc.equals("Y") || rc.equals("y")) && firstUseDate != null ) isrclinked = true;
	return isrclinked;
  }

  public void setIsRcLinked(boolean ssRc)
  {
  }

  public boolean getIsLinked()
  {
	boolean islinked = false;
	if( rc != null && (rc.equals("N") || rc.equals("n")) && firstUseDate != null ) islinked = true;
	return islinked;
  }

  public void setIsLinked(boolean ssRc)
  {
  }


}
