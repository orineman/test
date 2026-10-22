package com.infowave.pda.actions.storage;

import com.infowave.pda.kernel.actions.concurrent.MultipleRequestBaseAction;
import com.infowave.pda.kernel.objects.UserSettings;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by Alexey.Ogarkov.
 * User: Alexey.Ogarkov
 * Date: 09.04.2008
 * Time: 0:56:43
 * Paccage : com.infowave.pda.actions.storage
 */

public class SaveLookAndFeel extends MultipleRequestBaseAction {
    private static String[] SERVICE_NAMES = {
            "email_service",
            "contact_service",
            "event_service",
            "note_service",
            "favorites_service",
            "task_service",
            "wds_service",
            "gds_service",
            "bookmarks_service",
            "weather_service"};

    @Override
    protected void preConfigure(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        UserSettings settings = (UserSettings) session.getAttribute("user_settings");

        for (int i=0;i<SERVICE_NAMES.length;i++) {
            if (request.getParameter(SERVICE_NAMES[i]) != null) {
                try {
                    settings.getClass().getMethod("set" + SERVICE_NAMES[i].substring(0, 1).toUpperCase() + SERVICE_NAMES[i].substring(1), boolean.class).invoke(settings, Boolean.TRUE.booleanValue());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                } catch (InvocationTargetException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
        }

        session.setAttribute("user_settings",settings);
        session.setAttribute(GetUserParameters.PROTOCOL_URL,settings.getProtocolURL());
    }
}
