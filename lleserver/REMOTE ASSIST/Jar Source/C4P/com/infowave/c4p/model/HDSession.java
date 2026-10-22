package com.infowave.c4p.model;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 */
public class HDSession
 {

  private String id = null;
  private String companyid = null;
  private String techcid = null;
  private String did = null;
  private String name = null;

  private int numuses = 0;

  private Date createdatetime = null;
  private Date startdatetime = null;
  private Date enddatetime = null;


  private static Log m_log = LogFactory.getLog(HDSession.class);

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

  public String getDid() {
    return did;
  }

  public void setDid(String did) {
    this.did = did;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getNumuses() {
    return numuses;
  }

  public void setNumuses(int numuses) {
    this.numuses = numuses;
  }

  public Date getCreatedatetime() {
    return createdatetime;
  }

  public void setCreatedatetime(Date createdatetime) {
    this.createdatetime = createdatetime;
  }

  public Date getStartdatetime() {
    return startdatetime;
  }

  public void setStartdatetime(Date startdatetime) {
    this.startdatetime = startdatetime;
  }

  public Date getEnddatetime() {
    return enddatetime;
  }

  public void setEnddatetime(Date enddatetime) {
    this.enddatetime = enddatetime;
  }
}
