package com.infowave.c4p.model.facade;

import com.infowave.c4p.service.delegate.LicenseManager;
import com.infowave.c4p.service.ServiceException;
import com.infowave.c4p.service.dto.LicenseDTO;
import com.infowave.c4p.datasource.dao.*;
import com.infowave.c4p.datasource.DataException;
import com.infowave.c4p.model.Desktop;
import com.infowave.c4p.model.License;
import com.infowave.c4p.model.Account;
import com.infowave.c4p.model.Reseller;
import com.infowave.c4p.model.RCUsage;
import com.infowave.c4p.model.assembler.LicenseAssembler;
import com.infowave.c4p.util.ErrorCode;
import com.infowave.c4p.util.PortalError;
import com.infowave.c4p.web.util.LLOnyx;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;
import java.util.Iterator;
import java.util.Date;
import java.util.ArrayList;

/**
 * This class contains the busine3ss logic for license-related operations.
 *
 * Created by: Vincent Aumont
 * Copyright 2003 Infowave Software, Inc.
 */
public class LicenseManagerImpl implements LicenseManager
{
  private static final long NB_MILLISECONDS_IN_A_DAY = (long)24*60*60*1000;
  private static LLOnyx onyx = new LLOnyx();


  /** The class's logger */
  private static Log m_log = LogFactory.getLog(LicenseManagerImpl.class);

  /**
   * Determine whether a license code corresponds to a fixed term license.
   * we look at teh first license we find for that code, assuming that all
   * licenses with teh saem code have the same type.
   * @param licenseCode
   * @param resellerId
   * @return
   * @throws ServiceException
   */
  public boolean isFixedTerm(String licenseCode, String resellerId)
  throws ServiceException
  {
   boolean isFixedTerm = false;;
   LicenseDAO licenseDAO = DAOFactory.getLicenseDAO();
    try {
      List licenses =  licenseDAO.getByCode(licenseCode,resellerId);
      if (!licenses.isEmpty()) {
        isFixedTerm = ((License)licenses.get(0)).isFixedTerm();
      }
    }
    catch(Exception e){
      PortalError error = new PortalError(ErrorCode.SYSTEM_ERROR);
      throw new ServiceException(error,e);
    }
    return isFixedTerm;
  }

