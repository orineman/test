package com.infowave.c4p.web.util;

import com.infowave.c4p.commons.ClientCapabilities;
import com.infowave.c4p.commons.DisplayMode;
import com.infowave.c4p.service.ServiceException;
import com.infowave.c4p.service.delegate.DelegateFactory;
import com.infowave.c4p.service.delegate.DesktopManager;
import com.infowave.c4p.service.delegate.UserManager;
import com.infowave.c4p.service.dto.DesktopDTO;
import com.infowave.c4p.service.dto.ResellerDTO;
import com.infowave.c4p.service.dto.UserDTO;
import com.infowave.c4p.web.config.Configurator;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;
import javax.servlet.jsp.jstl.fmt.LocalizationContext;
import java.io.Serializable;
import java.security.Principal;
import java.util.*;

/**
 * Created by: Vincent Aumont
 * Copyright 2003 Infowave Software, Inc.
 */
public class SessionState implements Serializable {
    /**
     * The class's logger
     */
    private static Log m_log = LogFactory.getLog(SessionState.class);

    private static final int NO_SECTION = 0;
    private static final int DESKTOP_SECTION = 1;
    private static final int ACCOUNT_SECTION = 2;
    private static final int HELP_SECTION = 3;

    /**
     * user hsid
     */
    private String hsid = null;
    /**
     * the user
     */
    private UserDTO m_userDTO = null;
    /**
     * The user's desktops
     */
    private List m_desktops = null;
    /**
     * the desktop that's currently selected
     */
    private DesktopDTO m_currentDesktop = null;
    /**
     * the selected display mode
     */
    private DisplayMode m_displayMode = null;
    /**
     * The display capabilities of the user's browser
     */
    private ClientCapabilities clientCapabilities = null;
    /**
     * The modes the user's browser supports as a slash separated string
     * (e.d. L/M/S
     */
    private String supportedDisplayModeAsString = null;
    /**
     * The reseller
     */
    private ResellerDTO reseller = null;
    /**
     * The user's locale
     */
    private Locale m_locale = null;
    /**
     * The current section
     */
    private int m_currentSection = NO_SECTION;

    /**
     * The LLE version
     */
    private double m_version = 5.0;

    /**
     * The buffer for RSA Authentication
     */
    private byte[] m_RSAbuffer = null;

    private int screenWidth;

    private int screenHeight;

    private DesktopDTO defaultDesktop;

// ---------------------------------------------------------------Accessers

    public byte[] getRSAbuffer() {
        return m_RSAbuffer;
    }

    public void setRSAbuffer(byte[] RSAbuffer) {
        m_RSAbuffer = RSAbuffer;
    }

    public double getVersion() {
        return m_version;
    }

    public void setVersion(double fVersion) {
        m_version = fVersion;
    }

    public UserDTO getUserDTO() {
        //if (m_session != null)
        //m_log.debug("getUser " + m_session.getId() + " " + m_locale);
        return m_userDTO;
    }

    public synchronized void setUserDTO(UserDTO userDTO) {
        this.m_userDTO = userDTO;
    }

    public synchronized List getDesktops() {
        //return m_desktops;
        return getOrderedDesktops();
    }

    public synchronized void setCurrentDesktop(DesktopDTO currentDesktop) {
        this.m_currentDesktop = currentDesktop;
    }

    public DesktopDTO getCurrentDesktop() {
        return m_currentDesktop;
    }

    public DisplayMode getDisplayMode() {
        return m_displayMode;
    }

    public synchronized void setDisplayMode(DisplayMode displayMode) {
        this.m_displayMode = displayMode;
    }

    public ResellerDTO getReseller() {
        return reseller;
    }

    /**
     * Registers teh reseller for this session
     *
     * @param reseller
     */
    public synchronized void setReseller(ResellerDTO reseller) {
        this.reseller = reseller;
        if (reseller != null) {
            m_log.debug("Reseller for this session: " + reseller.getName());
        }
    }

    public Locale getLocale() {
        return m_locale;
    }

    public ClientCapabilities getClientCapabilities() {
        return clientCapabilities;
    }
// ------------------------------------------------------------ Public methods

    public SessionState(HttpServletRequest request) {
        HttpSession session;
        session = request.getSession();
        init(session);
        session.setAttribute("c4.sessionTerminationNotifier", new SessionTerminationNotifier(request, this));

    }

    /**
     * Initializes the session state
     */
    public void init(HttpSession session) {
        Config.set(session, Config.FMT_LOCALIZATION_CONTEXT, "messages");
    }

    /**
     * Refreshes the session information.
     */
    public synchronized void refresh(HttpServletRequest request) throws ServiceException {
        refreshUser(request);
        refreshDesktopList();
    }

    /**
     * Sets the current desktop after verifying that we have access to this
     * desktop and that is connected.
     *
     * @param did - the desktop's did
     * @return true is this desktop has been set has the current desktop; false if
     *         this user doesn't have access to that desktop or if the desktop is not
     *         connected.
     * @deprecated
     */
    public synchronized boolean setCurrentDesktop(String did) {
        boolean set = false;
        if (m_desktops != null) {
            for (Iterator it = m_desktops.iterator(); it.hasNext();) {
                DesktopDTO desktop = (DesktopDTO) it.next();
                if (did.equals(desktop.getDid())) {
                    if (desktop.isConnected()) {
                        m_currentDesktop = desktop;
                        set = true;
                    }
                }
            }
        }
        return set;
    }

