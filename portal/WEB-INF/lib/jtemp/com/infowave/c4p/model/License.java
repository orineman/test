package com.infowave.c4p.model;

import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;

import java.util.Date;

/**
 * This class reprsents a License.
 * 
 * Created by: Vincent Aumont
 * Copyright 2003 Infowave Software, Inc.
 */
public class License
{
  public static final int SUBSCRIPTION_TYPE = 0x00;
  public static final int BOXED_TYPE = 0x01;
  public static final String SUBSCRIPTION_STRING = "sub";
  public static final String BOXED_TYPE_STRING = "box";
  private static final long NB_MILLISECONDS_IN_A_DAY = (long)24*60*60*1000;

    /** The class's logger */
  private static Log m_log = LogFactory.getLog(License.class);

  private String id = null;
  private String code;
  private String rc;
  private int duration;
  private Date firstUseDate;
  private int numUses;
  private int maxUses;
  private boolean revoked;
  private boolean regCode;
  private int licenseType;

  private RCUsage rcusage;
  private Account account;
  private Reseller reseller;

  public String getId()
  {
    return id;
  }

  public void setId(String id)
  {
    this.id = id;
  }

  public String getRc()
  {
    return rc;
  }

  public void setRc(String rc)
  {
    this.rc = rc;
  }

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

  public void setLicenseType(int type)
  {
    this.licenseType = type;
  }

  public int getDuration()
  {
    return duration;
  }

