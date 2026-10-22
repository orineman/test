// Decompiled by DJ v3.4.4.74 Copyright 2003 Atanas Neshkov  Date: 10/7/2004 10:42:02 AM
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   RemoveDesktop.java

package com.infowave.c4p.web.actions;

import com.infowave.c4p.service.ServiceException;
import com.infowave.c4p.service.delegate.DelegateFactory;
import com.infowave.c4p.service.delegate.DesktopManager;
import com.infowave.c4p.web.util.SessionState;
import com.infowave.c4p.web.util.WebUtil;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.*;

// Referenced classes of package com.infowave.c4p.web.actions:
//            UserAction

public class RemoveDesktop extends UserAction
{

    public RemoveDesktop()
    {
    }

    public ActionForward executeLogic(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, List errors)
        throws Exception
    {
        SessionState state = WebUtil.getSessionState(request);
        String did = request.getParameter("did");
        try
        {
            if(did != null)
            {
                com.infowave.c4p.service.dto.DesktopDTO desktop = state.getDesktop(did);
                if(desktop != null)
                {
                    DelegateFactory.getDesktopManager().removeDesktop(did);
                    state.refreshDesktopList();
                } else
                {
                    m_log.info("Invalid did passed in the request: " + did + " - ignoring");
                }
            } else
            {
                m_log.debug("Missing did parameter - ignoring");
            }
        }
        catch(ServiceException e)
        {
            m_log.error("cannot save desktop", e);
        }
        ActionForward main = mapping.findForward("showAccountManagementPage");
        request.setAttribute("page", main.getPath());
        return null;
    }

    private static Log m_log;

    static 
    {
        m_log = LogFactory.getLog(com.infowave.c4p.web.actions.RemoveDesktop.class);
    }
}