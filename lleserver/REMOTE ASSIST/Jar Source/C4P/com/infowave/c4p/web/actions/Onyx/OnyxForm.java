package com.infowave.c4p.web.actions.onyx;

import com.infowave.c4p.web.actions.PasswordForm;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.infowave.c4p.service.dto.CompanyDTO;
import com.infowave.c4p.service.delegate.CompanyManager;
import com.infowave.c4p.service.delegate.TechnicianManager;
import com.infowave.c4p.service.delegate.DelegateFactory;
import com.infowave.c4p.service.ServiceException;
import com.infowave.c4p.datasource.DataException;
import com.infowave.c4p.model.Company;
import com.infowave.c4p.model.Technician;
import com.infowave.c4p.datasource.dao.TechnicianDAO;
import com.infowave.c4p.datasource.dao.CompanyDAO;
import com.infowave.c4p.datasource.dao.DAOFactory;
import com.infowave.c4p.util.ApplicationParameters;
import com.infowave.c4p.util.PortalError;
import com.infowave.c4p.util.ErrorCode;
import com.infowave.c4p.util.StringManager;
import com.infowave.c4p.util.security.PasswordEncoder;
import com.infowave.c4p.util.security.PasswordEncoderFactory;
import com.infowave.c4p.web.actions.TechnicianForm;
import com.infowave.c4p.web.util.Notifier;
import com.infowave.c4p.web.util.WebConstants;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.ArrayList;
import java.util.Locale;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;



public class OnyxForm extends ActionForm
{
  private static final String ENG = "ENG";
  private static final String FRE = "FRA";
  private static final String GER = "GER";
  private static final String ITA = "ITA";
  private static final String JPN = "JPN";
  private static final String SPA = "SPA";
  private static final String EN = "en";
  private static final String FR = "fr";
  private static final String DE = "de";
  private static final String IT = "it";
  private static final String JA = "ja";
  private static final String ES = "es";

  private String action = null;		
  private String localestr = null;
  private String activeFlags = null;
  private String maxConns = null;
  private String miscinfo1 = null;
  private String miscinfo2 = null;
  private String companyID = null;
  private String companyName = null;
  private String xref = null;
  private String licenseType = null;
  private String technicianID = null;
  private String email = null;

  private static Log m_log = LogFactory.getLog(OnyxForm.class);


  public String getAction() { return action; }

  public void setAction(String action)
  {
    this.action = action;
  }

  public String getLocalestr() { return localestr; }

  public void setLocalestr(String locale)
  {
    if( locale != null ) {
		String loc = EN;
		locale = locale.trim();
		if( locale.equalsIgnoreCase( ENG ) ) {
			loc = EN;
		} else if( locale.equalsIgnoreCase( FRE ) ) {
			loc = FR;
		} else if( locale.equalsIgnoreCase( GER ) ) {
			loc = DE;
		} else if( locale.equalsIgnoreCase( ITA ) ) {
			loc = IT;
		} else if( locale.equalsIgnoreCase( JPN ) ) {
			loc = JA;
		} else if( locale.equalsIgnoreCase( SPA ) ) {
			loc = ES;
		}
		this.localestr = loc;
	}
  }

  public String getActiveFlags() { return activeFlags; }

  public void setActiveFlags(String activeFlags)
  {
    this.activeFlags = activeFlags;
  }

  public String getMaxConns() 
  { 
	return maxConns; 
  }

  public void setMaxConns(String maxConns)
  {
    this.maxConns = maxConns;
  }

  public String getMiscinfo2() { return miscinfo2; }

  public void setMiscinfo2(String miscinfo2)
  {
    this.miscinfo2 = miscinfo2;
  }

  public String getMiscinfo1() { return miscinfo1; }

  public void setMiscinfo1(String miscinfo1)
  {
    this.miscinfo1 = miscinfo1;
  }

  public String getCompanyID() { return companyID; }

  public void setCompanyID(String companyID)
  {
    this.companyID = companyID;
  }

  public String getCompanyName() { return companyName; }

  public void setCompanyName(String companyName)
  {
    this.companyName = companyName;
  }

  public String getXref() { return xref; }

  public void setXref(String xref)
  {
    this.xref = xref;
  }

  public String getLicenseType() { return licenseType; }

  public void setLicenseType(String licenseType)
  {
    this.licenseType = licenseType;
  }

  public String getTechnicianID() { return technicianID; }

  public void setTechnicianID(String technicianID)
  {
    this.technicianID = technicianID;
  }

  public String getEmail() { return email; }

  public void setEmail(String email)
  {
    m_log.error("JAE debug email = " +email );
    this.email = email.trim();
  }

  public boolean isCancel()
  {
    boolean isCancel = false;
	if( action != null && action.equalsIgnoreCase( "cancel" ) ) {
		isCancel = true;
	}
	return isCancel;
  }

