package com.infowave.pda.actions.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;

/**
 *
 * @author Alexey.Ogarkov
 * @version
 */

public class GetProtocolJSP extends Action {

    /* forward name="success" path="" */
    private final static String SUCCESS = "success";


    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP HostConnector we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward execute(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
      String uri = request.getParameter("protocolURI");
      //String requestedResource = ResourceManager.getProtocolURI(uri, WebUtil.getSessionState(request),false,false);
      //response.sendRedirect(requestedResource);
/*
      if (ResourceManager.isProtocolRequest(uri)){
        InputStream resourceStream;
        boolean localized = false;
        resourceStream = ResourceManager.getProtocolJSP(uri,request,false);
        if (resourceStream != null){
          //m_log.debug("Found requested resource: " + request.getRequestURI());
          byte[] buff = new byte[256];
          int nbBytes = 0;
          while ((nbBytes = resourceStream.read(buff)) != -1){
            response.getOutputStream().write(buff,0,nbBytes);
          }
          resourceStream.close();
          response.flushBuffer();
        } else {
          response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
*/

      
      return null;

    }
}