  /**
   * Sets a new license for a desktop.
   * @param desktopId
   * @param licenseCode
   * @param extend
   * @return the list of licenses that have been freed up
   * @throws ServiceException
   */
  public List setLicenseCode(String userId, String desktopId,
                             String licenseCode,
                             boolean extend)
          throws ServiceException
  {
    DesktopDAO desktopDAO = DAOFactory.getDesktopDAO();
    AccountDAO accountDAO = DAOFactory.getAccountDAO();

    List expiredLicenses = null;
    List licensesMadeAvailable = null;
    boolean mustSaveAccount = false;
    StringBuffer logMsg = new StringBuffer();

    try{
      // Find the account, the desktop, and the reseller
      // Make sure the user owns this desktop.
      Desktop desktop;
      Account account;
      Reseller reseller;

      account = accountDAO.getById(userId);
      desktop = desktopDAO.getByIdWithLicense(desktopId);

      if (account == null) {
        m_log.warn("setLicenseCode - no account for userId: " + userId);
        PortalError error = new PortalError(ErrorCode.INVALID_ID);
        throw new ServiceException(error,userId);
      }
      if (desktop == null) {
        m_log.warn("setLicenseCode - no dektop for id: " + desktopId);
        PortalError error = new PortalError(ErrorCode.INVALID_ID);
        throw new ServiceException(error,desktopId);
      }
      if (!desktop.getAccount().getId().equals(userId)) {
        m_log.warn("setLicenseCode - the userId (" + userId + ") is not the" +
                   " owner of this desktop (" + desktopId + ")");
        PortalError error = new PortalError(ErrorCode.INVALID_PARAMETERS,
                                            userId,desktopId);
        throw new ServiceException(error);
      }

      // Get the reseller.
      reseller = account.getReseller();

      // Get the existing license for that desktop
      License lastLicense = desktop.getLicense();

      // Find an available license
      License license = null;
      license = findLicense(licenseCode,desktop,account,reseller);

      // Stop here if we could not find a license
      if (license == null){
        PortalError error = new PortalError(ErrorCode.INVALID_LICENSE_NUMBER,
                                            licenseCode);
        throw new ServiceException(error);
      }
      m_log.debug("Found license: " + license);

      // Check that the input parameters: we can request an extension only if
      // we switch from a fixed term to a fixed term
      if (extend && ((lastLicense == null) || !lastLicense.isFixedTerm() ||
              !license.isFixedTerm())) {
        m_log.error("can only extend when switching from fixed term to fixed term");
        PortalError error = new PortalError(ErrorCode.INVALID_PARAMETERS,
                                            licenseCode);
        throw new ServiceException(error);

      }
      /* The rules for the regcodes have changed!
      // The rules for regcodes are:
      //
      // 1) We don't allow users to use more than one regcode. So, if the new
      // license is a regcode have they already have used a different regcode,
      // we deny reject the license.
      // 2) a user cannot use a regcode more than once for the same desktop.

      if (license.isRegCode()) {
        // Make sure that the user is not trying to use a second regcode
        if ((account.getRegCode() != null) &&
                (!license.getCode().equals(account.getRegCode()))) {
          m_log.debug("setLicense: the license: " + license.getCode() +
                      " is a regcode and the user has already used a regcode:" +
                      account.getRegCode());
          PortalError error = new PortalError(ErrorCode.INVALID_LICENSE_NUMBER,
                                              licenseCode);
          throw new ServiceException(error);
        }
        // Make sure that the user is not trying to use a regcode twice for
        // the same desktop
        if ((desktop.getRegistrationCode() != null)){
          m_log.debug("setLicense: the license: " + license.getCode() +
                      " is a regcode and the user has already used the regcode:" +
                      desktop.getRegistrationCode() + " for desktop: " +
                      desktop.getDid());
          PortalError error = new PortalError(ErrorCode.INVALID_LICENSE_NUMBER,
                                              licenseCode);
          throw new ServiceException(error);

        }
      }
      */
      // At this point, we're guaranteed that we have a valid license, that has
      // not expired, it not used by another user, and has never been used for
      // this desktop.
      logMsg.append("User: ").append(account.getUsername()).append(" [");
      logMsg.append(reseller.getName()).append("] desktop: ");
      logMsg.append(desktop.getDid());


      if (extend) {
        // We know we have a fixed term license.
        // The license's first use is set to the desktop's old expiry date,
        // and the desktop's expiry date is extended by the number of days
        // left on the license.
        // But first we must make sure that we have not reached the maximum
        // number of licenses in the history.
        if (desktop.licenseHistoryIsFull()){
          expiredLicenses = purgeExpiredLicenseFromHistory(desktop,account);
        }
        if (desktop.licenseHistoryIsFull()){
          // We've tried to purge the history but it is still full. We cannot
          // add a license.
          m_log.error("The license history for desktop " + desktop.getDid() +
                      " is full -  we cannot extend it with license: " +
                      license.getCode());
          PortalError error = new PortalError(ErrorCode.TOO_MANY_LICENSES);
          throw new ServiceException(error);
        }
        Date oldDesktopExpiryDate = desktop.getExpiryDate();
		if( !license.isRegCode() ) {
          license.setFirstUseDate(oldDesktopExpiryDate);
		}
        long extension = license.getRemainingDays() * NB_MILLISECONDS_IN_A_DAY;
        long expiryTime = oldDesktopExpiryDate.getTime() + extension;
        desktop.setExpiryDate(new Date(expiryTime));
        logMsg.append(" - Extending desktop with license " + license.getCode() +
                      " remaining days on the new license: " +license.getRemainingDays() +
                      " the old desktop expiry date was: " + oldDesktopExpiryDate +
                      " - the new expiry date is: " + desktop.getExpiryDate());
      } else {
        // We are transfering the license.
        // We must start by unlicensing the desktop
        licensesMadeAvailable = removeAllLicenses(desktop,lastLicense,account,reseller,true,null);
       if (license.isFixedTerm()) {
         // The license starts when the desktop was set to expire (i.e. after
         // the end ofthe demo period) or now if it has already expired.
		 if( !license.isRegCode() ) {

           if (desktop.hasExpired()){
             Date now = new Date();
             license.setFirstUseDate(now);
           } else {
             license.setFirstUseDate(desktop.getExpiryDate());
           }
		 }

          // the desktop expires when the license expires.
         desktop.setExpiryDate(license.getExpiryDate());
         logMsg.append(" - Transfered fixed license: " + license.getCode() +
                     " to desktop: " + desktop.getDid() +
                     " desktop's expiry date: " + desktop.getExpiryDate());
       } else {
         // It's a subscription: the desktop never expires, we license's first
         // use date is now
         desktop.setExpiryDate(null);
         license.setFirstUseDate(new Date());
         logMsg.append(" - Transferred subscription: " + license.getCode() +
                       " to desktop: " + desktop.getDid() +
                       " desktop's expiry date: " + desktop.getExpiryDate());
       }
      }

      // Increment the license's num of uses
      license.incrementNumUse();

      // We store the license code in the desktop table for performance reasons
      desktop.setLicenseSerialNumber(license.getCode());

      // Attach this license to this account (if not already attached).
      if (license.getAccount() == null){
        license.setAccount(account);
      }

      // Attach this license to this desktop
      desktop.setLicense(license);
       desktop.setLicenseSerialNumber(license.getCode());
      desktop.addLicenseToHistory(license);

      // If this license is a regcode, we record this information
      if (license.isRegCode()) {
        account.setRegCode(license.getCode());
        desktop.setRegistrationCode(license.getCode());
        mustSaveAccount = true;
      }

      if ((licensesMadeAvailable != null) && !licensesMadeAvailable.isEmpty()){
        logMsg.append(" - the following licenses were freed up:");
        for(Iterator it = licensesMadeAvailable.iterator();it.hasNext();){
          License l = (License) it.next();
          logMsg.append(l.getCode()).append("(numUses=").append(l.getNumUses());
          logMsg.append("),");
        }
      } else{
        logMsg.append(" - no licenses were freed up");
      }
      // Now we can save the objects
      LicenseDAO licenseDAO = DAOFactory.getLicenseDAO();
      Account accountToSave = mustSaveAccount? account : null;
      licenseDAO.licenseDesktop(license,desktop,
                                licensesMadeAvailable,expiredLicenses,
                                accountToSave);

      // log what was done
      m_log.info(logMsg.toString());

	  // If the onyx update fails, allow the user to continue
	  try {
		// update Onyx
		onyx.UpdateLicenseInfo( account, licenseCode );
	  } catch( Exception ignored ) {
			m_log.error("Unable to update ONYX for " +account+ ", licenseCode = " +licenseCode );
	  }

      // Returns the list of licenses that have been freed up
      List freedUp = new ArrayList(3);
      if (licensesMadeAvailable != null){
        for(Iterator it = licensesMadeAvailable.iterator();it.hasNext();){
          License l = (License) it.next();
          LicenseDTO dto = LicenseAssembler.createLicenseDTO(l);
          freedUp.add(dto);
        }
      }
      return freedUp;
    }
    catch(DataException e) {
      PortalError error = new PortalError(ErrorCode.SYSTEM_ERROR);
      throw new ServiceException(error,e); 
    }
  }