  protected List validateData(ActionMapping mapping, HttpServletRequest request) throws OnyxException
  {
     List errors = new ArrayList(1);
	 String cid = getCompanyID();
	 if( isCancel() ) {
		 if( xref == null || xref.length() == 0 ) {
			m_log.debug("No xref in Onyx cancel");
			throw new OnyxException("No xref");
		 }
	 } else {
		 if( cid == null || cid.length() == 0 ) {
			m_log.debug("No Companyid");
			throw new OnyxException("No companyid");
		 }
	 }
     return errors;
  }

  protected void performAction() throws OnyxException
  {
	try {
	    if( isCancel() ) {
			cancelCompany();
		} else {
			CompanyManager companyManager = DelegateFactory.getCompanyManager();
			int count = companyManager.getCountLikeCompanyid( getCompanyID() );
			if( count == 0 ) {
				addCompany();
				addTechnician();
			 } else {
				updateCompany();
			 }
		 }
	} catch( ServiceException se ) {
		m_log.debug("ServiceException = " +se.toString());
		throw new OnyxException(se.toString());
	}
 }


  protected void cancelCompany() throws OnyxException
  {
     try {
		CompanyManager companyManager = DelegateFactory.getCompanyManager();
		List companies = companyManager.getCompaniesLikeXref( getXref() );
		if( !companies.isEmpty() ) {
			// cancel the company
			m_log.debug("Canceling companies with xref = " +getXref() );
			companyManager.cancelByXref( getXref() );
			// set the technicians to inactive
			CompanyDTO dto = null;
			int len = companies.size();
			for( int i=0; i<len; i++ ) {
				dto = (CompanyDTO) companies.get(i);
				m_log.debug("Canceling technicians for companyid " +dto.getCompanyid() );
				setTechnicianToInactive(dto.getCompanyid());
			}

		} else {
			m_log.debug("Company with GUID, " +getXref()+ ", doesn't exist.");
		}
	} catch( ServiceException se ) {
			throw new OnyxException(se.toString());
	} catch( DataException de ) {
			throw new OnyxException(de.toString());
	}

  }

  protected void updateCompany() throws OnyxException
  {
     boolean isNewlyDedicated = false;
     boolean activeChanged = false;
     boolean isActive = false;
     try {
	    // First see if id exists in database
		CompanyManager companyManager = DelegateFactory.getCompanyManager();
		CompanyDTO dto = companyManager.getByCompanyid( getCompanyID() );
		if( dto != null ) {
			// update company
			Company company = new Company();
			company.setId(dto.getId());
			company.setCompanyid(dto.getCompanyid());
			double lt = dto.getLicensetype();
			if( licenseType != null ) {
				lt = convertLicenseToType();
			}
			if( lt != dto.getLicensetype() && lt == WebConstants.DEDICATED_LICENSE ) {
				isNewlyDedicated = true;
			}
			company.setLicensetype(lt);
			company.setMiscflags(dto.getMiscflags());
			company.setCreatedatetime(dto.getCreatedatetime());

			String loc = getLocalestr();
			if( loc != null && loc.length() > 0 ) {
				company.setLocale(loc);
			} else {
				company.setLocale(dto.getLocale());
			}

			if( getMiscinfo1() != null ) {
				company.setMiscinfo1(getMiscinfo1());
			} else {
				company.setMiscinfo1(dto.getMiscinfo1());
			}

			if( getMiscinfo2() != null ) {
				company.setMiscinfo2(getMiscinfo2());
			} else {
				company.setMiscinfo2(dto.getMiscinfo2());
			}

			// This is what can be updated
			if( getCompanyName() != null ) {
				company.setName(getCompanyName());
			} else {
				company.setName(dto.getName());
			}

			if( getXref() != null ) {
				company.setXref(getXref());
			} else {
				company.setXref(dto.getXref());
			}

			m_log.debug("getMaxConns() = " +getMaxConns()+ ", dto.maxconns = " +dto.getMaxconns() );
			if( getMaxConns() != null ) {
				company.setMaxconns(Integer.parseInt(getMaxConns())); 
			} else {
				company.setMaxconns(dto.getMaxconns()); 
			}

			if( getActiveFlags() != null ) {
				double currentFlags = dto.getActiveflags();
				double newFlags = (isActive()?1:0);
				activeChanged = (currentFlags != newFlags);
				isActive = (newFlags > 0);
				company.setActiveflags(newFlags); 
			} else {
				company.setActiveflags(dto.getActiveflags()); 
			}

			// update the database
			CompanyDAO companyDAO = DAOFactory.getCompanyDAO();
			companyDAO.update(company);

			// flip the technicians if the active flag for the company changed
			if(activeChanged) {
				setTechnicianActivation( isActive );
			}

			if( isNewlyDedicated ) {
				setTechnicianAccessToLowest();
			}
		} else {
			throw new OnyxException("Company, " +getCompanyID()+ ", doesn't exist!");
		}
	} catch( ServiceException se ) {
			throw new OnyxException(se.toString());
	} catch( DataException de ) {
			throw new OnyxException(de.toString());
	}
  }

  private void setTechnicianToInactive(String companyid) throws DataException
  {
	// set the technician to inactive
	setCompanyID( companyid );
	setTechnicianActivation( false );
  }

