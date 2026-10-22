package com.infowave.c4p.web.actions.teksupport;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;

import com.infowave.c4p.service.dto.DesktopDTO;
import com.infowave.c4p.service.delegate.DesktopManager;
import com.infowave.c4p.service.dto.RCUsageDTO;
import com.infowave.c4p.service.delegate.RCUsageManager;
import com.infowave.c4p.service.delegate.DelegateFactory;
import com.infowave.c4p.service.ServiceException;
import com.infowave.c4p.datasource.DataException;
import com.infowave.c4p.datasource.dao.DAOFactory;
import com.infowave.c4p.datasource.dao.DesktopDAO;
import com.infowave.c4p.datasource.dao.LicenseDAO;
import com.infowave.c4p.model.License;
import com.infowave.c4p.web.util.*;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;


public class DesktopForm extends ActionForm
{
    private static Log m_log;

    static 
    {
        m_log = LogFactory.getLog(com.infowave.c4p.web.actions.teksupport.DesktopForm.class);
    }

  private String error = null;
  private boolean wasError = false;
  private boolean wasUpdated = false;
  private String license = null;
  private String days = null;
  private String data = null;
  private String type = null;
  private List desktopList = null;
  private List rcUsageList = null;
  private int count = 0;
  private static final int MAX_ALLOWED = 500;

  private SessionState state = null;


  public boolean getWasUpdated() { return wasUpdated; }

  public boolean getWasError() { return wasError; }

  public String getError() { return error; }

  public String getData() { return data; }

  public String getType()  { return type; }

  public List getDesktopList()  { return desktopList ; }

  public void setDesktopList( List list )
  {
     desktopList = list;
  }

  public String getLicense() { return license; }

  public String getDays() { return days; }

  public void setLicense(String license)
  {
    this.license = license;
  }

  public void setDays(String days)
  {
    this.days= days;
  }

  public void setData(String data)
  {
    this.data= data;
  }

  public void setType(String type)
  {
    this.type= type;
  }

  public String getDesktopCount() 
  {
    //if( userList != null ) {
    //	count = userList.size();
    //}
    String countStr = Integer.toString(count);
    return countStr ;
  }

  protected List validateData(ActionMapping mapping, HttpServletRequest request)
  {
    HttpSession session = request.getSession();
    state = WebUtil.getSessionState(request);

     List errors = new ArrayList(1);
     if( data == null || data.length() == 0 ) {
	data = "%";
     }
     return errors;
  }

  protected void updateExpiryDate(ActionMapping mapping, HttpServletRequest request)
  {
       try {
	int noDaysToAdd = Integer.parseInt(days);
	DesktopDTO desktop = (DesktopDTO)desktopList.get(0);
	int currentDays = desktop.getDaysToExpiry();
	desktop.addDaysToExpiry( noDaysToAdd );
	try{
        		DesktopDTO updated = DelegateFactory.getDesktopManager().updateExpiryDate(desktop.getDid(),desktop);
		desktopList.remove(0);
		desktopList.add(updated);
		wasUpdated = true;
            } catch( ServiceException se ) {
		wasError = true;
		error = "Unable to perform the update.";
	}
       } catch( NumberFormatException nfe ) {
	wasError = true;
	error = "Days must be a number.";
       }
  }

  protected void updateRCExpiryDate(ActionMapping mapping, HttpServletRequest request)
  {
    try {
	int noDaysToAdd = Integer.parseInt(days);
	RCUsageDTO rcusageDto = (RCUsageDTO)rcUsageList.get(0);
	rcusageDto.addDaysToExpiry( noDaysToAdd );
	try{
        RCUsageDTO updated = DelegateFactory.getRCUsageManager().updateExpiryDate(rcusageDto.getDid(),rcusageDto);
		wasUpdated = true;
		findRCUsage();
            } catch( ServiceException se ) {
		wasError = true;
		error = "Unable to perform the update.";
	}
       } catch( NumberFormatException nfe ) {
			wasError = true;
			error = "Days must be a number.";
       }
  }

