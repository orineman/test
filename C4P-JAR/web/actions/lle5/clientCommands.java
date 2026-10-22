package com.infowave.c4p.web.actions.lle5;

import com.infowave.c4p.datasource.DataException;
import com.infowave.c4p.datasource.dao.AccountDAO;
import com.infowave.c4p.datasource.dao.DAOFactory;
import com.infowave.c4p.datasource.dao.DesktopDAO;
import com.infowave.c4p.datasource.dao.LicenseDAO;
import com.infowave.c4p.datasource.dao.hibernate.HibernateSession;
import com.infowave.c4p.model.Account;
import com.infowave.c4p.model.Desktop;
import com.infowave.c4p.model.License;
import com.infowave.c4p.service.ServiceException;
import com.infowave.c4p.service.delegate.DelegateFactory;
import com.infowave.c4p.service.delegate.LicenseManager;
import com.infowave.c4p.service.delegate.UserManager;
import com.infowave.c4p.service.dto.DesktopDTO;
import com.infowave.c4p.service.dto.UserDTO;
import com.infowave.c4p.util.ErrorCode;
import com.infowave.c4p.util.PortalError;
import com.infowave.c4p.web.util.LLOnyx;
import com.infowave.c4p.web.util.SessionState;
import com.infowave.c4p.web.util.WebUtil;
import static com.infowave.c4p.web.util.XmlUtil.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.Action;
import org.hibernate.HibernateException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.*;
import java.lang.reflect.InvocationTargetException;

/**
 * Class clientCommands represents the first part of Protocol about communication of Client and Server
 *
 * @author alexogar
 *         Created on Jul 28, 2008
 */
public class clientCommands extends Action {
    /**
     * The class's logger
     */
    private static Log m_log = LogFactory.getLog(clientCommands.class);

    /**
     * Class CommandRequestAction is subclass for every Command class action
     * Has common function & props for Commands
     *
     * @author alexogar
     * Created on Jul 28, 2008
     */
    abstract class CommandRequestAction {

        /**
         * Constructor CommandRequestAction creates a new CommandRequestAction instance.
         */
        public CommandRequestAction() {}

        /** Field response  */
        protected HttpServletResponse response;
        /** Field session  */
        protected HttpSession session;
        /** Field state  */
        protected SessionState state;
        /** Field request  */
        protected HttpServletRequest request;
        /** Field document  */
        protected Document document;
        /** Field nodes  */
        protected NodeList nodes;

        /**
         * Method executeLogic is abstract method represents reaction of server for Command,
         * it will call from main execute method of Action.
         *
         * @return String
         * @throws ServiceException when
         * @throws DataException    when
         */
        public abstract String executeLogic() throws ServiceException, DataException;

