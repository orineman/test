package com.infowave.c4p.admin;

import com.infowave.c4p.datasource.dao.LicenseDAO;
import com.infowave.c4p.datasource.dao.DAOFactory;
import com.infowave.c4p.datasource.dao.ResellerDAO;
import com.infowave.c4p.datasource.dao.AccountDAO;
import com.infowave.c4p.datasource.dao.RCUsageDAO;
import com.infowave.c4p.datasource.dao.DesktopDAO;
import com.infowave.c4p.datasource.DataException;
import com.infowave.c4p.model.Reseller;
import com.infowave.c4p.model.License;
import com.infowave.c4p.model.Account;
import com.infowave.c4p.model.Desktop;
import com.infowave.c4p.model.RCUsage;
import com.infowave.c4p.service.delegate.RCUsageManager;
import com.infowave.c4p.service.delegate.DesktopManager;
import com.infowave.c4p.service.delegate.LicenseManager;
import com.infowave.c4p.service.delegate.DelegateFactory;
import com.infowave.c4p.service.ServiceException;
import com.infowave.c4p.service.dto.LicenseDTO;
import com.infowave.c4p.service.dto.DesktopDTO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;

import org.apache.commons.cli.*;

/**
 * Created by IntelliJ IDEA.
 * User: vincent
 * Date: Jan 8, 2004
 * Time: 11:33:20 AM
 * To change this template use Options | File Templates.
 */
public class LicenseTerminator
{

  private static final String RESELLER_OPT = "reseller";
  private static final String REG_CODE_OPT = "regcode";
  private static final String LICENSE_OPT = "license";
  private static final String HELP_OPT = "help";
  private static final String CODE_OPT = "code";
  private static final String USERNAME_OPT = "username";
  private static final String ALL_OPT = "all";

  private static final int REGCODE        = 0;
  private static final int LICENSE        = 1;

  /** The class's logger */
  private static Log m_log = LogFactory.getLog(LicenseTerminator.class);

  private String m_code;
  private int m_licenseType;
  private Reseller m_reseller;
  private int m_licenseCodeSize;
  private String m_username;
  private boolean m_revokeAllRegCodes;
  private Account m_account;
  private static boolean fromCommandLine = false;
  private String m_results = "";
  private Date m_expiryDate = null;
  //private static SimpleDateFormat m_dateFormat = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss.SSS" );
  private static SimpleDateFormat m_dateFormat = new SimpleDateFormat( "MMM d yyyy hh:mmaaa" );

  public String getResults() { return m_results; }

  public boolean setLicenseCode( String code, String reseller, String dtExpires )
  {
	if( dtExpires != null )	m_expiryDate = m_dateFormat.parse( dtExpires.trim(), new ParsePosition(0) );
	return setLicenseCode( code, reseller );
  }

  public boolean setLicenseCode( String code, String reseller )
  {
      m_licenseType = LICENSE;
      m_licenseCodeSize = 0;
      m_code = code;

      // Find the reseller
     ResellerDAO resellerDAO = DAOFactory.getResellerDAO();
     try {
        m_reseller = resellerDAO.getByCode(reseller);
        if (m_reseller == null) {
      	m_results = "Invalid reseller: " + reseller;
      	return false;
        }
     } catch(DataException e) {
        m_results = "Invalid reseller: " + reseller;
        return false;
     }

      if (m_code.length() != getLicenseCodeSize()) {
          m_results = "The license code must have " + getLicenseCodeSize() + " characters";
          return false;
      }
      return true;
  }

  public boolean reviveLicense( ) throws DataException, ServiceException
  {
    int count = 0;
    List licenses;
    LicenseDAO licenseDAO = DAOFactory.getLicenseDAO();
    licenses = licenseDAO.getByCode(m_code,m_reseller.getId());
    // This request should return exactly one request
    if ((licenses == null) || (licenses.size() == 0)) {
       licenses = licenseDAO.getByCodeRC(m_code,m_reseller.getId());
       // This request should return exactly one request
       if ((licenses == null) || (licenses.size() == 0)) {
         if(fromCommandLine) System.out.println("Unknown license (or already revoked/cancelled) code: " + m_code);
         else m_results = "Unknown license (or already revoked/cancelled) code: " + m_code;
         return false;
      } else {
		 RCUsageManager rcusageManager = DelegateFactory.getRCUsageManager();
		 count = rcusageManager.countRCUsagesLikeLicense( m_code );	  }
    } else {
		DesktopManager desktopManager = DelegateFactory.getDesktopManager();
		count = desktopManager.getDesktopcountLikeLicense( m_code );	
	}
    if (licenses.size() > 1){
      if(fromCommandLine) System.out.println("Error: more than one license with code: " + m_code);
      else m_results = "Error: more than one license with code: " + m_code;
      return false;
    }
	// We have the license
    License license = (License) licenses.get(0);

	// Now see if this license is attached to a desktop
	if( count > 0 ) {
	  // we don't mess with a license that is currently assigned
      if(fromCommandLine) System.out.println("Error: license is actively assigned, code: " + m_code);
      else m_results = "Error: license is actively assigned, code: " + m_code;
      return false;
	}

	// Make sure this is a single instance license
	if( license.getMaxUses() != 1 ) {
      if(fromCommandLine) System.out.println("Error: license is not a single use license, code: " + m_code);
      else m_results = "Error: license is not a single use license, code: " + m_code;
      return false;
	}

	// Its okay to update the license
	license.setAccount( null );
	license.setRcusage( null );
	license.setNumUses( 0 );
	license.setFirstUseDate( null );
    licenseDAO.update(license);
	m_results = "License: " + m_code +" has been successfully reset.";
	return true;
  }

