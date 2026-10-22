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
import com.infowave.c4p.util.ApplicationParameters;
import com.infowave.c4p.util.IdGenerator;
import com.infowave.c4p.web.WebException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;



public class TechnicianForm extends ActionForm
{
  private String nextaction = null;		// used for action mapping
  private String type = null;		// db column to use in search
  private String data = null;		// search string
  private List technicianList = null;
  private String technicianid = "";
  private int count = 0;
  private static final int MAX_ALLOWED = ApplicationParameters.instance().getMaxWebCount();
  private static final String DEFAULT_SEARCH = "%";
  private static final String TECHID = "techid";
  private static final String NAME = "name";
  private static final String LISTALL = "listalltechnicians";
  private static final String LISTBYEMAIL = "locatetechnicianbyemail";
  private static final String EMAIL = "email";
  private static final String TECHCID = "techcid";
  private static final String ENCODING = "8859_1";

  private String activeflags = null;
  private String email = null;
  private String misc = null;
  private String phone = null;
  private String name = null;
  private String password1 = null;
  private String password2 = null;
  private String techcid = null;
  private String techid = null;
  private String companyid = null;
  private String accessname = null;

  TechnicianDTO m_LoggedinTechnicianDTO = null;
  private static Log m_log = LogFactory.getLog(TechnicianForm.class);

  // ------------------------------------------------------- setters and getters
  public TechnicianDTO getLoggedinUser() { return m_LoggedinTechnicianDTO; }

  public void setLoggedinUser( TechnicianDTO techdto ) {
	m_LoggedinTechnicianDTO = techdto;
	if( !isAnyAdmin() ) {
		// This is a regular technician so we'll just
		// prepopulate the form with their info
		List errors = new ArrayList(1);
		findTechnician(errors);
	}
  }

  public String getAccessname() { return accessname; }

  public void setAccessname(String accessname)
  {
    this.accessname = accessname;
  }

  public String getActiveflags() { return activeflags; }

  public void setActiveflags(String activeflags)
  {
    this.activeflags = activeflags;
  }

  public String getEmail() { return email; }

  public void setEmail(String email)
  {
    this.email = StringManager.webDecode(email);
  }

  public String getMisc() { return misc; }

  public void setMisc(String misc)
  {
    this.misc = StringManager.webDecode(misc);
  }

  public String getPhone() { return phone; }

  public void setPhone(String phone)
  {
    this.phone = StringManager.webDecode(phone);
  }

  public String getName() { return name; }

  public void setName(String name)
  {
    this.name = StringManager.webDecode(name);
  }

  public String getPassword1() { return password1; }

  public void setPassword1(String password1)
  {
	try {
		byte[] item_b = password1.getBytes( ENCODING );
		this.password1 = new String(item_b);
	} catch( Exception eek ) { this.password1 = password1; }
  }

  public String getPassword2() { return password2; }

  public void setPassword2(String password2)
  {
	try {
		byte[] item_b = password2.getBytes( ENCODING );
		this.password2 = new String(item_b);
	} catch( Exception eek ) { this.password2 = password2; }
  }

  public String getTechid() { return techid; }

  public void setTechid(String techid)
  {
    this.techid = StringManager.webDecode(techid);
  }

  public String getTechcid() { return techcid; }

  public void setTechcid(String techcid)
  {
    this.techcid = StringManager.webDecode(techcid);
  }

  public String getCompanyid() { return companyid; }

  public void setCompanyid(String companyid)
  {
    this.companyid = StringManager.webDecode(companyid);
  }

  // ------------------------------------------------------- setters and getters

  public String getNextaction() { return nextaction; }

  public void setNextaction(String nextaction)
  {
    this.nextaction = nextaction;
  }

  public String getType() { return type; }

  public void setType(String type)
  {
    this.type = type;
  }

  public String getData() { return data; }

  public void setData(String data)
  {
    //this.data= data;
    this.data = StringManager.webDecode(data);
  }

  public String getTechnicianid() { return technicianid; }

  public void setTechnicianid(String technicianid)
  {
    this.technicianid = StringManager.webDecode(technicianid);
  }


  public List getTechnicianList()  { return technicianList; }

