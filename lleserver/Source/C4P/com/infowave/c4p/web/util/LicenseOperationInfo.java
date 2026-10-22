package com.infowave.c4p.web.util;

/**
 * Created by IntelliJ IDEA.
 * User: vincent
 * Date: Dec 5, 2003
 * Time: 4:24:47 PM
 * To change this template use Options | File Templates.
 */
public class LicenseOperationInfo
{
  public static final int OP_UNDEFINED         = 0;
  public static final int OP_ADD_FIRST_LICENSE = 1;
  public static final int OP_EXTEND_FIXED      = 2;
  public static final int OP_TRANSFER          = 3;
  public static final int OP_REMOVE            = 4;

  private int operation = OP_UNDEFINED;
  private String did;
  private String newLicenseCode;


  public int getOperation()
  {
    return operation;
  }

  public void setOperation(int operation)
  {
    this.operation = operation;
  }

  public String getDid()
  {
    return did;
  }

  public void setDid(String did)
  {
    this.did = did;
  }

  public String getNewLicenseCode()
  {
    return newLicenseCode;
  }

  public void setNewLicenseCode(String newLicenseCode)
  {
    this.newLicenseCode = newLicenseCode;
  }


}
