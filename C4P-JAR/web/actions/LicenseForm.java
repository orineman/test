package com.infowave.c4p.web.actions;

import com.infowave.c4p.util.ErrorCode;
import com.infowave.c4p.util.PortalError;
import com.infowave.c4p.web.util.SessionState;
import com.infowave.c4p.web.util.WebUtil;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


public class LicenseForm extends ActionForm
{
  String did;
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
  private String enterLicenseCodeButton;
  private String extensionQuestionButton;
  private String confirmButton;
  private String extendFixedTerm;
  private String extensionAnswer;
  private String code;
  private String m_licenseCode;
  private boolean expired;

  public String getLicenseCode() {return m_licenseCode;}

  public String getDid()
  {
    return did;
  }

  public void setDid(String did)
  {
    this.did = did;
  }

  public boolean getExpired()
  {
    return expired;
  }

  public void setExpired(boolean expired)
  {
    this.expired = expired;
  }

  public String getCode()
  {
    return code;
  }

  /**
   * This variable is set by an hidden field in forms other than the code
   * entry form (extension question form and confirmation form).
   * @param code
   */
  public void setCode(String code)
  {
    this.code = code;
    m_licenseCode = code;
  }

  public String getExtensionAnswer()
  {
    return extensionAnswer;
  }

  public void setExtensionAnswer(String extensionAnswer)
  {
    this.extensionAnswer = extensionAnswer;
  }

  public String getExtendFixedTerm()
  {
    return extendFixedTerm;
  }

  public void setExtendFixedTerm(String extendFixedTerm)
  {
    this.extendFixedTerm = extendFixedTerm;
  }

  public String getEnterLicenseCodeButton()
  {
    return enterLicenseCodeButton;
  }

  public void setEnterLicenseCodeButton(String enterLicenseCodeButton)
  {
    this.enterLicenseCodeButton = enterLicenseCodeButton;
  }

  public String getExtensionQuestionButton()
  {
    return extensionQuestionButton;
  }

  public void setExtensionQuestionButton(String extensionQuestionButton)
  {
    this.extensionQuestionButton = extensionQuestionButton;
  }

  public String getConfirmButton()
  {
    return confirmButton;
  }

  public void setConfirmButton(String confirmButton)
  {
    this.confirmButton = confirmButton;
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
    code = m_licenseCode;
  }

   protected List validateLicenseCode(ActionMapping mapping,
                                  HttpServletRequest request)

   {
     List errors = new ArrayList(1);
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

  /**
   * make sure we have a did in the request and that it's one of the user's
   * desktops.
   * @param mapping
   * @param request
   * @return
   */
  protected List validateDid(ActionMapping mapping,
                             HttpServletRequest request)

  {
    List errors = new ArrayList(1);
    SessionState state = WebUtil.getSessionState(request);
    if ((did == null) || (state.getUserDesktop(did) == null)) {
      PortalError error;
      error = new PortalError(ErrorCode.INVALID_ID);
      errors.add(error);
    }
    return errors;
  }

  }