  /**
   * remove the desktop's license if it exsits.
   * @param desktopId
   * @throws ServiceException
   */
  public List unlicense(String desktopId, boolean resetExpiry, Date newExpiryDate)
  throws ServiceException
  {
    DesktopDAO desktopDAO = DAOFactory.getDesktopDAO();
    LicenseDAO licenseDAO = DAOFactory.getLicenseDAO();
    List removedLicenses = null;
    try {
      // Find the desktop
      Desktop desktop;
      desktop = desktopDAO.getByIdWithLicense(desktopId);
      if (desktop == null) {
        m_log.warn("unlicense - no dektop with id: " + desktopId);
        PortalError error = new PortalError(ErrorCode.INVALID_ID);
        throw new ServiceException(error,desktopId);
      }
      removedLicenses = removeAllLicenses(desktop,desktop.getLicense(),
                                          desktop.getAccount(), desktop.getAccount().getReseller(), 
				  resetExpiry, newExpiryDate);
      licenseDAO.unlicenseDesktop(removedLicenses,desktop);
    }
      catch(DataException e) {
      PortalError error = new PortalError(ErrorCode.SYSTEM_ERROR);
      throw new ServiceException(error,e);
    }
    // Returns the list of licenses that have been freed up
    List freedUp = new ArrayList(3);
    if (removedLicenses != null){
      for(Iterator it = removedLicenses.iterator();it.hasNext();){
        License l = (License) it.next();
        LicenseDTO dto = LicenseAssembler.createLicenseDTO(l);
        freedUp.add(dto);
      }
    }
    return freedUp;
  }

