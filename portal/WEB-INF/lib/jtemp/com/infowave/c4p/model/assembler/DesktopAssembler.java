package com.infowave.c4p.model.assembler;

import com.infowave.c4p.service.dto.DesktopDTO;
import com.infowave.c4p.model.Desktop;

import java.util.Date;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 * This classes is responsible for assembling Desktop DTOs.
 *
 * Created by: Vincent Aumont
 * Copyright 2003 Infowave Software, Inc.
 */
public class DesktopAssembler
 {
  /** The class's logger */
  private static Log m_log = LogFactory.getLog(DesktopAssembler.class);
  /**
   * Builds a desktop DTO from a DEsktop bean.
   * @param desktop - a desktop
   * @return the DTO correponding to that desktop
   */
  public static DesktopDTO createDesktopDTO(Desktop desktop)
  {
    DesktopDTO dto = new DesktopDTO();
    dto.setId(desktop.getId());
    dto.setDid(desktop.getDid());
    dto.setName(desktop.getName());
    dto.setOwner(desktop.getOwner());
    dto.setIcmURL(desktop.getIcmURL());
    dto.setAppUrlRegexp(desktop.getAppUrlRegexp());
    dto.setAppListUrls(desktop.getAppListUrls());
    dto.setStatusURL(desktop.getStatusURL());
    dto.setConnected(desktop.getStatusURL() != null);
    dto.setVisible(desktop.isVisible());
    dto.setLicenseSerialNumber(desktop.getLicenseSerialNumber());
    dto.setLicensed(desktop.getLicenseSerialNumber() != null);
    dto.setRclicenseSerialNumber(desktop.getRCLicenseSerialNumber());
    dto.setRclicensed( (desktop.getRCLicenseSerialNumber() != null && desktop.getDaysToRCExpiry() > 0) );
    dto.setLogoutURL(desktop.getLogoutURL());
    // Expiry date and # of days to expiry. A null value for the nb of days to
    // expiry means that the desktop does not expire. A positive value indicates
    // the number of days before the expiry date, and a negative value indicates
    // the number of days since the expiry date.
    dto.setRcexpiryDate(desktop.getRCExpiryDate());
    int daysToRCExpiry = desktop.getDaysToRCExpiry();
    dto.setExpiryDate(desktop.getExpiryDate());
    int daysToExpiry = desktop.getDaysToExpiry();
    // A license is a fixed term if the license code it has a license code
    // *and* an expiration date
    dto.setFixedTerm((desktop.getLicenseSerialNumber() != null) &&
                     (desktop.getExpiryDate() != null));
    dto.setDaysToExpiry((int)daysToExpiry);
    dto.setHasExpired(daysToExpiry < 0);
    dto.setHasRCExpired(daysToRCExpiry < 0);
    dto.setSoftwareVersion(desktop.getSoftwareVersion());
    dto.setRdpEnabled( desktop.getRdpEnabled() );
    dto.setRdpURL( desktop.getRdpURL() );

   return dto;
  }
}
