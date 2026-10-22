package com.infowave.c4p.model;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.*;

import com.infowave.c4p.datasource.ObjectNotFoundException;
import com.infowave.c4p.datasource.DataException;
import com.infowave.c4p.datasource.dao.hibernate.HibernateSession;
import com.infowave.c4p.datasource.dao.AccountDAO;
import com.infowave.c4p.datasource.dao.DAOFactory;


/**
 * This class represents an Account.
 * <p/>
 * Created by: Vincent Aumont
 * Copyright 2003 Infowave Software, Inc.
 */

public class Account

{


    /**
     * The class's logger
     */

    private static Log m_log = LogFactory.getLog(Account.class);

    private String id = null;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private Locale locale;
    private Date creationDate;
    private int securityQuestion;
    private String securityAnswer;
    private Reseller reseller;
    private Set desktops;
    private Map<Desktop, Boolean> hiddenDesktops;
    private Map<Desktop, String> desktopNames;
    private Set foreignDesktops;
    private String billingId;
    private Desktop defaultDesktop;

    /**
     * The first license code entered for teh user - for tracking purposes
     */

    private String regCode;
    private String countryCode;

    public Account() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Locale getLocale() {
        return locale;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String code) {
        this.countryCode = code;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public int getSecurityQuestion() {
        return securityQuestion;
    }

    public void setSecurityQuestion(int securityQuestion) {
        this.securityQuestion = securityQuestion;
    }

    public String getSecurityAnswer() {
        return securityAnswer;
    }

    public void setSecurityAnswer(String securityAnswer) {
        this.securityAnswer = securityAnswer;
    }

    public Reseller getReseller() {
        return reseller;
    }

    public void setReseller(Reseller reseller) {
        this.reseller = reseller;
    }

    public Set getDesktops() {
        return desktops;
    }

    public void setDesktops(Set desktops) {
        this.desktops = desktops;
    }

    public Set getForeignDesktops() {
        return foreignDesktops;
    }

    public void setForeignDesktops(Set foreignDesktops) {
        this.foreignDesktops = foreignDesktops;
    }

    public String getBillingId() {
        return billingId;
    }

    public void setBillingId(String billingId) {
        this.billingId = billingId;
    }

    public String getRegCode() {
        return regCode;
    }

    public void setRegCode(String regCode) {
        this.regCode = regCode;
    }

    public Desktop getDefaultDesktop() {
        return this.defaultDesktop;
    }

    public void setDefaultDesktop( Desktop desktop ) {
        this.defaultDesktop = desktop;
    }

    /**
     * Returns the user's full name
     *
     * @return String
     */

    public String getFullName() {
        StringBuffer sb = new StringBuffer();
        if (firstName != null) {
            sb.append(firstName);
        }
        if (lastName != null) {
            sb.append(" ").append(lastName);
        }
        if (sb.length() == 0) {
            sb.append("");
        }
        return sb.toString();
    }


    /**
     * Returns a desktop givin it's did.
     *
     * @param did - the did we're looking for
     * @return the desktop identified by that did or null;
     */

    public Desktop getDesktop(String did) {
        Desktop desktop = null;
        if (getDesktops() != null) {
            for (Object o : getDesktops()) {
                Desktop d = (Desktop) o;
                if (d.getDid().equals(did)) {
                    desktop = d;
                    break;
                }
            }
        }
        return desktop;
    }


    /**
     * Returns a foreign desktop givin it's did.
     *
     * @param did - the did we're looking for
     * @return the desktop identified by that did or null;
     */

    public Desktop getForeignDesktop(String did) {
        Desktop desktop = null;
        if (getForeignDesktops() != null) {
            for (Object o : getForeignDesktops()) {
                Desktop d = (Desktop) o;
                if (d.getDid().equals(did)) {
                    desktop = d;
                    break;
                }
            }
        }
        return desktop;
    }

    /**
     * Removes a desktop given it's did.
     *
     * @param did - the did we're looking for
     * @return the desktop identified by that did or null;
     */

    public Desktop removeDesktop(String did) {
        Desktop desktop = null;
        if (getDesktops() != null) {
            for (Iterator it = getDesktops().iterator(); it.hasNext();) {
                Desktop d = (Desktop) it.next();
                if (d.getDid().equals(did)) {
                    it.remove();
                    desktop = d;
                    break;
                }
            }
        }
        // todo delete the desktop
        return desktop;
    }


    /**
     * Adds a desktop to the list of desktops owned by the owner of thsi account.
     *
     * @param desktop - the desktop to add.
     * @throws DesktopAlreadyAddedException
     */

    public void addDesktop(Desktop desktop)
            throws DesktopAlreadyAddedException {
        if (getDesktop(desktop.getDid()) != null) {
            throw new DesktopAlreadyAddedException("did: " + desktop.getDid());
        } else {
            if (desktops == null) {
                desktops = new HashSet();
            }
            desktops.add(desktop);
            desktop.setAccount(this);
            //DAOFactory.getDesktopDAO().update(desktop);
        }
    }


    /**
     * Adds a desktop to the list of hidden desktops the owner of this account
     * has access to.
     *
     * @param desktop
     * @throws DesktopAlreadyAddedException
     */

    public void hideDesktop(Desktop desktop) {

        if (hiddenDesktops == null) {
            hiddenDesktops = new HashMap<Desktop, Boolean>();
        }

        for (Map.Entry<Desktop, Boolean> entry : getHiddenDesktops().entrySet()) {
                if (entry.getKey().getDid().equals(desktop.getDid())) {
                    entry.setValue(true);
                    return;
                }
        }

        this.getHiddenDesktops().put(desktop, true);
        if (getDesktopName(desktop.getDid()) == null) {
            this.addDesktopName(desktop, "");
        }
/*        if (!getHiddenDesktops().isEmpty()) {
            for (Map.Entry<Desktop, Boolean> entry : getHiddenDesktops().entrySet()) {
                if (entry.getKey().getDid().equals(desktop.getDid())) {
                    entry.setValue(true);
                    break;
                }
            }
        } else {
            this.getHiddenDesktops().put(desktop, true);
        }
        if (hiddenDesktops.isEmpty()) {
            this.getHiddenDesktops().put(desktop, true);
            if (getDesktopName(desktop.getDid()) == null) {
                this.addDesktopName(desktop, "");
            }
        }*/
    }


    /**
     * Adds a desktop to the list of hidden desktops the owner of this account
     * has access to.
     *
     * @param desktop
     * @throws DesktopAlreadyAddedException
     */

    public Desktop showDesktop(Desktop desktop) throws ObjectNotFoundException {
        if (getDesktops() != null) {
            for (Map.Entry<Desktop, Boolean> entry : getHiddenDesktops().entrySet()) {
                if (entry.getKey().getDid().equals(desktop.getDid())) {
                    entry.setValue(false);
                    desktop = entry.getKey();
                    break;
                }
            }
        }

        if (!getDesktopNames().containsKey(desktop)) {
            addDesktopName(desktop, "");
        }
        return desktop;
    }


    /**
     * Returns a hidden desktop givin it's did.
     *
     * @param did - the did we're looking for
     * @return the desktop identified by that did or null;
     */

    public Map.Entry<Desktop, Boolean> getHiddenDesktop(String did) {
        Desktop desktop = null;
        if (getHiddenDesktops() != null) {
            for (Map.Entry<Desktop, Boolean> entry : getHiddenDesktops().entrySet()) {
                if (entry.getKey().getDid().equals(did)) {
                    desktop = entry.getKey();
                    return entry;
                }
            }
        }
        return null;
    }

    public Map.Entry<Desktop, String> getNamedDesktop(Desktop desktop) {
        if (getHiddenDesktops() != null) {
            for (Map.Entry<Desktop, String> entry : getDesktopNames().entrySet()) {
                if (entry.getKey().getDid().equals(desktop.getDid())) {
                    return entry;
                }
            }
        }
        return null;
    }

    public void addDesktopName(Desktop desktop, String name) {
        if (this.getNamedDesktop(desktop)!=null) {
            Map.Entry<Desktop, String> entry;
            m_log.debug("addDesktopName.1");
            for (Iterator<Map.Entry<Desktop, String>> it = (Iterator<Map.Entry<Desktop, String>>) this.getDesktopNames().entrySet().iterator(); it.hasNext();)
            {
                entry = it.next();
                if (entry.getKey().equals(desktop)) {
                    entry.setValue(name);
                    m_log.debug("addDesktopName.2");
                }
            }


        } else {
            m_log.debug("addDesktopName.3");
            this.getDesktopNames().put(desktop, name);
            try {
                m_log.debug("addDesktopName.4");
                this.showDesktop(desktop);
            } catch (ObjectNotFoundException e) {
                m_log.debug("Object not found");
                this.getHiddenDesktops().put(desktop, false);
            }
        }
    }


    /**
     * Adds a desktop to the list of foreign desktops the owner of this account
     * has access to.
     *
     * @param desktop
     * @throws DesktopAlreadyAddedException
     */

    public void addForeignDesktop(Desktop desktop)
            throws DesktopAlreadyAddedException {
        if (getForeignDesktop(desktop.getDid()) != null) {
            throw new DesktopAlreadyAddedException("did: " + desktop.getDid());
        }
        if (foreignDesktops == null) {
            foreignDesktops = new HashSet();
        }
        foreignDesktops.add(desktop);
        //DAOFactory.getAccountDAO().update(this);
    }

    public String getDesktopName(String did) {
        Desktop desktop = null;
        String name = "";
        if (getDesktopNames() != null) {
            for (Object o : getDesktopNames().keySet()) {
                Desktop d = (Desktop) o;
                if (d.getDid().equals(did)) {
                    desktop = d;
                    name = (String) getDesktopNames().get(d);
                    break;
                }
            }
        }
        return name;
    }

    /**
     * Return the list of desktops sorted by name;
     *
     * @return
     */

    public List getSortedDesktops() {
        List sorted;
        if (desktops != null) {
            sorted = new ArrayList(desktops);
            Collections.sort(sorted, new DesktopComparator());
        } else {
            sorted = new ArrayList(0);
        }
        return sorted;
    }


    /**
     * Return the list of foreign desktops sorted by owner, and then by name.
     *
     * @return
     */

    public List getSortedForeignDesktops() {
        List sorted;
        if (foreignDesktops != null) {
            sorted = new ArrayList(foreignDesktops);
            Collections.sort(sorted, new DesktopComparator());
        } else {
            sorted = new ArrayList(0);
        }
        return sorted;
    }

    public Map<Desktop, Boolean> getHiddenDesktops() {
        return hiddenDesktops;
    }

    public void setHiddenDesktops(Map<Desktop, Boolean> hiddenDesktops) {
        this.hiddenDesktops = hiddenDesktops;
    }

    public boolean isHiddenDesktop(String did) {
        Map.Entry<Desktop,Boolean> entry = getHiddenDesktop(did);
        if (entry != null) {
            if (entry.getValue() != null) {
                return (getHiddenDesktop(did).getValue());
            } else {
                entry.setValue(false);
                AccountDAO accountDAO = DAOFactory.getAccountDAO();
                try {
                    accountDAO.update(this);
                } catch (DataException e) {
                    m_log.debug("Unable to update value og isHidden of table desktopNames");
                }
                return false;
            }
        } else {
            return false;
        }
    }

    public void setDesktopNames(Map<Desktop,String> desktopNames) {
        this.desktopNames = desktopNames;
    }

    public Map<Desktop,String> getDesktopNames() {
        return desktopNames;
    }


    /**
     * Sorts two desktops. We sort by owner name first, then by name.
     */

    private static class DesktopComparator implements Comparator {
        public int compare(Object o1, Object o2) {
            int res;
            Desktop d1 = (Desktop) o1;
            Desktop d2 = (Desktop) o2;

            res = d1.getOwner().compareToIgnoreCase(d2.getOwner());
            if (res == 0) {
                res = d1.getName().compareToIgnoreCase(d2.getName());
            }
            return res;
        }

    }


    /**
     * For debugging purposes.
     *
     * @return
     */

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("Account - ");
        sb.append("username:").append(username).append(" ");
        sb.append("firstName:").append(firstName).append(" ");
        sb.append("lastName:").append(lastName).append(" ");

        if (reseller != null) {
            sb.append("reseller:").append(reseller.getCode()).append(" ");
        } else {
            sb.append("reseller: null!!").append(" ");
        }
        sb.append("Destops: ");
        if (desktops != null) {
            for (Object desktop1 : desktops) {
                Desktop desktop = (Desktop) desktop1;
                sb.append(desktop.getId()).append(",");
            }
        }
        sb.append(" ");
        sb.append("Foreign Destops: ");
        if (foreignDesktops != null) {
            for (Object foreignDesktop : foreignDesktops) {
                Desktop desktop = (Desktop) foreignDesktop;
                sb.append(desktop.getId()).append(",");
            }
        }
        sb.append(" ");

        return sb.toString();
    }
// --------------------------------------------------- Test methods


}

