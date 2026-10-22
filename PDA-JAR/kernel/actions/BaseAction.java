/*
 * BaseAction.java
 *
 * Created on 20 �������� 2007 �., 12:58
 */

package com.infowave.pda.kernel.actions;

import com.infowave.c4p.commons.DisplayMode;
import com.infowave.c4p.model.CannotConnectToICMException;
import com.infowave.c4p.util.ErrorCode;
import com.infowave.c4p.web.actions.lle5.hostCommands;
import com.infowave.c4p.web.util.ReqUtils;
import com.infowave.c4p.web.util.SessionState;
import com.infowave.c4p.web.util.WebUtil;
import com.infowave.pda.kernel.filters.NavigationFilter;
import com.infowave.pda.kernel.hostConnector.HostConnector;
import com.infowave.pda.kernel.hostConnector.errors.*;
import org.apache.commons.httpclient.Header;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.*;
import org.w3c.dom.Document;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Alexey.Ogarkov
 */

public class BaseAction extends Action {

    /* forward name="success" path="" */
    public final static String SUCCESS = "success";

    public final static String PHONE_SUCCESS = "phone.success";

    public final static String BACK = "c4p_back";
    
    protected DynaActionForm dynamicForm;

    protected HostConnector req;

    private SessionState state;

    protected String protocolURL;

    protected ReentrantLock lock = new ReentrantLock();
    /**
     * The class's logger
     */
    private static Log m_log = LogFactory.getLog(BaseAction.class);

    /**
     * This is the action called from the Struts framework.
     *
     * @param mapping  The ActionMapping used to select this instance.
     * @param form     The optional ActionForm bean for this request.
     * @param request  The HTTP HostConnector we are processing.
     * @param response The HTTP Response we are processing.
     * @return
     * @throws java.lang.Exception
     */
    public ActionForward execute(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        m_log.debug("++Base Action Execute");
        setDynamicForm((DynaActionForm) form);
        //form = convertStringsToUTF(getDynamicForm());
                

        protocolURL = mapping.getParameter();
        if (protocolURL == null) {
            protocolURL = request.getParameter("protocol");
        }
		m_log.debug("JAE protocolURL = " + protocolURL);

        if (protocolURL != null) {
            req = new HostConnector(request);
        } else {
            throw new InvalidInputParameters("parameter protocol not exist");
        }

        /*this method can be overriden if needed*/
        ActionForward forward = preHostRequest(req, request, mapping, response);
        if (forward != null) {
            return forward;
        }
        m_log.debug("Sending HostConnector to Host");

        try {
            req.send(protocolURL);
        } catch (CouldNotFindProtocolJSP couldNotFindProtocolJSP) {
            m_log.error("CouldNotFindProtocolJSP:" + couldNotFindProtocolJSP.getMessage());
        } catch (InvalidProtocolData invalidProtocolData) {
            m_log.error("InvalidProtocolData:" + invalidProtocolData.getMessage());
        } catch (CannotConnectToICMException e) {
            m_log.error("CannotConnectToICMException:" + e.getMessage());
        } catch (InvalidHostResponseXML invalidHostResponseXML) {
            m_log.error("InvalidHostResponseXML:" + invalidHostResponseXML.getMessage());
        } catch (ParserConfigurationException e) {
            m_log.error("ParserConfigurationException:" + e.getMessage());
        } catch (CannotFindDesktop cannotFindDesktop) {
            m_log.error("CannotFindDesktop:" + cannotFindDesktop.getMessage());
        }

        this.setResponseHeaders(response, req);

        if (req.getError() != null) {
            return errorHandler(req, mapping, request, response);
        }
        /*This method can be overriden */
        try {
            return afterHostRequest(req, mapping, request, response);
        } catch (InvalidHostResponseXML e) {
            return null;
        }
    }

    protected ActionForward errorHandler(HostConnector req, ActionMapping mapping, HttpServletRequest request, HttpServletResponse response) throws IOException {
        return findError(mapping, request, response, new ActionMessages(), req.getError());
    }

