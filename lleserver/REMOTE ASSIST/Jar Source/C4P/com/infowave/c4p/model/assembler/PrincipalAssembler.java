package com.infowave.c4p.model.assembler;

import com.infowave.c4p.service.dto.PrincipalDTO;
import com.infowave.c4p.service.dto.TechnicianDTO;
import com.infowave.c4p.model.Authentication;

/**
 * This classes is responsible for assembling Principal DTOs.
 *
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
    dto.setCompanyid(auth.getCompanyid());
    dto.setUsername(auth.getUsername());
    roles[0]=auth.getRole().getName();
    dto.setRoles(roles);
    if (auth.getTechnician() != null){
      TechnicianDTO technicianDTO = null;
      technicianDTO = TechnicianAssembler.createTechnicianDTO(auth.getTechnician());
      dto.setTechnicianDTO(technicianDTO);
    }
    return dto;
  }
}
