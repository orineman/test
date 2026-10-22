package com.infowave.c4p.web.actions;

import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.ArrayList;

import com.infowave.c4p.util.PortalError;
import com.infowave.c4p.util.ErrorCode;


public class AttachDesktopToAccountForm extends AccountForm
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
    validateUsername(mapping,request,errors);

    // PC name
    validatePcName(mapping,request,errors);
    return errors;
  }
}