  public void setDuration(int duration)
  {
    this.duration = duration;
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

  public boolean isRevoked()
  {
    return revoked;
  }

  public void setRevoked(boolean revoked)
  {
    this.revoked = revoked;
  }

  public boolean isRegCode()
  {
    return regCode;
  }

  public void setRegCode(boolean regCode)
  {
    this.regCode = regCode;
  }

  public Account getAccount()
  {
    return account;
  }

  public void setAccount(Account account)
  {
    this.account = account;
  }

  public RCUsage getRcusage()
  {
    return rcusage;
  }

  public void setRcusage(RCUsage rcusage)
  {
    this.rcusage= rcusage;
  }

  public Reseller getReseller()
  {
    return reseller;
  }

  public void setReseller(Reseller reseller)
  {
    this.reseller = reseller;
  }

  public int getMaxUses()
  {
    return maxUses;
  }

  public void setMaxUses(int maxUses)
  {
    this.maxUses = maxUses;
  }

  public String toString()
  {
    StringBuffer sb = new StringBuffer();
    sb.append("License - ");
    sb.append("code: ").append(code).append(" ");
    sb.append("User: ");
    if (account != null) {
      sb.append(account.getUsername()).append(" ");
    } else{
      sb.append("[not allocated]").append(" ");
    }
    sb.append("Type: ").append(isSubscription()?"subscription":"fixed").append(" ");
    sb.append("num uses: ").append(numUses).append(" ");
    sb.append("max uses: ").append(maxUses).append(" ");
    sb.append("RegCode: ").append(isRegCode()?"yes":"no").append(" ");
    return sb.toString();
  }

  // ------------------------------------------------------------ Public methods
  /**
   * Is this license  a subscription?
   */
  public boolean isSubscription()
  {
    return (duration == 0);
  }

  /**
   * Is this license a fixed term?
   */
  public boolean isFixedTerm()
  {
    return !isSubscription();
  }

  /**
   * Return the licenses's expiry date. Subscripions never expire.
   * @return
   */
  public Date getExpiryDate()
  {
    Date expiry = null;
    if (!isSubscription()) {
      Date from = (firstUseDate != null? firstUseDate: new Date());
      expiry = new Date(from.getTime() + (long) duration * NB_MILLISECONDS_IN_A_DAY);
    }
    return expiry;
  }

  /**
   * Returns the remaining time (in milli-seconds) for that license, 0 if the
   * license has expired, or -1 if the license is a subscription.
   *
   * @return
   */
  public long getRemainingTime_bak()
  {
    long time;
    if (isSubscription()) {
      return -1;
    }
    Date now = new Date();
    // If the license's first use date is in the past (or if there is no first
    // use date), we count the remaining time from now. Otherwise, we count
    // from the first use date ( in that case, the remaining time is the
    // license's duration).
    if ((firstUseDate == null) || (firstUseDate.getTime() < now.getTime())){
      time = getExpiryDate().getTime() - now.getTime();
    } else {
      time = duration * NB_MILLISECONDS_IN_A_DAY;
    }
    if (time < 0){
      time = 0;
    }
    return time;
  }

    public long getRemainingTime()
  {
    long time;
    if (isSubscription()) {
      return -1;
    }
    Date now = new Date();
    // If the license's first use date is in the past (or if there is no first
    // use date), we count the remaining time from now. Otherwise, we count
    // from the first use date ( in that case, the remaining time is the
    // license's duration).
    if ((firstUseDate == null) || (firstUseDate.getTime() < now.getTime())){

      time = getExpiryDate().getTime() - (new Date()).getTime() ;
    } else {
      time = duration * NB_MILLISECONDS_IN_A_DAY;
    }
    if (time < 0){
      time = 0;
    }
    return time;
  }
  /**
   * returns the number of days remaining for this license.
   * @return
   */
  public int getRemainingDays()
  {
    long remainingTime = getRemainingTime();
    int days = (int) (remainingTime/NB_MILLISECONDS_IN_A_DAY);
    // We had one day because we want to count partial days as entire days
    if (remainingTime != 0 &&
            (remainingTime%NB_MILLISECONDS_IN_A_DAY != 0)) {
      days++;
    }
    return days;
  }
 
  /**
   * Return wheter a license has expired. Subscription never expire.
   * @return
   */
  public boolean hasExpired()
  {
    if (isSubscription()){
      return false;
    }
    return (getRemainingTime() == 0);
  }

  /**
   * Increments the licenses number of uses.
   */
  public void incrementNumUse()
  {
    numUses ++;
  }
  /**
   * Decrements the licenses number of uses.
   */
  public void decrementNumUse()
  {
    numUses --;
  }

  //--------------------------------------------------------------- Test methods

  private static void testGetRemainingDays()
  {
    int duration = 2;
    // remaining days when the license has an expiry date
    License l1 = new License();
    l1.setDuration(duration);
    l1.setFirstUseDate(new Date((new Date()).getTime() - 1* NB_MILLISECONDS_IN_A_DAY));
    System.out.println("-> With a first use date in the past");
    System.out.println("Duration: " + l1.getDuration());
    System.out.println("expiry date: " + l1.getExpiryDate());
    System.out.println("remaining time (in days - no rounding): " + l1.getRemainingTime()/NB_MILLISECONDS_IN_A_DAY);
    System.out.println("remaining days: " + l1.getRemainingDays());

    System.out.println();
    // remaining days when the license has no expiry date
    License l2 = new License();
    l2.setDuration(duration);
    System.out.println("-> With no first use date");
    System.out.println("expiry date: " + l2.getExpiryDate());
    System.out.println("remaining time (in days - no rounding): " + l2.getRemainingTime()/NB_MILLISECONDS_IN_A_DAY);
    System.out.println("remaining days: " + l2.getRemainingDays());

    // Subscription
    System.out.println("\n-> Subscrition");
    License l3 = new License();
    l3.setDuration(0);
    System.out.println("is subscription: " + l3.isSubscription());
    System.out.println("expiry date: " + l3.getExpiryDate());
    System.out.println("remaining time (in day - no rounding): " + l3.getRemainingTime()/NB_MILLISECONDS_IN_A_DAY);
    System.out.println("remaining days: " + l3.getRemainingDays());


  }

  public static void main (String[] args)
  {
    testGetRemainingDays();


/*

    long decemberFourth = 1070604962420L;
    long twentyFiveDays = (long)25 *  24 *3600 * 1000;
    long tenDays = (long)10 *  24 *3600 * 1000;

    System.out.println("December 4: " + new Date (decemberFourth));
    System.out.println("10 days later: " +  new Date(decemberFourth + tenDays));
    System.out.println("25 days later: " +  new Date(decemberFourth + twentyFiveDays));


    Date d = new Date(decemberFourth + twentyFiveDays);
    System.out.println(d.getTime());
    System.out.println(decemberFourth + twentyFiveDays);
    System.out.println(new Date(d.getTime()));
    */
  }

}