  protected void findRCUsage()
  {
    DesktopManager desktopManager = DelegateFactory.getDesktopManager();
	RCUsageManager rcusageManager = DelegateFactory.getRCUsageManager();
	try {
		count = rcusageManager.countRCUsagesLikeLicense( data );
	} catch( ServiceException ignored ) {}
	if( count > 0 && count <= MAX_ALLOWED ) {
		try {
			desktopList = new ArrayList(0);
	    	rcUsageList = rcusageManager.getRCUsagesLikeLicense( data );
			DesktopDTO dto;
			// For each rcusage entry get the desktop that matches it
			for (Iterator it = rcUsageList.iterator();it.hasNext();){
			  RCUsageDTO rcusageDto = (RCUsageDTO) it.next();
			  dto = desktopManager.getByDid(rcusageDto.getDid());
			  if( dto != null ) {
				desktopList.add(dto);
			  }
			}
		} catch( ServiceException ignored ) { }
	}
  }

  protected void findDesktop()
  {
	// Find out how many entries will be returned
	// Number is okay, get the actual data
     count = 0;
     DesktopManager desktopManager = DelegateFactory.getDesktopManager();
     if( type.equals("accountname") ) {
	try {
	    count = desktopManager.getDesktopcountLikeAccountname( data );
	} catch( ServiceException ignored ) {  }
	if( count > 0 && count <= MAX_ALLOWED ) {
	    try {
	    	desktopList = desktopManager.getDesktopsLikeAccountname( data );
	    } catch( ServiceException ignored ) {  }
        	}
     } else if( type.equals("desktopname") ) {
	try {
	    count = desktopManager.getDesktopcountLikeDesktopname( data );
	} catch( ServiceException ignored ) {  }
 	if( count > 0 && count <= MAX_ALLOWED ) {
	    try {
	    	desktopList = desktopManager.getDesktopsLikeDesktopname( data );
	    } catch( ServiceException ignored ) {  }
        	}
    } else if( type.equals("license") ) {
		try {
			count = desktopManager.getDesktopcountLikeLicense( data );
		} catch( ServiceException ignored ) { }
		if( count > 0 && count <= MAX_ALLOWED ) {
			try {
	    		desktopList = desktopManager.getDesktopsLikeLicense( data );
			} catch( ServiceException ignored ) { }
        }
    } else if( type.equals("rclicense") ) {
		findRCUsage();
	} else if( type.equals("expirydate") ) {
	try {
	    count = desktopManager.getDesktopcountLikeExpirydate( data );
	} catch( ServiceException ignored ) { }
	if( count > 0 && count <= MAX_ALLOWED ) {
	    try {
	    	desktopList = desktopManager.getDesktopsLikeExpirydate( data );
	    } catch( ServiceException ignored ) { }
        	}
     }
     getAddlLicenseInfo( desktopList );
  }

  // at this point there seems to be no need to slow down the rest of
  // the system to get the license type -- so it's going here
  private void getAddlLicenseInfo( List desktopList )
  {
     if( desktopList != null && desktopList.size() > 0 ) {
	Iterator it = desktopList.iterator();
	DesktopDTO desktop;
	String serNo;
      	LicenseDAO licenseDAO = DAOFactory.getLicenseDAO();
      	License license;
	while( it.hasNext() ) {
	    desktop = (DesktopDTO)it.next();
	    try {
	    	serNo = desktop.getLicenseSerialNumber();
	    	if( serNo != null && serNo.length() > 0 ) {
      			license= licenseDAO.getSingleByCode(serNo, state.getReseller().getId());
			if( license != null ) desktop.setLicenseType( license.getLicenseType() );
	    	}
	    } catch( DataException de ) {}
	    try {
	    	serNo = desktop.getRclicenseSerialNumber();
	   	 if( serNo != null && serNo.length() > 0 ) {
      			license= licenseDAO.getSingleByCode(serNo, state.getReseller().getId());
			if( license != null ) desktop.setRcLicenseType( license.getLicenseType() );
	    	}
 	    } catch( DataException de ) {}
 	}
      }
  }

  }
