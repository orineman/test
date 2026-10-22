package com.infowave.c4p.service.delegate;

import com.infowave.c4p.service.ServiceException;
import com.infowave.c4p.service.dto.CompanyDTO;
import com.infowave.c4p.model.Company;

import java.util.List;

/**
 * This is the Business Delegate for Company-related operation.
 *
 */
public interface CompanyManager
{

  /**
   * Returns the company corresponding the companyid.
   * @param companyid
   * @return a CompanyDTO
   * @throws ServiceException
   */
  public CompanyDTO getByCompanyid(String companyid) throws ServiceException;

  public int getCountLikeCompanyid(String partialData) throws ServiceException;
  public int getCountLikeName(String partialData) throws ServiceException;
  public int getCountLikeXref(String partialData) throws ServiceException;

  public List getCompaniesLikeCompanyid(String partialData) throws ServiceException;
  public List getCompaniesLikeName(String partialData) throws ServiceException;
  public List getCompaniesLikeXref(String partialData) throws ServiceException;
  public List getCompaniesActiveRecent(String days) throws ServiceException;

  public void cancelByXref(String xref) throws ServiceException;


}
