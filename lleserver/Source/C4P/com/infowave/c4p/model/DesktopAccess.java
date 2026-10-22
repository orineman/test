package com.infowave.c4p.model;

/**
 * Created by: Vincent Aumont
 * Copyright 2003 Infowave Software, Inc.
 * @deprecated not needed
 */
public class DesktopAccess
 {
  private String id = null;
  private String desktopId;
  private String authorizedAccountId;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getDesktopId() {
    return desktopId;
  }

  public void setDesktopId(String desktopId) {
    this.desktopId = desktopId;
  }

  public String getAuthorizedAccountId() {
    return authorizedAccountId;
  }

  public void setAuthorizedAccountId(String authorizedAccountId) {
    this.authorizedAccountId = authorizedAccountId;
  }

  public String toString()
  {
   StringBuffer sb = new StringBuffer();
    sb.append("DesktopAccess- id:" + id);
    sb.append("desktop: " + desktopId).append(" ");
    sb.append("account: " + authorizedAccountId).append(" ");
    return sb.toString();
  }
}
