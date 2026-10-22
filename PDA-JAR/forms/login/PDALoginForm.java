/*
 * PDALoginForm.java
 *
 * Created on 20 Сентябрь 2007 г., 12:07
 */

package com.infowave.pda.forms.login;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 *
 * @author Alexey.Ogarkov
 * @version
 */

public class PDALoginForm extends org.apache.struts.action.ActionForm {
    
    private String username;
    
    private String password;

    private String rememberMe;
    
    /**
     * @return
     */
    public String getUsername() {
        return username;
    }
    
    /**
     * @param string
     */
    public void setUsername(String string) {
        username = string;
    }
        
    /**
     *
     */
    public PDALoginForm() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        if (getUsername() == null) {
            errors.add("name", new ActionMessage("error.DATA_ENTRY_USERNAME_MISSING"));
        } else if ( getUsername().length() < 4){
            errors.add("name", new ActionMessage("error.DATA_ENTRY_USERNAME_FORMAT"));
        }
        //Password
        if (getPassword() == null) {
            errors.add("name", new ActionMessage("error.DATA_ENTRY_PASSWORD_MISSING"));
        }  else if ( getPassword().length() < 4){
            errors.add("name", new ActionMessage("error.DATA_ENTRY_PASSWORD_FORMAT"));
        }
        
        return errors;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRememberMe() {
        return rememberMe;
    }

    public String isRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(String rememberMe) {
        this.rememberMe = rememberMe;
    }
}