  public boolean renewLicense( ) throws DataException, ServiceException
  {
    List licenses;
    LicenseDAO licenseDAO = DAOFactory.getLicenseDAO();
    licenses = licenseDAO.getByCode(m_code,m_reseller.getId());
    // This request should return exactly one request
    if ((licenses == null) || (licenses.size() == 0)) {
       licenses = licenseDAO.getByCodeRC(m_code,m_reseller.getId());
       // This request should return exactly one request
       if ((licenses == null) || (licenses.size() == 0)) {
         if(fromCommandLine) System.out.println("Unknown license (or already revoked/cancelled) code: " + m_code);
         else m_results = "Unknown license (or already revoked/cancelled) code: " + m_code;
         return false;
      }
    }
    if (licenses.size() > 1){
      if(fromCommandLine) System.out.println("Error: more than one license with code: " + m_code);
      else m_results = "Error: more than one license with code: " + m_code;
      return false;
    }
	// We have the license
    License license = (License) licenses.get(0);

   // update the license firstusedate and duration to match current state of
   // user's account
   Date now = new Date();
   if( license.getFirstUseDate() != null ) {
	license.setFirstUseDate( now );
   }
   long expiryMSecs = m_expiryDate.getTime();
   long nowMSecs = now.getTime();
   long diffMSecs = 0;
   if( expiryMSecs > nowMSecs ) {
	diffMSecs = expiryMSecs - nowMSecs;
   } 
   long diffDays = diffMSecs/86400000;
   license.setDuration((int)diffDays);
   licenseDAO.update(license);
   m_log.error("JTRACE license code = " +license.getCode()+ ", duration = " +diffDays+ ", expiryDate = " +m_expiryDate );

	// Now find the desktop or rc entity that matches this license
   String rc = license.getRc().toUpperCase();
   if( rc.equals("Y") ) {
	 // remote control
     RCUsage rcusage = license.getRcusage();
     RCUsageDAO rcUsageDAO = DAOFactory.getRCUsageDAO();
	 rcusage.setExpiryDate(m_expiryDate);
	 rcusage.setWindowLimit(0x7fffffff);
	 rcusage.setLicenseSerialNumber(license.getCode());
	 rcUsageDAO.update(rcusage);

   } else {
     // desktop
	 Desktop desktop = null;
     DesktopDAO desktopDAO = DAOFactory.getDesktopDAO();
     List desktops = desktopDAO.getDesktopsByLicense( m_code ); 
	 for( int i=0; i<desktops.size(); i++ ) {
		 desktop = (Desktop)desktops.get(i);
		 desktop.setExpiryDate(m_expiryDate);
		 desktopDAO.update(desktop);
	 }
   }

	return true;

  }


  public boolean cancelLicense( ) throws DataException, ServiceException
  {
	//return cancelOrRevokeLicense( false );
	if( m_expiryDate == null ) {
		m_expiryDate = new Date();
	}
	return renewLicense();
  }

  public boolean revokeLicense( ) throws DataException, ServiceException
  {
	m_expiryDate = null;
	return cancelOrRevokeLicense( true );
  }