        /**
         * Method getHostList constructs XML with list of accesibble hosts
         * for GetHostList response
         * see Laplink EveryWhere Protocol request GetHostList
         *
         * @param state of type SessionState
         * @return String with XML
         * @throws com.infowave.c4p.datasource.DataException when Database problem
         * @throws com.infowave.c4p.service.ServiceException when Logic problem
         */
        protected String getHostList(SessionState state) throws ServiceException, DataException {
            String sResponse = "";
            DocumentBuilder builder;
            try {
                builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            } catch (ParserConfigurationException e) {
                throw new ServiceException(new PortalError(ErrorCode.SYSTEM_ERROR));
            }

            Document document = builder.newDocument();
            Element elResponse = document.createElement("LLEResponse");
/////////////////////////////////////////////////////////////////////////////////////////////////////
// by lexx. Temp solution. After created working aythentication system it's should be deleted.
//      UserDTO user = state.getUserDTO();
//      Element elUserInfo = document.createElement("UserInfo");
//      Element elUserID = document.createElement("ID");
//      elUserID.setTextContent(user.getUsername());
//      elUserInfo.appendChild(elUserID);
//      elResponse.appendChild(elUserInfo);
/////////////////////////////////////////////////////////////////////////////////////////////////////
            Element elUserInfo = document.createElement("UserInfo");
            if ((state != null) && (state.getUserDesktops() != null)) {
                Element elPassword = document.createElement("Password");
                elPassword.setTextContent((String) session.getAttribute("j_password"));
                elUserInfo.appendChild(elPassword);


                Element elEmail = document.createElement("Email");
                elEmail.setTextContent(state.getUserDTO().getEmail());
                elUserInfo.appendChild(elEmail);

                Element elUsername = document.createElement("UserName");
                elUsername.setTextContent(state.getUserDTO().getUsername().toLowerCase());
                elUserInfo.appendChild(elUsername);

                Element elFirstname = document.createElement("FirstName");
                elFirstname.setTextContent(state.getUserDTO().getFirstName());
                elUserInfo.appendChild(elFirstname);

                Element elLastname = document.createElement("LastName");
                elLastname.setTextContent(state.getUserDTO().getLastName());
                elUserInfo.appendChild(elLastname);

                Element elSecurityQuestion = document.createElement("SecurityQuestion");
                elSecurityQuestion.setTextContent(String.valueOf(state.getUserDTO().getSecurityQuestion()));
                elUserInfo.appendChild(elSecurityQuestion);

                UserDTO user = state.getUserDTO();
                DesktopDTO defaultDesktop = DelegateFactory.getDesktopManager().getDefaultDesktop(user.getUsername());
                if (defaultDesktop != null) {
                    Element elDefaultDesktop = document.createElement("DefaultDesktop");
                    elDefaultDesktop.setTextContent(defaultDesktop.getDid());
                    elUserInfo.appendChild(elDefaultDesktop);
                }

                elResponse.appendChild(elUserInfo);
                //state.refreshDesktopList();
                state.setCurrentDesktop((DesktopDTO) null);
                List desktops = state.getOrderedDesktops();
                m_log.debug("+++GetHostList. Desktops count:" + desktops.size());
                for (Object desktop1 : desktops) {
                    DesktopDTO desktop = (DesktopDTO) desktop1;
                    Element elHostInfo = document.createElement("HostInfo");
                    Element elID = document.createElement("ID");
                    elID.setTextContent(desktop.getDid());
                    elHostInfo.appendChild(elID);
                    //state.refreshDesktop(desktop.getDid());
                    Account account = DAOFactory.getAccountDAO().getByUsername(state.getUserDTO().getUsername());

                    Element elName = document.createElement("Name");
                    if ((account.getDesktopName(desktop.getDid()) != null) && (!account.getDesktopName(desktop.getDid()).equals(""))) {
                        elName.setTextContent(account.getDesktopName(desktop.getDid()));
                    } else {
                        elName.setTextContent(desktop.getName());
                    }
                    elHostInfo.appendChild(elName);

                    Element elOwner = document.createElement("Owner");
                    elOwner.setTextContent(desktop.getOwner());
                    elHostInfo.appendChild(elOwner);


//                    if (desktop.getDirectAccessUrl()!=null) {
//                        Element elDirectAccess = document.createElement("DirectAccessURL");
//                        elDirectAccess.setTextContent(desktop.getDirectAccessUrl());
//                        elHostInfo.appendChild(elDirectAccess);
//                    }

                    Element elOnline = document.createElement("OnlineFlag");
                    Element elOnlyRC = document.createElement("OnlyRC");
                        elOnlyRC.setTextContent("Yes");
                        elHostInfo.appendChild(elOnlyRC);
                    if (desktop.isConnected()) {
                        elOnline.setTextContent("Yes");
                        elOnlyRC.setTextContent("No");
                    } else if (desktop.getRdpEnabled()) {
                        elOnline.setTextContent("Yes");
                        elOnlyRC.setTextContent("Yes");
                    } else {
                        elOnline.setTextContent("No");
                        elOnlyRC.setTextContent("No");
                    }
                    elHostInfo.appendChild(elOnlyRC);
                    elHostInfo.appendChild(elOnline);

                    Element elHidden = document.createElement("HiddenFlag");

                    if (account.isHiddenDesktop(desktop.getDid())) {
                        elHidden.setTextContent("Yes");
                    } else {
                        elHidden.setTextContent("No");
                    }
                    elHostInfo.appendChild(elHidden);

                    Element elSerial = document.createElement("SerialNumber");
                    elSerial.setTextContent(desktop.getLicenseSerialNumber());
                    elHostInfo.appendChild(elSerial);

                    Element elDays = document.createElement("DaysToExpiry");
                    elDays.setTextContent(Integer.toString(desktop.getDaysToExpiry()));
                    elHostInfo.appendChild(elDays);

                    Element expDate = document.createElement("ExpiryDate");
                    expDate.setTextContent(DateFormat.getDateInstance(DateFormat.MEDIUM).format(desktop.getExpiryDate()));
                    elHostInfo.appendChild(expDate);

                    Element elICM = document.createElement("ICM");
                    elICM.setTextContent(desktop.getIcmURL());
                    elHostInfo.appendChild(elICM);

                    Element elICMRC = document.createElement("ICMRC");
                    elICMRC.setTextContent(desktop.getRdpURL());
                    elHostInfo.appendChild(elICMRC);

                    Element elVersion = document.createElement("Version");
                    elVersion.setTextContent(desktop.getSoftwareVersion());
                    elHostInfo.appendChild(elVersion);

                    elResponse.appendChild(elHostInfo);
                }
                document.appendChild(elResponse);
                document.normalizeDocument();
                sResponse = XML2String(document);
            }

            return sResponse;
        }

        /**
         * Method getSubscriptionStatus constructs XML for GetSybscriptionStatus response
         * see Laplink Protocol
         *
         * @param status of type boolean
         * @return String
         * @throws ServiceException when Logic problem
         */
        protected String getSubscriptionStatus(boolean status) throws ServiceException {
            DocumentBuilder builder;
            try {
                builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            } catch (ParserConfigurationException e) {
                throw new ServiceException(new PortalError(ErrorCode.SYSTEM_ERROR));
            }
            Document document = builder.newDocument();
            Element elResponse = document.createElement("LLEResponse");

            Element elSubInfo = document.createElement("SubscriptionInfo");

            Element elCurrStatus = document.createElement("Status");
            elCurrStatus.setTextContent((status ? "0" : "1"));
            elSubInfo.appendChild(elCurrStatus);

            elResponse.appendChild(elSubInfo);
            document.appendChild(elResponse);
            document.normalizeDocument();
            return XML2String(document);
        }

        /**
         * Method getResponse returns the response of this CommandRequestAction object.
         *
         * @return the response (type HttpServletResponse) of this CommandRequestAction object.
         */
        public HttpServletResponse getResponse() {
            return response;
        }

        /**
         * Method setResponse sets the response of this CommandRequestAction object.
         *
         * @param response the response of this CommandRequestAction object.
         */
        public void setResponse(HttpServletResponse response) {
            this.response = response;
        }

        /**
         * Method getSession returns the session of this CommandRequestAction object.
         *
         * @return the session (type HttpSession) of this CommandRequestAction object.
         */
        public HttpSession getSession() {
            return session;
        }

        /**
         * Method setSession sets the session of this CommandRequestAction object.
         *
         * @param session the session of this CommandRequestAction object.
         */
        public void setSession(HttpSession session) {
            this.session = session;
        }

        /**
         * Method getState returns the state of this CommandRequestAction object.
         *
         * @return the state (type SessionState) of this CommandRequestAction object.
         */
        public SessionState getState() {
            return state;
        }

        /**
         * Method setState sets the state of this CommandRequestAction object.
         *
         * @param state the state of this CommandRequestAction object.
         */
        public void setState(SessionState state) {
            this.state = state;
        }