  public void setTechnicianList( List list )
  {
     technicianList = list;
  }


  public String getTechnicianCount() 
  {
    String countStr = Integer.toString(count);
    return countStr;
  }
  //--------------------------------------------------------------------------------------------

  protected List validateData(ActionMapping mapping, HttpServletRequest request)
  {
     List errors = new ArrayList(1);
     if( data == null || data.length() == 0 ) {
		data = DEFAULT_SEARCH;
     }
     return errors;
  }

  protected void findTechnician(List errors)
  {
    // Ensure proper access is being granted
	if( isCompanyAdmin() ) {
		// set companyid
		companyid = m_LoggedinTechnicianDTO.getCompanyid();
	}
	if( !isAnyAdmin() ) {
		// set companyid, techid, type
		companyid = m_LoggedinTechnicianDTO.getCompanyid();
		type = TECHID;
		data = m_LoggedinTechnicianDTO.getTechid();
	}

	// If the search is for every technician in this company, set a couple of items
	if( nextaction.equals(LISTALL) ) {
		type = TECHID;
		data = DEFAULT_SEARCH;
	}
	 
	// find out how many entries will be returned
	// if the number of okay, get the actual data
    TechnicianManager technicianManager = DelegateFactory.getTechnicianManager();
    if( nextaction.equals(LISTBYEMAIL) ) {
		// This one requests all technicians across all companies by email address
		if( isSiteAdmin() ) {
			try {
				count = technicianManager.getCountLikeEmailAndCompany( getCompanyid(), data );
			} catch( ServiceException ignored ) {  }
				if( count > 0 && count <= MAX_ALLOWED ) {
				try {
					technicianList = technicianManager.getTechniciansLikeEmailAndCompany( getCompanyid(), data );
					TechnicianDTO dto = (TechnicianDTO)technicianList.get(0);
				} catch( ServiceException ignored ) {  }
			}
		} else {
			// Only a company admin can request this info
			PortalError error = new PortalError(ErrorCode.NORIGHTS_ERROR, getTechid());
			setErrorInfo( errors, error );
			return;
		}
    } else if( type.equals(TECHID) ) {
		try {
			count = technicianManager.getCountLikeTechid( getCompanyid(), data );
		} catch( ServiceException ignored ) {  }
			if( count > 0 && count <= MAX_ALLOWED ) {
			try {
				technicianList = technicianManager.getTechniciansLikeTechid( getCompanyid(), data );
				TechnicianDTO dto = (TechnicianDTO)technicianList.get(0);
			} catch( ServiceException ignored ) {  }
		}
	} else if( type.equals(TECHCID) ) {
		if( isSiteAdmin() && getCompanyid() == null ) {
			try {
				count = technicianManager.getCountLikeTechcid( data );
			} catch( ServiceException ignored ) {  }
				if( count > 0 && count <= MAX_ALLOWED ) {
				try {
					technicianList = technicianManager.getTechniciansLikeTechcid( data );
					TechnicianDTO dto = (TechnicianDTO)technicianList.get(0);
				} catch( ServiceException ignored ) {  }
			}
		} else {
			try {
				count = technicianManager.getCountLikeTechcid( getCompanyid(), data );
			} catch( ServiceException ignored ) {  }
				if( count > 0 && count <= MAX_ALLOWED ) {
				try {
					technicianList = technicianManager.getTechniciansLikeTechcid( getCompanyid(), data );
					TechnicianDTO dto = (TechnicianDTO)technicianList.get(0);
				} catch( ServiceException ignored ) {  }
			}
		}
	} else if( type.equals(NAME) ) {
		try {
			count = technicianManager.getCountLikeName( getCompanyid(), data );
		} catch( ServiceException ignored ) {  }
			if( count > 0 && count <= MAX_ALLOWED ) {
			try {
				technicianList = technicianManager.getTechniciansLikeName( getCompanyid(), data );
				TechnicianDTO dto = (TechnicianDTO)technicianList.get(0);
			} catch( ServiceException ignored ) {  }
		}
	} else if( type.equals(EMAIL) ) {
		try {
			count = technicianManager.getCountLikeEmail( getCompanyid(), data );
		} catch( ServiceException ignored ) {  }
			if( count > 0 && count <= MAX_ALLOWED ) {
			try {
				technicianList = technicianManager.getTechniciansLikeEmail( getCompanyid(), data );
				TechnicianDTO dto = (TechnicianDTO)technicianList.get(0);
			} catch( ServiceException ignored ) {  }
		}
	}
  }

