package com.infowave.c4p.model.facade;

import com.infowave.c4p.service.ServiceException;
import com.infowave.c4p.datasource.DataException;

import com.infowave.c4p.service.dto.TechnicianDTO;
import com.infowave.c4p.model.Technician;
import com.infowave.c4p.model.assembler.TechnicianAssembler;
import com.infowave.c4p.datasource.dao.TechnicianDAO;
import com.infowave.c4p.datasource.dao.DAOFactory;
import com.infowave.c4p.util.PortalError;
import com.infowave.c4p.util.ErrorCode;
import com.infowave.c4p.service.delegate.TechnicianManager;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

/**
 * This class contains the business logic of technician-related operations.
 *
 */
public class TechnicianManagerImpl implements TechnicianManager
{

  /** The class's logger */
  private static Log m_log = LogFactory.getLog(TechnicianManagerImpl.class);

  public TechnicianManagerImpl()
  {
  }

  /**
   * Retrieves the list of Technicians associated with a company. 
   * 
   * @param companyid - a companyid
   * @return a list of Technician associated with this company.
   * @throws ServiceException
   */
  public List getTechniciansByCompanyid(String companyid)
          throws ServiceException
  {
    List technicians = null;
    List technicianDTOs = new ArrayList();

    TechnicianDAO technicianDAO = DAOFactory.getTechnicianDAO();
    try {
		technicians  = technicianDAO.getByCompanyid(companyid);
    } catch (DataException e){
		PortalError error = new PortalError(ErrorCode.SYSTEM_ERROR);
		throw new ServiceException(error,e);
    }

	if( technicians == null ) {
		PortalError error = new PortalError(ErrorCode.INVALID_COMPANYID_ERROR, companyid);
		throw new ServiceException(error);
	}

	for (Iterator it = technicians.iterator();it.hasNext();){
		Technician technician = (Technician) it.next();
		TechnicianDTO dto = TechnicianAssembler.createTechnicianDTO(technician);
		technicianDTOs.add(dto);
    }

	return technicianDTOs;

  }

  /**
   * Returns the technician corresponding the a techid/companyid combo.
   * @param companyid
   * @param techid
   * @return a technicianDTO
   * @throws ServiceException
   */
   public TechnicianDTO getByDoubleid(String companyid, String techid)
          throws ServiceException
  {
    TechnicianDTO dto = null;
    Technician technician = null;

    TechnicianDAO technicianDAO = DAOFactory.getTechnicianDAO();
    try {
		technician = technicianDAO.getByDoubleid(companyid, techid);
    } catch (DataException e){
		PortalError error = new PortalError(ErrorCode.SYSTEM_ERROR);
		throw new ServiceException(error,e);
    }

	if( technician != null ) {
		dto = TechnicianAssembler.createTechnicianDTO(technician);
	}

	return dto;
  }

   public TechnicianDTO getByTechcid(String companyid, String techcid)
          throws ServiceException
  {
    TechnicianDTO dto = null;
    Technician technician = null;

    TechnicianDAO technicianDAO = DAOFactory.getTechnicianDAO();
    try {
		technician = technicianDAO.getByTechcid(companyid, techcid);
    } catch (DataException e){
		PortalError error = new PortalError(ErrorCode.SYSTEM_ERROR);
		throw new ServiceException(error,e);
    }

	if( technician != null ) {
		dto = TechnicianAssembler.createTechnicianDTO(technician);
	}

	return dto;
  }

   public TechnicianDTO getByTechcid(String techcid)
          throws ServiceException
  {
    TechnicianDTO dto = null;
    Technician technician = null;

    TechnicianDAO technicianDAO = DAOFactory.getTechnicianDAO();
    try {
		technician = technicianDAO.getByTechcid(techcid);
    } catch (DataException e){
		PortalError error = new PortalError(ErrorCode.SYSTEM_ERROR);
		throw new ServiceException(error,e);
    }

	if( technician != null ) {
		dto = TechnicianAssembler.createTechnicianDTO(technician);
	}

	return dto;
  }

  /**
   * Returns the technician corresponding the a techid/companyid combo, must be an active technician.
   * @param companyid
   * @param techid
   * @return a technicianDTO or null
   * @throws ServiceException
   */
   public TechnicianDTO getActiveByDoubleid(String companyid, String techid)
          throws ServiceException
  {
    TechnicianDTO dto = null;
    Technician technician = null;

    TechnicianDAO technicianDAO = DAOFactory.getTechnicianDAO();
    try {
		technician = technicianDAO.getActiveByDoubleid(companyid, techid);
    } catch (DataException e){
		PortalError error = new PortalError(ErrorCode.SYSTEM_ERROR);
		throw new ServiceException(error,e);
    }

	if( technician != null ) {
		dto = TechnicianAssembler.createTechnicianDTO(technician);
	}

	return dto;
  }

