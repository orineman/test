package com.infowave.c4p.web.actions.teksupport;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.infowave.c4p.service.dto.UserDTO;
import com.infowave.c4p.service.delegate.UserManager;
import com.infowave.c4p.service.delegate.DelegateFactory;
import com.infowave.c4p.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.ArrayList;


public class UserForm extends ActionForm
{
  private String pwd1 = null;
  private String pwd2 = null;
  private String data = null;
  private String type = null;
  private List userList = null;
  private UserDTO user = null;
  private int count = 0;
  private static final int MAX_ALLOWED = 500;

  public String getPwd1() { return pwd1; }

  public String getPwd2() { return pwd2; }

  public String getData() { return data; }

  public String getType()  { return type; }

  public List getUserList()  { return userList ; }

  public void setUserList( List list )
  {
     userList = list;
  }

  public void setData(String data)
  {
    this.data= data;
  }

  public void setPwd1(String pwd1)
  {
    this.pwd1 = pwd1;
  }

  public void setPwd2(String pwd2)
  {
    this.pwd2 = pwd2;
  }

  public void setType(String type)
  {
    this.type= type;
  }

  public String getUserCount() 
  {
    //if( userList != null ) {
    //	count = userList.size();
    //}
    String countStr = Integer.toString(count);
    return countStr ;
  }

  protected List validateData(ActionMapping mapping, HttpServletRequest request)
  {
     List errors = new ArrayList(1);
     if( data == null || data.length() == 0 ) {
	data = "%";
     }
     return errors;
  }

  protected void findUser()
  {
	// Find out how many entries will be returned
	// Number is okay, get the actual data
     count = 0;
     UserManager userManager = DelegateFactory.getUserManager();
     if( type.equals("accountname") ) {
	try {
	    count = userManager.getUsercountLikeUsername( data );
	} catch( ServiceException ignored ) {  }
	if( count > 0 && count <= MAX_ALLOWED ) {
	    try {
	    	userList = userManager.getUsersLikeUsername( data );
	    } catch( ServiceException ignored ) {  }
        	}
     } else if( type.equals("firstname") ) {
	try {
	    count = userManager.getUsercountLikeFirstname( data );
	} catch( ServiceException ignored ) {  }
 	if( count > 0 && count <= MAX_ALLOWED ) {
	    try {
	    	userList = userManager.getUsersLikeFirstname( data );
	    } catch( ServiceException ignored ) {  }
        	}
    } else if( type.equals("lastname") ) {
	try {
	    count = userManager.getUsercountLikeLastname( data );
	} catch( ServiceException ignored ) {  }
	if( count > 0 && count <= MAX_ALLOWED ) {
	    try {
	    	userList = userManager.getUsersLikeLastname( data );
	    } catch( ServiceException ignored ) {  }
        	}
     } else if( type.equals("email") ) {
	try {
	    count = userManager.getUsercountLikeEmail( data );
	} catch( ServiceException ignored ) {  }
	if( count > 0 && count <= MAX_ALLOWED ) {
	    try {
	    	userList = userManager.getUsersLikeEmail( data );
	    } catch( ServiceException ignored ) {  }
        	}
     }
  }

  }
