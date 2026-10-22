package com.infowave.c4p.model.assembler;

import com.infowave.c4p.service.dto.CompanyDTO;
import com.infowave.c4p.model.Company;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 * This classes is responsible for assembling Company DTOs.
 *
 */
public class CompanyAssembler
 {

  /** The class's logger */
  private static Log m_log = LogFactory.getLog(CompanyAssembler.class);
  /**
   * Builds a company DTO from a company bean.
   * @param company - a company
   * @return the DTO correponding to that company
   */
  public static CompanyDTO createCompanyDTO(Company company)
  {
	CompanyDTO dto = new CompanyDTO();
	dto.setId(company.getId());
	dto.setCompanyid(company.getCompanyid());
	dto.setName(company.getName());
	dto.setLocale(company.getLocale());
	dto.setMiscinfo1(company.getMiscinfo1());
	dto.setMiscinfo2(company.getMiscinfo2());
	dto.setXref(company.getXref());
	dto.setMaxconns(company.getMaxconns());
	dto.setLicensetype(company.getLicensetype());
	dto.setActiveflags(company.getActiveflags());
	dto.setMiscflags(company.getMiscflags());
	dto.setCreatedatetime(company.getCreatedatetime());

	return dto;
  }
}
