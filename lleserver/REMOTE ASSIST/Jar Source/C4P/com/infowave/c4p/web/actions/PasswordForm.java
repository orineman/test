package com.infowave.c4p.web.actions;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.infowave.c4p.service.dto.CompanyDTO;
import com.infowave.c4p.service.dto.TechnicianDTO;
import com.infowave.c4p.service.delegate.TechnicianManager;
import com.infowave.c4p.service.delegate.CompanyManager;
import com.infowave.c4p.service.delegate.DelegateFactory;
import com.infowave.c4p.service.ServiceException;
import com.infowave.c4p.datasource.DataException;
import com.infowave.c4p.model.Technician;
import com.infowave.c4p.datasource.dao.TechnicianDAO;
import com.infowave.c4p.datasource.dao.DAOFactory;
import com.infowave.c4p.web.util.WebConstants;
import com.infowave.c4p.util.PortalError;
import com.infowave.c4p.util.ErrorCode;
import com.infowave.c4p.util.StringManager;
import com.infowave.c4p.util.security.PasswordEncoder;
import com.infowave.c4p.util.security.PasswordEncoderFactory;
import com.infowave.c4p.web.WebException;
import com.infowave.c4p.util.IdGenerator;
import com.infowave.c4p.web.util.Notifier;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.ArrayList;
import java.util.Locale;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;



public class PasswordForm extends ActionForm
{
  private String password = null;
  private String techid = null;
  private String companyid = null;

  private static Log m_log = LogFactory.getLog(TechnicianForm.class);

  // ------------------------------------------------------- setters and getters

  public String getPassword() { return password; }

  public void setPassword(String password)
  {
    this.password = password;
  }

  public String getTechid() { return techid; }

  public void setTechid(String techid)
  {
    this.techid = StringManager.webDecode(techid);
  }

  public String getCompanyid() { return companyid; }

  public void setCompanyid(String companyid)
  {
    this.companyid = StringManager.webDecode(companyid);
  }

  //--------------------------------------------------------------------------------------------

  protected List validateData(ActionMapping mapping, HttpServletRequest request)
  {
     List errors = new ArrayList(1);
     return errors;
  }

  protected TechnicianDTO findTechnician(TechnicianManager technicianManager, List errors)
  {
    TechnicianDTO dto = null;

	try {
		dto = technicianManager.getByDoubleid( getCompanyid(), getTechid() );
		if( dto == null ) {
			PortalError error = new PortalError(ErrorCode.NOUSER_ERROR, getCompanyid()+ " - " +getTechid());
			setErrorInfo( errors, error );
		}
	} catch( ServiceException se ) {  
		PortalError error = new PortalError(ErrorCode.NOUSER_ERROR, getCompanyid()+ " - " +getTechid());
		setErrorInfo( errors, error );
	}

	return dto;
  }

  protected CompanyDTO findCompany(CompanyManager companyManager, List errors)
  {
    CompanyDTO dto = null;

	try {
		dto = companyManager.getByCompanyid( getCompanyid() );
		if( dto == null ) {
			PortalError error = new PortalError(ErrorCode.NOCOMPANY_ERROR, getCompanyid());
			setErrorInfo( errors, error );
		}
	} catch( ServiceException ignored ) {  
		PortalError error = new PortalError(ErrorCode.NOCOMPANY_ERROR, getCompanyid());
		setErrorInfo( errors, error );
	}

	return dto;
  }


  protected void resetPassword(List errors)
  {
    // get the user & company
	TechnicianManager technicianManager = DelegateFactory.getTechnicianManager();
	TechnicianDTO dto = findTechnician(technicianManager, errors);
	if( dto != null ) {
		CompanyManager companyManager = DelegateFactory.getCompanyManager();
		CompanyDTO companyDto = findCompany(companyManager, errors);
		if( companyDto != null ) {
			// get a random password
			password = generatePassword();

			// reset the password
			try { 
				Technician technician = new Technician();
				technician.setId(dto.getId());
				technician.setCompanyid(dto.getCompanyid());
				technician.setTechid(dto.getTechid());
				technician.setTechcid(dto.getTechcid());
				PasswordEncoder enc = PasswordEncoderFactory.instance().getEncoder(WebConstants.PWD_ENCODER);
				if( enc == null ) {
					throw new WebException( "PasswordEncoder MIA" );
				}
				technician.setPassword(enc.encode(password));
				technician.setName(dto.getName());
				technician.setEmail(dto.getEmail());
				technician.setPhone(dto.getPhone());
				technician.setMisc(dto.getMisc());
				technician.setMaxconns(dto.getMaxconns());
				technician.setAccesslevel(dto.getAccesslevel());
				technician.setCreatedatetime(dto.getCreatedatetime());

				// update the database
				TechnicianDAO technicianDAO = DAOFactory.getTechnicianDAO();
				technicianDAO.update(technician);

				// notify the user by email that the password has been reset
				Notifier.instance().notifyPasswordReset( companyid, dto.getEmail(), password, new Locale(companyDto.getLocale()) );

			} catch( WebException we ) {
				PortalError error = new PortalError(ErrorCode.SYSTEM_ERROR);		
				setErrorInfo( errors, error );
			} catch( DataException de ) {
				PortalError error = new PortalError(ErrorCode.SYSTEM_ERROR);		
				setErrorInfo( errors, error );
			}
		}
	}
  }
	
  public final static String generatePassword()
  {
    boolean isOkay = false;
	String pwd = null;
	while( !isOkay ) {
		pwd = IdGenerator.getPassword();
		while (pwd.length() < WebConstants.PWD_MIN_SIZE){
			pwd += IdGenerator.getPassword();
		}
		int len = Math.min(pwd.length(), WebConstants.PWD_MAX_SIZE);
		pwd = pwd.substring(0, len);
		isOkay = StringManager.isOkay( pwd );
	}
	return pwd;
  }


  private void setErrorInfo( List errors, PortalError error )
  {
	errors.add(error);
  }


}
