package com.infowave.c4p.datasource.dao;

import com.infowave.c4p.model.Company;

import com.infowave.c4p.datasource.DataException;

import java.util.List;

/**
 * This interface defines the methods supported by DAO Company implementions.
 *
 */
public interface CompanyDAO
{
  /**
   * Gets a company by its id
   *
   * @param id - the company's id
   * @return The company for that id or null if this companyid does not
   *         exist.
   * @throws DataException
   */
  public Company getByCompanyid(String id)
          throws DataException;

  public void deleteByCompanyid(String id)
          throws DataException;

  public void addCompany(Company company) 
		  throws DataException;

  public void update(Company company)
          throws DataException;

  public int countLikeCompanyid(String partialData)
          throws DataException;

  public int countLikeName(String partialData)
          throws DataException;

  public int countLikeXref(String partialData)
          throws DataException;

  public List getCompaniesLikeCompanyid(String partialData)
          throws DataException;

  public List getCompaniesLikeName(String partialData)
          throws DataException;

  public List getCompaniesLikeXref(String partialData)
          throws DataException;

  public List getCompaniesActiveRecent(String days)
          throws DataException;

  public void cancelByXref(String xref) 
		  throws DataException;

}