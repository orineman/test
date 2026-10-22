package com.infowave.c4p.web.actions;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import com.infowave.c4p.util.PortalError;
import com.infowave.c4p.util.ErrorCode;

/**
 * Created by: Vincent Aumont
 * Copyright 2003 Infowave Software, Inc.
 */
public class CreateAccountForm extends AccountForm
 {
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
    if ((getPasswordConfirmation() == null) || (getPasswordConfirmation().length() == 0)){
      errors.add(new PortalError(ErrorCode.DATA_ENTRY_PASSWORD_CONF_MISSING_ERROR));
    }

    // Password must match
    if ((getPassword() != null) && (getPassword().length() != 0) &&
            (getPasswordConfirmation() != null) && (getPasswordConfirmation().length() != 0))
    {
      if (!getPassword().equals(getPasswordConfirmation())) {
        errors.add(new PortalError(ErrorCode.DATA_ENTRY_PASSWORDS_DONT_MATCH_ERROR));
      }
    }
    // PC name if we have a did
    if ((getDid() != null) && !getDid().equals("")) {
      validatePcName(mapping,request,errors);
    }
    // Email
    boolean required = true;
    validateEmail(mapping,request,required,errors);

    // Security answer
    validateSecurityAnswer(mapping,request,errors);

    return errors;
    }


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

}
