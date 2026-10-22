package com.infowave.c4p.model.assembler;

import com.infowave.c4p.service.dto.RCUsageDTO;
import com.infowave.c4p.model.RCUsage;

import java.util.Date;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 * This classes is responsible for assembling RCUsage DTOs.
 *
 */
public class RCUsageAssembler
 {
  /** The class's logger */
  private static Log m_log = LogFactory.getLog(RCUsageAssembler.class);

  public static RCUsageDTO createRCUsageDTO(RCUsage rcusage)
  {
    RCUsageDTO dto = new RCUsageDTO ();

    dto.setId(rcusage.getId());
    dto.setDid(rcusage.getDid());
    dto.setLicenseSerialNumber(rcusage.getLicenseSerialNumber());

    dto.setExpiryDate(rcusage.getExpiryDate());
    dto.setWindowExpiryDate(rcusage.getWindowExpiryDate());

    dto.setBytes(rcusage.getBytes());
    dto.setServerSeconds(rcusage.getServerSeconds());
    dto.setClientSeconds(rcusage.getClientSeconds());
    dto.setWindowLimit(rcusage.getWindowLimit());

   return dto;
  }
}
