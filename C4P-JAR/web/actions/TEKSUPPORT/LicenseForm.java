package com.infowave.c4p.web.actions.teksupport;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.infowave.c4p.admin.LicenseTerminator;
import com.infowave.c4p.service.ServiceException;
import com.infowave.c4p.service.delegate.LicenseManager;
import com.infowave.c4p.service.delegate.DelegateFactory;
import com.infowave.c4p.web.util.SessionState;
import com.infowave.c4p.web.util.WebUtil;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.ArrayList;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;


public class LicenseForm extends ActionForm
{
  /** The class's logger */
    private static Log m_log;

    static 
    {
        m_log = LogFactory.getLog(com.infowave.c4p.web.actions.teksupport.LicenseForm.class);
    }


  private String data = null;
  private String type = null;
  private String reseller = null;
  private boolean wasSuccessful;
  private String results = "";
  private String dtExpires = null;
  private int count = 0;
  private List licenseList = null;
  private static final int MAX_ALLOWED = 100;
  private SessionState state = null;

  public String getDtExpires() { return dtExpires; }
  public void setDtExpires( String results ) {
	this.dtExpires = results;
  }

  public String getResults() { return results; }
  public void setResults( String results ) {
	this.results = results;
  }

  public boolean getWasSuccessful() { return wasSuccessful; }
  public void setWasSuccessful( boolean wasSuccessful ) {
	this.wasSuccessful = wasSuccessful;
  }

  public String getReseller() { 
	if( reseller == null ) {
		reseller = state.getReseller().getId();
	}
	return reseller; 
  }
  public void setReseller( String reseller) {
	this.reseller= reseller;
  }

  public List getLicenseList()  { return licenseList; }

  public void setLicenseList( List list )
  {
     licenseList = list;
  }

  public String getData() { return data; }

  public String getType()  { return type; }

  public void setData(String data)
  {
    this.data= data;
  }

  public void setType(String type)
  {
    this.type= type;
  }

  public String getLicenseCount() 
  {
    String countStr = Integer.toString(count);
    return countStr ;
  }

  protected List validateData(ActionMapping mapping, HttpServletRequest request)
  {
	List errors = new ArrayList(1);
	HttpSession session = request.getSession();
	state = WebUtil.getSessionState(request);
	return errors;
  }

  protected void performAction()
  {
     if( type.equals("status") ) {
	revokeLicense();
     } else if( type.equals("statusc") ) {
	cancelLicense();
     } else if( type.equals("statusr") ) {
	renewLicense();
     } else if( type.equals("statusv") ) {
	reviveLicense();
     } else {
	// allow for the 'are you sure?' page
  	wasSuccessful = true;
	results = "";
     }
 }

  protected void reviveLicense()
  {
    try {
	    LicenseTerminator arnold = new LicenseTerminator();
	    wasSuccessful = arnold.setLicenseCode( data, getReseller(), null );
	    if( wasSuccessful ) {
			wasSuccessful = arnold.reviveLicense( );
		}
		results = arnold.getResults();
   } catch( Exception e ) {
		wasSuccessful = false;
		results = e.toString();
   }
  }

  protected void revokeLicense()
  {
        try {
	    LicenseTerminator arnold = new LicenseTerminator();
	    wasSuccessful = arnold.setLicenseCode( data, getReseller(), null );
	    if( wasSuccessful ) {
		wasSuccessful = arnold.revokeLicense( );
	    }
	    results = arnold.getResults();
       } catch( Exception e ) {
	    wasSuccessful = false;
	    results = e.toString();
       }
  }

  protected void cancelLicense()
  {
        try {
	    LicenseTerminator arnold = new LicenseTerminator();
	    wasSuccessful = arnold.setLicenseCode( data, getReseller(), dtExpires );
	    if( wasSuccessful ) {
		wasSuccessful = arnold.cancelLicense( );
	    }
	    results = arnold.getResults();
       } catch( Exception e ) {
	    wasSuccessful = false;
	    results = e.toString();
       }
  }

  protected void renewLicense()
  {
        try {
	    LicenseTerminator arnold = new LicenseTerminator();
	    wasSuccessful = arnold.setLicenseCode( data, getReseller(), dtExpires );
	    if( wasSuccessful ) {
		wasSuccessful = arnold.renewLicense( );
	    }
	    results = arnold.getResults();
       } catch( Exception e ) {
	    wasSuccessful = false;
	    results = e.toString();
       }
  }

  protected void findLicense()
  {
       LicenseManager licenseManager = DelegateFactory.getLicenseManager();
		try {
			count = licenseManager.getCountLikeCode( data, getReseller() );
		} catch( ServiceException ignored ) {  }
		if( count > 0 && count <= MAX_ALLOWED ) {
			try {
	    		licenseList = licenseManager.getLicensesLikeCode( data, getReseller() );
			} catch( ServiceException ignored ) { }
        }

  }


  }
