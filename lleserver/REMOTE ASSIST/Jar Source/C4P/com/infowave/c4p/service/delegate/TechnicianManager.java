package com.infowave.c4p.service.delegate;

import com.infowave.c4p.service.ServiceException;
import com.infowave.c4p.service.dto.TechnicianDTO;
import com.infowave.c4p.model.Technician;

import java.util.List;

/**
 * This is the Business Delegate for Technician-related operation.
 *
 */
public interface TechnicianManager
{
  /**
   * Retrieves the list of technicians associated with a company. 
   * 
   * @param companyid - a companyid
   * @return a list of technicians associated with this company.
   * @throws ServiceException
   */
  public List getTechniciansByCompanyid(String companyid)
          throws ServiceException;


  /**
   * Returns the technician corresponding the a techid/companyid combo.
   * @param companyid
   * @param techid
   * @return a technicianDTO
   * @throws ServiceException
   */
   public TechnicianDTO getByDoubleid(String companyid, String techid)
          throws ServiceException;

  /**
   * Returns the technician corresponding the a techid/companyid combo, must be an active technician.
   * @param companyid
   * @param techid
   * @return a technicianDTO or null
   * @throws ServiceException
   */
   public TechnicianDTO getActiveByDoubleid(String companyid, String techid) throws ServiceException;

   public TechnicianDTO getByTechcid(String techcid) throws ServiceException;
   public TechnicianDTO getByTechcid(String companyid, String techcid) throws ServiceException;

   public List getTechniciansLikeTechid(String companyid, String partialData) throws ServiceException;
   public List getTechniciansLikeName(String companyid, String partialData) throws ServiceException;
   public List getTechniciansLikeEmail(String companyid, String partialData) throws ServiceException;
   public List getTechniciansLikeTechcid(String companyid, String partialData) throws ServiceException;
   public List getTechniciansLikeTechcid(String partialData) throws ServiceException;
   public List getTechniciansLikeEmailAndCompany(String partialCompanyid, String partialData) throws ServiceException;
   public int getCountLikeTechcid(String partialData) throws ServiceException;
   public int getCountLikeTechcid(String companyid, String partialData) throws ServiceException;
   public int getCountLikeTechid(String companyid, String partialData) throws ServiceException;
   public int getCountLikeName(String companyid, String partialData) throws ServiceException;
   public int getCountLikeEmail(String companyid, String partialData) throws ServiceException;
   public int getCountLikeEmailAndCompany(String partialCompanyid, String partialData) throws ServiceException;
   public int getCountActiveByCompanyid(String companyid) throws ServiceException;

   public TechnicianDTO update(TechnicianDTO technicianDto) throws ServiceException;

}