    /**
     * this method implement the error handling functionality.
     * When you declaring action yuo should know what will happends when error come from host or other developer errors.
     * <p/>
     * The example
     * <global-forwards>
     * <errorPage ....           <- this page will be when you are don`t whant to handle error by yourself
     * <global-forwards>
     * ....
     * <action
     * ....
     * input="hereInput Source" <- error forward to this path if there is no other path to go
     * <p/>
     * >
     * <forward name="error_2000" <- if wait an special code then put the desitination forward "error_YOURCODE">
     * <forward name="error"  <-or simply error and all errors will be forward to this path
     * <p/>
     * Offcourse all input request parameters are putting back.
     * parameter errorXML contains XML from host.
     *
     * @param mapping  The ActionMapping used to select this instance.
     * @param request  The HTTP HostConnector we are processing.
     * @param response The HTTP Response we are processing.
     * @param errors   The ActionErrorss to read error message in the error page
     * @param error    Host Error ErrorCode from HostConnector.send() method.
     * @return
     */
    protected ActionForward findError(ActionMapping mapping,
                                      HttpServletRequest request,
                                      HttpServletResponse response,
                                      ActionMessages errors, ErrorCode error) throws IOException {
        ActionForward forward;

        errors.add("hostError", new ActionMessage(error.getName(), error.toInt()));
        WebUtil.setError(request, error);
        this.saveMessages(request, errors);
        String input = mapping.getInput();

        forward = mapping.findForward("error_" + error.toInt());

        if (forward == null) {
            forward = mapping.findForward("error");
        }

        if (forward == null) {
            if (input != null) {
                if (input.startsWith("/")) {
                    forward = new ActionForward(input);
                } else {
                    forward = mapping.findForward(input);
                }
                if (forward == null) {
                    m_log.warn("findError - no forward found for input: " + input + " action: " + mapping.getType());
                }
            } else {
                forward = mapping.findForward("errorPage");
            }
        }

        //New funuctionality if in path with
        if (forward != null) {
            String path = forward.getPath();
            String[] paramString = path.split("\\=");
            if (paramString[0].equalsIgnoreCase("param")) {
                String go = null;
                if (paramString[1].equals("back")) {
                    LinkedList navigationStack = (LinkedList) request.getSession().getAttribute(NavigationFilter.NAVIGATION);
                    if (navigationStack.size() > 0) {
                        go = (String) navigationStack.get(0);
                    }
                }
                
                int index_temp = paramString[1].indexOf("back[");
                if (index_temp!=-1) {
                    int index = Integer.valueOf(paramString[1].substring(index_temp+5,index_temp+6)).intValue();
                    LinkedList navigationStack = (LinkedList) request.getSession().getAttribute(NavigationFilter.NAVIGATION);
                    go = (String) navigationStack.get(index);
                }
                
                if (paramString[1].equals("sback")&&request.getSession().getAttribute(BaseAction.BACK)!=null) {
                    go = (String) request.getSession().getAttribute(BaseAction.BACK);
                }

                if (request.getParameter(paramString[1]) != null) {
                    go = request.getParameter(paramString[1]);
                }

                if (request.getAttribute(paramString[1]) != null) {
                    go = (String) request.getAttribute(paramString[1]);
                }

                if (go.indexOf("?") == -1) {
                    go += "?c4p_error=" + error.toString();
                } else {
                    go += "&c4p_error=" + error.toString();
                }

                if (go != null) {
                    response.sendRedirect(go);
                    return null;
                }
            }
        }
        // add the request parameters if needed
        if ((forward != null) && forward.getRedirect()) {
            forward = ReqUtils.putBackAllParametersInRequest(forward, request);
        }
        String sResponse = hostCommands.Error2XML(error.toInt(),error.getName());
        request.setAttribute("errorXML", sResponse);
        
        if (forward.getRedirect()) {
            String query = "";

/*            Enumeration attrs = request.getAttributeNames();
            while (attrs.hasMoreElements()) {
                redirect.addParameter((String) attrs.nextElement(), request.getAttribute((String) attrs.nextElement()));
            }*/

            String path = forward.getPath();

            String[] pathString = path.split("\\?");
            if (pathString.length > 1) {
                String[] paramNames = path.split("\\?")[1].split("&");

                Enumeration<String> params = request.getParameterNames();
                while (params.hasMoreElements()) {
                    String param = params.nextElement();
                    for (int i = 0; i < paramNames.length; i++) {
                        if (param.equals(paramNames[i])) {
                            query += "&" + param + "=" + request.getParameter(param);
                        }
                    }
                }

                response.sendRedirect(pathString[0] + "?" + query.substring(1)+"&c4p_error=" + error.toString());
            } else {
                response.sendRedirect(path+"?c4p_error=" + error.toString());
            }
            return null;
        }

        m_log.debug("forward to:" +forward.getPath());
        return forward;
    }

    protected void setResponseHeaders(HttpServletResponse response, HostConnector req) {
        Header[] headers = req.getResponseHeaders();
        if (headers != null) {
            for (Header header : headers) {
                response.setHeader(header.getName(), header.getValue());
            }
        }
    }

