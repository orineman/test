package com.infowave.c4p.service.delegate;

import com.infowave.c4p.service.dto.UserDTO;
import com.infowave.c4p.service.dto.UserRegistrationDTO;
import com.infowave.c4p.service.ServiceException;
import com.infowave.c4p.model.Authentication;

import java.util.List;
import java.util.Locale;

/**
 * This is the Business Delegate for User-related operation.
 *
 * Created by: Vincent Aumont
 * Copyright 2003 Infowave Software, Inc.
 */
/**
 * The UserManager is the Business Delegate for all user-related operations.
 */
public interface UserManager
{
  /**
   * Retrieve the information about a user.
   *
   * @param username - a user name
   * @return The user DTO if the username/password is valid. Null otherwise.
   * @throws ServiceException
   */
  public UserDTO getUserDTO(String username)
          throws ServiceException;

/**
 * Creates a new user.
 *
 * @param registrationInfo - the information we need to create a user.
 * @return the user DTO if the user was created successfully.
 * @throws ServiceException
 */
  public UserDTO RegisterUser(UserRegistrationDTO registrationInfo)
        throws  ServiceException;

/**
 * Adds a desktop to an existing account
 * @param username
 * @param password
 * @param did
 * @throws ServiceException
 */
  public void addDesktopToAccount(String username,String password,
                                    String did, String pcName, String hostKey, String serverKey)
//                                  String did, String pcName)
    throws  ServiceException;


/**
 * Changes the user's profile.
 * @param username
 * @param firstName
 * @param lastName
 * @param email
 * @throws ServiceException
 */
  public UserDTO changeIdentity(String username,
                             String firstName,String lastName,
                             String email, Locale userLocale)
        throws  ServiceException;

/**
     * Method changeFirstLastName ...
 *
 * @param username of type String
 * @param newfirstname of type String
 * @param newlastname of type String
 * @throws com.infowave.c4p.service.ServiceException when
 */
public void changeFirstLastName(String username,
                                String newfirstname,
                                String newlastname)
                                    throws ServiceException;


/**
 * Changer full user`s profile
 *
 * @param username
 * @param newEmail
 * @param newfirstname
 * @param newlastname
 * @param password
 * @param newPassword
 * @throws ServiceException
 */
public void changeFullUserProfile(String username,
                                  String newEmail,
                                  String newfirstname,
                                  String newlastname,
                                  String password,
                                  String newPassword,
                                  int securityQuestion,
                                  String securityAnswer) throws ServiceException;
/**
 * Changes the user's profile.
 * @param username
 * @param newusername
 * @param newEmail
 * @param password
 * @param newPassword
 * @throws ServiceException
 */
public void changeUserInformation(String username,String newusername,String newEmail,
                                  String password,String newPassword)
        throws  ServiceException;

/**
 * Check the user's password.
 *
 * @param username
 * @param password
 * @throws ServiceException
 */
public boolean checkPassword(String username, String password) throws ServiceException;

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
  throws  ServiceException;

/**
 * Changes the user's security question and answer
 * @param username
 * @param question
 * @param answer
 * @throws ServiceException
 */
public void changeSecurityQuestion(String username,String password,int question,
                           String answer)
  throws  ServiceException;

  /**
   * Returns the user's security question
   * @param username
   * @return the user's security question
   * @throws ServiceException
   */
  public int getSecurityQuestion(String username) throws  ServiceException;

  /**
   * Validate the user's security question
   * @param username
   * @return true if the answer matches the user's security answer
   * @throws ServiceException
   */

  public boolean validateSecurityAnswer(String username,
                                        String answer)
          throws  ServiceException;

  /**
   * Resets the user's password.
   *
   * @param username
   * @param password
   * @throws ServiceException
   */
  public void resetPassword(String username,String password)
          throws  ServiceException;
   /**
   * Change the user's username.
   *
   * @param username
   * @param password
   * @param newusername
   * @throws ServiceException
   */
  public void changeUsername(String username,String password,String newusername)
          throws ServiceException;

  public int getUsercountLikeUsername( String partialName )
          throws  ServiceException;
  public int getUsercountLikeFirstname( String partialName )
          throws  ServiceException;
  public int getUsercountLikeLastname( String partialName )
          throws  ServiceException;
  public int getUsercountLikeEmail( String partialName )
          throws  ServiceException;

  public List getUsersLikeUsername( String partialName )
    throws ServiceException;
  public List getUsersLikeFirstname( String partialName )
    throws ServiceException;
  public List getUsersLikeLastname( String partialName )
    throws ServiceException;
  public List getUsersLikeEmail( String partialName )
    throws ServiceException;
  public boolean validatePassword(Authentication authentication, String password)
    throws ServiceException;

}
