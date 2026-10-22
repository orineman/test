package com.infowave.c4p.util;

public class ErrorCode
{
  private static final int SYSTEM_ERROR_CODE					= 2000;

  private static final int INVALID_COMPANYID_CODE				= 1100;
  private static final int NONUNIQUE_TECHCID_CODE				= 1101;
  private static final int NONUNIQUE_TECHID_CODE				= 1102;


  private static final int DATA_ENTRY_COMPANYID_FORMAT_CODE		= 3100;
  private static final int DATA_ENTRY_PASSWORD_FORMAT_CODE		= 3101;
  private static final int DATA_ENTRY_USERNAME_FORMAT_CODE		= 3102;
  private static final int DATA_ENTRY_TECHCID_FORMAT_CODE		= 3103;

  private static final int NONUNIQUE_COMPANYID_CODE				= 3203;
  private static final int NORIGHTS_CODE						= 3204;
  private static final int NOUSER_CODE							= 3205;
  private static final int NOCOMPANY_CODE						= 3206;
  private static final int MAXCONNS_CODE						= 3207;


  // A general system error. A database error for instance
  public static final ErrorCode SYSTEM_ERROR =
          new ErrorCode(SYSTEM_ERROR_CODE,
                        "SYSTEM_ERROR");

  // Companyid is wrong
  public static final ErrorCode INVALID_COMPANYID_ERROR =
          new ErrorCode(INVALID_COMPANYID_CODE,
                        "INVALID_COMPANYID");

  // Techcid is wrong
  public static final ErrorCode NONUNIQUE_TECHCID_ERROR =
          new ErrorCode(NONUNIQUE_TECHCID_CODE,
                        "NONUNIQUE_TECHCID");

  // Techid is wrong
  public static final ErrorCode NONUNIQUE_TECHID_ERROR =
          new ErrorCode(NONUNIQUE_TECHID_CODE,
                        "NONUNIQUE_TECHID");

  // Company's license is dedicated and too many technicians
  public static final ErrorCode MAXCONNS_ERROR =
          new ErrorCode(MAXCONNS_CODE,
                        "MAXCONNS");



  // companyid format is wrong (e.g., 0 length)
  public static final ErrorCode DATA_ENTRY_COMPANYID_FORMAT_ERROR =
          new ErrorCode(DATA_ENTRY_COMPANYID_FORMAT_CODE,
                        "DATA_ENTRY_COMPANYID_FORMAT");

  // password (did) format is wrong (e.g., 0 length)
  public static final ErrorCode DATA_ENTRY_PASSWORD_FORMAT_ERROR =
          new ErrorCode(DATA_ENTRY_PASSWORD_FORMAT_CODE,
                        "DATA_ENTRY_PASSWORD_FORMAT");

  // username format is wrong (e.g., 0 length)
  public static final ErrorCode DATA_ENTRY_USERNAME_FORMAT_ERROR =
          new ErrorCode(DATA_ENTRY_USERNAME_FORMAT_CODE,
                        "DATA_ENTRY_USERNAME_FORMAT");

  // techcid format is wrong (e.g., 0 length)
  public static final ErrorCode DATA_ENTRY_TECHCID_FORMAT_ERROR =
          new ErrorCode(DATA_ENTRY_TECHCID_FORMAT_CODE,
                        "DATA_ENTRY_TECHCID_FORMAT");

  // Companyid is wrong
  public static final ErrorCode NONUNIQUE_COMPANYID_ERROR =
          new ErrorCode(NONUNIQUE_COMPANYID_CODE,
                        "NONUNIQUE_COMPANYID");

  // Access level won't permit request
  public static final ErrorCode NORIGHTS_ERROR =
          new ErrorCode(NORIGHTS_CODE,
                        "NORIGHTS");

  // techid/companyid combo is wrong
  public static final ErrorCode NOUSER_ERROR =
          new ErrorCode(NOUSER_CODE,
                        "NOUSER");

  // companyid combo is wrong
  public static final ErrorCode NOCOMPANY_ERROR =
          new ErrorCode(NOCOMPANY_CODE,
                        "NOCOMPANY");


  private int m_code;
  private String m_name;

  private ErrorCode(int code,String name)
  {
    m_code=code;
    m_name = name;
  }

  public String getName() {return m_name;}
  public int toInt() {return m_code;}

  public String toString()
  {
    return m_name;
  }

}