  public List rcunlicense(String did, Reseller reseller, boolean resetExpiry, Date newExpiryDate)
  throws ServiceException
  {
    RCUsageDAO rcusageDAO = DAOFactory.getRCUsageDAO();
    LicenseDAO licenseDAO = DAOFactory.getLicenseDAO();
    List removedLicenses = null;
    try {
      // Find the rcusage
      RCUsage  rcusage;
      rcusage= rcusageDAO.getByDid(did);
      if (rcusage== null) {
        m_log.warn("unlicense - no rcusage with id: " + did);
        PortalError error = new PortalError(ErrorCode.INVALID_ID);
        throw new ServiceException(error,did);
      }
      removedLicenses = removeAllRCLicenses(rcusage.getLicenseSerialNumber(), did, reseller);
      if( removedLicenses != null ) {
         License license = (License)removedLicenses.get(0);
         if( license != null ) {
	// update the account id and # days left on this license
           license.setRcusage( null );
           license.setDuration( getDaysToRCExpiry(rcusage) );
         }
      }
      // update the rcusage info
      rcusage.setLicenseSerialNumber(null);
      rcusage.setDefaultEntry();
	  if( resetExpiry ) {
          	rcusage.setExpiryDate(newExpiryDate);
	  } else {
		  // If a cancellation, we're supposed to allow the
		  // subscription to run out
          rcusage.setWindowLimit( 0x7fffffff );
          rcusage.setWindowExpiryDate(rcusage.getExpiryDate());
	  }
      licenseDAO.unlicenseRC(removedLicenses,rcusage);
    }
      catch(DataException e) {
      PortalError error = new PortalError(ErrorCode.SYSTEM_ERROR);
      throw new ServiceException(error,e);
    }
    // Returns the list of licenses that have been freed up
    List freedUp = new ArrayList(3);
    if (removedLicenses != null){
      for(Iterator it = removedLicenses.iterator();it.hasNext();){
        License l = (License) it.next();
        LicenseDTO dto = LicenseAssembler.createLicenseDTO(l);
        freedUp.add(dto);
      }
    }
    return freedUp;
  }

  public int getDaysToRCExpiry( RCUsage rcusage )
  {
    int daysToExpiry;
    Date rcexpiryDate = rcusage.getExpiryDate();

    if (rcexpiryDate == null ) {
      // this should never happen as a desktop with no licenses is a demo and
      // shoud have an expiry date.
      if (rcusage.getLicenseSerialNumber() == null) {
        daysToExpiry = -1;
      } else {
        // the desktop does not expire
        daysToExpiry = 0;
      }
    } else {
      long now = (new Date()).getTime();
      long expiryTime = rcexpiryDate.getTime();
      daysToExpiry = (int)((expiryTime - now) /(1000*3600*24));
      if (daysToExpiry == 0) {
        if (now < expiryTime) {
          daysToExpiry = 1;
        } else {
        daysToExpiry = -1;
        }
      } else {
        // we had one day so that we're sure we count partial days. E.g. if the
        // desktop expired on Wednesday and we check on tuesday at 1:00PM, we
        // still consider we have 2 days before it expires.
        daysToExpiry++;
      }
    }
    return daysToExpiry;
  }


