package com.infowave.c4p.web.actions;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.infowave.c4p.service.dto.CompanyDTO;
import com.infowave.c4p.service.delegate.CompanyManager;
import com.infowave.c4p.service.delegate.DelegateFactory;
import com.infowave.c4p.service.ServiceException;
import com.infowave.c4p.datasource.DataException;
import com.infowave.c4p.model.Company;
import com.infowave.c4p.datasource.dao.TechnicianDAO;
import com.infowave.c4p.datasource.dao.CompanyDAO;
import com.infowave.c4p.datasource.dao.DAOFactory;
import com.infowave.c4p.web.util.WebConstants;
import com.infowave.c4p.util.ApplicationParameters;
import com.infowave.c4p.util.PortalError;
import com.infowave.c4p.util.ErrorCode;
import com.infowave.c4p.util.StringManager;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;



public class CompanyForm extends ActionForm
{
  private String nextaction = null;		// used for action mapping
  private String type = null;		// db column to use in search
  private String data = null;		// search string
  private List companyList = null;
  private String companyName = "";
  private int count = 0;
  private static final int MAX_ALLOWED = ApplicationParameters.instance().getMaxWebCount();
  private static final String DEFAULT_SEARCH = "%";
  private static final String COMPANYID = "companyid";
  private static final String NAME = "name";
  private static final String XREF = "sn";
  private static final String LISTALL = "listallcompanies";

  private String locale = null;
  private String activeflags = null;
  private String maxconns = null;
  private String miscinfo1 = null;
  private String miscinfo2 = null;
  private String name = null;
  private String companyid = null;
  private String sn = null;
  private String licenseType = null;

  private static Log m_log = LogFactory.getLog(CompanyForm.class);

  // ------------------------------------------------------- setters and getters

  public String getLocale() { return locale; }

  public void setLocale(String locale)
  {
    this.locale = locale;
  }

  public String getActiveflags() { return activeflags; }

  public void setActiveflags(String activeflags)
  {
    this.activeflags = activeflags;
  }

  public String getMaxconns() { return maxconns; }

  public void setMaxconns(String maxconns)
  {
    this.maxconns = maxconns;
  }

  public String getMiscinfo2() { return miscinfo2; }

  public void setMiscinfo2(String miscinfo2)
  {
    this.miscinfo2 = StringManager.webDecode(miscinfo2);
  }

  public String getMiscinfo1() { return miscinfo1; }

  public void setMiscinfo1(String miscinfo1)
  {
    this.miscinfo1 = StringManager.webDecode(miscinfo1);
  }

  public String getName() { return name; }

  public void setName(String name)
  {
    this.name = StringManager.webDecode(name);
  }

  public String getCompanyid() { return companyid; }

  public void setCompanyid(String companyid)
  {
    this.companyid = StringManager.webDecode(companyid);
  }

  public String getSn() { return sn; }

  public void setSn(String sn)
  {
    this.sn = StringManager.webDecode(sn);
  }

  public String getLicenseType() { return licenseType; }

  public void setLicenseType(String licenseType)
  {
    this.licenseType = licenseType;
  }

  // ------------------------------------------------------- 

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

  public String getCompanyname() { return companyName; }

  public void setCompanyname(String companyName)
  {
    this.companyName = StringManager.webDecode(companyName);
  }


  public List getCompanyList()  { return companyList; }

  public void setCompanyList( List list )
  {
     companyList = list;
  }


  public String getCompanyCount() 
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

  protected void findCompany()
  {
    // If this is a request to list all companies, we set some data
	if( nextaction.equals( LISTALL ) ) {
		type = COMPANYID;
		data = DEFAULT_SEARCH;
	}
	// find out how many entries will be returned
	// if the number of okay, get the actual data
    CompanyManager companyManager = DelegateFactory.getCompanyManager();
    if( type.equals(COMPANYID) ) {
		try {
			count = companyManager.getCountLikeCompanyid( data );
		} catch( ServiceException ignored ) {  }
			if( count > 0 && count <= MAX_ALLOWED ) {
			try {
				companyList = companyManager.getCompaniesLikeCompanyid( data );
				CompanyDTO dto = (CompanyDTO)companyList.get(0);
				companyName = dto.getName();
				companyid = dto.getCompanyid();
				sn = dto.getXref();
			} catch( ServiceException ignored ) {  }
		}
	} else if( type.equals(NAME) ) {
		try {
			count = companyManager.getCountLikeName( data );
		} catch( ServiceException ignored ) {  }
			if( count > 0 && count <= MAX_ALLOWED ) {
			try {
				companyList = companyManager.getCompaniesLikeName( data );
				CompanyDTO dto = (CompanyDTO)companyList.get(0);
				companyName = dto.getName();
				companyid = dto.getCompanyid();
				sn = dto.getXref();
			} catch( ServiceException ignored ) {  }
		}
	} else if( type.equals(XREF) ) {
		try {
			count = companyManager.getCountLikeXref( data );
		} catch( ServiceException ignored ) {  }
			if( count > 0 && count <= MAX_ALLOWED ) {
			try {
				companyList = companyManager.getCompaniesLikeXref( data );
				CompanyDTO dto = (CompanyDTO)companyList.get(0);
				companyName = dto.getName();
				companyid = dto.getCompanyid();
				sn = dto.getXref();
			} catch( ServiceException ignored ) {  }
		}
	}
  }