  protected void deleteTechnician(List errors)
  {
     // Ensure proper access is being granted
	 if( isCompanyAdmin() ) {
		// set companyid
		companyid = m_LoggedinTechnicianDTO.getCompanyid();
	 }
	 if( !isAnyAdmin() ) {
		// error
		PortalError error = new PortalError(ErrorCode.NORIGHTS_ERROR, getTechid());
		setErrorInfo( errors, error );
		return;
	 }
     try {
		TechnicianDAO technicianDAO = DAOFactory.getTechnicianDAO();
		technicianDAO.deleteByTechcid(getCompanyid(), getTechcid());
	 } catch( DataException de ) {
		PortalError error = new PortalError(ErrorCode.SYSTEM_ERROR);		
		setErrorInfo( errors, error );
	 }
  }

  protected void addTechnician(List errors)
  {
    // Ensure proper access is being granted
	if( isCompanyAdmin() ) {
		// set companyid
		companyid = m_LoggedinTechnicianDTO.getCompanyid();
	}
	if( !isAnyAdmin() ) {
		// error
		PortalError error = new PortalError(ErrorCode.NORIGHTS_ERROR, getTechid());
		setErrorInfo( errors, error );
		return;
	}
    try {
	    // First check if another technician can be added
		TechnicianManager technicianManager = DelegateFactory.getTechnicianManager();
		if( atMaxConns(technicianManager) && evaluateMaxconns() > 0 ) {
			PortalError error = new PortalError(ErrorCode.MAXCONNS_ERROR);		
			setErrorInfo( errors, error );
		} else {

			// see if id exists in database
			count = technicianManager.getCountLikeTechid( getCompanyid(), getTechid() );
			if( count == 0 ) {
				// We know that the techid/companyid combo is unique
				techcid = getUniqueCid(technicianManager);

				Technician technician = new Technician();
				technician.setCompanyid(getCompanyid());
				technician.setTechid(getTechid());
				technician.setTechcid(getTechcid());
				PasswordEncoder enc = PasswordEncoderFactory.instance().getEncoder(WebConstants.PWD_ENCODER);
				if( enc == null ) {
					throw new WebException( "PasswordEncoder MIA" );
				}
				technician.setPassword(enc.encode(password1));
				technician.setEmail(getEmail());
				technician.setName(getName());
				technician.setPhone(getPhone());
				technician.setMisc(getMisc());
				technician.setMaxconns(evaluateMaxconns());
				technician.setAccesslevel(convertAccessnameToLevel());

				// update the database
				TechnicianDAO technicianDAO = DAOFactory.getTechnicianDAO();
				technicianDAO.addTechnician(technician);

				// Load for later
				type = TECHCID;
				data = getTechcid();
				findTechnician(errors);
			} else {
				PortalError error = new PortalError(ErrorCode.NONUNIQUE_TECHID_ERROR, getTechid());
				setErrorInfo( errors, error );
			}
		}
	} catch( WebException we ) {
		PortalError error = new PortalError(ErrorCode.SYSTEM_ERROR);		
		setErrorInfo( errors, error );
	} catch( ServiceException se ) {
		PortalError error = new PortalError(ErrorCode.SYSTEM_ERROR);		
		setErrorInfo( errors, error );
	} catch( DataException de ) {
		PortalError error = new PortalError(ErrorCode.SYSTEM_ERROR);		
		setErrorInfo( errors, error );
	}
  }