  /**
   * Removes all expired licenses from the history
   * @param desktop
   * @return the list the licenses that have been removed.
   */
  public List purgeExpiredLicenseFromHistory(Desktop desktop,Account account)
          throws DataException
  {

    List removed = new ArrayList(2);
    LicenseDAO licenseDAO = DAOFactory.getLicenseDAO();
    String[] history = desktop.getLicenseCodesInHistory();
    for (int i=0;i<history.length;i++){
      License license;
      List licenses;
      licenses = licenseDAO.getByCodeAndUserId(history[i],
                                               account.getReseller().getId(),
                                               account.getId());
      if (licenses.size() == 0) {
        String msg;
        msg = "purgeExpiredLicenseFromHistory - data inconsistency: a license is present in " +
                " the history but we can't find a license with that code for that user"+
                " desktop: " + desktop.getDid() +
                " code: " + history[i] + " user: " + account.getUsername() +
                " (" + account.getId() + ") reseller: " +
                account.getReseller().getId();
        m_log.error(msg);
        throw new DataException(msg);
      }
      if (licenses.size() > 1) {
        String msg;
        msg = "purgeExpiredLicenseFromHistory - data inconsistency: a license is present in " +
                " the history and we have " + licenses.size() + " licenses with " +
                " that code for that user" +
                " desktop: " + desktop.getDid() +
                " code: " + history[i] + " user: " + account.getUsername() +
                " (" + account.getId() + ") reseller: " +
                account.getReseller().getId();
        m_log.error(msg);
        throw new DataException(msg);
      }
      license = (License) licenses.get(0);
      if (license.hasExpired()){
        m_log.debug("purgeExpiredLicenseFromHistory - purging: " + license);
        desktop.removeLicenseFromHistory(license);
        removed.add(license);
      }
    }
      return removed;
    }
  /**
   * Removes the license this desktop holds (the current license and the ones in
   * teh history), and return the list of all released licenses that are still
   * valid (i.e. not expired).
   * @param desktop
   * @return
   */
  private List removeAllLicenses(Desktop desktop, License lastLicense,
                                 Account account, Reseller reseller, 
			   boolean resetExpiry, Date newExpiryDate)
          throws DataException
  {
    List removed = new ArrayList(2);
    LicenseDAO licenseDAO = DAOFactory.getLicenseDAO();
    String[] history = desktop.getLicenseCodesInHistory();
    for (int i=0;i<history.length;i++){
      License license;

      // if the license corresponds to the license we were passed, no need to
      // retrieve it from the database
      if ((lastLicense != null) && lastLicense.getCode().equalsIgnoreCase(history[i])){
        license = lastLicense;
      } else {
        List licenses;

        licenses = licenseDAO.getByCodeAndUserId(history[i],
                                                 account.getReseller().getId(),
                                                 account.getId());
        if (licenses.size() == 0) {
          String msg;
          msg = "removeAllLicenses - data inconsistency: a license is present in " +
                  " the history but we can't find a license with that code for that user"+
                  " desktop: " + desktop.getDid() +
                  " code: " + history[i] + " user: " + account.getUsername() +
                  " (" + account.getId() + ") reseller: " +
                  account.getReseller().getId();
          m_log.error(msg);
          throw new DataException(msg);
        }
        if (licenses.size() > 1) {
          String msg;
          msg = "removeAllLicenses - data inconsistency: a license is present in " +
                  " the history and we have " + licenses.size() + " licenses with " +
                  " that code for that user" +
                  " desktop: " + desktop.getDid() +
                  " code: " + history[i] + " user: " + account.getUsername() +
                  " (" + account.getId() + ") reseller: " +
                  account.getReseller().getId();
          m_log.error(msg);
          throw new DataException(msg);
        }
        license = (License) licenses.get(0);
      }
      m_log.debug("removeAllLicenses - found license " + license);
      // If the license has not expired, we decrement the number of uses and - if
      // the number of uses reaches 0- reset the license's duration
      if (!license.hasExpired()) {
        removed.add(license);
        license.decrementNumUse();
        m_log.debug("removeAllLicenses - decreasing numUses for license: " +
                    license.getCode());
        if ((license.getNumUses() == 0) && (license.isFixedTerm())) {
          int remainingDays = license.getRemainingDays();
          m_log.debug("removeAllLicenses - resetting the duration for license: " +
                      license.getCode() + " remaining days: " + remainingDays);
          license.setDuration(remainingDays);
		  if( !license.isRegCode() ) {
            license.setFirstUseDate(null);
		  }
        }
      }
    }
    // Clear the history
    desktop.setLicenseHistory(null);

    // We've removed all the licenses, therefore we're back into demo mode.
    desktop.setLicense(null);
    desktop.setLicenseSerialNumber(null);
	desktop.setRegistrationCode(null);
	if( resetExpiry ) {
      		desktop.setExpiryDate(null);
      		Date creationDate = desktop.getCreationDate();
		if( newExpiryDate == null ) {
      			desktop.setExpiryDate(reseller.getDemoExpiryDate(creationDate));
		} else {
      			desktop.setExpiryDate(newExpiryDate);
		}
	}
    m_log.debug("removeAllLicenses - After removing all licenses, "  +
                " the expiry date for desktop " + desktop.getDid() + " is now: "+
                desktop.getExpiryDate());
    return removed;
  }

  private List removeAllRCLicenses(String code, String did, Reseller reseller)
          throws DataException
  {
    List removed = new ArrayList(2);
    LicenseDAO licenseDAO = DAOFactory.getLicenseDAO();
    RCUsageDAO rcusageDAO = DAOFactory.getRCUsageDAO();
    List licenses = null;

    RCUsage rcusage = rcusageDAO.getByCodeAndDid(code,did);
    if( rcusage == null ) {
            // no rc entries with this license code
	return licenses;
    }

    // update the entry in the rcusage table
    try {
	rcusage.setExpiryDate(null);
	rcusage.setLicenseSerialNumber(null);
	rcusage.setDefaultEntry();
    	rcusageDAO.update(rcusage);
   } catch( ServiceException se ) {
          String msg;
          msg = "Cannot update rcusage table for  "+
                  " code: " + code +" reseller: " +
                  reseller.getId();
          m_log.error(msg);
          throw new DataException(msg);
   }

    licenses = licenseDAO.getByCodeRC(code, reseller.getId());
    if (licenses.size() == 0) {
          String msg;
          msg = "removeAllLicenses - data inconsistency: a license is present in " +
                  " the history but we can't find a license with that code for "+
                  " code: " + code +" reseller: " +
                  reseller.getId();
          m_log.error(msg);
          throw new DataException(msg);
    }

    License license = (License) licenses.get(0);

   if (!license.hasExpired()) {
         removed.add(license);
         license.decrementNumUse();
         m_log.debug("removeAllLicenses - decreasing numUses for license: " +
                    license.getCode());
         if ( license.getNumUses() == 0 ) {
           int remainingDays = license.getRemainingDays();
           m_log.debug("removeAllLicenses - resetting the duration for license: " +
                      license.getCode() + " remaining days: " + remainingDays);
           license.setDuration(remainingDays);
		   if( !license.isRegCode() ) {
             license.setFirstUseDate(null);
		   }
        }
   }

    return removed;
  }

