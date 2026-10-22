package com.infowave.c4p.model.facade;

import com.infowave.c4p.service.delegate.UserManager;
import com.infowave.c4p.service.ServiceException;
import com.infowave.c4p.service.dto.UserDTO;
import com.infowave.c4p.service.dto.UserRegistrationDTO;
import com.infowave.c4p.model.*;
import com.infowave.c4p.model.assembler.UserAssembler;
import com.infowave.c4p.datasource.dao.*;
import com.infowave.c4p.datasource.DataException;
import com.infowave.c4p.util.PortalError;
import com.infowave.c4p.util.ErrorCode;
import com.infowave.c4p.util.security.PasswordEncoder;
import com.infowave.c4p.util.security.PasswordEncoderFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Locale;

/**
 * This class contains teh business logic for user-related operations.
 * Created by: Vincent Aumont
 * Copyright 2003 Infowave Software, Inc.
 */
public class UserManagerImpl implements UserManager
{

  /** The class's logger */
  private static Log m_log = LogFactory.getLog(UserManagerImpl.class);

/**
 * Returns a suer DTO given a user name. The account is expected to exist in the
 * database.
 *
 * @param username
 * @return
 */

  public UserDTO getUserDTO(String username)
  throws ServiceException
  {
    UserDTO dto = null;

    // Find the account corresponding to this username.
    Account account = null;
    AccountDAO accountDAO = DAOFactory.getAccountDAO();
    try {
      account  = accountDAO.getByUsername(username);
    } catch (DataException e){
      PortalError error = new PortalError(ErrorCode.SYSTEM_ERROR);
      throw new ServiceException(error,e);
    }
    if (account == null){
      PortalError error = new PortalError(ErrorCode.INVALID_USERNAME,username);
      throw new ServiceException(error);
    }

    // Create the DTO from the account.
    dto = UserAssembler.createUserDTO(account);

    return dto;
  }
  /**
   * Creates a new user, with or without an initial desktop.
   *
   * @param registrationInfo - the information we need to create a user.
   * @return the user DTO if the user was created successfully.
   * @throws ServiceException
   */
  public UserDTO RegisterUser(UserRegistrationDTO registrationInfo)
          throws  ServiceException
  {
    AccountDAO accountDAO = DAOFactory.getAccountDAO();
    DesktopDAO desktopDAO = DAOFactory.getDesktopDAO();
    ResellerDAO resellerDAO = DAOFactory.getResellerDAO();
    AuthenticationDAO authenticationDAO = DAOFactory.getAuthenticationDAO();
    try {
      // Make sure that this username is not already taken
      if (accountDAO.getByUsername(registrationInfo.getUsername()) != null) {
        PortalError error = new PortalError(ErrorCode.USERNAME_ALREADY_EXISTS,
                                            registrationInfo.getUsername());
        throw new ServiceException(error);
      }
      // If we have a did, make sure it does not exist already
      if (registrationInfo.getDid() != null) {
        if (desktopDAO.getByDid(registrationInfo.getDid()) != null) {
          PortalError error = new PortalError(ErrorCode.DID_ALREADY_DEFINED,
                                            registrationInfo.getDid());
          throw new ServiceException(error);
        }
      }
      //Find the reseller
      Reseller reseller = resellerDAO.getByCode(registrationInfo.getResellerId());
      if (reseller == null){
        m_log.error("RegisterUser - unknown reseller: " +  registrationInfo.getResellerId());
        PortalError error = new PortalError(ErrorCode.SYSTEM_ERROR);
        throw new ServiceException(error);
      }

      // get the password encoder for that reseller
      PasswordEncoder enc;
      enc = PasswordEncoderFactory.instance().getEncoder(reseller.getPasswordEncoder());

      // Create the desktop if we were passed a did
      Date creationDate = new Date();
      Desktop desktop =null;
      if (registrationInfo.getDid() != null) {
        desktop = new Desktop();
        desktop.setDid(registrationInfo.getDid());
        desktop.setCreationDate(creationDate);
        desktop.setExpiryDate(reseller.getDemoExpiryDate(creationDate));
        desktop.setName(registrationInfo.getPcName());
      }

      // Create the account
      Account account = new Account();
      account.setUsername(registrationInfo.getUsername());
      account.setEmail(registrationInfo.getEmail());
      account.setLastName(registrationInfo.getLastname());
      account.setFirstName(registrationInfo.getFirstname());
      account.setCreationDate(creationDate);
      //account.setLocale(reseller.getDefaultLocale());
      account.setLocale(registrationInfo.getLocale());
      account.setSecurityQuestion(registrationInfo.getSecurityQuestion());
      account.setSecurityAnswer(registrationInfo.getSecurityAnswer());
      account.setCountryCode(registrationInfo.getCountryCode());

      // Create the authentication
      Authentication authentication = new Authentication();
      authentication.setPassword(enc.encode(registrationInfo.getPassword()));
      authentication.setAuthType(reseller.getPasswordEncoder());
      authentication.setRole(Role.USER);
      authentication.setRolename(Role.USER.getName());
      authentication.setUsername(registrationInfo.getUsername());

      // link the objects
      account.setReseller(reseller);
      authentication.setAccount(account);
      if (registrationInfo.getDid() != null) {
        desktop.setAccount(account);
        account.addDesktop(desktop);
        desktop.setOwner(account.getUsername());
      }

      // Save the data
      DAOFactory.getAccountDAO().createAcount(account,authentication,desktop);
      m_log.info("Created new account for user: " + registrationInfo.getUsername());
    }
    catch (DesktopAlreadyAddedException e) {
      PortalError error = new PortalError(ErrorCode.PC_NAME_ALREADY_EXISTS,
                                          registrationInfo.getPcName());
      throw new ServiceException(error);
    }
    catch (DataException e) {
      PortalError error = new PortalError(ErrorCode.SYSTEM_ERROR);
      throw new ServiceException(error,e);
    }
    return null;
  }