  protected void changePasswordOfTechnician(List errors)
  {
     // Ensure proper access is being granted
	 if( isCompanyAdmin() ) {
		// set companyid
		String compId = m_LoggedinTechnicianDTO.getCompanyid();
		if( !compId.equals( getCompanyid() ) ) {
			PortalError error = new PortalError(ErrorCode.NORIGHTS_ERROR, getCompanyid());
			setErrorInfo( errors, error );
			return;
		}
	 }
	 if( !isAnyAdmin() ) {
		// Just set the pertinent info to match
		techcid = m_LoggedinTechnicianDTO.getTechcid();
		companyid = m_LoggedinTechnicianDTO.getCompanyid();
	 }	
	try { 
		TechnicianManager technicianManager = DelegateFactory.getTechnicianManager();
		TechnicianDTO dto = null;
		if( isSiteAdmin() ) {
			dto = technicianManager.getByTechcid( getTechcid() );
		} else {
			dto = technicianManager.getByTechcid( getCompanyid(), getTechcid() );
		}	
		Technician technician = new Technician();
		technician.setId(dto.getId());
		technician.setCompanyid(dto.getCompanyid());
		technician.setTechid(dto.getTechid());
		technician.setTechcid(dto.getTechcid());
		validatePassword();
		PasswordEncoder enc = PasswordEncoderFactory.instance().getEncoder(WebConstants.PWD_ENCODER);
		if( enc == null ) {
			throw new WebException( "PasswordEncoder MIA" );
		}
		technician.setPassword(enc.encode(password1));
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

		// Load for later
		companyid = dto.getCompanyid();
		type = TECHCID;
		data = dto.getTechcid();
		findTechnician(errors); 
	} catch( WebException we ) {
		PortalError error = new PortalError(ErrorCode.SYSTEM_ERROR);		
		setErrorInfo( errors, error );
	} catch( ServiceException se ) {
		PortalError error = new PortalError(ErrorCode.SYSTEM_ERROR);		
		setErrorInfo( errors, error );
	} catch( DataException de ) {
		PortalError error = new PortalError(ErrorCode.SYSTEM_ERROR);		
		setErrorInfo( errors, error );
	}
  }
	
	
  protected void validatePassword( ) throws WebException
  {
	// check that both passwords match and that they are the minimum length
	if( password1 == null || password1.length() < WebConstants.PWD_MIN || !password1.equals( password2) ) {
		throw new WebException( "bad password" );
	}

  }
	
	
	
  protected void updateTechnician(List errors)
  {
     // Ensure proper access is being granted
	 if( isCompanyAdmin() ) {
		// set companyid
		String compId = m_LoggedinTechnicianDTO.getCompanyid();
		if( !compId.equals( getCompanyid() ) ) {
			PortalError error = new PortalError(ErrorCode.NORIGHTS_ERROR, getCompanyid());
			setErrorInfo( errors, error );
			return;
		}
	 }
	 if( !isAnyAdmin() ) {
		// error if not same tech as the one logged in
		String compId = m_LoggedinTechnicianDTO.getCompanyid();
		if( !compId.equals( getCompanyid() ) ) {
			PortalError error = new PortalError(ErrorCode.NORIGHTS_ERROR, getCompanyid());
			setErrorInfo( errors, error );
			return;
		}
		String tcid = m_LoggedinTechnicianDTO.getTechcid();
		if( !tcid.equals( getTechcid() ) ) {
			PortalError error = new PortalError(ErrorCode.NORIGHTS_ERROR, getTechcid());
			setErrorInfo( errors, error );
			return;
		}
	 }
	try {
		int count = 0;
		// First gather all the data into an object we can use for updating
		TechnicianManager technicianManager = DelegateFactory.getTechnicianManager();
		TechnicianDTO dto = technicianManager.getByTechcid( getCompanyid(), getTechcid() );
		if( atMaxConns(technicianManager) && evaluateMaxconns() > 0 && dto.getMaxconns() <= 0 ) {
			PortalError error = new PortalError(ErrorCode.MAXCONNS_ERROR);		
			setErrorInfo( errors, error );

			// Load for later
			type = TECHCID;
			data = getTechcid();
			findTechnician(errors);

		} else {
			// The techid can be changed so we need to check some things
			if( !dto.getTechid().equals( getTechid() ) ) {
				// See if this techid has been taken
				count = technicianManager.getCountLikeTechid( getCompanyid(), getTechid() );
			}
			if( count == 0 ) {
				Technician technician = new Technician();
				technician.setId(dto.getId());
				technician.setCompanyid(dto.getCompanyid());
				technician.setTechid(getTechid());
				technician.setTechcid(dto.getTechcid());
				technician.setPassword(dto.getPassword());
				technician.setName(getName());
				technician.setEmail(getEmail());
				technician.setPhone(getPhone());
				technician.setMisc(getMisc());
				technician.setMaxconns(evaluateMaxconns());
				technician.setAccesslevel(convertAccessnameToLevel());
				technician.setCreatedatetime(dto.getCreatedatetime());

				// update the database
				TechnicianDAO technicianDAO = DAOFactory.getTechnicianDAO();
				technicianDAO.update(technician);

				// Load for later
				type = TECHCID;
				data = getTechcid();
				findTechnician(errors);
			} else {
				PortalError error = new PortalError(ErrorCode.NONUNIQUE_TECHID_ERROR, getTechid());
				setErrorInfo( errors, error );

				// Load for later
				type = TECHCID;
				data = getTechcid();
				findTechnician(errors);

			}
		}
	} catch( ServiceException se ) {
		PortalError error = new PortalError(ErrorCode.SYSTEM_ERROR);		
		setErrorInfo( errors, error );
	} catch( DataException de ) {
		PortalError error = new PortalError(ErrorCode.SYSTEM_ERROR);		
		setErrorInfo( errors, error );
	}
  }

