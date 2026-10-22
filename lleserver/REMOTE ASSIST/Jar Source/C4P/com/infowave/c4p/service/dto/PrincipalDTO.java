package com.infowave.c4p.service.dto;

import java.io.Serializable;

/**
 * The Data Transfer Object for a Principal.
 *
 */
public class PrincipalDTO  implements Serializable
{

  private String companyid;
  private String username;
  private String[] roles;
  private TechnicianDTO technicianDTO = null;

  public String getCompanyid() {
    return companyid;
  }

  public void setCompanyid(String companyid) {
    this.companyid = companyid;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String[] getRoles() {
    return roles;
  }

  public void setRoles(String[] roles) {
    this.roles = roles;
  }

  public TechnicianDTO getTechnicianDTO() {
    return technicianDTO;
  }

  public void setTechnicianDTO(TechnicianDTO technicianDTO) {
    this.technicianDTO = technicianDTO;
  }

  public String toString()
  {
    StringBuffer sb = new StringBuffer();
    sb.append("Principal: ").append(username);
    sb.append(" roles: ");
    if (roles != null){
      for (int i=0;i<roles.length;i++){
        sb.append(roles[i]).append(",");
      }
    }
    return sb.toString();
  }
}