  /**
 * Add a desktop to an existing account
 * @param username
 * @param password
 * @param did
 * @throws ServiceException
 */
  public void addDesktopToAccount(String username,String password,
                                  String did,String pcName)
        throws  ServiceException
  {
    AccountDAO accountDAO = DAOFactory.getAccountDAO();
    DesktopDAO desktopDAO = DAOFactory.getDesktopDAO();
    ResellerDAO resellerDAO = DAOFactory.getResellerDAO();
    AuthenticationDAO authenticationDAO = DAOFactory.getAuthenticationDAO();

    try {
      Authentication authentication = authenticationDAO.getByUsername(username);
      if (authentication == null) {
        PortalError error = new PortalError(ErrorCode.INVALID_USERNAME);
        throw new ServiceException(error);
      }

      // Make sure the passwords match
      if (!validatePassword(authentication,password)) {
        PortalError error = new PortalError(ErrorCode.INVALID_PASSWORD);
        throw new ServiceException(error);
      }

      // make sure we don't already hava a PC with that name
      Account account = authentication.getAccount();
      for (Iterator it = account.getDesktops().iterator();it.hasNext();) {
        Desktop desktop = (Desktop) it.next();
        if (desktop.getName().equalsIgnoreCase(pcName)) {
          PortalError error = new PortalError(ErrorCode.PC_NAME_ALREADY_EXISTS);
          throw new ServiceException(error,pcName);
        }
      }
      // Make sure this Did does not exist in teh system
      if (desktopDAO.getByDid(did) != null) {
          PortalError error = new PortalError(ErrorCode.DID_ALREADY_DEFINED,did);
          throw new ServiceException(error);
      }

      // Create the desktop
      Date creationDate = new Date();
      Desktop desktop = new Desktop();
      desktop.setDid(did);
      desktop.setCreationDate(creationDate);
      desktop.setExpiryDate(account.getReseller().getDemoExpiryDate(creationDate));
      desktop.setName(pcName);

      // Attach the desktop to the account
      try {
        account.addDesktop(desktop);
      } catch (DesktopAlreadyAddedException e) {
        // the user already has a desktop with that DID
          PortalError error = new PortalError(ErrorCode.DID_ALREADY_DEFINED);
          throw new ServiceException(error,did);
      }
      desktop.setAccount(account);
      desktop.setOwner(account.getUsername());

      desktopDAO.update(desktop);
      //accountDAO.addDesktopToAcount(account,desktop);
    }
    catch (DataException e){
      PortalError error = new PortalError(ErrorCode.SYSTEM_ERROR);
      throw new ServiceException(error,e);
    }

  }
  /**
   * Change the user's profile.
   * @param username
   * @param firstName
   * @param lastName
   * @param email
   * @throws ServiceException
   */
  public UserDTO changeIdentity(String username,
                             String firstName,String lastName,
                             String email, Locale userLocale)
          throws  ServiceException
  {
    Account account = null;
    try {
      AuthenticationDAO authenticationDAO = DAOFactory.getAuthenticationDAO();

      Authentication authentication = authenticationDAO.getByUsername(username);
      if (authentication == null) {
        PortalError error = new PortalError(ErrorCode.INVALID_USERNAME);
        throw new ServiceException(error);
      }
      account = authentication.getAccount();

      // Sets the new value and save the object but only if at least one field
      // is different from what we already have in the DB.

      boolean mustSave = false;
      if ((firstName != null) && !firstName.equals("") &&
              !firstName.equals(account.getFirstName())) {
        account.setFirstName(firstName);
        mustSave = true;
      }
      if ((lastName != null) && !lastName.equals("") &&
              !lastName.equals(account.getLastName())) {
        account.setLastName(lastName);
        mustSave = true;
      }
      if ((email != null) && !email.equals("") &&
              !email.equals(account.getEmail())) {
        account.setEmail(email);
        mustSave = true;
      }
      if(userLocale != null && !userLocale.equals(account.getLocale())) {
        account.setLocale(userLocale);
        mustSave = true;
      }
      if (mustSave) {
        AccountDAO accountDAO = DAOFactory.getAccountDAO();
        accountDAO.update(account);
      }
    }
    catch (DataException e){
      PortalError error = new PortalError(ErrorCode.SYSTEM_ERROR);
      throw new ServiceException(error,e);
    }

    // Returns the DTO
    return UserAssembler.createUserDTO(account);

  }

