package com.infowave.c4p.model;

/**
 * This class represents the Portal roles: user, admin, or api.
 *
 */
public class Role
{
  private static final String USER_CODE = "user";
  private static final String ADMIN_CODE = "admin";
  private static final String API_CODE = "api";

  public static final Role USER = new Role(USER_CODE,"user");
  public static final Role ADMIN = new Role(ADMIN_CODE,"admin");
  public static final Role API = new Role(API_CODE,"api");


  private  String m_code;
  private String m_name;

  private Role(String code,String name)
  {
    m_code = code;
    m_name = name;
  }

 
  /**
   * Returns the Role onbject corresponding to a code.
   * this method throws RuntimeException if the role is invalid.
   * @param code - a code
   * @return - the Role onbject corresponding to that code.
   */
  public static Role fromCode(String code)
  {
    if (USER_CODE.equals(code)) {
      return USER;
    } else if (ADMIN_CODE.equals(code)) {
      return ADMIN;
    } else if (API_CODE.equals(code)) {
      return API;
    } else {
      throw new RuntimeException("Invalid role code: " + code);
    }
  }
  public String getName()
  {
    return m_name;
  }
  public String toString()
  {
    return m_name;
  }
}
