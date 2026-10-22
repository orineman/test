package com.infowave.c4p.web.actions;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.infowave.c4p.web.util.WebConstants;
import com.infowave.c4p.web.util.SessionState;
import com.infowave.c4p.web.util.WebUtil;
import com.infowave.c4p.util.PortalError;
import com.infowave.c4p.util.ErrorCode;
import com.infowave.c4p.service.dto.DesktopDTO;
import com.infowave.c4p.service.delegate.DelegateFactory;
import com.infowave.c4p.service.delegate.LicenseManager;
import com.infowave.c4p.service.ServiceException;

import com.infowave.c4p.datasource.dao.DAOFactory;
import com.infowave.c4p.datasource.dao.RCUsageDAO;
import com.infowave.c4p.model.RCUsage;
import com.infowave.c4p.model.Reseller;
import com.infowave.c4p.datasource.dao.ResellerDAO;

/**
 * Created by IntelliJ IDEA.
 * User: vincent
 * Date: Dec 12, 2003
 * Time: 6:05:29 PM
 * To change this template use Options | File Templates.
 */
public class RCUnlicenseDesktop extends UserAction
{

  /** The class's logger */
  private static Log m_log = LogFactory.getLog(RCUnlicenseDesktop.class);

  /**
   * Executes the business logic.
   *
   * @param mapping The ActionMapping used to select this instance
   * @param form The optional ActionForm bean for this request (if any)
   * @param request The HTTP request we are processing
   * @param response The HTTP response we are creating
   * @exception IOException if an input/output error occurs
   * @exception ServletException if a servlet exception occurs
   * @return a forward
   * @throws Exception

   */
  public ActionForward executeLogic(ActionMapping mapping,
                                    ActionForm actionForm,
                                    HttpServletRequest request,
                                    HttpServletResponse response,
                                    List errors)
          throws Exception
  {
    SessionState state  = WebUtil.getSessionState(request);
    RCUnLicenseForm form = (RCUnLicenseForm ) actionForm;

    //String did = request.getParameter(WebConstants.DID);
    String did = form.getDid();
    if (did == null){
      m_log.warn("no did in request");
      PortalError error = new PortalError(ErrorCode.INVALID_PARAMETERS);
      errors.add(error);
      return null;
    }

    DesktopDTO desktop = state.getUserDesktop(did);
    if (desktop == null) {
      m_log.warn("The desktop " + did + " does not belong to user " + state.getUserDTO().getUsername());
      PortalError error = new PortalError(ErrorCode.INVALID_PARAMETERS);
      errors.add(error);
      return null;
    }

   // Find the rcusage
    RCUsageDAO rcusageDAO = DAOFactory.getRCUsageDAO();
    RCUsage rcusage = rcusageDAO.getByDid(did);
    String sln= null;;
    if( rcusage != null ) sln = rcusage.getLicenseSerialNumber();
    if (rcusage == null || sln  == null || sln.equals("")) {
      m_log.warn("The rcusage for " + did + " is not licensed.");
      PortalError error = new PortalError(ErrorCode.INVALID_PARAMETERS);
      errors.add(error);
      return null;
    }
    // Unlicense the desktop
    LicenseManager licenseManager = DelegateFactory.getLicenseManager();

    try{
      String resellerCode = form.getReseller();
      if (rcusage == null) {
        m_log.warn("The reseller was not set.");
        PortalError error = new PortalError(ErrorCode.INVALID_PARAMETERS);
        errors.add(error);
        return null;
      }

      ResellerDAO resellerDAO = DAOFactory.getResellerDAO();
      Reseller reseller = resellerDAO.getByCode(resellerCode );

      List freedUpLicenses = licenseManager.rcunlicense(did, reseller, true, null);
      // The confirmatio page dispolays the licenses that have been freed up.
      if (!freedUpLicenses.isEmpty()) {
        request.setAttribute(WebConstants.FREEDUP_LICENSES,freedUpLicenses);
      }      
    }
    catch (ServiceException e){
      errors.add(e.getError());
      m_log.error("Could not unlicense the rcusage",e);
    }

    // The desktop is edited in a popup window. The forward for this action is
    // the popupCloser page. We set in the request the page that should be
    // displayed in the main window (the popup's opener) when the popup is
    // closed.
    ActionForward main = mapping.findForward("showAccountManagementPage");
    request.setAttribute(WebConstants.PAGE,main.getPath());

    state.refreshDesktopList();
    return null;
  }
}