    /**
     * Returns one of the user's desktops given its did
     *
     * @param did
     * @return
     */
    public synchronized DesktopDTO getDesktop(String did) {
        DesktopDTO dto = null;
        for (Iterator it = m_desktops.iterator(); it.hasNext();) {
            DesktopDTO desktop = (DesktopDTO) it.next();
            if (desktop.getDid().equals(did)) {
                dto = desktop;
                break;
            }
        }
        return dto;
    }

    /**
     * Return an ordered list of desktop:
     * - start with the currently selected desktop
     * - then the user's desktop sorted by name
     * - then the foreign desktops sorted by owner and then by name.
     *
     * @return
     */
    public synchronized List getOrderedDesktops() {
        int size = 0;
        if (m_desktops != null) {
            size = m_desktops.size();
        }
        List ordered = new ArrayList(size);
        // selected desktop goes first
        DesktopDTO currentdesktop = getCurrentDesktop();
        if (currentdesktop != null) {
            ordered.add(currentdesktop);

            // the list is already ordered, so we just need to add all desktops except
            // for the one we already have.
            String currentDesktopDid = currentdesktop.getDid();
            for (Iterator it = m_desktops.iterator(); it.hasNext();) {
                DesktopDTO d = (DesktopDTO) it.next();
                if (!d.getDid().equals(currentDesktopDid)) {
                    ordered.add(d);
                }
            }
            return ordered;
        } else {
            return m_desktops;
        }
    }

    public void setOrderedDesktops() {
    }

    /**
     * Returns count of contected desktops
     *
     * @return int 
     */
    public synchronized int getCountOfConnectedDesktops() {
        int res = 0;
        if (getDesktops() != null) {
            for (Iterator it = getDesktops().iterator();it.hasNext();) {
                DesktopDTO desktop = (DesktopDTO) it.next();
                if (desktop.isConnected()) { res++; } 
            }
        }

        return res;
    }

    /**
     * Returns the desktops that belongs to this user.
     *
     * @return
     */
    public synchronized List getUserDesktops() {
        List list;
        if (getDesktops() != null) {
            list = new ArrayList(getDesktops().size());
            for (Iterator it = getDesktops().iterator(); it.hasNext();) {
                DesktopDTO desktop = (DesktopDTO) it.next();
                if (desktop.getOwner().equalsIgnoreCase(getUserDTO().getUsername())) {
                    list.add(desktop);
                }
            }
        } else {
            list = new ArrayList(0);
        }
        return list;
    }

    /**
     * Returns a desktop identified by it's did providing that the logged in user
     * owns it.
     *
     * @param did
     * @return
     */
    public synchronized DesktopDTO getUserDesktop(String did) {
        DesktopDTO desktop = null;

        for (Iterator it = getDesktops().iterator(); it.hasNext();) {
            DesktopDTO d = (DesktopDTO) it.next();
            if (d.getDid().equalsIgnoreCase(did) && d.getOwner().equalsIgnoreCase(getUserDTO().getUsername())) {
                desktop = d;
                break;
            }
        }
        return desktop;
    }

    /**
     * Returns wheter we're in the Desktop section
     *
     * @return
     */
    public boolean isInDesktopSection() {
        return (m_currentSection == DESKTOP_SECTION);
    }

    /**
     * Returns wheter we're in the Desktop section
     *
     * @return
     */
    public boolean isInAccountSection() {
        return (m_currentSection == ACCOUNT_SECTION);
    }

    /**
     * Returns wheter we're in the Desktop section
     *
     * @return
     */
    public boolean isInHelpSection() {
        return (m_currentSection == HELP_SECTION);
    }

    /**
     * The current page is a Desktop page.
     */
    public void enterDesktopSection() {
        m_currentSection = DESKTOP_SECTION;
    }

    /**
     * The current page is a Account Management page.
     */
    public void enterAccountSection() {
        m_currentSection = ACCOUNT_SECTION;
    }

    /**
     * The current page is a Help page.
     */
    public void enterHelpSection() {
        m_currentSection = HELP_SECTION;
    }

    /**
     * No tab is selected
     */
    public void notInAnySection() {
        m_currentSection = NO_SECTION;
    }

    /**
     * Refreshes a desktop's state from the database.
     * todo right now we're updating all desktops.
     *
     * @param did
     */
    public synchronized void refreshDesktop(String did) throws ServiceException {
        refreshDesktopList();
    }

    /**
     * Gets from the database the list of desktops this user has access to.
     *
     * @throws ServiceException
     */
    public synchronized void refreshDesktopList() throws ServiceException {
        DesktopManager desktopManager = DelegateFactory.getDesktopManager();
        m_desktops = desktopManager.getDesktops(m_userDTO.getUsername());
        defaultDesktop = desktopManager.getDefaultDesktop(m_userDTO.getUsername());
    }

