package com.infowave.pda.actions.login;

import com.infowave.c4p.web.util.Notifier;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Alexey.Ogarkov.
 * User: Alexey.Ogarkov
 * Date: 18.04.2008
 * Time: 16:02:22
 * Paccage : com.infowave.pda.actions.login
 */
public class SettingUpEmailNotifier  extends Action {
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
         DynaValidatorForm settingform = (DynaValidatorForm) form;
         String email = (String) settingform.get("email");

         if (Notifier.instance().notifySettingUp("laplink",email,request.getLocale())) {
             return mapping.findForward("success");
         } else {
             return mapping.findForward("error");
         }
     }
}
