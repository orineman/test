<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean" %>
<%@ page import="com.infowave.c4p.web.actions.teksupport.UserForm" %>
<%@ page import="com.infowave.c4p.util.StringManager" %>

<script type="text/javascript" language="JavaScript">
function TestDataCheck()
{
var pwd1=document.UserForm.pwd1.value;
var pwd2=document.UserForm.pwd2.value;
var retval = true;
  if( pwd1.length < 4 || pwd2.length < 4 ) {
        alert("Please use a password with a length of 4 or greater.");
        retval = false;
  }
  if( retval && pwd1 != pwd2 ) {
        alert("Please enter the same password in both text boxes.");
        retval = false;
  }
  return retval;
}
</script>

<%
String jtemp = request.getQueryString();
String name = "";
if( jtemp != null && jtemp.length() > 0 ) {	
    int idx = jtemp.indexOf("data=");
    if( idx != -1 ) jtemp = jtemp.substring( idx +5 );
    String jtemp1 = StringManager.linkdecode(jtemp);
    name = new String(jtemp1.getBytes("8859_1"), "UTF-8");
}
%>


<html:form action="tsAdminResetPassword" method="post" onsubmit="return TestDataCheck()" >
      <input type="hidden" name="data" value='<%=name %>' >
      <table class="shaded_text" width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
       	  <td>&nbsp;</td>
        </tr>
        <tr>
       	  <td>&nbsp;</td>
          <td >
            <font size="+1">Reset Password for '<%=name %>'</font>
          </td>
        </tr>
        <tr>
       	  <td>&nbsp;</td>
        </tr>
        <tr>
       	  <td>&nbsp;</td>
          <td >
            <b>Enter the new password:</b>
          </td>
        </tr>
        <tr>
       	  <td>&nbsp;</td>
          <td >
          <input type="text" style="content_input" size="12" maxlength="12" name="pwd1" value=""/>
          </td>
        </tr>
        <tr>
       	  <td>&nbsp;</td>
        </tr>
        <tr>
       	  <td>&nbsp;</td>
          <td >
            <b>Re-enter the new password:</b>
          </td>
        </tr>
        <tr>
       	  <td>&nbsp;</td>
          <td >
          <input type="text" style="content_input" size="12" maxlength="12" name="pwd2" value=""/>
          </td>
        </tr>
        <tr>
       	  <td>&nbsp;</td>
        </tr>
        <tr>
       	  <td>&nbsp;</td>
          <td >
            <INPUT TYPE="submit" name="confirm" value=" Reset " />
	  </td>
        </tr>
        <tr>
       	  <td>&nbsp;</td>
        </tr>
        <tr>
       	  <td>&nbsp;</td>
        </tr>
        <tr>
       	  <td>&nbsp;</td>
	  <TD><a href="tekSupport.do">Top</a></TD>
        </tr>
        <tr>
       	  <td>&nbsp;</td>
        </tr>
        <tr>
       	  <td>&nbsp;</td>
        </tr>
      </table>
</html:form>