  // ------------------------------------------------------------ Helper methods
  /**
   * Find a license for a user, given a license code.
   * @param code
   * @param account
   * @param reseller
   * @return the license if we found one; null if the license code is invalid,
   * or if teh user has used up all the device slots for this license, or if
   * all the licenses with that code have been taken.
   */
  public License findLicense(String code, Desktop desktop,
                             Account account,Reseller reseller)
          throws ServiceException
  {
    License license = null;
    LicenseDAO licenseDAO = DAOFactory.getLicenseDAO();

    try {
      // If the desktop has already used that license, if can't be reused
      if (desktop.hasUsedLicenseCode(code)) {
        m_log.debug("Cannot use - license " + code +  " has already been used by desktop " + desktop.getDid() );
        return null;
      }
      // See whether the user is trying to reuse a license:
      // In all the licenses with that code and owned by this user, see if there
      // is one that has not been maxed out.
      // We also make sure the the user has not used that license for that desktop
      // in the past and that the license has not expired
      List usedLicenses = licenseDAO.getByCodeAndUserId(code,reseller.getId(),
                                                        account.getId());
      for (Iterator it = usedLicenses.iterator();it.hasNext();){
        License l = (License)it.next();
        if ((l.getNumUses() < l.getMaxUses()) &&
                !desktop.hasUsedLicenseCode(code) &&
                !l.hasExpired()) {
          license = l;
          break;
        } else {
          // for debugging puropses
          if (l.getNumUses() >= l.getMaxUses()){
            m_log.debug("Cannot use - license " + l.getCode() + "[" + l.getId() + "] has exceeded the nb of uses (" + l.getNumUses()  + " uses - max = " + l.getMaxUses() + ")");
          } else if (desktop.hasUsedLicenseCode(code)) {
            m_log.debug("Cannot use - license " + l.getCode() + "[" + l.getId() + "] has already been used by desktop " + desktop.getDid() );
          } else if (l.hasExpired()){
            m_log.debug("Cannot use - license " + l.getCode() + "[" + l.getId() + "] has expired " + l);
          }
        }
      }
      if (license != null) {
        m_log.debug("Found a previously used license with "+ code +  " code " +
                    " for user " + account.getUsername());
      } else {
        m_log.debug("Found no previously used license with "+ code +  " code " +
                    " for user " + account.getUsername());
      }
      // If we don't have a license yet, we search for an unused license with that
      // code
      if (license == null) {
        List unusedLicenses = licenseDAO.getUnusedByCode(code,reseller.getId());

        if (!unusedLicenses.isEmpty()) {
          license = (License) unusedLicenses.get(0);
        }
        if (license != null) {
          m_log.debug("Found an unused license with "+ code +  " code " +
                      " for user " + account.getUsername());
        } else {
          m_log.debug("Found no unused license with "+ code +  " code " +
                      " for user " + account.getUsername());
        }
      }
	  /* No sanity for us!
      // Sanity check: if the license is a fixed term, its numUses should be
      // 0 and its maxUses should be one because - by covention- fixed term
      // licensese can't be reused.
      if ((license != null) && license.isFixedTerm() &&
              !license.isRegCode() && (license.getMaxUses() != 1) ){
        m_log.error("findLicense - The license we found is a fixed term, yet " +
                    " its maxuses is > 1: " + license);
        license = null;
      }
	  */
      return license;
    }
    catch(DataException e){
      PortalError error = new PortalError(ErrorCode.SYSTEM_ERROR);
      throw new ServiceException(error,e);
    }
  }


  /**
   * Find a license for a user, given a license code.
   * @param code
   * @param reseller
   * @return the license if we found one; null if the license code is invalid
   */
  public License findLicenseRC(String code, Reseller reseller)
          throws ServiceException
  {
    License license = null;
    LicenseDAO licenseDAO = DAOFactory.getLicenseDAO();

    try {
        List unusedLicenses = licenseDAO.getUnusedByCodeRC(code,reseller.getId());
        if (!unusedLicenses.isEmpty()) {
          license = (License) unusedLicenses.get(0);
        }
        if (license != null) {
          m_log.debug("Found an unused license with "+ code +  " code. ");
        } else {
          m_log.debug("Found no unused license with "+ code +  " code.");
        }
        return license;

    } catch(DataException e) {
      PortalError error = new PortalError(ErrorCode.SYSTEM_ERROR);
      throw new ServiceException(error,e);
    }
 
}