  // Note: revoking a license resets the desktop's expiry date, cancelling does not
  public boolean cancelOrRevokeLicense( boolean isRevoke ) throws DataException, ServiceException
  {
    String action = isRevoke?"Revoked":"Cancelled";
    List licenses;
    LicenseDAO licenseDAO = DAOFactory.getLicenseDAO();
    licenses = licenseDAO.getByCode(m_code,m_reseller.getId());
    // This request should return exactly one request
    if ((licenses == null) || (licenses.size() == 0)) {
       licenses = licenseDAO.getByCodeRC(m_code,m_reseller.getId());
       // This request shuold return exactly one request
       if ((licenses == null) || (licenses.size() == 0)) {
         if(fromCommandLine) System.out.println("Unknown license (or already revoked/cancelled) code: " + m_code);
         else m_results = "Unknown license (or already revoked/cancelled) code: " + m_code;
         return false;
      }
    }
    if (licenses.size() > 1){
      if(fromCommandLine) System.out.println("Error: more than one license with code: " + m_code);
      else m_results = "Error: more than one license with code: " + m_code;
      return false;
    }
    License license = (License) licenses.get(0);

    /* This is an okay thing to do now
    // If this license is a regcode, diplay an error message
    if (license.isRegCode()) {
      if(fromCommandLine) System.out.println("License: " + m_code + " is a registration code");
      else m_results = "License: " + m_code + " is a registration code";
      return false;
   }
   */
   // If this license is already revoked, nothing to do.
    if (license.isRevoked()) {
      if(fromCommandLine) System.out.println("License: " + m_code + " is already revoked/cancelled");
      else m_results = "License: " + m_code + " is already revoked/cancelled";
      return true;
    }

     if (license.hasExpired()) {
       license.setRevoked(true);
       licenseDAO.update(license);
       if(fromCommandLine) System.out.println("Revoked the expired license: " + m_code);
       else m_results = action +" the expired license: " + m_code;
       return true;
     }

   String rc = license.getRc().toUpperCase();
   if( rc.equals("Y") ) {
     // if this license has never been used, we simply revoke it.
     if (license.getRcusage() == null) {
       license.setRevoked(true);
       licenseDAO.update(license);
       if(fromCommandLine) System.out.println("Revoked the unused license: " + m_code);
       else m_results = action +" the unused license: " + m_code;
       return true;
     }

      // The licenses has not expired and id being used.
      // Remove the license from rcusage
      removeLicenseFromRCUsage(license, isRevoke);
   } else {
     // if this license has never been used, we simply revoke it.
     if (license.getAccount() == null) {
       license.setRevoked(true);
       licenseDAO.update(license);
       if(fromCommandLine) System.out.println("Revoked the unused license: " + m_code);
       else m_results = action +" the unused license: " + m_code;
       return true;
     }
     // If this license is being used but the account has no desktops (this could
     // happen if the user remove all his/her desktops).
     if (license.getAccount().getDesktops() == null) {
        license.setRevoked(true);
        licenseDAO.update(license);
        if(fromCommandLine) System.out.println("Revoked the unused license: " + m_code);
        else m_results = action +" the unused license: " + m_code ;
        return true;
     }
   

      // The licenses has not expired and is being used.
      // Remove the license from all of this account's desktops
      removeLicenseFromDesktops(license.getCode(), isRevoke);
    }

    // Revoke the license.
    license.setRevoked(true);
    licenseDAO.update(license);
    if(fromCommandLine) System.out.println("Revoked the license: " + m_code);
    else m_results = action +" the license: " + m_code ;
    return true;
  }


  private boolean removeLicenseFromRCUsage(License license, boolean isRevoke) throws DataException,ServiceException
  {
    LicenseManager licenseMgr = DelegateFactory.getLicenseManager();
    Reseller reseller = license.getReseller();
    if( license.isRegCode() ) {
        RCUsageDAO rcusageDAO = DAOFactory.getRCUsageDAO();
        List rcusageList = rcusageDAO.getByCode( license.getCode() );
        if( rcusageList != null ) {
	Object[] rcusageAry =  rcusageList.toArray();
	int max = rcusageAry.length;
	int idx = 0;
	while( idx < max ) {
        	    List freedUpLicenses = licenseMgr.rcunlicense(((RCUsage)rcusageAry[idx++]).getDid(), reseller, isRevoke, m_expiryDate);
	}
        }
    } else {
        RCUsage rcusage = license.getRcusage();
        List freedUpLicenses = licenseMgr.rcunlicense(rcusage.getDid(), reseller, isRevoke, m_expiryDate);
    }
    return true;
  }

