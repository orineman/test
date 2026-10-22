package com.infowave.pda.kernel.actions;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Locale;

/**
 * Created by Alexey.Ogarkov.
 * User: Alexey.Ogarkov
 * Date: 07.11.2007
 * Time: 13:25:17
 */
public class ChangeLocale extends Action{
    /* forward name="error" path="" */
    private final static String ERROR= "error";
    /* forward name="success" path="" */
    private final static String SUCCESS = "success";
     /**
     * The class's logger
     */
    private static Log m_log = LogFactory.getLog(ChangeLocale.class);

    public ChangeLocale() {
    }

    /**
     * This action called to change locale of user on pda session.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP HostConnector we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return ActionForward
     */
    public ActionForward execute ( ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response )
            throws Exception
    {
        String language = request.getParameter("language");
        String country = request.getParameter("country");
        String variant = request.getParameter("variant");

        if (country==null) country = "";
        if (variant==null) variant = "";                     

        if (language!=null&&language.length()>0) {
            Locale locale = new Locale(language, country, variant);
            setLocale(request, locale);
            HttpSession session = request.getSession();
            session.setAttribute("org.apache.struts.action.LOCALE",locale);

/*            HttpSession session = request.getSession(true);
            Config.set(session, Config.FMT_LOCALE, locale);*/
        } else {
            return mapping.findForward(ERROR);
        }

        return mapping.findForward(SUCCESS);
    }

}