package com.infowave.c4p.service.dto;

import java.io.Serializable;

/**
 * The Data Transfer Object for a Principal.
 *
 * Created by: Vincent Aumont
 * Copyright 2003 Infowave Software, Inc.
 */
public class PrincipalDTO  implements Serializable
{

  private String username;
  private String[] roles;
  private UserDTO userDTO = null;

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

  public UserDTO getUserDTO() {
    return userDTO;
  }

  public void setUserDTO(UserDTO userDTO) {
    this.userDTO = userDTO;
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