  private boolean removeLicenseFromDesktops(String code, boolean resetExpiry) throws DataException,ServiceException
  {
    // get a list of desktops that are using this license code
    LicenseManager licenseMgr = DelegateFactory.getLicenseManager();
    DesktopManager desktopMgr = DelegateFactory.getDesktopManager(); 
	List desktops = desktopMgr.getDesktopsLikeLicense( code );
	if( desktops == null ) return true;
    for (Iterator it = desktops.iterator();it.hasNext();){
      DesktopDTO desktop = (DesktopDTO)it.next();
        // This desktop uses this (unexpired license). We unlicense the desktop
        // and add the other licenses one by one.
        List removedLicenses = licenseMgr.unlicense(desktop.getId(), resetExpiry, m_expiryDate);
        System.out.println("unlicensed desktop: " + desktop.getDid() + " user: " + desktop.getOwner());
        if ((removedLicenses != null) && (removedLicenses.size() > 1)){
          // the desktop had more than one active licenses
          boolean first = true; // we don't want to extend the first license.
          for (Iterator jt = removedLicenses.iterator();jt.hasNext();){
            LicenseDTO removed = (LicenseDTO) jt.next();
            if (!removed.getCode().equals(code)){
              // We must had this one back
              licenseMgr.setLicenseCode(null,desktop.getId(),
                                        removed.getCode(),!first);
              first = false;
              System.out.println("Added back license: " + removed.getCode() +
                                 " to desktop: " + desktop.getDid() +
                                 " user: " + desktop.getOwner());
            }
          }
        }
	}
	return true;
  }

  private boolean removeLicenseFromDesktops(Account account, boolean resetExpiry) throws DataException,ServiceException
  {
    LicenseManager licenseMgr = DelegateFactory.getLicenseManager();
    for (Iterator it = account.getDesktops().iterator();it.hasNext();){
      Desktop desktop = (Desktop)it.next();
      if (desktop.hasUsedLicenseCode(m_code)){
        // This desktop uses this (unexpired license). We unlicense the desktop
        // and add the other licenses one by one.
        List removedLicenses = licenseMgr.unlicense(desktop.getId(), resetExpiry, m_expiryDate);
        System.out.println("unlicensed desktop: " + desktop.getDid() + " user: " + account.getUsername());
        if ((removedLicenses != null) && (removedLicenses.size() > 1)){
          // the desktop had more than one active licenses
          boolean first = true; // we don't want to extend the first license.
          for (Iterator jt = removedLicenses.iterator();jt.hasNext();){
            LicenseDTO removed = (LicenseDTO) jt.next();
            if (!removed.getCode().equals(m_code)){
              // We must had this one back
              licenseMgr.setLicenseCode(account.getId(),desktop.getId(),
                                        removed.getCode(),!first);
              first = false;
              System.out.println("Added back license: " + removed.getCode() +
                                 " to desktop: " + desktop.getDid() +
                                 " user: " + account.getUsername());
            }
          }
        }
      }
    }
    return true;
  }

  private boolean revokeRegCode() throws DataException,ServiceException
  {
    List regCodes;
    LicenseDAO licenseDAO = DAOFactory.getLicenseDAO();
    if (m_revokeAllRegCodes) {
      regCodes = licenseDAO.getByCode(m_code,m_reseller.getId());
    } else {
      regCodes = licenseDAO.getByCodeAndUserId(m_code,m_reseller.getId(),
                                               m_account.getId());
    }
    if ((regCodes == null) || (regCodes.size() == 0)){
      System.out.println("Unknown (or already revoked/cancelled) reg code: " + m_code);
      return false;
    }
    for(Iterator it = regCodes.iterator();it.hasNext();) {
      License regCode = (License)it.next();
      // If this license is not a regcode, display an error message
      if (!regCode.isRegCode()) {
        System.out.println("License: " + m_code + " is not a registration code");
        return false;
      }
      if ((!regCode.hasExpired()) && (regCode.getAccount() != null) &&
              (regCode.getAccount().getDesktops() != null)) {
        // This regcode is being used
        removeLicenseFromDesktops(regCode.getCode(), true);
      }
      // Now we revoke the reg code
      regCode.setRevoked(true);
      licenseDAO.update(regCode);
      System.out.println("revoked registration code: " + m_code);
    }
    return true;
  }
  private int getLicenseCodeSize()
  {
    if (m_licenseCodeSize == 0){
      StringTokenizer tokenizer;
      tokenizer = new StringTokenizer(m_reseller.getLicenseCodeFormat(),",");
      while (tokenizer.hasMoreTokens()) {
        String val = tokenizer.nextToken();
        int intVal = Integer.parseInt(val);
        m_licenseCodeSize += intVal;
      }
    }
    return m_licenseCodeSize;
  }

