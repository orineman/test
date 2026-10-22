package com.infowave.c4p.model.assembler;

import com.infowave.c4p.service.dto.TechnicianDTO;
import com.infowave.c4p.model.Technician;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 * This classes is responsible for assembling Technician DTOs.
 *
 */
public class TechnicianAssembler
 {

  /** The class's logger */
  private static Log m_log = LogFactory.getLog(TechnicianAssembler.class);

  /**
   * Builds a technician DTO from a technician bean.
   * @param technician - a technician
   * @return the DTO correponding to that technician
   */
  public static TechnicianDTO createTechnicianDTO(Technician technician)
  {
	TechnicianDTO dto = new TechnicianDTO();
	dto.setId(technician.getId());
	dto.setCompanyid(technician.getCompanyid());
	dto.setTechid(technician.getTechid());
	dto.setTechcid(technician.getTechcid());
	dto.setPassword(technician.getPassword());
	dto.setName(technician.getName());
	dto.setPhone(technician.getPhone());
	dto.setMisc(technician.getMisc());
	dto.setEmail(technician.getEmail());
	dto.setMaxconns(technician.getMaxconns());
	dto.setAccesslevel(technician.getAccesslevel());
	dto.setCreatedatetime(technician.getCreatedatetime());

	return dto;
  }
}
