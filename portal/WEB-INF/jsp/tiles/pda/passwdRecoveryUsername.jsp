<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt" %>



<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c" %>



<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>



<html:form action="getSecurityQuestion" method="post">



	<table cellspacing="0" cellpadding="0" width="100%" id="BodyBar">



  	<tr align="center">



			<td colspan="2" class="body_title">



			   <strong><fmt:message bundle="${c4p_resellerBundle}" key="title.passwordRecovery"/></strong>



			</td>



   	</tr>



  	<tr align="center" class="body_text">



			<td colspan="2">&nbsp;</td>



   	</tr>



		<tr align="center">



      <td colspan="2" class="body_heading">



				<strong><fmt:message key="title.passwordRecovery.username"/></strong>



				<p>



			</td>



		</tr>



		<tr>



      <td align="center" colspan="2"><span class="body_heading"><fmt:message key="field.username"/>:&nbsp;</span><span class="body_text"> 



        <input name="username" size="20" maxlength="20" >



      </span>



		</tr>



		<tr><td>&nbsp;</td></tr>



		<tr>



		   <td class="body_text" colspan="2" align="center">



		     <input type="submit" name="try" value='<fmt:message key="action.continue"/>'>



		   </td>



		</tr>



	</table>



</html:form>



