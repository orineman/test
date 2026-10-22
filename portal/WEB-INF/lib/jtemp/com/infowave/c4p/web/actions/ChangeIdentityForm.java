package com.infowave.c4p.web.actions;

import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by: Vincent Aumont
 * Copyright 2003 Infowave Software, Inc.
 */
public class ChangeIdentityForm extends AccountForm
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

    // first name
   boolean firstNameRequired = false;
    validateFirstName(mapping,request,firstNameRequired,errors);

    // Password
   boolean lastNameRequired = false;
   validateLastName(mapping,request,lastNameRequired,errors);

    // PC name
   boolean emailRequired = false;
    validateEmail(mapping,request,emailRequired,errors);
    return errors;
  }
}