  public int getCountLikeTechid(String companyid, String partialData) throws ServiceException
  {
	try {
		TechnicianDAO technicianDAO = DAOFactory.getTechnicianDAO();
		int count = technicianDAO.countLikeTechid( companyid, partialData );
		return count;
	} catch (DataException e) {
		PortalError error = new PortalError(ErrorCode.SYSTEM_ERROR);
		throw new ServiceException(error,e);
	}
  }

  public int getCountLikeName(String companyid, String partialData) throws ServiceException
  {
	try {
		TechnicianDAO technicianDAO = DAOFactory.getTechnicianDAO();
		int count = technicianDAO.countLikeName( companyid, partialData );
		return count;
	} catch (DataException e) {
		PortalError error = new PortalError(ErrorCode.SYSTEM_ERROR);
		throw new ServiceException(error,e);
	}
  }

  public int getCountLikeEmail(String companyid, String partialData) throws ServiceException
  {
	try {
		TechnicianDAO technicianDAO = DAOFactory.getTechnicianDAO();
		int count = technicianDAO.countLikeEmail( companyid, partialData );
		return count;
	} catch (DataException e) {
		PortalError error = new PortalError(ErrorCode.SYSTEM_ERROR);
		throw new ServiceException(error,e);
	}
  }

  public int getCountLikeTechcid(String companyid, String partialData) throws ServiceException
  {
	try {
		TechnicianDAO technicianDAO = DAOFactory.getTechnicianDAO();
		int count = technicianDAO.countLikeTechcid( companyid, partialData );
		return count;
	} catch (DataException e) {
		PortalError error = new PortalError(ErrorCode.SYSTEM_ERROR);
		throw new ServiceException(error,e);
	}
  }

  public int getCountLikeTechcid(String partialData) throws ServiceException
  {
	try {
		TechnicianDAO technicianDAO = DAOFactory.getTechnicianDAO();
		int count = technicianDAO.countLikeTechcid( partialData );
		return count;
	} catch (DataException e) {
		PortalError error = new PortalError(ErrorCode.SYSTEM_ERROR);
		throw new ServiceException(error,e);
	}
  }

  public int getCountActiveByCompanyid(String companyid) throws ServiceException
  {
	try {
		TechnicianDAO technicianDAO = DAOFactory.getTechnicianDAO();
		int count = technicianDAO.countActiveByCompanyid( companyid );
		return count;
	} catch (DataException e) {
		PortalError error = new PortalError(ErrorCode.SYSTEM_ERROR);
		throw new ServiceException(error,e);
	}
  }

  public List getTechniciansLikeTechcid(String partialData) throws ServiceException
  {
        List technicians = null;
        List technicianDTOs = new ArrayList();

        try
        {
			TechnicianDAO technicianDAO = DAOFactory.getTechnicianDAO();
            technicians = technicianDAO.getTechniciansLikeTechcid( partialData ); 
        }
        catch(DataException e)
        {
            PortalError error = new PortalError(ErrorCode.SYSTEM_ERROR);
            throw new ServiceException(error, e);
        }

        TechnicianDTO dto;
        for(Iterator it = technicians.iterator(); it.hasNext(); technicianDTOs.add(dto))
        {
            Technician technician = (Technician)it.next();
            dto = TechnicianAssembler.createTechnicianDTO(technician);
        }

        return technicianDTOs;

  }

  public List getTechniciansLikeTechid(String companyid, String partialData) throws ServiceException
  {
        List technicians = null;
        List technicianDTOs = new ArrayList();

        try
        {
			TechnicianDAO technicianDAO = DAOFactory.getTechnicianDAO();
            technicians = technicianDAO.getTechniciansLikeTechid( companyid, partialData ); 
        }
        catch(DataException e)
        {
            PortalError error = new PortalError(ErrorCode.SYSTEM_ERROR);
            throw new ServiceException(error, e);
        }

        TechnicianDTO dto;
        for(Iterator it = technicians.iterator(); it.hasNext(); technicianDTOs.add(dto))
        {
            Technician technician = (Technician)it.next();
            dto = TechnicianAssembler.createTechnicianDTO(technician);
        }

        return technicianDTOs;

  }

