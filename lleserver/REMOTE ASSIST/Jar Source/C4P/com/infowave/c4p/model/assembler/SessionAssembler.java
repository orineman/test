package com.infowave.c4p.model.assembler;

import com.infowave.c4p.service.dto.SessionDTO;
import com.infowave.c4p.model.HDSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * This classes is responsible for assembling Session DTOs.
 *
 */
public class SessionAssembler
 {
  /** The class's logger */
  private static Log m_log = LogFactory.getLog(SessionAssembler.class);
  /**
   * Builds a session DTO from a session bean.
   * @param session - a session
   * @return the DTO correponding to that session
   */
  public static SessionDTO createSessionDTO(HDSession session)
  {
	SessionDTO dto = new SessionDTO();
	dto.setId(session.getId());
	dto.setCompanyid(session.getCompanyid());
	dto.setTechcid(session.getTechcid());
	dto.setDid(session.getDid());
	dto.setName(session.getName());
	dto.setNumuses(session.getNumuses());
	dto.setCreatedatetime(session.getCreatedatetime());
	dto.setStartdatetime(session.getStartdatetime());
	dto.setEnddatetime(session.getEnddatetime());

	return dto;
  }
}