    protected ActionForward preHostRequest(HostConnector hostConnector, HttpServletRequest request, ActionMapping mapping, HttpServletResponse response) {
        m_log.debug("Before host request started");
        return null;
    }

    protected ActionForward afterHostRequest(HostConnector req, ActionMapping mapping, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Document responseXML;

        ActionForward forward = null;
        try {
            responseXML = req.getXmlResponse();
            if (responseXML != null) {
                responseXML.normalizeDocument();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new InvalidHostResponseXML("DOM Parser failed, invalid XML structure");
        }
        m_log.debug("After host request complete");
        request.setAttribute("hostXML", responseXML);
        return navigateForward(mapping, request, response, forward);
    }

    protected ActionForward navigateForward(ActionMapping mapping, HttpServletRequest request, HttpServletResponse response, ActionForward forward) throws IOException {
        if (request.getParameter("useraction") != null) {
            if (mapping.findForward(request.getParameter("useraction")) != null) {
                forward = mapping.findForward(request.getParameter("useraction"));
            }
        }

        state = WebUtil.getSessionState(request);

        if (forward == null) {
            if (!state.getClientCapabilities().getInitialDisplay().equals(DisplayMode.PHONE)) {
                forward = mapping.findForward(SUCCESS);
            } 
        }

        //New funuctionality if in path with
        String path = forward.getPath();
        String[] paramString = path.split("\\=");
        if (paramString[0].equalsIgnoreCase("param")) {
            String go = null;
            if (paramString[1].equals("back")) {
                LinkedList navigationStack = (LinkedList) request.getSession().getAttribute(NavigationFilter.NAVIGATION);
                go = (String) navigationStack.get(0);
            }

            int index_temp = paramString[1].indexOf("back[");
            if (index_temp!=-1) {
                int index = Integer.valueOf(paramString[1].substring(index_temp+5,index_temp+6)).intValue();
                LinkedList navigationStack = (LinkedList) request.getSession().getAttribute(NavigationFilter.NAVIGATION);
                go = (String) navigationStack.get(index);
            }
            
                            
            if (paramString[1].equals("sback")&&request.getSession().getAttribute(BaseAction.BACK)!=null) {
                go = (String) request.getSession().getAttribute(BaseAction.BACK);
            }

            if (request.getParameter(paramString[1]) != null) {
                go = request.getParameter(paramString[1]);
            }

            if (request.getAttribute(paramString[1]) != null) {
                go = (String) request.getAttribute(paramString[1]);
            }

            if (go != null) {
                response.sendRedirect(go);
                return null;
            }
        }

        if (forward.getRedirect()) {
            String query = "";

/*            Enumeration attrs = request.getAttributeNames();
            while (attrs.hasMoreElements()) {
                redirect.addParameter((String) attrs.nextElement(), request.getAttribute((String) attrs.nextElement()));
            }*/

            String[] pathString = path.split("\\?");
            if (pathString.length > 1) {
                String[] paramNames = path.split("\\?")[1].split("&");


                Enumeration<String> params = request.getParameterNames();
                while (params.hasMoreElements()) {
                    String param = params.nextElement();
                    for (int i = 0; i < paramNames.length; i++) {
                        if (param.equals(paramNames[i])) {
                            query += "&" + param + "=" + request.getParameter(param);
                        }
                    }
                }

                Enumeration<String> attrs = request.getAttributeNames();
                while (attrs.hasMoreElements()) {
                    String param = attrs.nextElement();
                    for (int i = 0; i < paramNames.length; i++) {
                        if (param.equals(paramNames[i])) {
                            if (request.getAttribute(param) instanceof String) {
                                query += "&" + param + "=" + request.getAttribute(param);
                            }
                        }
                    }
                }
                response.sendRedirect(pathString[0] + "?" + query.substring(1));
            } else {
                response.sendRedirect(path);
            }
            return null;
        }
        return forward;
    }

    public static DynaActionForm convertStringsToUTF(DynaActionForm form) {
        Set<Map.Entry<String, Object>> s = form.getMap().entrySet();

        for (Map.Entry<String, Object> e : s) {
            if (e.getValue() instanceof String) {
                try {
                    form.set(e.getKey(), new String(((String) e.getValue()).getBytes("ISO-8859-1"), "UTF-8"));
                } catch (UnsupportedEncodingException e1) {
                    e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
        }
        return form;
    }

    public DynaActionForm getDynamicForm() {
        return dynamicForm;
    }

    public void setDynamicForm(DynaActionForm dynamicForm) {
        this.dynamicForm = dynamicForm;
    }
}
