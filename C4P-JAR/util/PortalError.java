package com.infowave.c4p.util;

/**
 * Created by: Vincent Aumont
 * Copyright 2003 Infowave Software, Inc.
 */
public class PortalError
{

  private static final String MESSAGE_KEY_PREFIX ="error.";

  private ErrorCode m_code;
  private String[] fields;

  public PortalError(ErrorCode code)
  {
    m_code = code;
    fields = new String[0];
  }

  public PortalError(ErrorCode code, String field)
  {
    m_code = code;
    fields = new String[1];
    fields[0] = field;
  }

  public PortalError(ErrorCode code, String field1,String field2)
  {
    m_code = code;
    fields = new String[2];
    fields[0] = field1;
    fields[1] = field2;
  }

  public PortalError(ErrorCode code, String field1,String field2,String field3)
  {
    m_code = code;
    fields = new String[3];
    fields[0] = field1;
    fields[1] = field2;
    fields[2] = field3;
  }
  public PortalError(ErrorCode code, String field1,String field2,String field3,
                     String field4)
  {
    m_code = code;
    fields = new String[4];
    fields[0] = field1;
    fields[1] = field2;
    fields[2] = field3;
    fields[3] = field4;
  }

  public String getMessageKey()
  {
    return MESSAGE_KEY_PREFIX + m_code.getName();
  }

  public ErrorCode getCode() {return m_code;}
  public String[] getFields() {return fields;}
  public boolean equals(PortalError error)
  {
    return (m_code == error.getCode());
  }

  //private String firstField;
  //private String secondField;
  public String getFirstField() {return getField(0);}
  public String getSecondField() {return getField(1);}
  public String getThirdField() {return getField(1);}
  public String getFourthField() {return getField(1);}
  public String getFifthField() {return getField(1);}
  public String getSixthField() {return getField(1);}
  public String getSeventhField() {return getField(1);}

  private String getField (int i){
    if ((fields == null) || (fields.length <=i)){
      return "";
    } else {
      return fields[i];
    }
  }
  public String toString()
  {
    StringBuffer buff = new StringBuffer();
    buff.append(m_code.toString());
    if (fields != null) {
      buff.append(": ");
      for (int i=0;i<fields.length;i++){
        buff.append(fields[i]);
      }
    }
    return buff.toString();
  }
}