  protected void deleteCompany(List errors)
  {
     try {
		CompanyDAO companyDAO = DAOFactory.getCompanyDAO();
		companyDAO.deleteByCompanyid(getCompanyid());
		TechnicianDAO technicianDAO = DAOFactory.getTechnicianDAO();
		technicianDAO.deleteAllByCompanyid(getCompanyid());
	 } catch( DataException de ) {
		PortalError error = new PortalError(ErrorCode.SYSTEM_ERROR);		
		setErrorInfo( errors, error );
	 }
  }

  protected void addCompany(List errors)
  {
     try {
	    // First see if id exists in database
		CompanyManager companyManager = DelegateFactory.getCompanyManager();
		count = companyManager.getCountLikeCompanyid( getCompanyid() );
		if( count == 0 ) {
			// add company
			Company company = new Company();
			company.setCompanyid(getCompanyid());
			company.setName(getName());
			company.setLocale(getLocale());
			company.setMiscinfo1(getMiscinfo1());
			company.setMiscinfo2(getMiscinfo2());
			company.setXref(getSn());
			company.setMaxconns(Integer.parseInt(getMaxconns())); 
			company.setLicensetype(evaluateLicense());
			company.setActiveflags(("Y".equals(getActiveflags())?1:0)); 
			company.setMiscflags(WebConstants.COMPANY_MISCFLAGS_DEFAULT);

			// update the database
			CompanyDAO companyDAO = DAOFactory.getCompanyDAO();
			companyDAO.addCompany(company);

			// Load for later
			type = COMPANYID;
			data = getCompanyid();
			findCompany();
		} else {
			PortalError error = new PortalError(ErrorCode.NONUNIQUE_COMPANYID_ERROR, getCompanyid());
			setErrorInfo( errors, error );
		}
	} catch( ServiceException se ) {
		PortalError error = new PortalError(ErrorCode.SYSTEM_ERROR);		
		setErrorInfo( errors, error );
	} catch( DataException de ) {
		PortalError error = new PortalError(ErrorCode.SYSTEM_ERROR);		
		setErrorInfo( errors, error );
	}
  }

  protected void updateCompany(List errors)
  {
    boolean isNewlyDedicated = false;
	try {
		// First gather all the data into an object we can use for updating
		CompanyManager companyManager = DelegateFactory.getCompanyManager();
		CompanyDTO dto = companyManager.getByCompanyid( getCompanyid() );
		Company company = new Company();
		company.setId(dto.getId());
		company.setCompanyid(dto.getCompanyid());
		company.setName(getName());
		company.setLocale(getLocale());
		company.setMiscinfo1(getMiscinfo1());
		company.setMiscinfo2(getMiscinfo2());
		company.setXref(getSn());
		company.setMaxconns(Integer.parseInt(getMaxconns())); 
		double lt = evaluateLicense();
		if( lt != dto.getLicensetype() && lt == WebConstants.DEDICATED_LICENSE ) {
			isNewlyDedicated = true;
		}
		company.setLicensetype(lt);
		double activeflagsdbl = ("Y".equals(getActiveflags())?1:0);
		company.setActiveflags(activeflagsdbl); 
		company.setMiscflags(dto.getMiscflags());
		company.setCreatedatetime(dto.getCreatedatetime());

		// update the database
		CompanyDAO companyDAO = DAOFactory.getCompanyDAO();
		companyDAO.update(company);

		// Set the company's techs to match the current active status
		if( dto.getActiveflags() != activeflagsdbl ) {
			setTechnicianActivation(errors, (activeflagsdbl > 0) );
		}

		// If license has been switched to dedicated, ensure no company or siteadmin
		if( isNewlyDedicated ) {
			setTechnicianAccessToLowest(errors);
		}

		// Load for later
		type = COMPANYID;
		data = getCompanyid();
		findCompany();
	} catch( ServiceException se ) {
		PortalError error = new PortalError(ErrorCode.SYSTEM_ERROR);		
		setErrorInfo( errors, error );
	} catch( DataException de ) {
		PortalError error = new PortalError(ErrorCode.SYSTEM_ERROR);		
		setErrorInfo( errors, error );
	}
  }

  private double evaluateLicense()
  {
	int licenseInt = "C".equals(getLicenseType())?WebConstants.CONCURRENT_LICENSE:WebConstants.DEDICATED_LICENSE;
	double licenseDbl = 0;
	licenseDbl = (double) licenseInt;

	return licenseDbl;
  }

  private void setErrorInfo( List errors, PortalError error )
  {
	errors.add(error);

	// Load up a companyDTO object for later
	CompanyDTO dto = new CompanyDTO();
	dto.setCompanyid(getCompanyid());
	dto.setName(getName());
	dto.setLocale(getLocale());
	dto.setMiscinfo1(getMiscinfo1());
	dto.setMiscinfo2(getMiscinfo2());
	dto.setXref(getSn());
	dto.setMaxconns(Integer.parseInt(getMaxconns())); 
	dto.setLicensetype(evaluateLicense());
	dto.setActiveflags(("Y".equals(getActiveflags())?1:0)); 
	dto.setMiscflags(WebConstants.COMPANY_MISCFLAGS_DEFAULT);

	companyList = new ArrayList();
	companyList.add(dto);

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

  private void setTechnicianAccessToLowest(List errors)
  {
     try {
		TechnicianDAO technicianDAO = DAOFactory.getTechnicianDAO();
		// downgrade all technicians for this company
		technicianDAO.setAccesslevelByCompanyid( getCompanyid(), Integer.toString(WebConstants.TECHNICIAN_ACCESSLEVEL_TECH)  );
	 } catch( DataException de ) {
		PortalError error = new PortalError(ErrorCode.SYSTEM_ERROR);		
		setErrorInfo( errors, error );
	 }

  }

}
