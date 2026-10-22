package com.infowave.c4p.model.assembler;

import com.infowave.c4p.service.dto.PrincipalDTO;
import com.infowave.c4p.service.dto.UserDTO;
import com.infowave.c4p.model.Authentication;

/**
 * This classes is responsible for assembling Principal DTOs.
 *
 * Created by: Vincent Aumont
 * Copyright 2003 Infowave Software, Inc.
 */
public class PrincipalAssembler
{
  /**
   * Creates a PrincipalDTo from an authentication.
   * @param auth - an authentication.
   * @return the corresponding Principal DTO.
   */
  public static PrincipalDTO createPrincipalDTO(Authentication auth)
  {
    String[] roles = new String[1];
    PrincipalDTO dto = new PrincipalDTO();
    dto.setUsername(auth.getUsername());
    roles[0]=auth.getRole().getName();
    dto.setRoles(roles);
    if (auth.getAccount() != null){
      UserDTO userDTO = null;
      userDTO = UserAssembler.createUserDTO(auth.getAccount());
      dto.setUserDTO(userDTO);
    }
    return dto;
  }
}
