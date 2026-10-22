package com.infowave.c4p.web.actions;

import com.infowave.c4p.web.util.BaseAction;
import com.infowave.c4p.web.util.WebUtil;
import com.infowave.c4p.web.util.SessionState;
import com.infowave.c4p.service.dto.UserDTO;
import com.infowave.c4p.service.delegate.UserManager;
import com.infowave.c4p.service.delegate.DelegateFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: vincent
 * Date: Oct 18, 2003
 * Time: 9:57:39 PM
 * To change this template use Options | File Templates.
 */
public abstract class UserAction extends BaseAction
{
  /** The class's logger */
  private static Log m_log = LogFactory.getLog(UserAction.class);

  /**
   * This code will be executed before executing the business logic.
   * If there is no User DTO in the session (it happens if this action is
   * executed right after login), we retrieve it and store it.
   *
   * @throws Exception
   */
  protected void preProcessing(ActionMapping mapping,
                                          ActionForm form,
                                          HttpServletRequest request,
                                          HttpServletResponse response,
                                          List errors)
          throws Exception
  {
    /*
    SessionState state = WebUtil.getSessionState(request);

    if ((state.getUserDTO() == null) && (request.getUserPrincipal() != null)) {
      m_log.debug("No UserDTO defined yet");
      state.refresh(request);
    }
    */
  }
  /**
   * Checks that this action is authorized. For the action to be authorized, we
   * must have a principal in the request. Because we use container-managed
   * authentication, this should never happen.
   *
   * @param mapping The ActionMapping used to select this instance
   * @param form The optional ActionForm bean for this request (if any)
   * @param request The HTTP request we are processing
   * @param response The HTTP response we are creating
   * @return true if this action can be executed.
   */
  protected boolean isAuthorized(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response,
                                 ActionErrors errors)
  {
    if (request.getUserPrincipal() == null) {
      m_log.error("No principal for action: " + mapping.getName());
      return false;
    } else {
      return true;
    }
  }
}
