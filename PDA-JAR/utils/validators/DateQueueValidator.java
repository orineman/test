package com.infowave.pda.utils.validators;

import org.apache.commons.validator.ValidatorAction;
import org.apache.commons.validator.Field;
import org.apache.commons.validator.util.ValidatorUtils;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.validator.Resources;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.*;

import com.infowave.pda.kernel.filters.AddAttachFilter;

/**
 * Created by Alexey.Ogarkov.
 * User: Alexey.Ogarkov
 * Date: 31.03.2008
 * Time: 14:32:28
 * Paccage : com.infowave.pda.utils.validators
 */
public class DateQueueValidator implements Serializable {

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

        String hour_field = ValidatorUtils.getValueAsString(bean, field.getVarValue("hour"));
        int hour = 0;
        if (hour_field!=null) {
            hour = Integer.parseInt(hour_field);
            cal.set(Calendar.HOUR_OF_DAY,hour);
        }

        String minute_field = ValidatorUtils.getValueAsString(bean, field.getVarValue("minute"));
        int minute = 0;
        if (minute_field!=null) {
            minute = Integer.parseInt(minute_field);
            cal.set(Calendar.MINUTE,minute);
        }

        String day_field_due = field.getVarValue("due_day");
        int day_due = Integer.parseInt(ValidatorUtils.getValueAsString(bean, day_field_due));


        String year_field_due = field.getVarValue("due_year");
        int year_due = Integer.parseInt(ValidatorUtils.getValueAsString(bean, year_field_due));

        String month_field_due = field.getVarValue("due_month");
        int month_due = Integer.parseInt(ValidatorUtils.getValueAsString(bean, month_field_due));
        month_due--;

        Calendar cal_due = Calendar.getInstance((Locale) session.getAttribute("org.apache.struts.action.LOCALE"));

        cal_due.setTime(new Date());

        cal_due.set(Calendar.MONTH, month_due);
        cal_due.set(Calendar.YEAR, year_due);
        cal_due.set(Calendar.DAY_OF_MONTH, day_due);

        String hour_field_due = ValidatorUtils.getValueAsString(bean, field.getVarValue("due_hour"));
        int hour_due = 0;
        if (hour_field_due!=null) {
            hour_due = Integer.parseInt(hour_field_due);
            cal_due.set(Calendar.HOUR_OF_DAY,hour_due);
        }

        String minute_field_due = ValidatorUtils.getValueAsString(bean, field.getVarValue("due_minute"));
        int minute_due = 0;
        if (minute_field_due!=null) {
            minute_due = Integer.parseInt(minute_field_due);
            cal_due.set(Calendar.MINUTE,minute_due);
        }
        
        if (cal_due.getTimeInMillis()+1<cal.getTimeInMillis()) {
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