        /**
         * Method getRequest returns the request of this CommandRequestAction object.
         *
         * @return the request (type HttpServletRequest) of this CommandRequestAction object.
         */
        public HttpServletRequest getRequest() {
            return request;
        }

        /**
         * Method setRequest sets the request of this CommandRequestAction object.
         *
         * @param request the request of this CommandRequestAction object.
         */
        public void setRequest(HttpServletRequest request) {
            this.request = request;
        }

        /**
         * Method getDocument returns the document of this CommandRequestAction object.
         *
         * @return the document (type Document) of this CommandRequestAction object.
         */
        public Document getDocument() {
            return document;
        }

        /**
         * Method setDocument sets the document of this CommandRequestAction object.
         *
         * @param document the document of this CommandRequestAction object.
         */
        public void setDocument(Document document) {
            this.document = document;
        }

        /**
         * Method getNodes returns the nodes of this CommandRequestAction object.
         *
         * @return the nodes (type NodeList) of this CommandRequestAction object.
         */
        public NodeList getNodes() {
            return nodes;
        }

        /**
         * Method setNodes sets the nodes of this CommandRequestAction object.
         *
         * @param nodes the nodes of this CommandRequestAction object.
         */
        public void setNodes(NodeList nodes) {
            this.nodes = nodes;
        }
    }

   /**
    * Enum Command represents the scope of client commands
    *
    * each element has ID and Class
    *   ID - this is ID of command from client XML request
    *   Class - class this is Server action sceleton must be subclass of CommandRequestAction
    *
    * @author alexogar
    * Created on Jul 28, 2008
    */
    enum Command {

        /** Field GetHostListCommand  */
        GetHostListCommand                  (3, GetHostList.class),
        /** Field ChangeUserIdentityCommand  */
        ChangeUserIdentityCommand           (4, ChangeUserIdentity.class),
        /** Field GetSubscriptionCodeCommand  */
        GetSubscriptionCodeCommand          (7, GetSubscriptionCode.class),
        /** Field RenewSubscriptionCommand  */
        RenewSubscriptionCommand            (8, RenewSubscription.class),
        /** Field CheckNewSubscriptionCodeCommand  */
        CheckNewSubscriptionCodeCommand     (9, CheckNewSubscriptionCode.class),
        /** Field CheckUserPasswordCommand  */
        CheckUserPasswordCommand            (10, CheckUserPassword.class),
        /** Field DeleteDesktopCommand  */
        DeleteDesktopCommand                (11, DeleteDesktop.class),
        /** Field HideDesktopCommand  */
        HideDesktopCommand                  (12, HideDesktop.class),
        /** Field RenameDesktopNameCommand  */
        RenameDesktopNameCommand            (13, RenameDesktopName.class),
        /** Field SetDefaultDesktopCommand  */
        SetDefaultDesktopCommand            (14, SetDefaultDesktop.class),
        /** Field GetDefaultDesktopCommand  */
        GetDefaultDesktopCommand            (15, GetDefaultDesktop.class),
        /** Field UnlicenseDesktopCommand  */
        UnlicenseDesktopCommand             (16, UnlicenseDesktop.class),
        /** Field KeepAliveCommand          */
        KeepAliveCommand                    (17, KeepAlive.class);
        /** Field id  */
        private final int id;
        /** Field c  */
        private final Class c;

        /** Field lookup  */
        private static final Map<Integer, Command> lookup = new HashMap<Integer, Command>();

        static {
            for (Command c : EnumSet.allOf(Command.class))
                lookup.put(c.getId(), c);
        }

        /**
         * Constructor Command creates a new Command instance.
         *
         * @param id of type int
         * @param c  of type Class
         */
        Command(int id, Class c) {
            this.id = id;
            this.c = c;
        }

        /**
         * Method getCommand returns instance of Command
         *
         * @param id of type int
         * @return Command
         */
        public static Command getCommand(int id) {
            return lookup.get(id);
        }

        /**
         * Method getId returns the id of this Command object.
         *
         * @return the id (type int) of this Command object.
         */
        public int getId() {
            return id;
        }

        /**
         * Method getCommandInstance returns the commandInstance of this Command object.
         *
         * Method create new instance of Command sceleton class
         *
         * @param obj instance of SuperClass type of clientCommands
         * @return the commandInstance (type CommandRequestAction) of this Command object.
         * @throws IllegalAccessException when
         * @throws InstantiationException when
         * @throws ClassNotFoundException when
         * @throws NoSuchMethodException  when
         * @throws java.lang.reflect.InvocationTargetException when
         */
        public CommandRequestAction getCommandInstance(clientCommands obj) throws IllegalAccessException, InstantiationException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException {
            return (clientCommands.CommandRequestAction) c.getConstructor(clientCommands.class).newInstance(obj);
        }
    }

    /**
     * Class CommandActionFactory of creating configurated CommandRequestAction instances from command ID
     *
     * @author alexogar
     * Created on Jul 28, 2008
     */
    class CommandActionFactory {
        /**
         * Method createCommandAction - is factory Method creating configurated instance of CommandRequestAction
         *
         * @param request  of type HttpServletRequest
         * @param response of type HttpServletResponse
         * @param obj when
         * @return CommandRequestAction
         * @throws InstantiationException       when
         * @throws IllegalAccessException       when
         * @throws ParserConfigurationException when
         * @throws IOException                  when
         * @throws SAXException                 when
         * @throws ServiceException             when
         * @throws ClassNotFoundException       when
         * @throws NoSuchMethodException        when
         * @throws java.lang.reflect.InvocationTargetException when
         */
        public CommandRequestAction createCommandAction(HttpServletRequest request, HttpServletResponse response,clientCommands obj) throws InstantiationException, IllegalAccessException, ParserConfigurationException, IOException, SAXException, ServiceException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = builder.parse(request.getInputStream());
            document.normalizeDocument();
            NodeList nodes = document.getElementsByTagName("Command");