  private void setErrorInfo( List errors, PortalError error )
  {
	errors.add(error);

	// Load up a TechnicianDTO object for later
	TechnicianDTO dto = new TechnicianDTO();
	dto.setCompanyid(getCompanyid());
	dto.setTechid(getTechid());
	dto.setTechcid(getTechcid());
	dto.setPassword(getPassword1());
	dto.setName(getName());
	dto.setPhone(getPhone());
	dto.setMisc(getMisc());
	dto.setMaxconns(0);
	dto.setAccesslevel(convertAccessnameToLevel());

	technicianList = new ArrayList();
	technicianList.add(dto);
  }

  public boolean isCompanyDedicated(CompanyDTO dto)
  {
	boolean isDedicated = true;
	if( dto != null ) {
		int licenseTypeInt = (int) dto.getLicensetype();
		if( (licenseTypeInt & WebConstants.DEDICATED_LICENSE) == 0 ) {
			isDedicated = false;
		}
	}

	return isDedicated;

  }




  public boolean isCompanyDedicated()
  {
    boolean isCompanyDedicated = false;

	CompanyDTO dto = getCompany();
	if( dto != null ) {
		isCompanyDedicated = isCompanyDedicated( dto );
	} else {
		List errors = new ArrayList(1);
		findTechnician(errors);
		TechnicianDTO tdto = (TechnicianDTO)technicianList.get(0);
		if( tdto != null ) {
			companyid = tdto.getCompanyid();
			dto = getCompany();
			if( dto != null ) {
				isCompanyDedicated = isCompanyDedicated( dto );
			}
		}
	}

	return isCompanyDedicated;

  }

  public boolean atMaxConns(TechnicianManager technicianManager) throws ServiceException
  {
	boolean atMaxConns = false;
	CompanyDTO dto = getCompany();

	if( isCompanyDedicated(dto) ) {
		// get the count of active technicians
		int activeCount = technicianManager.getCountActiveByCompanyid( getCompanyid() );
		// check against the maxconns of this company
		if( activeCount >= dto.getMaxconns() ) {
			atMaxConns = true;
		}
	}

	return atMaxConns;
  }

  public boolean isSiteAdmin()
  {
	boolean isSiteAdmin = false;
	if( m_LoggedinTechnicianDTO != null ) {
		int userAccessLevel = (int)m_LoggedinTechnicianDTO.getAccesslevel();
		isSiteAdmin = (userAccessLevel & WebConstants.TECHNICIAN_ACCESSLEVEL_SITE_ADMIN) > 0;
	}
	return isSiteAdmin;
  }

