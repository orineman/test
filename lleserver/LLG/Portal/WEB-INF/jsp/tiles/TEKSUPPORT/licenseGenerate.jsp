
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean" %>

<script type="text/javascript" language="JavaScript">
function TestDataCheck()
{
var noUses=parseInt(document.TSLicenseGenerateForm.maxUses.value);
var noLicenses=parseInt(document.TSLicenseGenerateForm.totalCount.value);
var noDays=parseInt(document.TSLicenseGenerateForm.days.value);
var adr=document.TSLicenseGenerateForm.emailAddress.value;
var retval = true;
  if( noLicenses > 0 ) {
  } else {
	alert("Please enter the the number of licenses to create");
        retval = false;
  }
  if( noDays > 0 ) {
  } else {
	alert("Please enter the lifetime in days for these licenses");
        retval = false;
  }
  if( adr.length > 0 && (adr.indexOf("@") == -1 || adr.indexOf(".") == -1) ) {
	alert("Please enter email address in a format similar to name@company.com");
        retval = false;
  }
  if( retval && noLicenses > 1000 ) {
	alert("This could take a long time to generate!");
  }
  return retval;
}
</script>

<html:form action="tsAdminCreateLicense" method="post" onsubmit="return TestDataCheck()" >
      <input type="hidden" name="reseller" value="laplink" >
      <input type="hidden" name="type" value="confirm" >
      <table class="shaded_text" width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
       	  <td>&nbsp;</td>
        </tr>
        <tr>
       	  <td>&nbsp;</td>
          <td >
            <font size="+1">Create New License</font>
          </td>
        </tr>
        <tr>
       	  <td>&nbsp;</td>
        </tr>
        <tr>
       	  <td>&nbsp;</td>
          <td >
            <b>What type of licenses do you want to create?
	    </b>
          </td>
        </tr>
        <tr>
       	  <td>&nbsp;</td>
       	  <td>
	  <input type="radio" name="rc" value="N" checked>Desktop
	  </td>
        </tr>
        <tr>
       	  <td>&nbsp;</td>
        </tr>
        <tr>
       	  <td>&nbsp;</td>
          <td >
            <b>How will these licenses be sold?
	    </b>
          </td>
        </tr>
        <tr>
       	  <td>&nbsp;</td>
       	  <td>
	  <input type="radio" name="boxed" value="N" checked>Online subscription
	  </td>
        </tr>
        <tr>
       	  <td>&nbsp;</td>
        </tr>
        <tr>
       	  <td>&nbsp;</td>
          <td >
            <b>How many licenses do you want to create?</b>
          </td>
        </tr>
        <tr>
       	  <td>&nbsp;</td>
          <td >
          <input type="text" style="content_input" size="5" maxlength="5" name="totalCount" value="1000"/>
          </td>
        </tr>
        <tr>
       	  <td>&nbsp;</td>
        </tr>
        <tr>
       	  <td>&nbsp;</td>
          <td >
            <b>How long should each license last?</b>
          </td>
        </tr>
        <tr>
       	  <td>&nbsp;</td>
          <td >
          <input type="text" style="content_input" size="5" maxlength="5" name="days" value="365"/> days
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
          <td >
            <b>A file containing the new licenses numbers will be emailed to the address below. (You may enter more than 
		one address, but be sure to separate the addresses with commas.)
	    </b>
          </td>
        </tr>
        <tr>
       	  <td>&nbsp;</td>
        </tr>
        <tr>
       	  <td>&nbsp;</td>
          <td >
            <b>Enter the email address:</b>
          </td>
        </tr>
        <tr>
       	  <td>&nbsp;</td>
          <td >
          <input type="text" style="content_input" size="40" maxlength="256" name="emailAddress" value=""/>
          </td>
        </tr>
        <tr>
       	  <td>&nbsp;</td>
          <td >
            <b>Add text to the email message body:</b>
          </td>
        </tr>
        <tr>
       	  <td>&nbsp;</td>
          <td >
          <textarea rows="5" cols="65" name="emailText" value=""/></textarea>
          </td>
        </tr>
        <tr>
       	  <td>&nbsp;</td>
        </tr>
        <tr>
       	  <td>&nbsp;</td>
          <td >
            <INPUT TYPE="submit" value=" Create " />
            <INPUT TYPE="reset" />
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
