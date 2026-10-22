package com.infowave.pda.kernel.actions.concurrent;

import com.infowave.c4p.util.ErrorCode;
import com.infowave.pda.kernel.actions.BaseAction;
import com.infowave.pda.kernel.actions.error.InvalidParameterStructure;
import com.infowave.pda.kernel.actions.misc.Parameter;
import com.infowave.pda.kernel.hostConnector.HostConnector;
import org.apache.struts.action.*;
import org.w3c.dom.Document;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by Alexey.Ogarkov.
 * User: Alexey.Ogarkov
 * Date: 11.03.2008
 * Time: 15:00:16
 * Paccage : com.infowave.pda.kernel.actions.concurrent
 */
public class MultipleRequestBaseAction extends BaseAction {
    /**
     * The class's logger
     */
    private static Log m_log = LogFactory.getLog(MultipleRequestBaseAction.class);
    /**
     * This is the action called from the Struts framework.
     *
     * @param mapping  The ActionMapping used to select this instance.
     * @param form     The optional ActionForm bean for this request.
     * @param request  The HTTP HostConnector we are processing.
     * @param response The HTTP Response we are processing.
     * @return ActionForward
     * @throws java.io.IOException
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response) throws IOException, InvalidParameterStructure {
        setDynamicForm((DynaActionForm) form);
        form = convertStringsToUTF(getDynamicForm());
        String _name = "protocol";
        ErrorCode error = null;
        preConfigure(request,response);
        ActionForward forward = null;
        protocolURL = mapping.getParameter();

        if (protocolURL == null || protocolURL.equals("param")) {
            protocolURL = request.getParameter(_name);
        }
        if (protocolURL == null || protocolURL.equals("rattr")) {
            protocolURL = (String)request.getAttribute(_name);
        }
        if (protocolURL == null || protocolURL.equals("sattr")) {
            protocolURL = (String)request.getSession().getAttribute(_name);
        }
        if (protocolURL.indexOf("param=")!=-1 || protocolURL.indexOf("rattr=")!=-1 || protocolURL.indexOf("sattr=")!=-1 ) {
            String[] __t = protocolURL.split("=");
            if (__t.length == 2) {
                _name = __t[1];
                if (__t[0].equals("param")) {
                    protocolURL = request.getParameter(_name);
                }
                if (__t[0].equals("rattr")) {
                    protocolURL = (String) request.getAttribute(_name);
                }
                if (__t[0].equals("sattr")) {
                    protocolURL = (String) request.getSession().getAttribute(_name);                    
                }
            }
        }

        if (protocolURL!=null) {
        String[] _protocolParam = protocolURL.split(";");
        ExecutorService pool = Executors.newCachedThreadPool();

        Collection requestCalls = new ArrayList<RequestCall>();
        req = new HostConnector(request);
        for (String s : _protocolParam) {
            String[] param_name = s.split("==");
            if (param_name.length!=2) {
                throw new InvalidParameterStructure("Invalid parameter in struts-pda-settings");
            }
            String name = param_name[0].trim();
            String url  = param_name[1];
            if (name.equals("param")) {
                url = request.getParameter(url);
            } else if (name.equals("rattr")) {
                url = (String) request.getAttribute(url);
            } else if (name.equals("sattr")) {
                url = (String) request.getSession().getAttribute(url);                
            }
            initRequest(requestCalls,  url, name);
        }
        /*this method can be overriden if needed*/
        forward = preHostRequest(req, request, mapping, response);

        if (forward != null) {
            return forward;
        }
        
        try {
            List<Future<Parameter<String, Document>>> results = pool.invokeAll(requestCalls);
            for (Future<Parameter<String,Document>> result : results) {
                Parameter<String,Document> param = result.get();
                if (!param.isError()) {
                    request.setAttribute(param.getName(),param.getValue());
                } else if (error==null) {
                  //  return findError(mapping,request,response,new ActionMessages(),param.getError());
                    //request.setAttribute("errorXML",param.getValue());
                    error = param.getError();
                }
            }
            //this.setResponseHeaders(response, req);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } finally {
            pool.shutdown();
        }

        afterHostRequests(request,response,mapping);
        }
        if (error==null) {
            return navigateForward(mapping, request, response, forward);
        } else {
            m_log.error("error catched forward to error_"+error.toInt());
            return findError(mapping, request, response, new ActionMessages(), error);
        }
    }

    protected void preConfigure(HttpServletRequest request, HttpServletResponse response) {
        //Can be ovverriden by childrens
    }

    protected void afterHostRequests(HttpServletRequest request, HttpServletResponse response, ActionMapping mapping) {
        
    }

    protected void initRequest( Collection<RequestCall> futures, String __protocolurl, String name) {
        //req.initialize(request,__protocolurl);

        //Future<Document> result = pool.submit(hostRequest);
        futures.add(new RequestCall( __protocolurl, name, req));

    }
}
