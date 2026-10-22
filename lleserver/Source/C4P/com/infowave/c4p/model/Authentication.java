package com.infowave.c4p.model;

import com.infowave.c4p.util.security.PasswordEncoderFactory;

/**
 * This class represents an Authentication.
 *
 * Created by: Vincent Aumont
 * Copyright 2003 Infowave Software, Inc.
 */
public class Authentication
{
  private String id = null;
  private String username = null;
  private String password = null;
  private String authType;
  /** For role-based auth */
  private String rolename;

  /** A reference to the account object if this authentication is for a user.
   *  Null otherwise.
   */
  private Account account = null;
  private Role role;
  // -------------------------------------------------------- Getters & Setters
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getAuthType() {
    return authType;
  }

  public void setAuthType(String authType) {
    this.authType = authType;
  }

  public String getRolename() {
    return rolename;
  }

  public void setRolename(String name) {
    this.rolename = name;
    role = Role.fromCode(name);
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
    rolename = role.getName();
  }

  public Account getAccount() {
    return account;
  }

  public void setAccount(Account account) {
    this.account = account;
  }
// -------------------------------------------------------------- Public methods

  /**
   * Checks a non-encrypted password against this authentication's password.
   * @param password
   * @return
   * @deprecated
   */
  public boolean validatePassword(String password)
  {
    // todo put this information in a config file or in the DB
    String encoderClassname = "com.infowave.c4p.laplink.security.PasswordEncoderImpl";
    PasswordEncoderFactory factory = PasswordEncoderFactory.instance();
    return factory.getEncoder(encoderClassname).match(password,this.password);

  }


  /**
   * For debugging purposes.
   *
   * @return
   */
  public String toString()
  {
    StringBuffer sb = new StringBuffer();
    sb.append("Authentication -username: ").append(username);
    sb.append(" password: ").append(password);
    sb.append(" type: ").append(authType);
    sb.append(" role: ").append(role);
    if (account != null) {
      sb.append(" account: ").append(account.getUsername());
    }
    return sb.toString();

  }
  // -------------------------------------------------------------- Test methods
  private static void testValidatePassword() throws Exception
  {
    String password = "pp";
    String encodedPassword = "p";
    Authentication auth = new Authentication();
    auth.setPassword(encodedPassword);
    auth.setAuthType("laplink");
    System.out.println("password valid: " + auth.validatePassword(password));
  }

  public static void main(String[] args) {
    try {
      testValidatePassword();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

}
