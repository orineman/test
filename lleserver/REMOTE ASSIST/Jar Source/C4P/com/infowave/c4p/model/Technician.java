package com.infowave.c4p.model;

import java.util.Date;

import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;

/**
 * This class represents a Technician.
 *
 */
public class Technician
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


  private static Log m_log = LogFactory.getLog(Technician.class);

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

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
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




}