  /**
   * Changes the user's password.
   *
   * @param username
   * @param currentPassword
   * @param newPassword
   * @throws ServiceException
   */
  public void changePassword(String username,String currentPassword,
                             String newPassword)
          throws  ServiceException
  {
    try {
      AuthenticationDAO authenticationDAO = DAOFactory.getAuthenticationDAO();

      Authentication authentication = authenticationDAO.getByUsername(username);
      if (authentication == null) {
        PortalError error = new PortalError(ErrorCode.INVALID_USERNAME);
        throw new ServiceException(error);
      }
      // Make sure the password is valid
      if (!validatePassword(authentication,currentPassword)) {
        PortalError error = new PortalError(ErrorCode.INVALID_PASSWORD);
        throw new ServiceException(error);
      }
      // Encode the new password
      String encrypted = encodePassword(authentication,newPassword);
      // Save the modification
      authentication.setPassword(encrypted);
      authenticationDAO.update(authentication);
    }
    catch (DataException e){
      PortalError error = new PortalError(ErrorCode.SYSTEM_ERROR);
      throw new ServiceException(error,e);
    }


  }
  /**
   * Changes the user's security question and answer
   * @param username
   * @param question
   * @param answer
   * @throws ServiceException
   */
  public void changeSecurityQuestion(String username,String password,
                                     int question, String answer)
          throws  ServiceException
  {

    try {
      AuthenticationDAO authenticationDAO = DAOFactory.getAuthenticationDAO();

      Authentication authentication = authenticationDAO.getByUsername(username);
      if (authentication == null) {
        PortalError error = new PortalError(ErrorCode.INVALID_USERNAME);
        throw new ServiceException(error);
      }
      // Make sure the password is valid
      if (!validatePassword(authentication,password)) {
        PortalError error = new PortalError(ErrorCode.INVALID_PASSWORD);
        throw new ServiceException(error);
      }
      // Save the data
      Account account = null;
      account = authentication.getAccount();
      account.setSecurityQuestion(question);
      account.setSecurityAnswer(answer);
      AccountDAO accountDAO = DAOFactory.getAccountDAO();
      accountDAO.update(account);
    }
    catch (DataException e){
      PortalError error = new PortalError(ErrorCode.SYSTEM_ERROR);
      throw new ServiceException(error,e);
    }
  }
  /**
   * Returns the user's security question
   * @param username
   * @return the user's security question
   * @throws ServiceException
   */
  public int getSecurityQuestion(String username) throws  ServiceException
  {
    try {
      AccountDAO accountDAO = DAOFactory.getAccountDAO();
      Account account;
      account = accountDAO.getByUsername(username);
      if (account == null) {
        PortalError error = new PortalError(ErrorCode.INVALID_USERNAME);
        throw new ServiceException(error);
      }
      return account.getSecurityQuestion();
    }
    catch (DataException e){
      PortalError error = new PortalError(ErrorCode.SYSTEM_ERROR);
      throw new ServiceException(error,e);
    }
  }