            int id = Integer.parseInt(getXMLNodeValue(nodes, "ID").trim());

            CommandRequestAction action;
            if (request.getUserPrincipal()==null && id==3) {
                action = new GetGuestHostList();
            } else {
                Command c = Command.getCommand(id);
                action = c.getCommandInstance(obj);
                SessionState state = WebUtil.getSessionState(request);
                state.refresh(request);
                action.setState(state);
            }


            action.setRequest(request);
            action.setResponse(response);
            action.setSession(request.getSession());

            action.setDocument(document);
            action.setNodes(nodes);
            return action;
        }
    }

    /**
     * Class GetHostList.
     *   Constuct response for Request with Command/ID = 3
     *
     * @author alexogar
     * Created on Jul 28, 2008
     */
    class GetHostList extends CommandRequestAction {

        /**
         * @see clientCommands.CommandRequestAction#CommandRequestAction()
         */
        public GetHostList() { super(); }

        /**
         * @see clientCommands.CommandRequestAction#executeLogic()
         */
        @Override
        public String executeLogic() throws ServiceException, DataException {
            return getHostList(getState());
        }
    }

    /**
     * Class ChangeUserIdentity ...
     *    Constuct response for Request with Command/ID = 4
     *
     * @author alexogar
     * Created on Jul 28, 2008
     */
    class ChangeUserIdentity extends CommandRequestAction {

        /**
         * @see clientCommands.CommandRequestAction#CommandRequestAction()
         */
        public ChangeUserIdentity() { super();}

        /**
         * @see clientCommands.CommandRequestAction#executeLogic()
         */
        @Override
        public String executeLogic() throws ServiceException, DataException {
            String sResponse = "";
            nodes = getDocument().getElementsByTagName("UserInfo");
            String username = getXMLNodeValue(nodes, "Username");
            if (state.getUserDTO().getUsername().equals(username)) {
                String firstName = getXMLNodeValue(nodes, "NewFirstName");
                String lastName = getXMLNodeValue(nodes, "NewLastName");
                String newEmail = getXMLNodeValue(nodes, "NewEmail");
                //DelegateFactory.getUserManager().changeIdentity(username,firstName,lastName,newEmail,null);

                String newUsername = getXMLNodeValue(nodes, "NewUsername");
                String password = getXMLNodeValue(nodes, "Password");
                String newPassword = getXMLNodeValue(nodes, "NewPassword");

                int newSecurityQuestion = getXMLNodeValue(nodes, "SecurityQuestion") != null ? Integer.parseInt(getXMLNodeValue(nodes, "SecurityQuestion")) : -1;
                String newSecurityAnswer = getXMLNodeValue(nodes, "SecurityAnswer");

                m_log.debug("+++ClientCommands. Request:" + username + " " + newUsername + " " + newEmail + " " + newPassword + " " + password);
                //DelegateFactory.getUserManager().changeUserInformation(username, newUsername, newEmail, password, newPassword);
                DelegateFactory.getUserManager().changeFullUserProfile(username,
                        newEmail,
                        firstName,
                        lastName,
                        password,
                        newPassword,
                        newSecurityQuestion,
                        newSecurityAnswer);
                UserDTO user = state.getUserDTO();
                user.setUsername(newUsername);
                user.setEmail(newEmail);
                state.setUserDTO(user);
                sResponse = getHostList(state);
                session.removeAttribute("j_password");
            }

            return sResponse;
        }
    }

    /**
     * Class GetSubscriptionCode ...
     *      Constuct response for Request with Command/ID = 7
     * @author alexogar
     * Created on Jul 28, 2008
     */
    class GetSubscriptionCode extends CommandRequestAction {

        /**
         * @see clientCommands.CommandRequestAction#CommandRequestAction()
         */
        public GetSubscriptionCode(){super();}

        /**
         * @see clientCommands.CommandRequestAction#executeLogic()
         */
        @Override
        public String executeLogic() throws ServiceException, DataException {
            nodes = document.getElementsByTagName("HostInfo");
            String did = getXMLNodeValue(nodes, "DID");

            DesktopDAO desktopDAO = DAOFactory.getDesktopDAO();
            Desktop desktop = desktopDAO.getByDid(did);

            if (desktop != null) {
                return getSubscriptionCode(desktop.getLicenseSerialNumber());
            } else {
                throw new ServiceException(new PortalError(ErrorCode.UNKNOWN_DID_DEFINED));
            }
        }

        /**
         * Method getSubscriptionCode constructs response
         *
         * @param code of type String
         * @return String
         * @throws ServiceException when Logic problem
         */
        private String getSubscriptionCode(String code) throws ServiceException {
            DocumentBuilder builder;
            try {
                builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            } catch (ParserConfigurationException e) {
                throw new ServiceException(new PortalError(ErrorCode.SYSTEM_ERROR));
            }
            Document document = builder.newDocument();
            Element elResponse = document.createElement("LLEResponse");

            Element elSubInfo = document.createElement("SubscriptionInfo");

            Element elCurrCode = document.createElement("CurrentCode");
            elCurrCode.setTextContent(code);
            elSubInfo.appendChild(elCurrCode);

            elResponse.appendChild(elSubInfo);
            document.appendChild(elResponse);
            document.normalizeDocument();
            return XML2String(document);
        }
    }

    /**
     * Class RenewSubscription
     *    Constuct response for Request with Command/ID = 8
     * @author alexogar
     * Created on Jul 28, 2008
     */
    class RenewSubscription extends CommandRequestAction {

        /**
         * @see clientCommands.CommandRequestAction#CommandRequestAction()
         */
        public RenewSubscription() {super();}

        /**
         * @see clientCommands.CommandRequestAction#executeLogic()
         */
        @Override
        public String executeLogic() throws ServiceException, DataException {
            nodes = document.getElementsByTagName("SubscriptionInfo");

            String code = getXMLNodeValue(nodes, "NewSubscriptionCode");
            String username = state.getUserDTO().getUsername();
            nodes = document.getElementsByTagName("HostInfo");
            String did = getXMLNodeValue(nodes, "DID");

            //LicenseTerminator lterminator = new LicenseTerminator();

            //lterminator.renewLicense(resselerID);
            m_log.debug("Renew License Params:" + username + ",code" + code + ",did" + did);

            return getSubscriptionStatus(renewLicense(code, username, did, state));
        }

        /**
         * Method renewLicense execute logic for renewing Licence for Host
         *
         * @param sLicense  of type String Licence parameter from requestXML
         * @param sUsername of type String Username parameter from session
         * @param sDid      of type String Did parameter from session
         * @param state     of type SessionState
         * @return boolean
         * @throws ServiceException when Logic problem
         * @throws DataException    when Database problem
         */
        private boolean renewLicense(String sLicense, String sUsername, String sDid, SessionState state) throws ServiceException, DataException {
            LLOnyx onyx = new LLOnyx();
            //      String rccode = "";
            UserDTO userDTO = null;
            LicenseManager licenseMgr = null;
            if (sLicense != null && sLicense.length() > 0) {
                UserManager userManager = DelegateFactory.getUserManager();
                userDTO = userManager.getUserDTO(sUsername);
                licenseMgr = DelegateFactory.getLicenseManager();
            }

            DesktopDAO desktopDAO = DAOFactory.getDesktopDAO();
            Desktop desktop;
            desktop = desktopDAO.getByDid(sDid);


            if (!((sLicense != null && sLicense.length() > 0) && userDTO != null && licenseMgr != null)) {
                // update onyx with the account information only if license info isn't getting updated
                // If the onyx update fails, allow the user to continue
                try {
                    onyx.UpdateUserInfo(sUsername);
                }
                catch (Exception ignored) {
                    m_log.error("Unable to update ONYX for " + sUsername);
                    return false;
                }
            }

            if (sLicense != null && sLicense.length() > 0 && userDTO != null && licenseMgr != null && desktop != null) {
                // update onyx with the license information
                // If the onyx update fails, allow the user to continue
                try {
                    onyx.UpdateLicenseInfo(sUsername, sLicense);
                }
                catch (Exception logged) {
                    LicenseDAO licenseDAO = DAOFactory.getLicenseDAO();
                    License license1 = licenseDAO.getSingleByCode(sLicense, state.getReseller().getId());
                    if (license1 != null && license1.getLicenseType() == License.BOXED_TYPE) {
                        m_log.error("ONYX BOXED UPDATE FAILURE FOR LICENSECODE " + sLicense);
                    } else {
                        m_log.error("Unable to update ONYX for " + sUsername + ", code = " + sLicense);
                    }
                    m_log.error("Cannot update ONYX (3)", logged);
                }

                try {
                    licenseMgr.setLicenseCode(userDTO.getId(), desktop.getId(), sLicense, desktop.getLicense() != null);
                }
                catch (ServiceException e) {
                    // Add the error to our list so that the user gets a message.
                    //errors.add(e.getError());
                    // If this error is not a user error, we log it
                    ErrorCode ecode = e.getError().getCode();
                    if (!ecode.equals(ErrorCode.INVALID_LICENSE_NUMBER)) {
                        m_log.error("Could not set license", e);
                    }
//              forward = super.findForward(request, mapping, "success.licenseexception");
                    return false;
                }
            }
            return true;
        }
    }

    /**
     * Class CheckNewSubscriptionCode
     *  Constuct response for Request with Command/ID = 9
     *
     * @author alexogar
     * Created on Jul 28, 2008
     */
    class CheckNewSubscriptionCode extends CommandRequestAction {

        /**
         * @see clientCommands.CommandRequestAction#CommandRequestAction()
         */
        public CheckNewSubscriptionCode() { super(); }

        /**
         * @see clientCommands.CommandRequestAction#executeLogic()
         */
        @Override
        public String executeLogic() throws ServiceException, DataException {
            String sResponse;
            nodes = document.getElementsByTagName("SubscriptionInfo");

            String code = getXMLNodeValue(nodes, "NewSubscriptionCode");

            LicenseDAO licenseDAO = DAOFactory.getLicenseDAO();
            List listLicense = licenseDAO.getUnusedByCode(code, state.getReseller().getId());

            if (listLicense.size() == 0) {
                List usedLicenses = licenseDAO.getByCodeAndUserId(code, state.getReseller().getId(), state.getUserDTO().getId());
                if (usedLicenses.size() == 0) {
                    sResponse = getSubscriptionStatus(false);
                } else {
                    sResponse = getSubscriptionStatus(true);
                }
            } else {
                sResponse = getSubscriptionStatus(true);
            }

            return sResponse;
        }
    }

    /**
     * Class CheckUserPassword
     * Constuct response for Request with Command/ID = 10
     *
     * @author alexogar
     * Created on Jul 28, 2008
     */
    class CheckUserPassword extends CommandRequestAction {

        /**
         * @see clientCommands.CommandRequestAction#CommandRequestAction()
         */
        public CheckUserPassword() {super();}

        /**
         * @see clientCommands.CommandRequestAction#executeLogic()
         */
        @Override
        public String executeLogic() throws ServiceException, DataException {
            nodes = document.getElementsByTagName("UserInfo");
            String username = getXMLNodeValue(nodes, "Username");
            String password = getXMLNodeValue(nodes, "Password");

            boolean status = DelegateFactory.getUserManager().checkPassword(username, password);

            return getCheckPasswordStatus(status);
        }


        /**
         * Method getCheckPasswordStatus constracts XML with flag difference
         *
         * @param status of type boolean
         * @return String
         */
        private String getCheckPasswordStatus(boolean status) {
            String sResponse;
            try {
                DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
                Document document = builder.newDocument();
                Element elResponse = document.createElement("LLEResponse");

                Element elSubInfo = document.createElement("PasswordValid");

                Element elCurrStatus = document.createElement("Status");
                elCurrStatus.setTextContent((status ? "0" : "1"));
                elSubInfo.appendChild(elCurrStatus);

                elResponse.appendChild(elSubInfo);
                document.appendChild(elResponse);
                document.normalizeDocument();
                sResponse = XML2String(document);
            } catch (Exception ex) {
                m_log.error("+++ClientCommands. getHostList catch:" + ex);
                sResponse = Error2XML(ErrorCode.SYSTEM_ERROR.toInt(), ErrorCode.SYSTEM_ERROR.toString());
            }
            return sResponse;
        }
    }

    /**
     * Class DeleteDesktop
     * Constuct response for Request with Command/ID = 11
     *
     * @author alexogar
     * Created on Jul 28, 2008
     */
    class DeleteDesktop extends CommandRequestAction {

        /**
         * @see clientCommands.CommandRequestAction#CommandRequestAction()
         */
        public DeleteDesktop() {super();}

        /**
         * @see clientCommands.CommandRequestAction#executeLogic()
         */
        @Override
        public String executeLogic() throws ServiceException, DataException {
            nodes = document.getElementsByTagName("DesktopInfo");
            String did = request.getHeader("DID");
            m_log.debug("+++Action : DeleteForeignDesktop");
            DesktopDAO desktopDAO = DAOFactory.getDesktopDAO();
            Desktop desktop = desktopDAO.getByDid(did);

            //Check if the user is the owner of desktop
            AccountDAO accountDAO = DAOFactory.getAccountDAO();
            Account account = accountDAO.getByUsername(state.getUserDTO().getUsername());
            if (!state.getDesktop(did).isConnected()) {
                if (state.getUserDTO().getUsername().equals(desktop.getOwner())) {
                    try {
                        Statement st = HibernateSession.currentSession().connection().createStatement();
                        String deletenamessql = "delete from desktopnames where desktop_names='" + desktop.getId() + "'";
                        st.executeUpdate(deletenamessql);

                        String deleteDefaultDesktops = "update account set defaultDesktop=NULL where defaultDesktop='" + desktop.getId() + "'";
                        st.execute(deleteDefaultDesktops);

                        st.close();
                    } catch (SQLException e) {
                        throw new DataException(e);
                    } catch (HibernateException e) {
                        throw new DataException(e);
                    }

                    DelegateFactory.getDesktopManager().removeDesktop(did);

                } else {
                    Set foreignAccounts = desktop.getForeignAccounts();
                    for (Object foreignAccount : foreignAccounts) {
                        Account acc;
                        acc = (Account) foreignAccount;

                        if (acc.getId().equals(account.getId())) {
                            m_log.debug("Remove Account:" + account.toString());
                            foreignAccounts.remove(acc);
                        }
                    }

                    state.refreshDesktopList();
                    desktop.setForeignAccounts(foreignAccounts);
                    desktopDAO.update(desktop);
                }
            } else {
                throw new ServiceException(new PortalError(ErrorCode.DESKTOP_CONNECTED));
            }
            return "";
        }
    }

    /**
     * Class HideDesktop
     * Constuct response for Request with Command/ID = 12
     *
     * @author alexogar
     * Created on Jul 28, 2008
     */
    class HideDesktop extends CommandRequestAction {

        /**
         * @see clientCommands.CommandRequestAction#CommandRequestAction()
         */
        public HideDesktop() { super();}

        /**
         * @see clientCommands.CommandRequestAction#executeLogic()
         */
        @Override
        public String executeLogic() throws ServiceException, DataException {
            m_log.debug("+++Action : HideDesktop");

            nodes = document.getElementsByTagName("UserInfo");
            String did = request.getHeader("DID");
            boolean flag = Boolean.parseBoolean(getXMLNodeValue(nodes, "Flag"));
            AccountDAO accountDAO = DAOFactory.getAccountDAO();
            Account account = accountDAO.getByUsername(state.getUserDTO().getUsername());

            DesktopDAO desktopDAO = DAOFactory.getDesktopDAO();
            Desktop desktop = desktopDAO.getByDid(did);

            if (!flag) {
                account.hideDesktop(desktop);
            } else {
                desktop = account.showDesktop(desktop);
            }

            accountDAO.update(account);
            desktopDAO.update(desktop);

            return "";
        }
    }


    /**
     * Class RenameDesktopName
     * Constuct response for Request with Command/ID = 13
     *
     * @author alexogar
     * Created on Jul 28, 2008
     */
    class RenameDesktopName extends CommandRequestAction {

        /**
         * @see clientCommands.CommandRequestAction#CommandRequestAction()
         */
        public RenameDesktopName() {super();}

        /**
         * @see clientCommands.CommandRequestAction#executeLogic()
         */
        @Override
        public String executeLogic() throws ServiceException, DataException {
            nodes = document.getElementsByTagName("UserInfo");
            String newdesktopname = getXMLNodeValue(nodes, "NewDesktopName");
            String utfnewDesktop;
            try {
                utfnewDesktop = new String(newdesktopname.getBytes("UTF-8"), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                throw new ServiceException(new PortalError(ErrorCode.SYSTEM_ERROR));
            }
            m_log.debug(newdesktopname + "to utf-8" + utfnewDesktop);
            AccountDAO accountDAO = DAOFactory.getAccountDAO();
            Account account = accountDAO.getByUsername(state.getUserDTO().getUsername());
            m_log.debug("+++Action : Change Desktop Name");
            String did = request.getHeader("DID");
            DesktopDAO desktopDAO = DAOFactory.getDesktopDAO();
            Desktop desktop = desktopDAO.getByDid(did);
            account.addDesktopName(desktop, newdesktopname);
            accountDAO.update(account);
            desktopDAO.update(desktop);

            return "";
        }
    }

    /**
     * Class SetDefaultDesktop
     * Constuct response for Request with Command/ID = 14
     *
     * @author alexogar
     * Created on Jul 28, 2008
     */
    class SetDefaultDesktop extends CommandRequestAction {

        /**
         * @see clientCommands.CommandRequestAction#CommandRequestAction()
         */
        public SetDefaultDesktop() {super();}

        /**
         * @see clientCommands.CommandRequestAction#executeLogic()
         */
        @Override
        public String executeLogic() throws ServiceException, DataException {
            String did = request.getHeader("DID");
            if (did != null) {
                UserDTO user = state.getUserDTO();
                DelegateFactory.getDesktopManager().setDefaultDesktop(user.getUsername(), did);
            } else {
                throw new ServiceException(new PortalError(ErrorCode.UNKNOWN_DID_DEFINED));
            }

            return "";
        }
    }

    /**
     * Class GetDefaultDesktop
     * Constuct response for Request with Command/ID = 15
     *
     * @author alexogar
     * Created on Jul 28, 2008
     */
    class GetDefaultDesktop extends CommandRequestAction {

        /**
         * @see clientCommands.CommandRequestAction#CommandRequestAction()
         */
        public GetDefaultDesktop() {super();}

        /**
         * @see clientCommands.CommandRequestAction#executeLogic()
         */
        @Override
        public String executeLogic() throws ServiceException, DataException {
            String sResponse;
            UserDTO user = state.getUserDTO();
            DesktopDTO desktop = DelegateFactory.getDesktopManager().getDefaultDesktop(user.getUsername());
            if (desktop != null) {
                sResponse = getDefaultDesktop(desktop.getDid());
            } else {
                sResponse = getDefaultDesktop("");
            }

            return sResponse;
        }

        /**
         * Method getDefaultDesktop constructs XML
         *
         * @param did of type String
         * @return String
         * @throws ServiceException when Logic problem
         */
        private String getDefaultDesktop(String did) throws ServiceException {

            DocumentBuilder builder;
            try {
                builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            } catch (ParserConfigurationException e) {
                throw new ServiceException(new PortalError(ErrorCode.SYSTEM_ERROR));
            }
            Document document = builder.newDocument();
            Element elResponse = document.createElement("LLEResponse");

            Element elHostInfo = document.createElement("HostInfo");

            Element elDid = document.createElement("DID");
            elDid.setTextContent(did);
            elHostInfo.appendChild(elDid);

            elResponse.appendChild(elHostInfo);
            document.appendChild(elResponse);
            document.normalizeDocument();


            return XML2String(document);
        }
    }

    /**
     * Class UnlicenseDesktop
     * Constuct response for Request with Command/ID = 16
     *
     *
     * @author alexogar
     * Created on Jul 28, 2008
     */
    class UnlicenseDesktop extends CommandRequestAction {

        /**
         * @see clientCommands.CommandRequestAction#CommandRequestAction()
         */
        public UnlicenseDesktop() {super();}

        /**
         * @see clientCommands.CommandRequestAction#executeLogic()
         */
        @Override
        public String executeLogic() throws ServiceException, DataException {
            String sDID = request.getHeader("DID");

            DesktopDAO desktopDAO = DAOFactory.getDesktopDAO();
            Desktop desktop = desktopDAO.getByDid(sDID);
            AccountDAO accountDAO = DAOFactory.getAccountDAO();
            LicenseManager licenseManager = DelegateFactory.getLicenseManager();
            Account acc = accountDAO.getById(state.getUserDTO().getId());
            licenseManager.unlicense(desktop.getId(), acc.getReseller(), true, null);
            state.refreshDesktop(desktop.getDid());
            return "";
        }
    }

    /**
     * Class GetGuestHostList simulates getHostLIst request for guest Mode
     * returns 1 host user with name guest and some params.
     *
     * @author alexogar
     * Created on Sep 8, 2008
     */
    class GetGuestHostList extends CommandRequestAction {

        /** Field guestDID from request parameter */
        protected String guestDID;

        /**
         * @see clientCommands.CommandRequestAction#CommandRequestAction()
         */
        public GetGuestHostList() {
            super();
        }

        /**
         * Method executeLogic ...
         * @return String
         * @throws ServiceException when
         * @throws DataException when
         *
         * @see clientCommands.CommandRequestAction#executeLogic()
         */
        public String executeLogic() throws ServiceException, DataException {
            String sResponse;
            DocumentBuilder builder;

            if (session.getAttribute("did")!=null) {
                setGuestDID((String) session.getAttribute("did"));
            } else {
                throw new ServiceException(new PortalError(ErrorCode.UNKNOWN_DID_DEFINED));
            }

            try {
                builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            } catch (ParserConfigurationException e) {
                throw new ServiceException(new PortalError(ErrorCode.SYSTEM_ERROR));
            }

            Document document = builder.newDocument();
            Element elResponse = document.createElement("LLEResponse");
            Element elUserInfo = document.createElement("UserInfo");

            Element elUsername = document.createElement("UserName");
            elUsername.setTextContent("guest");
            elUserInfo.appendChild(elUsername);

            Element elFirstname = document.createElement("FirstName");
            elFirstname.setTextContent("guest");
            elUserInfo.appendChild(elFirstname);

            Element elLastname = document.createElement("LastName");
            elLastname.setTextContent("guest");
            elUserInfo.appendChild(elLastname);

            Element elDefaultDesktop = document.createElement("DefaultDesktop");
            elDefaultDesktop.setTextContent(getGuestDID());
            elUserInfo.appendChild(elDefaultDesktop);

            elResponse.appendChild(elUserInfo);
            DesktopDTO desktop = DelegateFactory.getDesktopManager().getDesktop(getGuestDID());
            if (desktop!=null) {
                Element elHostInfo = document.createElement("HostInfo");
                Element elID = document.createElement("ID");
                elID.setTextContent(desktop.getDid());
                elHostInfo.appendChild(elID);
                //state.refreshDesktop(desktop.getDid());

                Element elName = document.createElement("Name");
                elName.setTextContent("host");
                elHostInfo.appendChild(elName);

                Element elOwner = document.createElement("Owner");
                elOwner.setTextContent(desktop.getOwner());
                elHostInfo.appendChild(elOwner);

                Element elGuest = document.createElement("GuestMode");
                elGuest.setTextContent("Yes");
                elHostInfo.appendChild(elGuest);

                Element elOnline = document.createElement("OnlineFlag");
                Element elOnlyRC = document.createElement("OnlyRC");
                elOnlyRC.setTextContent("Yes");
                elHostInfo.appendChild(elOnlyRC);
                if (desktop.isConnected()) {
                    elOnline.setTextContent("Yes");
                    elOnlyRC.setTextContent("No");
                } else if (desktop.getRdpEnabled()) {
                    elOnline.setTextContent("Yes");
                    elOnlyRC.setTextContent("Yes");
                } else {
                    elOnline.setTextContent("No");
                    elOnlyRC.setTextContent("No");
                }
                elHostInfo.appendChild(elOnlyRC);
                elHostInfo.appendChild(elOnline);

                Element elHidden = document.createElement("HiddenFlag");
                elHidden.setTextContent("No");
                elHostInfo.appendChild(elHidden);

                Element elICM = document.createElement("ICM");
                elICM.setTextContent(desktop.getIcmURL());
                elHostInfo.appendChild(elICM);

                Element elICMRC = document.createElement("ICMRC");
                elICMRC.setTextContent(desktop.getRdpURL());
                elHostInfo.appendChild(elICMRC);

                Element elVersion = document.createElement("Version");
                elVersion.setTextContent(desktop.getSoftwareVersion());
                elHostInfo.appendChild(elVersion);

                elResponse.appendChild(elHostInfo);

                getSession().setAttribute("guest_desktop",desktop);
            } else {
                throw new ServiceException(new PortalError(ErrorCode.UNKNOWN_DID_DEFINED));    
            }
            document.appendChild(elResponse);
            document.normalizeDocument();
            sResponse = XML2String(document);

            return sResponse;
        }

        public String getGuestDID() {
            return guestDID;
        }

        public void setGuestDID(String guestDID) {
            this.guestDID = guestDID;
        }
    }

    /**
     * Class KeepAlive represent command for keepalive request
     *
     * @author alexogar
     * Created on Oct 10, 2008
     */
    class KeepAlive extends CommandRequestAction {

       /**
        * @see clientCommands.CommandRequestAction#CommandRequestAction()
        */
        public KeepAlive() {
            super();
        }

        /**
         * Method executeLogic ...
         * @return String
         * @throws ServiceException when
         * @throws DataException when
         *
         * @see clientCommands.CommandRequestAction#executeLogic()        
         */
        public String executeLogic() throws ServiceException, DataException {
            String sResponse;
            DocumentBuilder builder;
            try {
                builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            } catch (ParserConfigurationException e) {
                throw new ServiceException(new PortalError(ErrorCode.SYSTEM_ERROR));
            }
            Document document = builder.newDocument();
            Element elResponse = document.createElement("LLEResponse");

            Element elSessionInfo = document.createElement("SessionInfo");

            Element elCreationTime = document.createElement("CreationTime");
            elCreationTime.setTextContent(String.valueOf(session.getCreationTime()));
            elSessionInfo.appendChild(elCreationTime);

            elResponse.appendChild(elSessionInfo);

            document.appendChild(elResponse);
            document.normalizeDocument();
            sResponse = XML2String(document);

            return sResponse;
        }
    }

    /**
     * Executes the business logic.
     *
     * @param mapping    The ActionMapping used to select this instance
     * @param actionForm The optional ActionForm bean for this request (if any)
     * @param request    The HTTP request we are processing
     * @param response   The HTTP response we are creating
     * @return a forward
     * @throws IOException if an input/output error occurs
     *
     * @see com.infowave.c4p.web.util.BaseAction#executeLogic(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse, List)
     */
    public ActionForward execute(ActionMapping mapping,
                                      ActionForm actionForm,
                                      HttpServletRequest request,
                                      HttpServletResponse response) throws IOException,
            SAXException,
            InstantiationException,
            IllegalAccessException,
            ParserConfigurationException,
            ClassNotFoundException, InvocationTargetException, NoSuchMethodException {
        String sResponse;

        try {
            CommandActionFactory factory = new CommandActionFactory();
            CommandRequestAction command = factory.createCommandAction(request, response,this);
            sResponse = command.executeLogic();
        } catch (ServiceException e) {
            sResponse = Error2XML(e.getError().getCode().toInt(), e.getError().getCode().toString());
        } catch (DataException e) {
            sResponse = Error2XML(ErrorCode.DATABASE_ERROR.toInt(), ErrorCode.DATABASE_ERROR.toString());
        }

        if (sResponse.indexOf("LLEError") >= 0)
            response.setStatus(500);
        else response.setStatus(200);

        byte[] utf8Bytes = sResponse.getBytes("UTF-8");
        String utf8Response = new String(utf8Bytes, "UTF-8");

        m_log.debug(utf8Response);

        response.setHeader("content-type", "text/xml; charset=utf-8");
        response.getWriter().print(utf8Response);
        response.flushBuffer();
        return null;
    }
}