  private void setTechnicianActivation(boolean activate) throws DataException
  {
	// Get a list of this company's technicians
	// change the status
	// save the list back into the database
	TechnicianDAO technicianDAO = DAOFactory.getTechnicianDAO();
	if( activate ) {
		// activate all technicians
		technicianDAO.activateByCompanyid( getCompanyID() );
	} else {
		// inactivate all technicians
		technicianDAO.inactivateByCompanyid( getCompanyID() );
	}

  }

  private void setTechnicianAccessToLowest() throws DataException
  {
	TechnicianDAO technicianDAO = DAOFactory.getTechnicianDAO();
	// downgrade all technicians for this company
	technicianDAO.setAccesslevelByCompanyid( getCompanyID(), Integer.toString(WebConstants.TECHNICIAN_ACCESSLEVEL_TECH)  );

  }

  protected void addCompany() throws OnyxException
  {
     try {
		// add company
		Company company = new Company();
		company.setCompanyid(getCompanyID());
		company.setName(getCompanyName());
		company.setLocale(getLocalestr());
		company.setMiscinfo1(getMiscinfo1());
		company.setMiscinfo2(getMiscinfo2());
		company.setXref(getXref());
		company.setMaxconns(Integer.parseInt((getMaxConns()==null?"1":getMaxConns()))); 
		company.setLicensetype(convertLicenseToType());
		company.setActiveflags((isActive()?1:0)); 
		company.setMiscflags(WebConstants.COMPANY_MISCFLAGS_DEFAULT);

		// update the database
		CompanyDAO companyDAO = DAOFactory.getCompanyDAO();
		companyDAO.addCompany(company);

	} catch( DataException de ) {
			throw new OnyxException(de.toString());
	}
  }

  protected void addTechnician()  throws OnyxException
  {
    try {
	    // First see if id exists in database
		TechnicianManager technicianManager = DelegateFactory.getTechnicianManager();

		String techcid = TechnicianForm.getUniqueCid(technicianManager);
		String password = PasswordForm.generatePassword();

		Technician technician = new Technician();
		technician.setCompanyid(getCompanyID());
		technician.setTechid(getTechnicianID());
		technician.setTechcid( techcid );
		PasswordEncoder enc = PasswordEncoderFactory.instance().getEncoder(WebConstants.PWD_ENCODER);
		if( enc == null ) {
			throw new OnyxException( "PasswordEncoder MIA" );
		}
		technician.setPassword(enc.encode(password));
		technician.setEmail(getEmail());
		technician.setName("");
		technician.setPhone("");
		technician.setMisc("");
		technician.setMaxconns(1);
		technician.setAccesslevel(convertAccessLevel());

		// update the database
		TechnicianDAO technicianDAO = DAOFactory.getTechnicianDAO();
		technicianDAO.addTechnician(technician);

		// notify the user by email that their account is ready to go
		Email email = new Email(getCompanyID(), getTechnicianID(), password, getEmail(), new Locale(getLocalestr()));
		email.start();

	} catch( OnyxException oe ) {
			throw new OnyxException(oe.toString());
	} catch( ServiceException se ) {
			throw new OnyxException(se.toString());
	} catch( DataException de ) {
			throw new OnyxException(de.toString());
	}
  }

  private boolean isActive()
  {
	return "1".equals(getActiveFlags());
  }

  private double convertAccessLevel()
  {
	double accesslevel = 0;
	int ali = 0;
	if( isActive() ) {
		ali |= WebConstants.TECHNICIAN_ACCESSLEVEL_ACTIVE;
	}
	switch( getLicenseTypeInt() ) {
	case WebConstants.CONCURRENT_LICENSE:
		ali |= WebConstants.TECHNICIAN_ACCESSLEVEL_COMPANY_ADMIN;
		break;
	default:
	case WebConstants.DEDICATED_LICENSE:
		ali |= WebConstants.TECHNICIAN_ACCESSLEVEL_TECH;
		break;
	}
	accesslevel = (double)ali;

	return accesslevel;
  }

  private double convertLicenseToType()
  {
	double typeDbl = 0;
	int typeInt = getLicenseTypeInt();
	typeDbl = (double) typeInt;
	return typeDbl;
  }

  private int getLicenseTypeInt()
  {
	int type = 0;
	m_log.debug("licenseType = " +licenseType);
	if( licenseType != null && (licenseType.charAt(0) == 'C' || licenseType.charAt(0) == 'c') ) {
		type |= WebConstants.CONCURRENT_LICENSE;
	} else {
		type |= WebConstants.DEDICATED_LICENSE;
	}
	return type;
  }

  private class Email extends Thread
  {
    String companyId;
	String techId;
	String pwd;
	String email;
	Locale loc = null;

	Email(String cid, String tid, String password, String adr, Locale l) 
	{
		companyId = cid;
		techId = tid;
		pwd = password;
		email = adr;
		loc = l;
	}
	public void run( )
	{
		Notifier.instance().notifyNewAccount( companyId, techId, pwd, email, loc );
	}
  }
}