  // ------------------------------------------------------------ LLG
  /**
   * Find a license for a user, given a license code.
   * @param did  -- desktop id
   * @param sn -- first 11 digits of a 12 digit serial number
   * @return 200 = success (or already matched), 500 = failure
   */
    // return codes: 200 = success (or already matched), 400 = no licenses, 500 = failure
    public int setLLGLicense(String did, String sn)
        throws ServiceException
    {
	int resp = 200;

	try {
 		LicenseDAO licenseDAO = DAOFactory.getLicenseDAO();
        		DesktopDAO desktopDAO = DAOFactory.getDesktopDAO();
		AccountDAO accountDAO = DAOFactory.getAccountDAO();
		ResellerDAO resellerDAO = DAOFactory.getResellerDAO();

		// get infro from the reseller table
		Reseller reseller = resellerDAO.getByCode( "laplink" );

		// Get the info from the desktop table
		Desktop desktop = desktopDAO.getByDid(did);

		// Check if this did has already been licensed
		if( desktop.getLicenseSerialNumber() == null ) {

			// Get the info from the license table
			java.util.List licenseList = licenseDAO.getUnusedLLGByCode(sn, reseller.getId());

			if( !licenseList.isEmpty() ) {
				// Get the datestamp
				Date now = new Date();

				// Get the first unused license
				License license = (License)licenseList.get(0);

				// Get the info from the account table
				Account account =  desktop.getAccount();

				// Update info & save back to database
				account.setRegCode( license.getCode() );
				desktop.setLicenseSerialNumber( license.getCode() );
				desktop.setExpiryDate( now );
				desktop.setLicenseHistory( license.getCode() );
				license.incrementNumUse();
				if( !license.isRegCode() ) {
				  license.setFirstUseDate( now );
				}
				license.setAccount( account );

				accountDAO.update( account );

				desktopDAO.update( desktop );
				licenseDAO.update( license );

			} else {
				resp = 400;
			}
		}
	} catch( Exception e ) {
		m_log.debug("exception: " +e.toString() );
		resp = 500;
	}

	return resp;
    }

