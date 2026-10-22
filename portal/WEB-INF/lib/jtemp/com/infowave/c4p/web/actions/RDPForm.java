package com.infowave.c4p.web.actions;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;

import javax.servlet.http.HttpServletRequest;

import com.infowave.c4p.web.util.WebUtil;
import com.infowave.c4p.web.util.SessionState;
import com.infowave.c4p.util.PortalError;
import com.infowave.c4p.util.ErrorCode;
import com.infowave.c4p.service.dto.DesktopDTO;

import java.util.List;
import java.util.ArrayList;


public class RDPForm extends ActionForm
{
  String did;
  String name;

  public String getName() {return name;}

  public String getDid()
  {
    return did;
  }

  public void setDid(String did)
  {
    this.did = did;
  }


  public void setName(String name)
  {
    this.name = name;
  }

    /**
   * make sure we have a did in the request and that it's one of the user's
   * desktops.
   * @param mapping
   * @param request
   * @return
   */
  public List validateDid(ActionMapping mapping,
                             HttpServletRequest request)

  {
    List errors = new ArrayList(1);
    SessionState state = WebUtil.getSessionState(request);
    if ((did == null) || (state.getUserDesktop(did) == null)) {
      PortalError error;
      error = new PortalError(ErrorCode.INVALID_ID);
      errors.add(error);
    }
    return errors;
  }

  private static Log m_log = LogFactory.getLog(RDPForm.class);


  }