    // ----------------------------------------------------------- helper methods
    private synchronized void refreshUser(HttpServletRequest request) throws ServiceException {
        // if we don't have  user DTO, it is because the user has just logged in.
        if (m_userDTO == null) {
            HttpSession session = request.getSession();
            Principal principal = request.getUserPrincipal();
            UserManager userManager = DelegateFactory.getUserManager();
            String username = principal.getName();
            m_userDTO = userManager.getUserDTO(username);
            if (m_userDTO != null) {
                m_log.debug("got user: " + m_userDTO.getUsername() + " " + m_userDTO.getLocale());
                setLocale(session, m_userDTO.getLocale());
            }
        }
    }


    /**
     * Sets the browser's capabilities.
     *
     * @param request
     */
    public synchronized void setClientCapabilities(HttpServletRequest request) {
        clientCapabilities = new ClientCapabilities(request);
        if (m_displayMode == null) {
            m_displayMode = clientCapabilities.getInitialDisplay();
        }
        if (supportedDisplayModeAsString == null) {
            boolean first = true;
            StringBuffer buff = new StringBuffer();
            for (Iterator it = clientCapabilities.getCapabilities().iterator(); it.hasNext();) {
                DisplayMode mode = (DisplayMode) it.next();
                if (first) {
                    first = false;
                } else {
                    buff.append("/");
                }
                buff.append(mode.getCode());
            }
            supportedDisplayModeAsString = buff.toString();
        }
        BrowserInfo browser = new BrowserInfo(request, request.getSession());
        setScreenHeight(browser.getHeight());
        setScreenWidth(browser.getWidth());
        m_log.debug("Client display capabilities: " + clientCapabilities + " Accept: " + request.getHeader("accept") + " User Agent: " + request.getHeader("user-agent")+"Scrren Resolution: "+screenWidth+"x"+screenHeight);
    }


    /**
     * Returns the display modes this session supports
     *
     * @return the display modes this session supports
     */
    public synchronized List getSupportedDisplayModes() {
        if (clientCapabilities == null) {
            m_log.error("calling getSupportedDisplayModes too early", new Throwable());
            return new ArrayList(0);
        } else {
            return clientCapabilities.getCapabilities();
        }
    }

    /**
     * Returns the display modes this session supports in the format we use for
     *
     * @return the display modes this session supports
     */
    public String getSupportedDisplayModesAsString() {

        return supportedDisplayModeAsString;
    }

    /**
     * Determine the reseller by looking at the hostname.
     *
     * @param request
     */
    public synchronized boolean setReseller(HttpServletRequest request) {
        boolean success = true;
        // The reseller can't change during a session
        if (reseller != null) {
            return true;
        }
        // find the reseller
        Configurator cfg = Configurator.instance();
        String hostname = request.getServerName();
        setReseller(cfg.getResellerByHostname(hostname));
        if (getReseller() == null) {
            m_log.error("No reseller found for hostname: " + hostname);
            success = false;
        }
        return success;
    }

    /**
     * Find a locale we'll use until we've authenticated the user and retrieved
     * his/her preferred locale.
     * If the browser passed a locale, we use it. Otherwise, we use the reseller's
     * default locale.
     * <p/>
     * todo: we should get the list of locales from the request and return the
     * first one for which we have a localized messages file.
     */
    public synchronized void setTemporaryLocale(HttpServletRequest request) {
        Locale locale = null;
        HttpSession session = request.getSession();
        if (request.getParameter("locale") != null) {
            setLocale(session, new Locale(request.getParameter("locale")));
        } else if (request.getLocale() != null) {
            setLocale(session, request.getLocale());
        } else {
            setLocale(session, reseller.getDefaultLocale());
        }

    }


    /**
     * Changes the session's locale. We also change the reseller's bundle as well
     * as the  JSTL locale.
     */
    public synchronized void setLocale(HttpSession session, Locale locale) {
        m_locale = new Locale(locale.getLanguage());

        // The reseller's bundle
        if (getReseller() != null) {
            ResourceBundle rb = null;
            rb = ResourceBundle.getBundle(getReseller().getName(), locale);
            LocalizationContext lc = new LocalizationContext(rb);
            session.setAttribute(WebConstants.RESELLER_BUNDLE, lc);
            m_log.debug("Setting the reseller's bundle to " + getReseller().getName() + "_" + locale);
        }
        m_log.debug("Setting the locale to :" + locale + " for session " + session.getId());
        Config.set(session, Config.FMT_LOCALE, locale);
    }

    public String getHsid() {
        return hsid;
    }

    public void setHsid(String hsid) {
        this.hsid = hsid;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public void setScreenWidth(int screenWidth) {
        this.screenWidth = screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public void setScreenHeight(int screenHeight) {
        this.screenHeight = screenHeight;
    }

    public DesktopDTO getDefaultDesktop() {
        return defaultDesktop;
    }

    public void setDefaultDesktop(DesktopDTO defaultDesktop) {
        this.defaultDesktop = defaultDesktop;
    }
}
