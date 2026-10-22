package com.infowave.c4p.model;

//import com.infowave.c4p.datasource.dao.DAOFactory;
//import com.infowave.c4p.datasource.DataException;

import java.util.Date;

//import org.apache.commons.collections.ListUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * This class represents a Company.
 *
 */
public class Company

{

  private String id = null;
  private String companyid = null;
  private String name = null;
  private String locale = null;
  private String miscinfo1 = null;
  private String miscinfo2 = null;
  private String xref = null;

  private int maxconns = 0;

  private double licensetype = 0;
  private double activeflags = 0;
  private double miscflags = 0;

  private Date createdatetime = null;


  private static Log m_log = LogFactory.getLog(Company.class);

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

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLocale() {
    return locale;
  }

  public void setLocale(String locale) {
    this.locale = locale;
  }

  public String getMiscinfo1() {
    return miscinfo1;
  }

  public void setMiscinfo1(String info) {
    miscinfo1 = info;
  }

  public String getMiscinfo2() {
    return miscinfo2;
  }

  public void setMiscinfo2(String info) {
    miscinfo2 = info;
  }

  public String getXref() {
    return xref;
  }

  public void setXref(String xref) {
    this.xref = xref;
  }

  public int getMaxconns() {
    return maxconns;
  }

  public void setMaxconns(int maxconns) {
    this.maxconns = maxconns;
  }

  public double getLicensetype() {
    return licensetype;
  }

  public void setLicensetype(double licensetype) {
    this.licensetype = licensetype;
  }

  public double getActiveflags() {
    return activeflags;
  }

  public void setActiveflags(double activeflags) {
    this.activeflags = activeflags;
  }

  public double getMiscflags() {
    return miscflags;
  }

  public void setMiscflags(double miscflags) {
    this.miscflags = miscflags;
  }

  public Date getCreatedatetime() {
    return createdatetime;
  }

  public void setCreatedatetime(Date createdatetime) {
    this.createdatetime = createdatetime;
  }


}
