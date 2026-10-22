package com.infowave.c4p.web.actions;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.ArrayList;

import com.infowave.c4p.web.util.WebUtil;
import com.infowave.c4p.web.util.SessionState;
import com.infowave.c4p.util.PortalError;
import com.infowave.c4p.util.ErrorCode;
import com.infowave.c4p.service.dto.DesktopDTO;


public class RCUnLicenseForm extends ActionForm
{
  String did;
  private String reseller;

  public String getReseller() {return reseller;}

  public void setReseller(String reseller)
  {
    this.reseller= reseller;
  }

  public String getDid() {return did;}

  public void setDid(String did)
  {
    this.did= did;
  }


  /**
   * make sure we have a did in the request and that it's one of the user's
   * desktops.
   * @param mapping
   * @param request
   * @return
   */
  protected List validateReseller(ActionMapping mapping,
                             HttpServletRequest request)

  {
/*
    List errors = new ArrayList(1);
    SessionState state = WebUtil.getSessionState(request);
    if ((did == null) || (state.getUserDesktop(did) == null)) {
      PortalError error;
      error = new PortalError(ErrorCode.INVALID_ID);
      errors.add(error);
    }
    return errors;
*/
    List errors = new ArrayList(1);
    return errors;
  }

  }