  public List getTechniciansLikeTechcid(String companyid, String partialData) throws ServiceException
  {
        List technicians = null;
        List technicianDTOs = new ArrayList();

        try
        {
			TechnicianDAO technicianDAO = DAOFactory.getTechnicianDAO();
            technicians = technicianDAO.getTechniciansLikeTechcid( companyid, partialData ); 
        }
        catch(DataException e)
        {
            PortalError error = new PortalError(ErrorCode.SYSTEM_ERROR);
            throw new ServiceException(error, e);
        }

        TechnicianDTO dto;
        for(Iterator it = technicians.iterator(); it.hasNext(); technicianDTOs.add(dto))
        {
            Technician technician = (Technician)it.next();
            dto = TechnicianAssembler.createTechnicianDTO(technician);
        }

        return technicianDTOs;

  }

  public List getTechniciansLikeName(String companyid, String partialData) throws ServiceException
  {
        List technicians = null;
        List technicianDTOs = new ArrayList();

        try
        {
			TechnicianDAO technicianDAO = DAOFactory.getTechnicianDAO();
            technicians = technicianDAO.getTechniciansLikeName( companyid, partialData ); 
        }
        catch(DataException e)
        {
            PortalError error = new PortalError(ErrorCode.SYSTEM_ERROR);
            throw new ServiceException(error, e);
        }

        TechnicianDTO dto;
        for(Iterator it = technicians.iterator(); it.hasNext(); technicianDTOs.add(dto))
        {
            Technician technician = (Technician)it.next();
            dto = TechnicianAssembler.createTechnicianDTO(technician);
        }

        return technicianDTOs;

  }

  public List getTechniciansLikeEmail(String companyid, String partialData) throws ServiceException
  {
        List technicians = null;
        List technicianDTOs = new ArrayList();

        try
        {
			TechnicianDAO technicianDAO = DAOFactory.getTechnicianDAO();
            technicians = technicianDAO.getTechniciansLikeEmail( companyid, partialData ); 
        }
        catch(DataException e)
        {
            PortalError error = new PortalError(ErrorCode.SYSTEM_ERROR);
            throw new ServiceException(error, e);
        }

        TechnicianDTO dto;
        for(Iterator it = technicians.iterator(); it.hasNext(); technicianDTOs.add(dto))
        {
            Technician technician = (Technician)it.next();
            dto = TechnicianAssembler.createTechnicianDTO(technician);
        }

        return technicianDTOs;

  }

   public TechnicianDTO update(TechnicianDTO technicianDto) throws ServiceException
   {
     TechnicianDTO dto = null;
     TechnicianDAO dao = DAOFactory.getTechnicianDAO();
     try {
        Technician technician = new Technician();
		// copy the fields
		technician.setId(technicianDto.getId());
		technician.setCompanyid(technicianDto.getCompanyid());
		technician.setTechid(technicianDto.getTechid());
		technician.setTechcid(technicianDto.getTechcid());
		technician.setPassword(technicianDto.getPassword());
		technician.setEmail(technicianDto.getEmail());
		technician.setName(technicianDto.getName());
		technician.setPhone(technicianDto.getPhone());
		technician.setMisc(technicianDto.getMisc());
		technician.setMaxconns(technicianDto.getMaxconns());
		technician.setAccesslevel(technicianDto.getAccesslevel());
		technician.setCreatedatetime(technicianDto.getCreatedatetime());

	    // update the database
	    dao.update(technician);
	    dto = TechnicianAssembler.createTechnicianDTO(technician);
     }
     catch (DataException e){
        PortalError error = new PortalError(ErrorCode.SYSTEM_ERROR);
        throw new ServiceException(error,e);
     }
     return dto;
   }

   public int getCountLikeEmailAndCompany(String partialCompanyid, String partialData) throws ServiceException
   {
	try {
		TechnicianDAO technicianDAO = DAOFactory.getTechnicianDAO();
		int count = technicianDAO.countLikeCompanyidEmail( partialCompanyid, partialData );
		return count;
	} catch (DataException e) {
		PortalError error = new PortalError(ErrorCode.SYSTEM_ERROR);
		throw new ServiceException(error,e);
	}
   }

   public List getTechniciansLikeEmailAndCompany(String partialCompanyid, String partialData) throws ServiceException
   {
        List technicians = null;
        List technicianDTOs = new ArrayList();

        try
        {
			TechnicianDAO technicianDAO = DAOFactory.getTechnicianDAO();
            technicians = technicianDAO.getTechniciansLikeCompanyidEmail( partialCompanyid, partialData ); 
        }
        catch(DataException e)
        {
            PortalError error = new PortalError(ErrorCode.SYSTEM_ERROR);
            throw new ServiceException(error, e);
        }

        TechnicianDTO dto;
        for(Iterator it = technicians.iterator(); it.hasNext(); technicianDTOs.add(dto))
        {
            Technician technician = (Technician)it.next();
            dto = TechnicianAssembler.createTechnicianDTO(technician);
        }

        return technicianDTOs;

   }

}


