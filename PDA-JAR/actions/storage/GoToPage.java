package com.infowave.pda.actions.storage;

import com.infowave.pda.kernel.filters.NavigationFilter;
import org.apache.struts.action.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URI;

/**
 * Created by Alexey.Ogarkov.
 * User: Alexey.Ogarkov
 * Date: 29.11.2007
 * Time: 11:49:23
 * Paccage : com.infowave.pda.actions.storage
 */
public class GoToPage  extends Action {

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
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        DynaActionForm settingsForm = (DynaActionForm) form;
        HttpSession session = request.getSession();
        Integer maxcount = (Integer) session.getAttribute("PDA_items_on_page");
        Integer currpage = (Integer) settingsForm.get("currpage");

        String backReferense = NavigationFilter.getBackReference(session);
        int t = backReferense.indexOf("startNumber");
        if (backReferense.indexOf("startNumber")!=-1) {
            backReferense = backReferense.substring(0,t)+(backReferense.indexOf("&",t)!=-1?backReferense.substring(backReferense.indexOf("&",t)+1):"");
        }
        RequestDispatcher dispatcher = null;
        URI url;
        if (backReferense.indexOf("?")!=-1) {
           url = new URI(backReferense+"&startNumber="+ maxcount*(currpage-1));           
        } else {
           url = new URI(backReferense+"?startNumber="+maxcount*(currpage-1));
        }
        /*dispatcher = request.getRequestDispatcher(url.getPath()+"?"+url.getQuery());
        dispatcher.forward(request,response);
*/
        response.sendRedirect(url.getPath()+"?"+url.getQuery());
        return mapping.findForward("DummyPage");
    }
}
