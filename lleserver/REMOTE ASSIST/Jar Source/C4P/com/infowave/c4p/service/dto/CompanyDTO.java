package com.infowave.c4p.service.dto;

import com.infowave.c4p.util.StringManager;
import com.infowave.c4p.web.util.WebConstants;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Date;
import java.util.Locale;
import java.text.DateFormat;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;

/**
 * The Data Transfer Object for a Company.
 *
 */
/**
 * This class represents a company
 */
public class CompanyDTO implements Serializable
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


  private static Log m_log = LogFactory.getLog(CompanyDTO.class);

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

  // ------------------------------------------------------- setters and getters for html pages

  public String getHtmlencodedCompanyid()
  {
	return StringManager.htmlencode( (companyid==null?"":companyid) );
  }

  public void setHtmlencodedCompanyid(String id)
  {
  }

  public String getLinkencodedCompanyid()
  {
	return StringManager.linkencode( (companyid==null?"":companyid) );
  }

  public void setLinkencodedCompanyid(String id)
  {
  }

  public String getHtmlencodedName() {
	return StringManager.htmlencode( (name==null?"":name) );
  }

  public void setHtmlencodedName(String name) {
  }

  public String getHtmlencodedMiscinfo1() {
	return StringManager.htmlencode( (miscinfo1==null?"":miscinfo1) );
  }

  public void setHtmlencodedMiscinfo1(String info) {
  }

  public String getHtmlencodedMiscinfo2() {
	return StringManager.htmlencode( (miscinfo2==null?"":miscinfo2) );
  }

  public void setHtmlencodedMiscinfo2(String info) {
  }

  public String getHtmlencodedXref() {
	return StringManager.htmlencode( (xref==null?"":xref) );
  }

  public void setHtmlencodedXref(String xref) {
  }

  public String getMaxconnsstr() {
	return Integer.toString(maxconns);
  }

  public void setMaxconnsstr(String info) {
  }

  public String getLicensetypeflag() {
    String flag = "C";
	int licensetypeint = (int)licensetype;
	if( (licensetypeint & WebConstants.DEDICATED_LICENSE) > 0 ) {
		flag = "D";
	}
	return flag; 
  }

  public void setLicensetypeflag(String flag) {
  }

  public String getActiveflag() {
	return (activeflags>0?"Y":"N");
  }

  public void setActiveflag(String flag) {
  }

  public String getCreatedatetimestr() {
    String locStr;
	if( locale != null && locale.length() > 0 ) {
		locStr = locale;
	} else {
		locStr = "en";
	}
	Locale loc = new Locale(locStr);
	DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM, loc);

	return df.format(createdatetime);
  }

  public void setCreatedatetimestr(String info) {
  }

}

