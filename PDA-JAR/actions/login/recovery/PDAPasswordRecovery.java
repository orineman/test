package com.infowave.pda.actions.login.recovery;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.Random;

import com.infowave.c4p.web.util.SessionState;
import com.infowave.c4p.web.util.WebUtil;
import com.infowave.c4p.web.util.Notifier;
import com.infowave.c4p.datasource.dao.AuthenticationDAO;
import com.infowave.c4p.datasource.dao.DAOFactory;
import com.infowave.c4p.datasource.dao.AccountDAO;
import com.infowave.c4p.model.Authentication;
import com.infowave.c4p.model.Account;
import com.infowave.c4p.util.security.PasswordEncoder;
import com.infowave.c4p.util.security.PasswordEncoderFactory;

/**
 * Created by Alexey.Ogarkov.
 * User: Alexey.Ogarkov
 * Date: 01.02.2008
 * Time: 0:42:33
 * Paccage : com.infowave.pda.actions.login.recovery
 */
public class PDAPasswordRecovery extends Action {
    /**
     * The class's logger
     */
    private static Log m_log = LogFactory.getLog(PDAPasswordRecovery.class);

    private static final String ENCODER = "com.infowave.c4p.laplink.security.PasswordEncoderImpl";

    private static final String SUCCESS = "success";
    private static final String ERROR = "error";

    public ActionForward execute(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String username = request.getParameter("username");

        SessionState state = WebUtil.getSessionState(request);
        Locale userLocale = request.getLocale();

        AuthenticationDAO authDAO = DAOFactory.getAuthenticationDAO();
        Authentication auth = authDAO.getByUsername(username);

        AccountDAO accountDAO = DAOFactory.getAccountDAO();
        Account account = accountDAO.getByUsername(username);
        //m_log.debug("Password Recovery Account Request by user" + account.getUsername());
        //User email adress
        if (account != null && auth != null) {
            //
            String email = account.getEmail();
            //Generate Password
            PasswordEncoder enc = PasswordEncoderFactory.instance().getEncoder(ENCODER);
            String newpass = gen();
            //String newpass = random(8,0,20,true,true,null,new Random());
            //Put new hash to the account
            auth.setPassword(enc.encode(newpass));
            authDAO.update(auth);
            //Notify user about new password
            //todo: Check genarate function

            Notifier.instance().notifyPasswordRecovery(state.getReseller().getName(), email, newpass, userLocale);
            return mapping.findForward(SUCCESS);
        } else {
            return mapping.findForward(ERROR);
        }
    }


    /**
     * Creates a random string
     *
     * @param count   the length of random string to create
     * @param start   the position in set of chars to start at
     * @param end     the position in set of chars to end before
     * @param letters only allow letters?
     * @param numbers only allow numbers?
     * @param chars   the set of chars to choose randoms from.
     *                If <code>null</code>, then it will use the set of all chars.
     * @param random  a source of randomness.
     * @return the random string
     * @throws ArrayIndexOutOfBoundsException if there are not
     *                                        <code>(end - start) + 1</code> characters in the set array.
     * @throws IllegalArgumentException       if <code>count</code> &lt; 0.
     */
    public static String random(int count, int start, int end, boolean letters, boolean numbers,
                                char[] chars, Random random) {
        if (count == 0) {
            return "";
        } else if (count < 0) {
            throw new IllegalArgumentException("Requested random string length " + count + " is less than 0.");
        }
        if ((start == 0) && (end == 0)) {
            end = 'z' + 1;
            start = ' ';
            if (!letters && !numbers) {
                start = 0;
                end = Integer.MAX_VALUE;
            }
        }

        StringBuffer buffer = new StringBuffer();
        int gap = end - start;

        while (count-- != 0) {
            char ch;
            if (chars == null) {
                ch = (char) (random.nextInt(gap) + start);
            } else {
                ch = chars[random.nextInt(gap) + start];
            }
            if ((letters && numbers && Character.isLetterOrDigit(ch))
                    || (letters && Character.isLetter(ch))
                    || (numbers && Character.isDigit(ch))
                    || (!letters && !numbers)) {
                buffer.append(ch);
            } else {
                count++;
            }
        }
        return buffer.toString();
    }

    private String gen() {
        String s = "";
        Random rGen = new Random();
        s += Integer.toHexString(rGen.nextInt());
        return s.substring(2, 7);
    }
}

