package com.infowave.c4p.model;

import com.infowave.c4p.util.security.PasswordEncoderFactory;
import com.infowave.c4p.web.util.WebConstants;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * This class represents an Authentication.
 *
 */
public class Authentication
{
  private static Log m_log = LogFactory.getLog(Authentication.class);

  private String id = null;
  private String companyid = null;
  private String username = null;
  private String password = null;
  private String authType;
  /** For role-based auth */
  private String rolename;

  /** A reference to the account object if this authentication is for a user.
   *  Null otherwise.
   */
  private Technician technician = null;
  private Role role;
  // -------------------------------------------------------- Getters & Setters
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getCompanyid() {
    return companyid;
  }

  public void setCompanyid(String id) {
    companyid = id;
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

  public Technician getTechnician() {
    return technician;
  }

  public void setTechnician(Technician technician) {
    this.technician = technician;
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
    PasswordEncoderFactory factory = PasswordEncoderFactory.instance();
    return factory.getEncoder(WebConstants.PWD_ENCODER).match(password,this.password);

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
    if (technician != null) {
      sb.append(" technician: ").append(technician.getTechid()).append(technician.getCompanyid());
    }
    return sb.toString();

  }
}
