package com.infowave.c4p.web.util;

import com.infowave.c4p.service.dto.CompanyDTO;
import com.infowave.c4p.service.dto.TechnicianDTO;
import com.infowave.c4p.service.delegate.DelegateFactory;
import com.infowave.c4p.service.delegate.CompanyManager;
import com.infowave.c4p.service.delegate.TechnicianManager;
import com.infowave.c4p.service.ServiceException;
import com.infowave.c4p.web.util.SessionTerminationNotifier;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.io.Serializable;
import java.util.Locale;
import java.util.ResourceBundle;
import java.security.Principal;
import java.util.MissingResourceException;

import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;

import javax.servlet.jsp.jstl.core.Config;
import javax.servlet.jsp.jstl.fmt.LocalizationContext;


public class SessionState  implements Serializable
{
  /** The class's logger */
  private static Log m_log = LogFactory.getLog(SessionState.class);

  /** The user's locale */
  private Locale m_locale = null;

  private TechnicianDTO m_technicianDto = null;


  public Locale getLocale() {
    return m_locale;
  }

  // ------------------------------------------------------------ Public methods

  public SessionState(HttpServletRequest request)
  {
    HttpSession session;
    session = request.getSession();
    init(session);
    session.setAttribute("c4.sessionTerminationNotifier",
                         new SessionTerminationNotifier(request,this));

  }
  /**
   * Initializes the session state
   */
  public void init(HttpSession session)
  {
    Config.set(session,
               Config.FMT_LOCALIZATION_CONTEXT,
               "messages");
  }
  /**
   * Find a locale we'll use until we've authenticated the user and retrieved
   * his/her preferred locale.
   * If the browser passed a locale, we use it. Otherwise, we use the reseller's
   * default locale.
   *
   * todo: we should get the list of locales from the request and return the
   * first one for which we have a localized messages file.
   */
  public void setTemporaryLocale(HttpServletRequest request)
  {
    Locale locale = null;
    HttpSession session = request.getSession();
    if (request.getParameter("locale")!= null) { // Mr. Icky
      setLocale(session,new Locale(request.getParameter("locale")));
    } else if(request.getLocale() != null) {
      setLocale(session,request.getLocale());
    } else {
		// TODO: get company's default locale in db
      //setLocale(session,reseller.getDefaultLocale());
      setLocale(session, new Locale(WebConstants.DEFAULT_LOCALE_STR));
    }

  }


  /**
   * Changes the session's locale. We also change the reseller's bundle as well
   * as the JSTL locale.
   */
  public void setLocale (HttpSession session, Locale locale)
  {
    m_locale = new Locale(locale.getLanguage());

    // The reseller's bundle
	String companyid = getCompanyId();
    if (companyid != null) {
      ResourceBundle rb = null;
	  try {
        rb = ResourceBundle.getBundle(companyid,locale);
	  } catch( MissingResourceException mre ) {
        rb = ResourceBundle.getBundle(WebConstants.DEFAULT_MESSAGES_FILE,locale);
	  }
      LocalizationContext lc = new LocalizationContext(rb);
      session.setAttribute(WebConstants.COMPANY_BUNDLE, lc);
      m_log.debug("Setting the company's bundle to " +companyid+
                  "_" +locale);
    } else {
      ResourceBundle rb = null;
      rb = ResourceBundle.getBundle(WebConstants.DEFAULT_MESSAGES_FILE,locale);
      LocalizationContext lc = new LocalizationContext(rb);
      session.setAttribute(WebConstants.COMPANY_BUNDLE, lc);
      m_log.debug("Setting the company's bundle to " +companyid+
                  "_" +locale);
	}
    m_log.debug("Setting the locale to :" +locale+ " for session " + session.getId());
    Config.set(session,Config.FMT_LOCALE,locale);
  }

  public String getCompanyId()
  {
    String company = null;

	if( m_technicianDto != null ) {
		company = m_technicianDto.getCompanyid();
	}				

	return company;
  }

  public TechnicianDTO getTechnicianDTO() {
    return m_technicianDto;
  }

  public double getAccessLevel() {
	double accesslevel = 0;
	if( m_technicianDto != null ) {
		accesslevel = m_technicianDto.getAccesslevel();
	}
	return accesslevel;
  }

  public boolean isSiteAdmin()
  {
	boolean isSiteAdmin = false;
	if( m_technicianDto != null ) {
		int userAccessLevel = (int)m_technicianDto.getAccesslevel();
		isSiteAdmin = (userAccessLevel & WebConstants.TECHNICIAN_ACCESSLEVEL_SITE_ADMIN) > 0;
	}
	return isSiteAdmin;
  }

  public boolean isCompanyAdmin()
  {
	boolean isSiteAdmin = false;
	if( m_technicianDto != null ) {
		int userAccessLevel = (int)m_technicianDto.getAccesslevel();
		isSiteAdmin = (userAccessLevel & WebConstants.TECHNICIAN_ACCESSLEVEL_COMPANY_ADMIN) > 0;
	}
	return isSiteAdmin;
  }

  public boolean isAnyAdmin()
  {
	boolean isAnyAdmin = isSiteAdmin();
	if( !isAnyAdmin ) {
		isAnyAdmin = isCompanyAdmin();
	} 
	return isAnyAdmin;
  }


  /**
   * Registers the reseller for this session
   * @param reseller
   */
  public void setTechnicianDTO(TechnicianDTO technician) {
    m_technicianDto = technician;
    if (technician != null) {
      m_log.debug("technician for this session: " + technician.getTechid());
    }
  }

  private CompanyDTO getCompany()
  {
    CompanyDTO dto = null;
	try {
		CompanyManager companyManager = DelegateFactory.getCompanyManager();
		dto = companyManager.getByCompanyid( getCompanyId() );
	} catch( ServiceException se ) {}

	return dto;
  }


  public boolean isCompanyDedicated()
  {
	boolean isCompanyDedicated = true;
	CompanyDTO dto = getCompany();
	if( dto != null ) {
		int licenseTypeInt = (int) dto.getLicensetype();
		if( (licenseTypeInt & WebConstants.DEDICATED_LICENSE) == 0 ) {
			isCompanyDedicated = false;
		}
	}
	return isCompanyDedicated;
  }


  public void refresh(HttpServletRequest request)
  throws ServiceException
  {
	// if we don't have user DTO, it is because the user has just logged in.
	if (m_technicianDto == null) {
		String companyid = "";
		String techid = "";
		HttpSession session = request.getSession();
		Principal principal =  request.getUserPrincipal();
		TechnicianManager technicianManager = DelegateFactory.getTechnicianManager();
		String username = principal.getName();
		int idx = username.indexOf( ";" );
		if( idx != -1 ) {
			companyid = username.substring( 0, idx );
			idx++;
			techid = username.substring( idx, username.length() );
		}
		setTechnicianDTO( technicianManager.getByDoubleid(companyid, techid) );
	} 
	HttpSession session = request.getSession();
	if (request.getParameter("locale")!= null) { // Mr. Icky
	  setLocale(session,new Locale(request.getParameter("locale")));
	} else if(request.getLocale() != null) {
	  setLocale(session,request.getLocale());
	} else {
		// TODO: get company's default locale in db
	  //setLocale(session,reseller.getDefaultLocale());
	  setLocale(session, new Locale(WebConstants.DEFAULT_LOCALE_STR));
	}
  }


}