  public List setRCLicenseCode(String userId, String desktopId,
                             String licenseCode,
                             boolean extend)
          throws ServiceException
  {
    DesktopDAO desktopDAO = DAOFactory.getDesktopDAO();
    RCUsageDAO rcusageDAO = DAOFactory.getRCUsageDAO();
    AccountDAO accountDAO = DAOFactory.getAccountDAO();

    List expiredLicenses = null;
    List licensesMadeAvailable = null;
    boolean mustSaveAccount = false;
    StringBuffer logMsg = new StringBuffer();

    try{
      // Find the account, the desktop, and the reseller
      // Make sure the user owns this desktop.
      Desktop desktop;
      Account account;
      Reseller reseller;
      RCUsage rcusage;

      rcusage = rcusageDAO.getByDid(desktopId);
      if (rcusage == null) {
        rcusage = new RCUsage();
        rcusage.clear();
        rcusage.setDefaultEntry();
        rcusage.setDid(desktopId);
      }

       account = accountDAO.getById(userId);
      if (account == null) {
        m_log.warn("setLicenseCode - no account for userId: " + userId);
        PortalError error = new PortalError(ErrorCode.INVALID_ID);
        throw new ServiceException(error,userId);
      }

      desktop = desktopDAO.getByDid(desktopId);

      if (desktop == null) {
        m_log.warn("setLicenseCode - no desktop for id: " + desktopId);
        PortalError error = new PortalError(ErrorCode.INVALID_ID);
        throw new ServiceException(error,desktopId);
      }
      if (!desktop.getAccount().getId().equals(userId)) {
        m_log.warn("setLicenseCode - the userId (" + userId + ") is not the" +
                   " owner of this desktop (" + desktopId + ")");
        PortalError error = new PortalError(ErrorCode.INVALID_PARAMETERS,
                                            userId,desktopId);
        throw new ServiceException(error);
      }

      // Get the reseller.
      reseller = account.getReseller();

      // Get the existing license for that desktop
      String licenseSerialNumber = rcusage.getLicenseSerialNumber();  // aka licenseCode

     License currentLicense = null;
     if( licenseSerialNumber != null && !licenseSerialNumber.equals("") ) {
          // Find out what we can about this current license
          currentLicense = findLicenseRC(licenseSerialNumber, reseller);
      }

      // Check the new license code info
      License license = null;
      license = findLicenseRC(licenseCode,reseller);

      // Stop here if we could not find a license
      if (license == null){
        PortalError error = new PortalError(ErrorCode.INVALID_RC_LICENSE_NUMBER,
                                            licenseCode);
        throw new ServiceException(error);
      }
      m_log.debug("Found license: " + license);

      // At this point, we're guaranteed that we have a valid license, that has
      // not expired, it not used by another user, and has never been used for
      // this desktop.
      logMsg.append("User: ").append(account.getUsername()).append(" [");
      logMsg.append(reseller.getName()).append("] desktop: ");
      logMsg.append(rcusage.getDid());


      if (extend) {
        // We know we have a fixed term license.
        // The license's first use is set to the RC's old expiry date,
        // and the RC's expiry date is extended by the number of days
        // left on the license.

        Date oldRcExpiryDate = rcusage.getExpiryDate();
		if( !license.isRegCode() ) {
          license.setFirstUseDate(oldRcExpiryDate );
		}
        long extension = license.getRemainingDays() * NB_MILLISECONDS_IN_A_DAY;
        long expiryTime = oldRcExpiryDate.getTime() + extension;
        rcusage.setExpiryDate(new Date(expiryTime));

        logMsg.append(" - Extending desktop with license " + license.getCode() +
                      " remaining days on the new license: " +license.getRemainingDays() +
                      " the old rc expiry date was: " + oldRcExpiryDate +
                      " - the new expiry date is: " + rcusage.getExpiryDate());
      } else {
        // We are transfering the license.
        // We must start by unlicensing the rc
        licensesMadeAvailable = removeAllRCLicenses(licenseCode,desktopId,reseller);
         // The license starts when the desktop was set to expire
		 if( !license.isRegCode() ) {
           if (rcusage.isLicenseExpired()){
             Date now = new Date();
             license.setFirstUseDate(now);
           } else {
             license.setFirstUseDate(rcusage.getExpiryDate());
           }
		 }
          // the desktop expires when the license expires.
         rcusage.setExpiryDate(license.getExpiryDate());
         logMsg.append(" - Transferred fixed license: " + license.getCode() +
                     " to desktop: " + rcusage.getDid() +
                     " desktop's expiry date: " + rcusage.getExpiryDate());
      }

      // Increment the license's num of uses
      license.incrementNumUse();
	  if( !license.isRegCode() ) {
        license.setFirstUseDate( new Date() );
	  }
      license.setRcusage( rcusage );

      // We store the license code in the desktop table for performance reasons
      rcusage.setLicenseSerialNumber(license.getCode());
	  rcusage.setWindowLimit( 0x7fffffff );

     if ((licensesMadeAvailable != null) && !licensesMadeAvailable.isEmpty()){
        logMsg.append(" - the following licenses were freed up:");
        for(Iterator it = licensesMadeAvailable.iterator();it.hasNext();){
          License l = (License) it.next();
          logMsg.append(l.getCode()).append("(numUses=").append(l.getNumUses());
          logMsg.append("),");
        }
      } else{
        logMsg.append(" - no licenses were freed up");
      }
      // Now we can save the objects
      LicenseDAO licenseDAO = DAOFactory.getLicenseDAO();
      //Account accountToSave = mustSaveAccount? account : null;
      licenseDAO.licenseRC(license,rcusage,
                                licensesMadeAvailable);

      // log what was done
      m_log.info(logMsg.toString());

	  // If the onyx update fails, allow the user to continue
	  try {
		// update Onyx
        onyx.UpdateLicenseInfo( account, licenseCode );
	  } catch( Exception ignored ) {
			m_log.error("Unable to update ONYX for " +account+ ", rclicenseCode = " +licenseCode );
	  }

      // Returns the list of licenses that have been freed up
      List freedUp = new ArrayList(3);
      if (licensesMadeAvailable != null){
        for(Iterator it = licensesMadeAvailable.iterator();it.hasNext();){
          License l = (License) it.next();
          LicenseDTO dto = LicenseAssembler.createLicenseDTO(l);
          freedUp.add(dto);
        }
      }
      return freedUp;
    }
    catch(DataException e) {
      PortalError error = new PortalError(ErrorCode.SYSTEM_ERROR);
      throw new ServiceException(error,e); 
    }
  }



// -------------------------------------------------------------------------
  private static void  testFindLicense() throws Exception
  {
    LicenseDAO licenseDAO = DAOFactory.getLicenseDAO();
    DesktopDAO desktopDAO = DAOFactory.getDesktopDAO();
    ResellerDAO resellerDAO = DAOFactory.getResellerDAO();

    Reseller reseller = resellerDAO.getByCode("laplink");
    String did = "LTqceHV2";
    String code = "f2";
    Desktop desktop = desktopDAO.getByDid(did);
    Account account = desktop.getAccount();
    LicenseManagerImpl lmgr = new LicenseManagerImpl();

    License license = lmgr.findLicense(code,desktop,account,reseller);
    System.out.println(license);

  }

  public static void main(String[] args)
  {
    try {
      testFindLicense();
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }

  }

}
