package com.infowave.c4p.model.assembler;

import com.infowave.c4p.service.dto.ResellerDTO;
import com.infowave.c4p.model.Reseller;

import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * This class is responsible for assembling resellers.
 *
 * Created by: Vincent Aumont
 * Copyright 2003 Infowave Software, Inc.
 */

public class ResellerAssembler
 {
    /** The class's logger */
  private static Log m_log = LogFactory.getLog(ResellerAssembler.class);

  /**
   * Create a Reseller DTO.
   * @param reseller
   * @return
   */
  public static ResellerDTO createResellerDTO(Reseller reseller)
  {
    ResellerDTO dto = new ResellerDTO();
    dto.setId(reseller.getId());
    dto.setName(reseller.getName());
    dto.setHostname(reseller.getHostname());
    dto.setExpiryUrl(reseller.getExpiryUrl());
    dto.setDefaultLocale(reseller.getDefaultLocale());
    dto.setLicenseCodeFormat(reseller.getLicenseCodeFormat());
    dto.setNbSecurityQuestions(reseller.getNbSecurityQuestions());
    dto.setPasswordEncoder(reseller.getPasswordEncoder());
 
    return dto;
  }

}