  /**
   * creates the command line options
   * @return
   */
  private static Options createOptions()
  {
    Options options = new Options();

    options.addOption(new Option(LICENSE_OPT, false,"license" ));
    options.addOption(new Option(REG_CODE_OPT,false, "registration code" ));

    options.addOption(RESELLER_OPT,true,"reseller's name");
    options.addOption(CODE_OPT,true,"registration code");
    options.addOption(ALL_OPT,false, "remove all regcodes");
    options.addOption(USERNAME_OPT,true, "remove teh regcodes for this user only");

    options.addOption(HELP_OPT,false,"display a help message");

    return options;
  }
  private static void printUsage(String msg)
  {
    if (msg != null) {
      System.out.println(msg);
    }
    System.out.println("Usage:");

    System.out.println("To display this message:");
    System.out.println(LicenseGenerator.class.getName() +
                       " -" + HELP_OPT);

    System.out.println("To revoke a license:");
    System.out.println(LicenseGenerator.class.getName() +
                       " -" + LICENSE_OPT +
                       " -" + RESELLER_OPT + " <reseller's name>" +
                       " -" + CODE_OPT + " <license code>");
    System.out.println("To revoke a registration code:");
    System.out.println(LicenseGenerator.class.getName() +
                       " -" + REG_CODE_OPT +
                       " -" + RESELLER_OPT + " <reseller's name>" +
                       " -" + CODE_OPT + " <license code>" +
                       " [ -" + USERNAME_OPT + " <username>" +
                       " | -" + ALL_OPT + "]");
  }

  private boolean parseCommandLineParameters (String[] args)
          throws NumberFormatException
  {
    CommandLineParser parser = new  BasicParser();
    CommandLine line = null;

    try {
      line = parser.parse(createOptions(), args );
    }
    catch(ParseException e){
      printUsage(e.getMessage());
      return false;
    }
    if (line.hasOption(HELP_OPT)){
      printUsage(null);
      return false;
    }

    // Find the reseller
    ResellerDAO resellerDAO = DAOFactory.getResellerDAO();
    if (!line.hasOption(RESELLER_OPT)){
      printUsage("The reseller parameter is required");
      return false;
    }
    try {
      m_reseller = resellerDAO.getByCode(line.getOptionValue(RESELLER_OPT));
      if (m_reseller == null) {
      printUsage("Invalid reseller: " + line.getOptionValue(RESELLER_OPT));
      return false;
      }
    }
    catch(DataException e) {
      printUsage(e.getMessage());
      return false;
    }
    // we need a code
    if (!line.hasOption(CODE_OPT)) {
      printUsage(CODE_OPT + " parameter required to revoke a license");
      return false;
    }
    m_code = line.getOptionValue(CODE_OPT);
    if (m_code.length() != getLicenseCodeSize()){
      printUsage("The license code must have " + getLicenseCodeSize() + " characters");
      return false;
    }

    // License
    // -------
    if (line.hasOption(LICENSE_OPT)){
      m_licenseType = LICENSE;
      if (line.hasOption(USERNAME_OPT) || line.hasOption(ALL_OPT)){
        printUsage(null);
        return false;
      }

    } else if (line.hasOption(REG_CODE_OPT)) {
      // Reg Code
      // --------
      LicenseDAO licenseDAO = DAOFactory.getLicenseDAO();
      m_licenseType = REGCODE;
      if (!line.hasOption(USERNAME_OPT) && !line.hasOption(ALL_OPT)){
        printUsage(null);
        return false;
      }
      if (line.hasOption(USERNAME_OPT)) {
        if (line.hasOption(ALL_OPT)) {
          printUsage(null);
          return false;
        }
        m_username = line.getOptionValue(USERNAME_OPT);
        AccountDAO accountDAO = DAOFactory.getAccountDAO();
        try {
          m_account = accountDAO.getByUsername(m_username);
          if (m_account == null){
            printUsage("Invalid username: " + m_username);
            return false;
          }
        }
        catch(Exception e) {
          printUsage(e.getMessage());
          return false;
        }
      } else  if (line.hasOption(ALL_OPT)) {
        if (line.hasOption(USERNAME_OPT)) {
          printUsage(null);
          return false;
        }
        m_revokeAllRegCodes = true;
      } else {
        printUsage(null);
        return false;
      }

    } else {
      printUsage(null);
      return false;
    }
    return true;
  }
  private static  void doit(String[] args)
  {
    try {
      LicenseTerminator lt = new LicenseTerminator();
      if (lt.parseCommandLineParameters(args) == false) {
        return;
      }
      if (lt.m_licenseType ==REGCODE) {
        lt.revokeRegCode();
      } else {
        lt.revokeLicense();
      }
    }
    catch (Exception e){
      e.printStackTrace();
    }
  }
  public static void main (String[] args)
  {
    fromCommandLine = true;
    doit(args);
  }

}
