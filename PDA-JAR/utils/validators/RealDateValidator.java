package com.infowave.pda.utils.validators;

import com.infowave.pda.kernel.filters.AddAttachFilter;
import org.apache.commons.validator.Field;
import org.apache.commons.validator.ValidatorAction;
import org.apache.commons.validator.util.ValidatorUtils;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.validator.Resources;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.*;

/**
 * Created by Alexey.Ogarkov.
 * User: Alexey.Ogarkov
 * Date: 26.02.2008
 * Time: 13:48:42
 * Paccage : com.infowave.pda.utils.validators
 */

public class RealDateValidator implements Serializable {

    public static boolean validateDate(Object bean,
                                       ValidatorAction va,
                                       Field field,
                                       ActionMessages errors,
                                       HttpServletRequest request) {
        //Getting other date fields
           int day = Integer.parseInt(ValidatorUtils.getValueAsString(bean,
                field.getProperty()));


        String year_field = field.getVarValue("year");
        int year = Integer.parseInt(ValidatorUtils.getValueAsString(bean, year_field));

        String month_field = field.getVarValue("month");
        int month = Integer.parseInt(ValidatorUtils.getValueAsString(bean, month_field));
        month--;
        HttpSession session = request.getSession();
        Calendar cal = Calendar.getInstance((Locale) session.getAttribute("org.apache.struts.action.LOCALE"));

        cal.setTime(new Date());

        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.DAY_OF_MONTH, day);

        if (month != cal.get(Calendar.MONTH)) {
            errors.add(field.getKey(),
                    Resources.getActionMessage(
                            request,
                            va,
                            field));
            HashMap<String, String> paramMap = new HashMap<String, String>();
            Enumeration<String> en = request.getParameterNames();
            while (en.hasMoreElements()) {
                String e = en.nextElement();
                paramMap.put(e, request.getParameter(e));
            }
            session.setAttribute(AddAttachFilter.FORM_MAP, paramMap);
            return false;
        }

        return true;
    }
}

