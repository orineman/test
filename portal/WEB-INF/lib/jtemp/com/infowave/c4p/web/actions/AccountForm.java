package com.infowave.c4p.web.actions;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import com.infowave.c4p.util.PortalError;
import com.infowave.c4p.util.ErrorCode;
import com.infowave.c4p.web.util.SessionState;
import com.infowave.c4p.web.util.WebUtil;

/**
 * Created by: Vincent Aumont
 * Copyright 2003 Infowave Software, Inc.
 */
public class AccountForm extends ActionForm
{
  // todo: this shouldd be put in a config file or should be reseller-secific
  private static final int MIN_LENGTH_USERNAME = 4;
  private static final int MIN_LENGTH_PASSWORD = 4;

  /** This regexp describes a the characters that are invalid */
  private static final String ILLEGAL_CHARS_REGEXP = "[^A-Za-z0-9.@_\\-~#]+";

  /** The pattern to match the regexp */
  private static Pattern ILLEGAL_CHARS_PATTERN = Pattern.compile(ILLEGAL_CHARS_REGEXP);

  /** This regexp describes a the characters that are invalid */
  private static final String EMAIL_REGEXP = "(.)+@([^\\.])+\\.(.)+";

  /** The pattern to match the regexp */
  private static Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEXP);

  private String username;
  private String password;
  private String passwordConfirmation;
  private String newPassword;
  private String newPasswordConfirmation;
  private String email;
  private String pcName;
  private int securityQuestion;
  private String securityAnswer;
  private String did;
  private String firstName;
  private String lastName;
  private String locale = "";
  private String countryCode;

  private String group1;
  private String group2;
  private String group3;
  private String group4;
  private String group5;
  private String group6;
  private String group7;
  private String group8;
  private String group9;
  private String group10;

  private String grouprc1;
  private String grouprc2;
  private String grouprc3;
  private String grouprc4;
  private String grouprc5;
  private String grouprc6;
  private String grouprc7;
  private String grouprc8;
  private String grouprc9;
  private String grouprc10;

  private String m_licenseCode = null;
  private String m_licenseCodeRC = null;

  public String getLocale() {
    return locale;
  }

  public void setLocale(String locale) {
    if (locale!= null) {
      locale= locale.trim();
    }
    this.locale= locale;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    if (username != null) {
      username = username.trim();
    }
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    if (password != null){
      password = password.trim();
    }
    this.password = password;
  }

  public String getCountryCode()
  {
    return countryCode;
  }

  public void setCountryCode(String code)
  {
    this.countryCode = code;
  }

  public String getNewPassword()
  {
    return newPassword;
  }

  public void setNewPassword(String newPassword)
  {
    if (newPassword != null){
      newPassword = newPassword.trim();
    }
    this.newPassword = newPassword;
  }

  public String getNewPasswordConfirmation()
  {
    return newPasswordConfirmation;
  }

  public void setNewPasswordConfirmation(String newPasswordConfirmation)
  {
    if (newPasswordConfirmation != null) {
      newPasswordConfirmation = newPasswordConfirmation.trim();
    }
    this.newPasswordConfirmation = newPasswordConfirmation;
  }

  public String getPasswordConfirmation() {
    return passwordConfirmation;
  }

  public void setPasswordConfirmation(String passwordConfirmation) {
    if (passwordConfirmation != null) {
      passwordConfirmation = passwordConfirmation.trim();
    }
    this.passwordConfirmation = passwordConfirmation;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    if (email != null) {
      email = email.trim();
    }
    this.email = email;
  }

  public String getPcName() {
    return pcName;
  }

  public void setPcName(String pcName) {
    if (pcName != null) {
      pcName = pcName.trim();
    }
    this.pcName = pcName;
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
    if (securityAnswer != null){
      securityAnswer = securityAnswer;
    }
    this.securityAnswer = securityAnswer;
  }

  public String getDid()
  {
    return did;
  }

  public void setDid(String did)
  {
    if (did != null) {
      did = did.trim();
    }
    this.did = did;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    if (firstName != null) {
      firstName = firstName.trim();
    }
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    if (lastName != null) {
      lastName = lastName.trim();
    }
    this.lastName = lastName;
  }

  /**
   * Validates the form parameters.
   *
   * @param mapping
   * @param request
   * @return A (possibly empty) list of errors.
   */

  public List validateInput(ActionMapping mapping,
                            HttpServletRequest request)
  {

    List errors = new ArrayList(3);

    // // Username
    validateUsername(mapping,request,errors);

    // Password
    validatePassword(mapping,request,errors);

    // Confirmation password
    if ((passwordConfirmation == null) || (passwordConfirmation.length() == 0)){
      errors.add(new PortalError(ErrorCode.DATA_ENTRY_PASSWORD_CONF_MISSING_ERROR));
    }

    // Password must match
    if ((password != null) && (password.length() != 0) &&
            (passwordConfirmation != null) && (passwordConfirmation.length() != 0))
    {
      if (!password.equals(passwordConfirmation)) {
        errors.add(new PortalError(ErrorCode.DATA_ENTRY_PASSWORDS_DONT_MATCH_ERROR));
      }
    }
    // PC name
    validatePcName(mapping,request,errors);

    // Email
    boolean required = true;
    validateEmail(mapping,request,required,errors);

    // Security answer
    validateSecurityAnswer(mapping,request,errors);

    // Validate license code if present
    validateLicenseCode(mapping, request, errors);

    // Validate RC license code if present
    validateRCLicenseCode(mapping, request, errors);

    return errors;
  }

  protected List validateLicenseCode(ActionMapping mapping,
                                  HttpServletRequest request,
                                  List errors)
  {
     SessionState state = WebUtil.getSessionState(request);
     int licenseCodeLength = state.getReseller().getLicenseCodeLength();

     if ((m_licenseCode == null) ||
             (m_licenseCode.trim().length() != licenseCodeLength)) {
       PortalError error;
       error = new PortalError(ErrorCode.DATA_ENTRY_LICENSE_CODE_FORMAT);
       errors.add(error);
     }
     return errors;
  }

  protected List validateRCLicenseCode(ActionMapping mapping,
                                  HttpServletRequest request,
                                  List errors)
  {
     SessionState state = WebUtil.getSessionState(request);
     int licenseCodeLength = state.getReseller().getLicenseCodeLength();

     if ((m_licenseCodeRC == null) ||
             (m_licenseCodeRC.trim().length() != licenseCodeLength)) {
       PortalError error;
       error = new PortalError(ErrorCode.DATA_ENTRY_LICENSE_CODE_FORMAT);
       errors.add(error);
     }
     return errors;
  }


  /**
   * Validate he username field.
   * @param mapping
   * @param request
   * @return
   */
  protected List validateUsername(ActionMapping mapping,
                                  HttpServletRequest request,
                                  List errors)
  {
    if ((username == null) || (username.length() == 0)){
      errors.add(new PortalError(ErrorCode.DATA_ENTRY_USERNAME_MISSING_ERROR));
    }  else if (username.length() <MIN_LENGTH_USERNAME){
      errors.add(new PortalError(ErrorCode.DATA_ENTRY_USERNAME_FORMAT_ERROR,
                                 Integer.toString(MIN_LENGTH_USERNAME)));
    } else {
      //Matcher matcher = ILLEGAL_CHARS_PATTERN.matcher(username);
      //if (matcher.find()) {
      //  errors.add(new PortalError(ErrorCode.DATA_ENTRY_USERNAME_FORMAT_ERROR));
      //}
    }
    return errors;
  }

  /**
   * Validate the first name field.
   * @param mapping
   * @param request
   * @return
   */
  protected List validateFirstName(ActionMapping mapping,
                                  HttpServletRequest request,
                                  boolean required,
                                  List errors)
  {
    if (required) {
      if ((firstName == null) || (firstName.length() == 0)){
        // todo creat an error, if we ever need one.
        //errors.add(new PortalError(ErrorCode.DATA_ENTRY_USERNAME_MISSING_ERROR));
      }
    }
    if ((firstName == null) || (firstName.length() == 0)){
      //Matcher matcher = ILLEGAL_CHARS_PATTERN.matcher(firstName);
      //if (matcher.find()) {
      //  errors.add(new PortalError(ErrorCode.DATA_ENTRY_FIRSTNAME_FORMAT_ERROR));
      //}
    }
    return errors;
  }

  /**
   * Validate the last name field.
   * @param mapping
   * @param request
   * @return
   */
  protected List validateLastName(ActionMapping mapping,
                                  HttpServletRequest request,
                                  boolean required,
                                  List errors)
  {
    if (required) {
      if ((lastName == null) || (lastName.length() == 0)){
        // todo creat an error, if we ever need one.
        //errors.add(new PortalError(ErrorCode.DATA_ENTRY_USERNAME_MISSING_ERROR));
      }
    }
    if ((lastName != null) && (lastName.length() != 0)){
      //Matcher matcher = ILLEGAL_CHARS_PATTERN.matcher(lastName);
      //if (matcher.find()) {
      //  errors.add(new PortalError(ErrorCode.DATA_ENTRY_LASTNAME_FORMAT_ERROR));
      //}
    }
    return errors;
  }

  /**
   * Validate the password field.
   * @param mapping
   * @param request
   * @return
   */
  protected List validatePassword(ActionMapping mapping,
                                  HttpServletRequest request,
                                  List errors)
  {
    if ((password == null) || (password.length() == 0)){
      errors.add(new PortalError(ErrorCode.DATA_ENTRY_PASSWORD_MISSING_ERROR));
    } else if (password.length() <MIN_LENGTH_PASSWORD){
      errors.add(new PortalError(ErrorCode.DATA_ENTRY_PASSWORD_FORMAT_ERROR,
                                 Integer.toString(MIN_LENGTH_PASSWORD)));
    } else {
      //Matcher matcher = ILLEGAL_CHARS_PATTERN.matcher(password);
      //if (matcher.find()) {
      //  errors.add(new PortalError(ErrorCode.DATA_ENTRY_PASSWORD_FORMAT_ERROR));
      //}
    }
    return errors;
  }
  /**
   * Validate the password field.
   * @param mapping
   * @param request
   * @return
   */
  protected List validateNewPassword(ActionMapping mapping,
                                  HttpServletRequest request,
                                  List errors)
  {
    if ((newPassword == null) || (newPassword.length() == 0)){
      errors.add(new PortalError(ErrorCode.DATA_ENTRY_PASSWORD_MISSING_ERROR));
    } else if (newPassword.length() <MIN_LENGTH_PASSWORD){
      errors.add(new PortalError(ErrorCode.DATA_ENTRY_PASSWORD_FORMAT_ERROR,
                                 Integer.toString(MIN_LENGTH_PASSWORD)));
    } else {
      //Matcher matcher = ILLEGAL_CHARS_PATTERN.matcher(newPassword);
      //if (matcher.find()) {
      //  errors.add(new PortalError(ErrorCode.DATA_ENTRY_PASSWORD_FORMAT_ERROR));
      //}
    }
    return errors;
  }

  /**
   * Validate the password field.
   * @param mapping
   * @param request
   * @return
   */
  protected List validateNewPasswordConfirmation(ActionMapping mapping,
                                  HttpServletRequest request,
                                  List errors)
  {
    boolean match = false;
    if ((newPassword != null) && (newPassword.length() != 0) &&
            (newPasswordConfirmation != null) && (newPasswordConfirmation.length() != 0))
    {
      if (newPassword.equals(newPasswordConfirmation)) {
        match = true;
      }
    }
    
    if (!match) {
      errors.add(new PortalError(ErrorCode.DATA_ENTRY_PASSWORDS_DONT_MATCH_ERROR));
    }
    return errors;
  }



  /**
   * Validate the PC Name  field.
   * @param mapping
   * @param request
   * @return
   */
  protected List validatePcName(ActionMapping mapping,
                                HttpServletRequest request,
                                List errors)
  {
    if ((pcName == null) || (pcName.length() == 0)){
      errors.add(new PortalError(ErrorCode.DATA_ENTRY_PC_NAME_MISSING_ERROR));
    }
    return errors;
  }

  /**
   * Validate the Email  field.
   * @param mapping
   * @param request
   * @return
   */
  protected List validateEmail(ActionMapping mapping,
                               HttpServletRequest request,
                               boolean required,
                               List errors)
  {
    if (required) {
      if ((email == null) || (email.length() == 0)){
        errors.add(new PortalError(ErrorCode.DATA_ENTRY_EMAIL_MISSING_ERROR));
      }
    }
    if ((email != null) && (!email.equals(""))) {
      Matcher matcher = EMAIL_PATTERN.matcher(email);
      if (!matcher.find()) {
        errors.add(new PortalError(ErrorCode.DATA_ENTRY_EMAIL_FORMAT_ERROR));
      }
    }
    return errors;
  }

  /**
   * Validate the Email  field.
   * @param mapping
   * @param request
   * @return
   */
  protected List validateSecurityAnswer(ActionMapping mapping,
                                        HttpServletRequest request,
                                        List errors)
  {
    if ((securityAnswer == null) || (securityAnswer.length() == 0)){
      errors.add(new PortalError(ErrorCode.DATA_ENTRY_SEC_ANSWER_MISSING_ERROR));
    }

    return errors;
  }


  public String getGroup1(){return group1;}
  public void setGroup1(String group){this.group1 = group;setLicenseCode();}

  public String getGroup2(){return group2;}
  public void setGroup2(String group){this.group2 = group;setLicenseCode();}

  public String getGroup3(){return group3;}
  public void setGroup3(String group){this.group3 = group;setLicenseCode();}

  public String getGroup4(){return group4;}
  public void setGroup4(String group){this.group4 = group;setLicenseCode();}

  public String getGroup5(){return group5;}
  public void setGroup5(String group){this.group5 = group;setLicenseCode();}

  public String getGroup6(){return group6;}
  public void setGroup6(String group){this.group6 = group;setLicenseCode();}

  public String getGroup7(){return group7;}
  public void setGroup7(String group){this.group7 = group;setLicenseCode();}

  public String getGroup8(){return group8;}
  public void setGroup8(String group){this.group8 = group;setLicenseCode();}

  public String getGroup9(){return group9;}
  public void setGroup9(String group){this.group9 = group;setLicenseCode();}

  public String getGroup10(){return group10;}
  public void setGroup10(String group){this.group10 = group;setLicenseCode();}

  public String getGrouprc1(){return grouprc1;}
  public void setGrouprc1(String group){this.grouprc1 = group;setLicenseCodeRC();}

  public String getGrouprc2(){return grouprc2;}
  public void setGrouprc2(String group){this.grouprc2 = group;setLicenseCodeRC();}

  public String getGrouprc3(){return grouprc3;}
  public void setGrouprc3(String group){this.grouprc3 = group;setLicenseCodeRC();}

  public String getGrouprc4(){return grouprc4;}
  public void setGrouprc4(String group){this.grouprc4 = group;setLicenseCodeRC();}

  public String getGrouprc5(){return grouprc5;}
  public void setGrouprc5(String group){this.grouprc5 = group;setLicenseCodeRC();}

  public String getGrouprc6(){return grouprc6;}
  public void setGrouprc6(String group){this.grouprc6 = group;setLicenseCodeRC();}

  public String getGrouprc7(){return grouprc7;}
  public void setGrouprc7(String group){this.grouprc7 = group;setLicenseCodeRC();}

  public String getGrouprc8(){return grouprc8;}
  public void setGrouprc8(String group){this.grouprc8 = group;setLicenseCodeRC();}

  public String getGrouprc9(){return grouprc9;}
  public void setGrouprc9(String group){this.grouprc9 = group;setLicenseCodeRC();}

  public String getGrouprc10(){return grouprc10;}
  public void setGrouprc10(String group){this.grouprc10 = group;setLicenseCodeRC();}


  private void setLicenseCode()
  {
    StringBuffer buff = new StringBuffer();
    if (group1 != null){
      buff.append(group1.trim());
    }
    if (group2 != null){
      buff.append(group2.trim());
    }
    if (group3 != null){
      buff.append(group3.trim());
    }
    if (group4 != null){
      buff.append(group4.trim());
    }
    if (group5 != null){
      buff.append(group5.trim());
    }
    if (group6 != null){
      buff.append(group6.trim());
    }
    if (group7 != null){
      buff.append(group7.trim());
    }
    if (group8 != null){
      buff.append(group8.trim());
    }
    if (group9 != null){
      buff.append(group9.trim());
    }
    if (group10 != null){
      buff.append(group10.trim());
    }
    m_licenseCode = buff.toString();
  }

  private void setLicenseCodeRC()
  {
    StringBuffer buff = new StringBuffer();
    if (grouprc1 != null){
      buff.append(grouprc1.trim());
    }
    if (grouprc2 != null){
      buff.append(grouprc2.trim());
    }
    if (grouprc3 != null){
      buff.append(grouprc3.trim());
    }
    if (grouprc4 != null){
      buff.append(grouprc4.trim());
    }
    if (grouprc5 != null){
      buff.append(grouprc5.trim());
    }
    if (grouprc6 != null){
      buff.append(grouprc6.trim());
    }
    if (grouprc7 != null){
      buff.append(grouprc7.trim());
    }
    if (grouprc8 != null){
      buff.append(grouprc8.trim());
    }
    if (grouprc9 != null){
      buff.append(grouprc9.trim());
    }
    if (grouprc10 != null){
      buff.append(grouprc10.trim());
    }
    m_licenseCodeRC = buff.toString();
  }
  public String getLicenseCodeRC() {return m_licenseCodeRC;}
  public String getLicenseCode() {return m_licenseCode;}


  //-------------------------------------------------------------- Test methods
  private static void testValidateInput() throws Exception
  {
    List errors;
    /*
    CreateAccountForm f = new CreateAccountForm();
    f.setUsername("QWpppp/");
    f.setPassword("pppppp");
    f.setPasswordConfirmation("l");
    f.setSecurityAnswer("popopopo");
    f.setEmail("popo@popo.com");
    List errors = f.validateInput(null,null);
    for (Iterator it = errors.iterator();it.hasNext();){
    System.out.println((PortalError) it.next());
    }
    */
    // Empty form
    CreateAccountForm empty = new CreateAccountForm();
    errors = empty.validateInput(null,null);
    System.out.println("Errors for the empty form:");
    for (Iterator it = errors.iterator();it.hasNext();){
      System.out.println((PortalError) it.next());
    }
    // All wrong
    CreateAccountForm wrong = new CreateAccountForm();
    wrong.setUsername("Qwewe/weweW");
    wrong.setPassword("lklklklkk");
    wrong.setPasswordConfirmation("l");
    wrong.setSecurityAnswer("");
    wrong.setEmail("wewewewe");
    errors = wrong.validateInput(null,null);
    System.out.println("Errors for the all-wrong form:");
    for (Iterator it = errors.iterator();it.hasNext();){
      System.out.println((PortalError) it.next());
    }
    // All good
    CreateAccountForm good = new CreateAccountForm();
    good.setUsername("QweweeweW");
    good.setPassword("qqqqq");
    good.setPasswordConfirmation("qqqqq");
    good.setSecurityAnswer("uiuiuiui");
    good.setEmail("wewewewe@opioi.lilik");
    errors = good.validateInput(null,null);
    System.out.println("Errors for the all-good form:");
    for (Iterator it = errors.iterator();it.hasNext();){
      System.out.println((PortalError) it.next());
    }

  }

  public static void main(String[] args) {
    try {
      testValidateInput();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

    public static Log m_log;

    static 
    {
        m_log = LogFactory.getLog(com.infowave.c4p.web.actions.AccountForm.class);
    }

}


