package com.infowave.c4p.datasource.dao;

import com.infowave.c4p.model.Technician;
import com.infowave.c4p.datasource.DataException;

import java.util.List;
/**
 * This interface defines the methods supported by Technician DAO implementions.
 */
public interface TechnicianDAO
{
  /**
   * Retrieve a list of technicians given a companyid.
   * @param id
   * @return
   * @throws DataException
   */
  public List getByCompanyid(String id) throws DataException;

  public Technician getByDoubleid(String companyid, String techid) throws DataException;

  public Technician getByTechcid(String techcid) throws DataException;

  public Technician getByTechcid(String companyid, String techcid) throws DataException;

  public Technician getActiveByDoubleid(String companyid, String techid) throws DataException;

  public void activateByCompanyid(String companyid) throws DataException;

  public void inactivateByCompanyid(String companyid) throws DataException;

  public void setAccesslevelByCompanyid(String companyid, String accesslevel) throws DataException;

  public int countLikeTechid(String companyid, String partialData) throws DataException;

  public int countLikeName(String companyid, String partialData) throws DataException;

  public int countLikeEmail(String companyid, String partialData) throws DataException;

  public int countLikeTechcid(String companyid, String partialData) throws DataException;

  public int countLikeTechcid(String partialData) throws DataException;

  public int countLikeCompanyidEmail(String partialCompanyid, String partialData) throws DataException;

  public int countActiveByCompanyid(String companyid) throws DataException;

  public List getTechniciansLikeTechid(String companyid, String partialData) throws DataException;

  public List getTechniciansLikeName(String companyid, String partialData) throws DataException;

  public List getTechniciansLikeEmail(String companyid, String partialData) throws DataException;

  public List getTechniciansLikeTechcid(String companyid, String partialData) throws DataException;

  public List getTechniciansLikeTechcid(String partialData) throws DataException;

  public List getTechniciansLikeCompanyidEmail(String partialCompanyid, String partialData) throws DataException;

  public void addTechnician(Technician technician) throws DataException;

  public void deleteByTechcid(String companyid, String techcid) throws DataException;

  public void update(Technician technician) throws DataException;

  public void deleteAllByCompanyid(String companyid) throws DataException;

}
