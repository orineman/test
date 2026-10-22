package com.infowave.c4p.model.assembler;

import com.infowave.c4p.service.dto.TechloginDTO;
import com.infowave.c4p.model.Techlogin;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 * This classes is responsible for assembling Techlogin DTOs.
 *
 */
public class TechloginAssembler
 {

  /** The class's logger */
  private static Log m_log = LogFactory.getLog(TechloginAssembler.class);
  /**
   * Builds a techlogin DTO from a techlogin bean.
   * @param techlogin - a techlogin
   * @return the DTO correponding to that techlogin
   */
  public static TechloginDTO createTechloginDTO(Techlogin techlogin)
  {
	TechloginDTO dto = new TechloginDTO();
	dto.setId(techlogin.getId());
	dto.setCompanyid(techlogin.getCompanyid());
	dto.setTechcid(techlogin.getTechcid());

	dto.setFlags(techlogin.getFlags());
	dto.setLoginid(techlogin.getLoginid());
	dto.setLogindatetime(techlogin.getLogindatetime());
	dto.setEnddatetime(techlogin.getEnddatetime());

	return dto;
  }
}