  /**
   * Validate the user's security question
   * @param username
   * @return true if the answer matches the user's security answer
   * @throws ServiceException
   */

  public boolean validateSecurityAnswer(String username,
                                        String answer)
          throws  ServiceException
   {
    try {
      AccountDAO accountDAO = DAOFactory.getAccountDAO();
      Account account;
      account = accountDAO.getByUsername(username);
      if (account == null) {
        PortalError error = new PortalError(ErrorCode.INVALID_USERNAME);
        throw new ServiceException(error);
      }
      if (account.getSecurityAnswer() != null &&
          account.getSecurityAnswer().equals(answer)){
      return true;
      } else {
        return false;
      }
    }
    catch (DataException e){
      PortalError error = new PortalError(ErrorCode.SYSTEM_ERROR);
      throw new ServiceException(error,e);
    }
  }
  /**
   * Resets the user's password.
   *
   * @param username
   * @param password
   * @throws ServiceException
   */
  public void resetPassword(String username,String password)
          throws  ServiceException
  {
    try {
      AuthenticationDAO authenticationDAO = DAOFactory.getAuthenticationDAO();

      Authentication authentication = authenticationDAO.getByUsername(username);
      if (authentication == null) {
        PortalError error = new PortalError(ErrorCode.INVALID_USERNAME);
        throw new ServiceException(error);
      }
      // Encode the new password and save it.
      String encrypted = encodePassword(authentication,password);
      authentication.setPassword(encrypted);
      authenticationDAO.update(authentication);
    }
    catch (DataException e){
      PortalError error = new PortalError(ErrorCode.SYSTEM_ERROR);
      throw new ServiceException(error,e);
    }
}

  public int getUsercountLikeUsername( String partialName )
          throws  ServiceException
  {
      try {
     	AccountDAO accountDAO = DAOFactory.getAccountDAO();
	int count = accountDAO.countUsersByUsername( partialName );
      	return count;
     } catch (DataException e) {
      	PortalError error = new PortalError(ErrorCode.SYSTEM_ERROR);
      	throw new ServiceException(error,e);
     }
  }

  public int getUsercountLikeFirstname( String partialName )
          throws  ServiceException
  {
      try {
     	AccountDAO accountDAO = DAOFactory.getAccountDAO();
	int count = accountDAO.countUsersByFirstname( partialName );
      	return count;
     } catch (DataException e) {
      	PortalError error = new PortalError(ErrorCode.SYSTEM_ERROR);
      	throw new ServiceException(error,e);
     }
  }

  public int getUsercountLikeLastname( String partialName )
          throws  ServiceException
  {
      try {
     	AccountDAO accountDAO = DAOFactory.getAccountDAO();
	int count = accountDAO.countUsersByLastname( partialName );
      	return count;
     } catch (DataException e) {
      	PortalError error = new PortalError(ErrorCode.SYSTEM_ERROR);
      	throw new ServiceException(error,e);
     }
  }

  public int getUsercountLikeEmail( String partialName )
          throws  ServiceException
  {
      try {
     	AccountDAO accountDAO = DAOFactory.getAccountDAO();
	int count = accountDAO.countUsersByEmail( partialName );
      	return count;
     } catch (DataException e) {
      	PortalError error = new PortalError(ErrorCode.SYSTEM_ERROR);
      	throw new ServiceException(error,e);
     }
  }

  public List getUsersLikeUsername( String partialName ) 
	throws ServiceException
  {
        List users = null;
        List userDTOs = new ArrayList();

        try
        {
      	AccountDAO accountDAO = DAOFactory.getAccountDAO();
            users = accountDAO.getUsersByUsername( partialName ); 
        }
        catch(DataException e)
        {
            PortalError error = new PortalError(ErrorCode.SYSTEM_ERROR);
            throw new ServiceException(error, e);
        }

        UserDTO dto;
        for(Iterator it = users.iterator(); it.hasNext(); userDTOs.add(dto))
        {
            Account user = (Account)it.next();
            dto = UserAssembler.createUserDTO(user);
        }

        return userDTOs;

  }

