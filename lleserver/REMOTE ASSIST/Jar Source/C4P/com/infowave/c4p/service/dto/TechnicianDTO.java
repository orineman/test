package com.infowave.c4p.service.dto;

import com.infowave.c4p.util.security.PasswordEncoder;
//import com.infowave.c4p.util.security.PasswordEncoderFactory;
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
 * The Data Transfer Object for a Technician.
 *
 */
/**
 * This class represents a technician
 */
public class TechnicianDTO implements Serializable
{

  private String id = null;
  private String companyid = null;
  private String techid = null;
  private String techcid = null;
  private String password = null;
  private String name = null;
  private String phone = null;
  private String misc = null;
  private String email = null;

  private int maxconns = 0;

  private double accesslevel = 0;

  private Date createdatetime = null;


  private static Log m_log = LogFactory.getLog(TechnicianDTO.class);

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

  public String getTechid() {
    return techid;
  }

  public void setTechid(String techid) {
    this.techid = techid;
  }

  public String getTechcid() {
    return techcid;
  }

  public void setTechcid(String techcid) {
    this.techcid = techcid;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getMisc() {
    return misc;
  }

  public void setMisc(String misc) {
    this.misc = misc;
  }

  public String getEmail()
  {
    return email;
  }

  public void setEmail(String email)
  {
    this.email = email;
  }

  public int getMaxconns() {
    return maxconns;
  }

  public void setMaxconns(int maxconns) {
    this.maxconns = maxconns;
  }

  public double getAccesslevel() {
    return accesslevel;
  }

  public void setAccesslevel(double accesslevel) {
    this.accesslevel = accesslevel;
  }

  public Date getCreatedatetime() {
    return createdatetime;
  }

  public void setCreatedatetime(Date createdatetime) {
    this.createdatetime = createdatetime;
  }
  // ------------------------------------------------------- setters and getters for html pages
  public String getLinkencodedCompanyid()
  {
	return StringManager.linkencode( (companyid==null?"":companyid) );
  }

  public void setLinkencodedCompanyid(String id)
  {
  }

  public String getLinkencodedTechid() {
	return StringManager.linkencode( (techid==null?"":techid) );
  }

  public void setLinkencodedTechid(String techid) {
  }

  public String getLinkencodedTechcid() {
	return StringManager.linkencode( (techcid==null?"":techcid) );
  }

  public void setLinkencodedTechcid(String techcid) {
  }

  public String getHtmlencodedCompanyid()
  {
	return StringManager.htmlencode( (companyid==null?"":companyid) );
  }

  public void setHtmlencodedCompanyid(String id)
  {
  }

  public String getHtmlencodedTechid() {
	return StringManager.htmlencode( (techid==null?"":techid) );
  }

  public void setHtmlencodedTechid(String techid) {
  }

  public String getHtmlencodedTechcid() {
	return StringManager.htmlencode( (techcid==null?"":techcid) );
  }

  public void setHtmlencodedTechcid(String techcid) {
  }

  public String getHtmlencodedPassword() {
	return StringManager.htmlencode( (password==null?"":password) );
  }

  public void setHtmlencodedPassword(String password) {
  }

  public String getHtmlencodedName() {
	return StringManager.htmlencode( (name==null?"":name) );
  }

  public void setHtmlencodedName(String name) {
  }

  public String getHtmlencodedPhone() {
	return StringManager.htmlencode( (phone==null?"":phone) );
  }

  public void setHtmlencodedPhone(String phone) {
  }

  public String getHtmlencodedMisc() {
	return StringManager.htmlencode( (misc==null?"":misc) );
  }

  public void setHtmlencodedMisc(String misc) {
  }

  public String getHtmlencodedEmail() {
	return StringManager.htmlencode( (email==null?"":email) );
  }

  public void setHtmlencodedEmail(String misc) {
  }

  public String getActiveflag() {
	boolean isactive = false;
	// use the partially active
	isactive = isPartiallyActive();
	return (isactive?"Y":"N");
  }

  public void setActiveflag(String flag) {
  }

  public String getCreatedatetimestr() {
    String locStr;
	//if( locale != null && locale.length() > 0 ) {
	//	locStr = locale;
	//} else {
		locStr = "en";
	//}
	Locale loc = new Locale(locStr);
	DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM, loc);

	return df.format(createdatetime);
  }

  public void setCreatedatetimestr(String info) {
  }


// ==================================================================
  public boolean doPasswordsMatch( String password )
  {
    boolean valid = false;

    try
    {
        PasswordEncoder encoder = null;
        Class theClass = Class.forName(WebConstants.PWD_ENCODER);
        encoder = (PasswordEncoder)theClass.newInstance();
        valid = encoder.match(password, this.password);
    } catch(Exception eek){ }

    return valid;
  }

  public boolean isSiteAdmin()
  {
	boolean isSiteAdmin = false;
	int userAccessLevel = (int)getAccesslevel();
	isSiteAdmin = (userAccessLevel & WebConstants.TECHNICIAN_ACCESSLEVEL_SITE_ADMIN) > 0;
	return isSiteAdmin;
  }

  public boolean isCompanyAdmin()
  {
	boolean isCompanyAdmin = false;
	int userAccessLevel = (int)getAccesslevel();
	isCompanyAdmin = (userAccessLevel & WebConstants.TECHNICIAN_ACCESSLEVEL_COMPANY_ADMIN) > 0;
	return isCompanyAdmin;
  }

  public boolean isAnyAdmin()
  {
	boolean isAnyAdmin = isSiteAdmin();
	if( !isAnyAdmin ) {
		isAnyAdmin = isCompanyAdmin();
	} 
	return isAnyAdmin;
  }

  public boolean isCompletelyActive()
  {
	return (maxconns>0 && (((int)accesslevel&WebConstants.TECHNICIAN_ACCESSLEVEL_ACTIVE) > 0) );
  }

  public boolean isPartiallyActive()
  {
	return (maxconns>0);
  }


}

