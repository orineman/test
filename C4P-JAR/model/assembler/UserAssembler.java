package com.infowave.c4p.model.assembler;

import com.infowave.c4p.service.dto.UserDTO;
import com.infowave.c4p.service.dto.DesktopDTO;
import com.infowave.c4p.model.Account;
import com.infowave.c4p.model.Desktop;
import com.infowave.c4p.model.Authentication;

import java.util.Iterator;

/**
 * This classes is responsible for assembling User DTOs.
 *
 * Created by: Vincent Aumont
 * Copyright 2003 Infowave Software, Inc.
 */

public class UserAssembler
{
  /**
   * Builds a User DTO from a user account.
   * @param account - a user authentication
   * @return the user DTO coresponding to that account.
   */
  public static UserDTO createUserDTO(Account account)
  {
    UserDTO userDTO = new UserDTO();

    // User information
    userDTO.setId(account.getId());
    userDTO.setFirstName(account.getFirstName());
    userDTO.setLastName(account.getLastName());
    userDTO.setEmail(account.getEmail());
    userDTO.setUsername(account.getUsername());
    userDTO.setLocale(account.getLocale());
    userDTO.setSecurityQuestion(account.getSecurityQuestion());
    userDTO.setResellerName(account.getReseller().getName());
    
    return userDTO;
  }
}
