package com.infowave.c4p.model.assembler;

import com.infowave.c4p.service.dto.LicenseDTO;
import com.infowave.c4p.model.License;

/**
 * This classes is responsible for assembling License DTOs.
 *
 * Created by: Vincent Aumont
 * Copyright 2003 Infowave Software, Inc.
 */
public class LicenseAssembler
{
  public static LicenseDTO createLicenseDTO(License license)
  {
    LicenseDTO dto = new LicenseDTO();
    dto.setCode(license.getCode());
    dto.setRemainingDays(license.getRemainingDays());
    dto.setFixedTerm(license.isFixedTerm());
    dto.setNumAvailableUses(license.getMaxUses() - license.getNumUses());
    dto.setLicenseType(license.getLicenseType());
    return dto;
  }

}
