package com.infowave.c4p.model.facade;

import com.infowave.c4p.model.Company;
import com.infowave.c4p.model.assembler.CompanyAssembler;
import com.infowave.c4p.datasource.dao.DAOFactory;
import com.infowave.c4p.datasource.dao.CompanyDAO;
import com.infowave.c4p.service.dto.CompanyDTO;

//import java.io.Serializable;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

import com.infowave.c4p.service.ServiceException;
import com.infowave.c4p.datasource.DataException;

import com.infowave.c4p.service.dto.CompanyDTO;
import com.infowave.c4p.model.Company;
import com.infowave.c4p.model.assembler.CompanyAssembler;
import com.infowave.c4p.datasource.dao.CompanyDAO;
import com.infowave.c4p.datasource.dao.DAOFactory;
import com.infowave.c4p.util.PortalError;
import com.infowave.c4p.util.ErrorCode;
import com.infowave.c4p.service.delegate.CompanyManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;


/**
 * This class contains the business logic of rcusage-related operations.
 *
 */
public class CompanyManagerImpl implements CompanyManager
{

  /** The class's logger */
  private static Log m_log = LogFactory.getLog(CompanyManagerImpl.class);

  public CompanyManagerImpl( ) 
  {
  }

  /**
   * Returns the company corresponding the companyid.
   * @param companyid
   * @return a CompanyDTO
   * @throws ServiceException
   */
  public CompanyDTO getByCompanyid(String companyid) throws ServiceException
  {
    	Company company = null;
		CompanyDTO dto = null;
    	CompanyDAO companyDAO = DAOFactory.getCompanyDAO();

		try {
			company = companyDAO.getByCompanyid( companyid );
			if( company == null ) {
				throw new DataException ("Company not found, companyid = " +companyid );
			}
		} catch( DataException de ) {
    		PortalError error = new PortalError(ErrorCode.SYSTEM_ERROR);
      		throw new ServiceException(error,de);
		}
		if (company != null){
		  dto = CompanyAssembler.createCompanyDTO(company);
		}

		return dto;
  }

  public int getCountLikeCompanyid(String partialData) throws ServiceException
  {
	try {
		CompanyDAO companyDAO = DAOFactory.getCompanyDAO();
		int count = companyDAO.countLikeCompanyid( partialData );
		return count;
	} catch (DataException e) {
		PortalError error = new PortalError(ErrorCode.SYSTEM_ERROR);
		throw new ServiceException(error,e);
	}
  }

  public int getCountLikeName(String partialData) throws ServiceException
  {
	try {
		CompanyDAO companyDAO = DAOFactory.getCompanyDAO();
		int count = companyDAO.countLikeName( partialData );
		return count;
	} catch (DataException e) {
		PortalError error = new PortalError(ErrorCode.SYSTEM_ERROR);
		throw new ServiceException(error,e);
	}
  }

  public int getCountLikeXref(String partialData) throws ServiceException
  {
	try {
		CompanyDAO companyDAO = DAOFactory.getCompanyDAO();
		int count = companyDAO.countLikeXref( partialData );
		return count;
	} catch (DataException e) {
		PortalError error = new PortalError(ErrorCode.SYSTEM_ERROR);
		throw new ServiceException(error,e);
	}
  }

  public List getCompaniesLikeCompanyid(String partialData) throws ServiceException
  {
        List companies = null;
        List companyDTOs = new ArrayList();

        try
        {
      		CompanyDAO companyDAO = DAOFactory.getCompanyDAO();
            companies = companyDAO.getCompaniesLikeCompanyid( partialData ); 
        }
        catch(DataException e)
        {
            PortalError error = new PortalError(ErrorCode.SYSTEM_ERROR);
            throw new ServiceException(error, e);
        }

        CompanyDTO dto;
        for(Iterator it = companies.iterator(); it.hasNext(); companyDTOs.add(dto))
        {
            Company company = (Company)it.next();
            dto = CompanyAssembler.createCompanyDTO(company);
        }

        return companyDTOs;

  }

  public List getCompaniesLikeName(String partialData) throws ServiceException
  {
        List companies = null;
        List companyDTOs = new ArrayList();

        try
        {
      		CompanyDAO companyDAO = DAOFactory.getCompanyDAO();
            companies = companyDAO.getCompaniesLikeName( partialData ); 
        }
        catch(DataException e)
        {
            PortalError error = new PortalError(ErrorCode.SYSTEM_ERROR);
            throw new ServiceException(error, e);
        }

        CompanyDTO dto;
        for(Iterator it = companies.iterator(); it.hasNext(); companyDTOs.add(dto))
        {
            Company company = (Company)it.next();
            dto = CompanyAssembler.createCompanyDTO(company);
        }

        return companyDTOs;

  }

  public List getCompaniesLikeXref(String partialData) throws ServiceException
  {
        List companies = null;
        List companyDTOs = new ArrayList();

        try
        {
      		CompanyDAO companyDAO = DAOFactory.getCompanyDAO();
            companies = companyDAO.getCompaniesLikeXref( partialData ); 
        }
        catch(DataException e)
        {
            PortalError error = new PortalError(ErrorCode.SYSTEM_ERROR);
            throw new ServiceException(error, e);
        }

        CompanyDTO dto;
        for(Iterator it = companies.iterator(); it.hasNext(); companyDTOs.add(dto))
        {
            Company company = (Company)it.next();
            dto = CompanyAssembler.createCompanyDTO(company);
        }

        return companyDTOs;

  }

  public List getCompaniesActiveRecent(String days) throws ServiceException
  {
        List companies = null;
        List companyDTOs = new ArrayList();

        try
        {
      		CompanyDAO companyDAO = DAOFactory.getCompanyDAO();
            companies = companyDAO.getCompaniesActiveRecent( days ); 
        }
        catch(DataException e)
        {
            PortalError error = new PortalError(ErrorCode.SYSTEM_ERROR);
            throw new ServiceException(error, e);
        }

        CompanyDTO dto;
        for(Iterator it = companies.iterator(); it.hasNext(); companyDTOs.add(dto))
        {
            Company company = (Company)it.next();
            dto = CompanyAssembler.createCompanyDTO(company);
        }

        return companyDTOs;
  }

  public void cancelByXref(String xref) throws ServiceException
  {
        try
        {
      		CompanyDAO companyDAO = DAOFactory.getCompanyDAO();
            companyDAO.cancelByXref( xref ); 
        }
        catch(DataException e)
        {
            PortalError error = new PortalError(ErrorCode.SYSTEM_ERROR);
            throw new ServiceException(error, e);
        }

  }

}