  private boolean isCompanyAdmin()
  {
	boolean isCompanyAdmin = false;
	if( m_LoggedinTechnicianDTO != null ) {
		int userAccessLevel = (int)m_LoggedinTechnicianDTO.getAccesslevel();
		isCompanyAdmin = (userAccessLevel & WebConstants.TECHNICIAN_ACCESSLEVEL_COMPANY_ADMIN) > 0;
		if( isCompanyAdmin ) {
			companyid = m_LoggedinTechnicianDTO.getCompanyid();
		}
	}
	return isCompanyAdmin;
  }

  public boolean isAnyAdmin()
  {
	boolean isAnyAdmin = isSiteAdmin();
	if( !isAnyAdmin ) {
		isAnyAdmin = isCompanyAdmin();
	} 
	return isAnyAdmin;
  }

  private CompanyDTO getCompany()
  {
    CompanyDTO dto = null;
	try {
		CompanyManager companyManager = DelegateFactory.getCompanyManager();
		dto = companyManager.getByCompanyid( getCompanyid() );
	} catch( ServiceException se ) {}

	return dto;
  }

  private int evaluateMaxconns()
  {
	int maxconns = WebConstants.TECHNICIAN_MAXCONNS_INACTIVE;

	// Until we have any different licensetypes, a technician's
	// maxconns is 1 or 0 (active or not)
	if( activeflags != null && "Y".equals( activeflags ) ) {
		maxconns = WebConstants.TECHNICIAN_MAXCONNS_ACTIVE;
	}

	return maxconns;
  }

  private double convertAccessnameToLevel()
  {
	int accesslevel = 0;
	boolean isCompanyDedicated = isCompanyDedicated();
	String accessnamestr = getAccessname();

	// Put in the active

	CompanyDTO companyDTO = getCompany();
	if( companyDTO != null ) {
		if( companyDTO.getActiveflags() > 0 ) {
			accesslevel |= WebConstants.TECHNICIAN_ACCESSLEVEL_ACTIVE;
		}
	}
	if( (isCompanyDedicated || WebConstants.TECHNICIAN_ACCESSNAME_TECH.equals(accessnamestr)) && isAnyAdmin() ) {
		accesslevel |= WebConstants.TECHNICIAN_ACCESSLEVEL_TECH;
	} else if( WebConstants.TECHNICIAN_ACCESSNAME_COMPANY_ADMIN.equals(accessnamestr) && isAnyAdmin() ) {
		accesslevel |= WebConstants.TECHNICIAN_ACCESSLEVEL_COMPANY_ADMIN;
	} else if( WebConstants.TECHNICIAN_ACCESSNAME_SITE_ADMIN.equals(accessnamestr) && isSiteAdmin() ) {
		accesslevel |= WebConstants.TECHNICIAN_ACCESSLEVEL_SITE_ADMIN;
	}
	double accessleveldbl = 0;
	accessleveldbl = accesslevel;

	return accessleveldbl;
  }

  private void setTechnicianActivation(List errors, boolean activate)
  {
	// Get a list of this company's technicians
	// change the status
	// save the list back into the database
     try {
		TechnicianDAO technicianDAO = DAOFactory.getTechnicianDAO();
	    if( activate ) {
			// activate all technicians
			technicianDAO.activateByCompanyid( getCompanyid() );
		} else {
			// inactivate all technicians
			technicianDAO.inactivateByCompanyid( getCompanyid() );
		}
	 } catch( DataException de ) {
		PortalError error = new PortalError(ErrorCode.SYSTEM_ERROR);		
		setErrorInfo( errors, error );
	 }

  }

  public final static String getUniqueCid(TechnicianManager technicianManager) throws ServiceException
  {
	// Next we have to check that the techcid id unique
	boolean found = false;
	String cid = null;
	int nbTries = 0;
	while (!found){
		cid = IdGenerator.getCid();
		nbTries ++;
		while (cid.length() < WebConstants.CID_SIZE){
			cid += IdGenerator.getId();
		}
		cid = cid.substring(0, WebConstants.CID_SIZE);
		if( StringManager.isOkay(cid) ) {
			if (technicianManager.getCountLikeTechcid( cid ) == 0){
				  found = true;
			}
		}
	}
	m_log.debug("Number of tried to find a unique cid: " + nbTries);
	return cid;
  }

}
