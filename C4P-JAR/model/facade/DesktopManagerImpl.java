package com.infowave.c4p.model.facade;

import com.infowave.c4p.commons.DisplayMode;
import com.infowave.c4p.datasource.DataException;
import com.infowave.c4p.datasource.dao.*;
import com.infowave.c4p.model.*;
import com.infowave.c4p.model.assembler.DesktopAssembler;
import com.infowave.c4p.service.ServiceException;
import com.infowave.c4p.service.delegate.DelegateFactory;
import com.infowave.c4p.service.delegate.DesktopManager;
import com.infowave.c4p.service.delegate.LicenseManager;
import com.infowave.c4p.service.dto.DesktopDTO;
import com.infowave.c4p.util.ErrorCode;
import com.infowave.c4p.util.IdGenerator;
import com.infowave.c4p.util.PortalError;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * This class contains teh business logic of desktop-related operations.
 * <p/>
 * Created by: Vincent Aumont
 * Copyright 2003 Infowave Software, Inc.
 */
public class DesktopManagerImpl implements DesktopManager
{

/**
 * The class's logger
 */
private static Log m_log = LogFactory.getLog(DesktopManagerImpl.class);
//  private static String rdpStatusUrl = "https://rc.ll2go.com/?LLListServers&did={did}"; // LLE
private static String rdpStatusUrl = "";
private static String rdpusername = "";
private static String rdppassword = "";
private static String rdphostname = "";
private static String rdpprotocol = "";
private static int rdpport = -1;

/**
* Retrieve the default user desktop, return null if there is no default desktops is set.
*
* @param username - a username
* @return a default desktop or null
* @throws ServiceException
*/
public DesktopDTO getDefaultDesktop(String username) throws ServiceException
{
    Account account = null;
    AccountDAO accountDAO = DAOFactory.getAccountDAO();
    try
    {
        account = accountDAO.getByUsername(username);
    }
    catch(DataException e)
    {
        PortalError error = new PortalError(ErrorCode.SYSTEM_ERROR);
        throw new ServiceException(error, e);
    }

    if(account == null)
    {
        PortalError error = new PortalError(ErrorCode.INVALID_USERNAME, username);
        throw new ServiceException(error);
    }

    Desktop temp_desktop = account.getDefaultDesktop();

    if (temp_desktop!=null) {
        setICMUrls(temp_desktop);
        return DesktopAssembler.createDesktopDTO(temp_desktop);
    } else { return null; }
}

/**
 * Retrieves the list of desktops a user has access to. This
 * method  also checks whether the desktops are connected.
 * *
 *
 * @param username - a username
 * @return a list of desktop this user has access to.
 * @throws ServiceException
 */
public List getDesktops(String username) throws ServiceException
{
    m_log.debug("---> getDesktops: username - " + username);

	List desktops = new ArrayList();
	List desktopDTOs = new ArrayList();

	// Find the account corresponding to this username.
	Account account = null;
	AccountDAO accountDAO = DAOFactory.getAccountDAO();
	try
	{
		account = accountDAO.getByUsername(username);
	}
	catch(DataException e)
	{
		PortalError error = new PortalError(ErrorCode.SYSTEM_ERROR);
		throw new ServiceException(error, e);
	}
	if(account == null)
	{
		PortalError error = new PortalError(ErrorCode.INVALID_USERNAME, username);
		throw new ServiceException(error);
	}
	// Build a list of desktops by combining the user's desktops and foreign
	// desktops.
    List desktopList = account.getSortedDesktops();
    m_log.debug("     getDesktops: desktops - " + desktopList);
    if (desktopList != null) {
        for (int i = 0, k = desktopList.size(); i < k; i++) {
            desktops.add(desktopList.get(i));
        }
    }

    desktopList = account.getSortedForeignDesktops();
    m_log.debug("     getDesktops: foreign desktops - " + desktopList);
    if (desktopList != null) {
        for (int i = 0, k = desktopList.size(); i < k; i++) {
            desktops.add(desktopList.get(i));
        }
    }
	// Sets the ICM urls
	for(Iterator it = desktops.iterator(); it.hasNext();)
	{
		Desktop desktop = (Desktop) it.next();
		setICMUrls(desktop);
	}

	DesktopDAO desktopDAO = DAOFactory.getDesktopDAO();
	// Assemble the DTOs
	for(Iterator it = desktops.iterator(); it.hasNext();)
	{
		Desktop desktop1;
		Desktop desktop = (Desktop) it.next();
		// I give up!!! Just refresh the G*D* thing and be done with it
		try
		{
			desktop1 = desktopDAO.getByDid(desktop.getDid());
			if(desktop1 != null)
			{
				desktop.setRCExpiryDate(desktop1.getRCExpiryDate());
				desktop.setRCLicenseSerialNumber(desktop1.getRCLicenseSerialNumber());
			}
		}
		catch(DataException e)
		{
			m_log.debug("DataException: " + e.toString());
		}

		DesktopDTO dto = DesktopAssembler.createDesktopDTO(desktop);
		desktopDTOs.add(dto);
	}

	return desktopDTOs;
}

/**
 * Returns the desktop corresponding the a given did.
 *
 * @param did
 * @return
 * @throws ServiceException
 */
public DesktopDTO getByDid(String did) throws ServiceException
{
	DesktopDTO dto = null;
	Desktop desktop = null;
	DesktopDAO desktopDAO = DAOFactory.getDesktopDAO();
	try
	{
		desktop = desktopDAO.getByDid(did);
	}
	catch(DataException e)
	{
		PortalError error = new PortalError(ErrorCode.SYSTEM_ERROR);
		throw new ServiceException(error, e);
	}
	if(desktop != null)
	{
		dto = DesktopAssembler.createDesktopDTO(desktop);
	}

	return dto;
}

/**
 * Retrieves a desktop by did and finds its managing ICM.
 *
 * @param did - the desktop's did.
 * @return the desktop bean..
 * @throws ServiceException
 */

public DesktopDTO getDesktop(String did) throws ServiceException
{
	DesktopDTO dto = null;
	DesktopDAO dao = DAOFactory.getDesktopDAO();
	try
	{
		Desktop desktop = null;
		desktop = dao.getByDid(did);
		setICMUrls(desktop);
		dto = DesktopAssembler.createDesktopDTO(desktop);
	}
	catch(DataException e)
	{
		PortalError error = new PortalError(ErrorCode.SYSTEM_ERROR);
		throw new ServiceException(error, e);
	}
	return dto;
}

/**
 * Attaches a desktop to a several users.
 *
 * @param did		   - the desktop
 * @param ownerUsername - teh desktop's owner
 * @param users		 - teh users toattache the desktop to.
 * @throws ServiceException
 */
public void resetForeignAccounts(String did, String ownerUsername, List users) throws ServiceException
{

	try
	{
		AccountDAO accountDAO = DAOFactory.getAccountDAO();
		Account ownerAccount;
		ownerAccount = accountDAO.getByUsername(ownerUsername);
		// Get the account
		if(ownerAccount == null)
		{
			PortalError error = new PortalError(ErrorCode.INVALID_USERNAME);
			throw new ServiceException(error);
		}
		// Get the desktop
		DesktopDAO desktopDAO = DAOFactory.getDesktopDAO();
		Desktop ownerDesktop = desktopDAO.getByDidWithForeignAccounts(did);
		if(ownerDesktop == null)
		{
			PortalError error = new PortalError(ErrorCode.UNKNOWN_DID_DEFINED, did);
			throw new ServiceException(error);
		}

		// Make sure that this user owns this desktop
		boolean ownsIt = false;
		for(Iterator it = ownerAccount.getDesktops().iterator(); it.hasNext();)
		{
			Desktop desktop = (Desktop) it.next();
			if(desktop.getDid().equals(did))
			{
				ownsIt = true;
				break;
			}
		}
		if(!ownsIt)
		{
			PortalError error = new PortalError(ErrorCode.NOT_OWNER_OF_DESKTOP, ownerUsername, did);
			throw new ServiceException(error);
		}
		// Remove all existing accounts
		ownerDesktop.removeAllForeignAccess();

		// Finds all the account and add then to the desktop
		for(Iterator it = users.iterator(); it.hasNext();)
		{
			String username = (String) it.next();
			// Make sure that the user is not the owner
			if(username.equals(ownerUsername))
			{
				String usernames = "";
				for(Iterator i = users.iterator(); i.hasNext();)
				{
					usernames += i.next() + ",";
				}
				PortalError error = new PortalError(ErrorCode.INVALID_PARAMETERS, "resetForeignAccounts", ownerUsername, did, usernames);
				throw new ServiceException(error);
			}
			// Get the user account abd add it to the desktop
			Account account = accountDAO.getByUsername(username);
			if(account == null)
			{
				String usernames = "";
				for(Iterator i = users.iterator(); i.hasNext();)
				{
					usernames += i.next() + ",";
				}
				PortalError error = new PortalError(ErrorCode.INVALID_USERNAME, "resetForeignAccounts", ownerUsername, did, usernames);
				throw new ServiceException(error);
			}
			ownerDesktop.addForeignAccess(account);
		}

		// Save the desktop
		desktopDAO.update(ownerDesktop);
	}
	catch(DataException e)
	{
		PortalError error = new PortalError(ErrorCode.SYSTEM_ERROR);
		throw new ServiceException(error, e);
	}

}

public int getDesktopcountLikeAccountname(String partialData) throws ServiceException
{
	try
	{
		DesktopDAO desktopDAO = DAOFactory.getDesktopDAO();
		int count = desktopDAO.countDesktopsByAccountname(partialData);
		return count;
	}
	catch(DataException e)
	{
		PortalError error = new PortalError(ErrorCode.SYSTEM_ERROR);
		throw new ServiceException(error, e);
	}
}

public int getDesktopcountLikeDesktopname(String partialData) throws ServiceException
{
	try
	{
		DesktopDAO desktopDAO = DAOFactory.getDesktopDAO();
		int count = desktopDAO.countDesktopsByDesktopname(partialData);
		return count;
	}
	catch(DataException e)
	{
		PortalError error = new PortalError(ErrorCode.SYSTEM_ERROR);
		throw new ServiceException(error, e);
	}
}

public int getDesktopcountLikeLicense(String partialData) throws ServiceException
{
	try
	{
		DesktopDAO desktopDAO = DAOFactory.getDesktopDAO();
		int count = desktopDAO.countDesktopsByLicense(partialData);
		return count;
	}
	catch(DataException e)
	{
		PortalError error = new PortalError(ErrorCode.SYSTEM_ERROR);
		throw new ServiceException(error, e);
	}
}

public int getDesktopcountLikeExpirydate(String partialData) throws ServiceException
{
	try
	{
		DesktopDAO desktopDAO = DAOFactory.getDesktopDAO();
		int count = desktopDAO.countDesktopsByExpirydate(partialData);
		return count;
	}
	catch(DataException e)
	{
		PortalError error = new PortalError(ErrorCode.SYSTEM_ERROR);
		throw new ServiceException(error, e);
	}
}

public List getDesktopsLikeAccountname(String partialData) throws ServiceException
{
	List desktops = null;
	List desktopDTOs = new ArrayList();

	try
	{
		DesktopDAO desktopDAO = DAOFactory.getDesktopDAO();
		desktops = desktopDAO.getDesktopsByAccountname(partialData);
	}
	catch(DataException e)
	{
		PortalError error = new PortalError(ErrorCode.SYSTEM_ERROR);
		throw new ServiceException(error, e);
	}

	DesktopDTO dto;
	for(Iterator it = desktops.iterator(); it.hasNext(); desktopDTOs.add(dto))
	{
		Desktop desktop = (Desktop) it.next();
		dto = DesktopAssembler.createDesktopDTO(desktop);
	}

	return desktopDTOs;

}

public List getDesktopsLikeDesktopname(String partialData) throws ServiceException
{
	List desktops = null;
	List desktopDTOs = new ArrayList();

	try
	{
		DesktopDAO desktopDAO = DAOFactory.getDesktopDAO();
		desktops = desktopDAO.getDesktopsByDesktopname(partialData);
	}
	catch(DataException e)
	{
		PortalError error = new PortalError(ErrorCode.SYSTEM_ERROR);
		throw new ServiceException(error, e);
	}

	DesktopDTO dto;
	for(Iterator it = desktops.iterator(); it.hasNext(); desktopDTOs.add(dto))
	{
		Desktop desktop = (Desktop) it.next();
		dto = DesktopAssembler.createDesktopDTO(desktop);
	}

	return desktopDTOs;

}

public List getDesktopsLikeLicense(String partialData) throws ServiceException
{
	List desktops = null;
	List desktopDTOs = new ArrayList();

	try
	{
		DesktopDAO desktopDAO = DAOFactory.getDesktopDAO();
		desktops = desktopDAO.getDesktopsByLicense(partialData);
	}
	catch(DataException e)
	{
		PortalError error = new PortalError(ErrorCode.SYSTEM_ERROR);
		throw new ServiceException(error, e);
	}

	DesktopDTO dto;
	for(Iterator it = desktops.iterator(); it.hasNext(); desktopDTOs.add(dto))
	{
		Desktop desktop = (Desktop) it.next();
		dto = DesktopAssembler.createDesktopDTO(desktop);
	}

	return desktopDTOs;

}

public List getDesktopsLikeExpirydate(String partialData) throws ServiceException
{
	List desktops = null;
	List desktopDTOs = new ArrayList();

	try
	{
		DesktopDAO desktopDAO = DAOFactory.getDesktopDAO();
		desktops = desktopDAO.getDesktopsByExpirydate(partialData);
	}
	catch(DataException e)
	{
		PortalError error = new PortalError(ErrorCode.SYSTEM_ERROR);
		throw new ServiceException(error, e);
	}

	DesktopDTO dto;
	for(Iterator it = desktops.iterator(); it.hasNext(); desktopDTOs.add(dto))
	{
		Desktop desktop = (Desktop) it.next();
		dto = DesktopAssembler.createDesktopDTO(desktop);
	}

	return desktopDTOs;

}

// -------------------------------------------------------------- Helper methods

/**
 * Sets the ICM desktop by polling all ICMs.
 *
 * @param desktop - the desktop.
 */
private void setICMUrls(Desktop desktop)
{
	boolean validICM = false;
	ICM icm = null;
	DisplayMode[] modes = DisplayMode.getAll();

	// Try to find the ICM for this desktop
	icm = ICM.findICM(desktop.getDid());

	// If we found one, sets all the urls for that desktop.
	if(icm != null)
	{
		validICM = ParseICM(icm, desktop);
	}
//	else
	{
		m_log.debug("Try to find RDP ICM for DID:" + desktop.getDid());
		// see if RDP is active
		boolean isRdpEnabled = isRdpEnabled(desktop.getDid());
		if(isRdpEnabled)
		{
			ICM rcicm = ICM.findRCICM();
			if(rcicm != null)
			{
				try
				{
					URL url = rcicm.getDesktopAppListURL(DisplayMode.PC, desktop); // pc only
					m_log.debug("Set RDP Url:" + url.toExternalForm());
					desktop.setRdpURL(url.toExternalForm());
					if(desktop.getIcmURL() == null)
					{// by lexx. In case only RDP ICM
						validICM = ParseICM(rcicm, desktop);
						desktop.setOnlyRDP(true);
					}
				}
				catch(MalformedURLException e)
				{
					m_log.error("Invalid status url for icm:" + icm);
				}
			}
		}
		desktop.setRdpEnabled(isRdpEnabled);
	}

	// If we did not find an icm or if one of the urls is invalid, we set all
	// urls to null
	if(!validICM)
	{
		for(int i = 0; i < modes.length; i++)
		{
			desktop.addAppListUrl(modes[i], null);
		}
		desktop.setStatusURL(null);
	}
}

private boolean ParseICM(ICM icm, Desktop desktop)
{
	m_log.debug("found icm: " + icm.getName() + " for " + desktop.getDid());
	DisplayMode[] modes = DisplayMode.getAll();
	try
	{
		for(int i = 0; i < modes.length; i++)
		{
			URL url = icm.getDesktopAppListURL(modes[i], desktop);
			desktop.addAppListUrl(modes[i], url.toExternalForm());
//				m_log.debug("ICM:" + icm.getName() + " AppListURL[" + i + "]:" + url.toExternalForm());
		}
		URL statusURL = icm.getDesktopStatusURL(desktop.getDid());
		m_log.debug("Parse ICM. Status URL:" + statusURL.toExternalForm());
		desktop.setStatusURL(statusURL.toExternalForm());
		desktop.setAppUrlRegexp(icm.getAppPattern());
		m_log.debug("Parse ICM. ICM URL:" + icm.getTopURL().toExternalForm());
		desktop.setIcmURL(icm.getTopURL().toExternalForm());
		URL logoutURL = icm.getDesktopLogoutURL(desktop.getDid());
		m_log.debug("Parse ICM. Logout URL:" + logoutURL.toExternalForm());
		desktop.setLogoutURL(logoutURL.toExternalForm());
		return true;
	}
	catch(MalformedURLException e)
	{
		m_log.error("Invalid status url for icm:" + icm + e);
	}
	return false;
}

public boolean isRdpEnabled(String did)
{
	getRdpHost();

	boolean rdpEnabled = false;
	try
	{
		String url = ICM.substituteVariableInString(rdpStatusUrl, "did", did);
		rdpEnabled = ICM.isDesktopConnected(url, rdpusername, rdppassword, rdphostname, rdpprotocol, rdpport);
		if(rdpEnabled)
		{
			m_log.debug("Rdp Enabled:" + url + " for DID:" + did + "username:" + rdpusername + "password:" +
					rdppassword + "hostname:" + rdphostname + "protocol:" + rdpprotocol + "port:" + rdpport);
		}
		else
		{
			m_log.debug("Rdp Disabled:" + url + " for DID:" + did + "username:" + rdpusername + "password:" +
					rdppassword + "hostname:" + rdphostname + "protocol:" + rdpprotocol + "port:" + rdpport);
		}
	}
	catch(Exception e)
	{
		rdpEnabled = false;
	}
	return rdpEnabled;
}

private static void getRdpHost()
{
	try
	{
		ICMDAO icmDAO = DAOFactory.getICMDAO();
		ICM icm = icmDAO.getByUsername("RDP"); // actually the ICM name
		if(icm != null)
		{
			rdpStatusUrl = icm.getStatusURL();
			rdpusername = icm.getUsername();
			rdppassword = icm.getPassword();
			rdphostname = icm.getHostname();
			rdpprotocol = icm.getProtocol();
			rdpport = icm.getPort();

		}
	}
	catch(Exception e)
	{
	}
}


/**
 * Updates a desktop
 *
 * @param did
 * @param desktopDTO
 * @return
 * @throws ServiceException
 */
public DesktopDTO update(String did, DesktopDTO desktopDTO) throws ServiceException
{
	DesktopDTO dto = null;
	DesktopDAO dao = DAOFactory.getDesktopDAO();
	try
	{
		Desktop desktop = null;
		desktop = dao.getByDid(did);
		boolean mustSave = false;
		if((desktopDTO.getName() != null) && (!desktopDTO.getName().equals(desktop.getName())))
		{
			desktop.setName(desktopDTO.getName());
			mustSave = true;
		}
		if(!(new Boolean(desktopDTO.isVisible())).equals(new Boolean(desktop.isVisible())))
		{
			desktop.setVisible(desktopDTO.isVisible());
			mustSave = true;
		}
		if(mustSave)
		{
			dao.update(desktop);
		}
		setICMUrls(desktop);
		dto = DesktopAssembler.createDesktopDTO(desktop);
	}
	catch(DataException e)
	{
		PortalError error = new PortalError(ErrorCode.SYSTEM_ERROR);
		throw new ServiceException(error, e);
	}
	return dto;
}

public DesktopDTO updateExpiryDate(String did, DesktopDTO desktopDTO) throws ServiceException
{
	DesktopDTO dto = null;
	DesktopDAO dao = DAOFactory.getDesktopDAO();
	try
	{
		Desktop desktop = null;
		desktop = dao.getByDid(did);
		desktop.setExpiryDate(desktopDTO.getExpiryDate());
		dao.update(desktop);
		setICMUrls(desktop);
		dto = DesktopAssembler.createDesktopDTO(desktop);
	}
	catch(DataException e)
	{
		PortalError error = new PortalError(ErrorCode.SYSTEM_ERROR);
		throw new ServiceException(error, e);
	}
	return dto;
}

/**
 * generates a unique did
 *
 * @param size - number of character for the did.
 * @return a unique did.
 * @throws ServiceException
 */
public String generateUniqueDid(int size) throws ServiceException
{
	boolean found = false;
	String did = null;
	DesktopDAO desktopDAO = DAOFactory.getDesktopDAO();
	int nbTries = 0;
	while(!found)
	{
		did = IdGenerator.getId();
		nbTries++;
		while(did.length() < size)
		{
			did += IdGenerator.getId();
		}
		did = did.substring(0, size);
		try
		{
			if(desktopDAO.getByDid(did) == null)
			{
				found = true;
			}
		}
		catch(DataException e)
		{
			PortalError error = new PortalError(ErrorCode.SYSTEM_ERROR);
			throw new ServiceException(error, e);
		}
	}
	m_log.debug("Number of tried to find a unique did: " + nbTries);
	return did;
}

public void removeDesktop(String did) throws ServiceException
{

	RCUsageDAO rcusageDAO = DAOFactory.getRCUsageDAO();
	DesktopDAO desktopDAO = DAOFactory.getDesktopDAO();
	LicenseManager licenseManager = DelegateFactory.getLicenseManager();
	ResellerDAO resellerDAO = DAOFactory.getResellerDAO();
	Reseller reseller = null;
	try
	{
		reseller = resellerDAO.getByCode("laplink");
		// before we delete the desktop we need to ensure that
		// any licenses associated with it are removed.
		RCUsage rcusage = rcusageDAO.getByDid(did);
		if(rcusage != null)
		{
			String lsn = rcusage.getLicenseSerialNumber();
			if(lsn != null && lsn.length() > 0)
			{
				// remove the license
				/*try
				{
					List freedUpLicenses = licenseManager.rcunlicense(did, reseller, true, null);
				}
				catch(ServiceException seignored)
				{
				}*/
			}
		}
	}
	catch(DataException deignored1)
	{
	}

	try
	{
		// before we delete the desktop we need to ensure that
		// any licenses associated with it are removed.
		Desktop desktop = desktopDAO.getByDid(did);
		if(desktop != null)
		{
			String lsn = desktop.getLicenseSerialNumber();
			if(lsn != null && lsn.length() > 0)
			{
				// Unlicense the desktop
				try
				{
					List freedUpLicenses = licenseManager.unlicense(desktop.getId(), reseller, true, null);
				}
				catch(ServiceException seignored)
				{
				}
			}
            if (!desktop.isDemo()) {
                rcusageDAO.delete(did);
            }
        }
	}
	catch(DataException deignored2)
	{
	}

	try
	{
		// delete did out of both rcusage and desktop tables

		desktopDAO.delete(did);
        
    }
	catch(DataException de)
	{
		PortalError error = new PortalError(ErrorCode.SYSTEM_ERROR);
		throw new ServiceException(error, de);
	}
}

/**
 * Sets the default desktop for the user.
 *
 * @param username - the owner name
 * @param did	 - teh desktop's did.
 * @throws ServiceException
 */
public void setDefaultDesktop(String username,String did) throws ServiceException
{
    AccountDAO accountDAO = DAOFactory.getAccountDAO();
    
    Account account;
    try {
        account = accountDAO.getByUsername(username);        
        Desktop desktop = account.getDesktop(did);
        account.setDefaultDesktop(desktop);
        accountDAO.update(account);
        
    } catch(DataException e) {
		PortalError error = new PortalError(ErrorCode.SYSTEM_ERROR);
		throw new ServiceException(error, e);
	}
}


/**
 * Sets the software for a desktop.
 *
 * @param did	 - teh desktop's did.
 * @param version - the software version
 * @throws ServiceException
 */
public void setSoftwareVersion(String did, String version) throws ServiceException
{
	DesktopDAO desktopDAO = DAOFactory.getDesktopDAO();
	Desktop desktop;
	try
	{
		desktop = desktopDAO.getByDid(did);
		if(desktop == null)
		{
			PortalError error = new PortalError(ErrorCode.UNKNOWN_DID_DEFINED);
			throw new ServiceException(error);
		}
		desktop.setSoftwareVersion(version);
		desktopDAO.update(desktop);
	}
	catch(DataException e)
	{
		PortalError error = new PortalError(ErrorCode.SYSTEM_ERROR);
		throw new ServiceException(error, e);
	}
}
// ------------------------------------------------------------- Test methods


private static void testResetForeignAccess() throws Exception
{
	String did = "123";
	String owner = "titi";
	//String[] usernames = {"vtang","c4test"};
	String[] usernames = {"vtang"};
	List userList = new ArrayList();

	for(int i = 0; i < usernames.length; i++)
	{
		userList.add(usernames[i]);
	}
	// Get the desktop
	DesktopDAO desktopDAO = DAOFactory.getDesktopDAO();
	Desktop desktop;


	System.out.println("Foreign access before resetting:");
	desktop = desktopDAO.getByDidWithForeignAccounts(did);
	for(Iterator it = desktop.getForeignAccounts().iterator(); it.hasNext();)
	{
		System.out.println(it.next());
	}

	DesktopManagerImpl mgr = new DesktopManagerImpl();
	mgr.resetForeignAccounts(did, owner, userList);

	System.out.println("Foreign access after resetting:");
	desktop = desktopDAO.getByDidWithForeignAccounts(did);
	for(Iterator it = desktop.getForeignAccounts().iterator(); it.hasNext();)
	{
		System.out.println(it.next());
	}
}

public static void main(String[] args)
{
	try
	{
		testResetForeignAccess();
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
}
}


