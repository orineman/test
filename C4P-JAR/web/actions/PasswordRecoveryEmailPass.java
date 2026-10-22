package com.infowave.c4p.web.actions;

import java.io.*;
import java.util.Random;
import java.util.Locale;

import com.infowave.c4p.datasource.dao.DAOFactory;
import com.infowave.c4p.datasource.dao.AccountDAO;
import com.infowave.c4p.datasource.dao.AuthenticationDAO;
import com.infowave.c4p.model.Account;
import com.infowave.c4p.model.Authentication;
import com.infowave.c4p.util.security.PasswordEncoder;
import com.infowave.c4p.util.security.PasswordEncoderFactory;
import com.infowave.c4p.util.StringManager;
import com.infowave.c4p.web.util.Notifier;
import com.infowave.c4p.web.util.SessionState;
import com.infowave.c4p.web.util.WebUtil;

import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * This servlet used to recover user password. After checking existence of e-mail and generating new password.
 * New password sending to user email address.
 * User: Alexey.Ogarkov
 * Date: 16.07.2007
 * Time: 15:11:26
 * To change this template use File | Settings | File Templates.
 */
public class PasswordRecoveryEmailPass extends HttpServlet {
    /**
     * The class's logger
     */
    private static Log m_log = LogFactory.getLog(PasswordRecoveryEmailPass.class);

    private static final String ENCODER = "com.infowave.c4p.laplink.security.PasswordEncoderImpl";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws javax.servlet.ServletException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        try {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
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
                response.setStatus(HttpServletResponse.SC_OK);
            } else {
                //Invalid Username
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            }
        } catch (Exception ex) {
            response.setStatus(404);
            throw new ServletException(ex);
        } finally {
            out.close();
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

    /**
     * Handles the HTTP GET method.
     *
     * @param request  servlet request
     * @param response servlet response
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP POST method.
     *
     * @param request  servlet request
     * @param response servlet response
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     */
    public String getServletInfo() {
        return "Password Recovery Tool";
    }
}