  public List getUsersLikeFirstname( String partialName ) 
	throws ServiceException
  {
        List users = null;
        List userDTOs = new ArrayList();

        try
        {
      	AccountDAO accountDAO = DAOFactory.getAccountDAO();
            users = accountDAO.getUsersByFirstname( partialName ); 
        }
        catch(DataException e)
        {
            PortalError error = new PortalError(ErrorCode.SYSTEM_ERROR);
            throw new ServiceException(error, e);
        }

        UserDTO dto;
        for(Iterator it = users.iterator(); it.hasNext(); userDTOs.add(dto))
        {
            Account user = (Account)it.next();
            dto = UserAssembler.createUserDTO(user);
        }

        return userDTOs;

  }

  public List getUsersLikeLastname( String partialName ) 
	throws ServiceException
  {
        List users = null;
        List userDTOs = new ArrayList();

        try
        {
      	AccountDAO accountDAO = DAOFactory.getAccountDAO();
            users = accountDAO.getUsersByLastname( partialName ); 
        }
        catch(DataException e)
        {
            PortalError error = new PortalError(ErrorCode.SYSTEM_ERROR);
            throw new ServiceException(error, e);
        }

        UserDTO dto;
        for(Iterator it = users.iterator(); it.hasNext(); userDTOs.add(dto))
        {
            Account user = (Account)it.next();
            dto = UserAssembler.createUserDTO(user);
        }

        return userDTOs;

  }

  public List getUsersLikeEmail( String partialName ) 
	throws ServiceException
  {
        List users = null;
        List userDTOs = new ArrayList();

        try
        {
      	AccountDAO accountDAO = DAOFactory.getAccountDAO();
            users = accountDAO.getUsersByEmail( partialName ); 
        }
        catch(DataException e)
        {
            PortalError error = new PortalError(ErrorCode.SYSTEM_ERROR);
            throw new ServiceException(error, e);
        }

        UserDTO dto;
        for(Iterator it = users.iterator(); it.hasNext(); userDTOs.add(dto))
        {
            Account user = (Account)it.next();
            dto = UserAssembler.createUserDTO(user);
        }

        return userDTOs;

  }


  // ---------------------------------------------------------- Helper methods
  /**
   * Encodes a password according the user's encryption scheme.
   * @param authentication
   * @param password
   * @return
   * @throws ServiceException
   */
  private String encodePassword(Authentication authentication,String password)
          throws ServiceException
  {
    String encodedPassword = null;
    try {
      PasswordEncoder encoder = null;
      Class theClass = Class.forName(authentication.getAuthType());
      encoder = (PasswordEncoder) theClass.newInstance();
      encodedPassword = encoder.encode(password);
    }
    catch (Exception e) {
      PortalError error = new PortalError(ErrorCode.SYSTEM_ERROR);
      throw new ServiceException(error,e);
    }
    return encodedPassword;
  }

  /**
   * Verifies that two passwords (one possibly encoded) match.
   *
   * @param authentication
   * @param password
   * @return true if the passwords match (after encoding); false otherwise.
   * @throws ServiceException
   */
  private boolean validatePassword(Authentication authentication,String password)
          throws ServiceException
  {
    boolean valid  = false;
    try {
      PasswordEncoder encoder = null;
      Class theClass = Class.forName(authentication.getAuthType());
      encoder = (PasswordEncoder) theClass.newInstance();
      valid = encoder.match(password,authentication.getPassword());
    }
    catch (Exception e) {
      PortalError error = new PortalError(ErrorCode.SYSTEM_ERROR);
      throw new ServiceException(error,e);
    }
    return valid;
  }


  // -------------------------------------------------------------- Test methods
  private static void testChangeIdentity() throws Exception
  {
    UserManagerImpl um = new UserManagerImpl();
    um.changeIdentity("c4test","toto",null,null,null);
  }

  private static void testChangeSecurityQuestion() throws Exception
  {
    UserManagerImpl um = new UserManagerImpl();
    um.changeSecurityQuestion("c4test","pppp",0,"NEW QUESTION");
  }


  private static void testRegisterUser() throws Exception
  {
    UserRegistrationDTO info = new UserRegistrationDTO();
    info.setUsername("toto");
    info.setPassword("p");
    info.setPcName("The PC");
    info.setEmail("qwqw@llk.com");
    info.setDid("1234");
    info.setResellerId("laplink");
    info.setSecurityAnswer("foo");
    info.setSecurityQuestion(0);

    UserManagerImpl um = new UserManagerImpl();
    um.RegisterUser(info);
  }


  public static void main (String[] args)
  {
    try {

      //testRegisterUser();
      //testChangeIdentity();
      testChangeSecurityQuestion();
    }
    catch(Exception e) {
      System.out.println(e);
      e.printStackTrace();
    }
  }


}
