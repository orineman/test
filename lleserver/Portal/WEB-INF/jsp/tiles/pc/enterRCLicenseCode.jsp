<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt" %>

<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c" %>

<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>

<%@ taglib uri="/WEB-INF/tlds/c4.tld" prefix="c4" %>

<script type="text/javascript" language="JavaScript">

<!--

function setTitle(){

document.title='<fmt:message  bundle="${c4p_resellerBundle}" key="title.subscripion"/>';



}



function cancel(){

this.close();

}



// -->

</script>

      <html:form action="/protected/getRCLicenseCode" method="post">

      <input type="hidden" name="did" value='<c:out value="${param.did}"/>' >

      <input type="hidden" name="desktopexpired" value='<c:out value="${requestScope.desktopexpired}"/>' >
      <input type="hidden" name="rcexpired" value='<c:out value="${requestScope.rcexpired}"/>' >





      <table class="shaded_text" width="100%" border="0" cellspacing="0" cellpadding="0">

        <tr>

          <td>

            <c:if test="${requestScope.rcexpired == false}">

              <b><font color="blue">

                <fmt:message key="msg.rclicenseHasNotExpiredWarning"/>

              </font></b>

            </c:if>

          </td>

        </tr>

        <tr>

          <td>&nbsp;</td>

        </tr>

        <tr>

          <td><fmt:message key="msg.rcpleaseEnterUnusedLicense"/></td>

        </tr>

        <tr>

          <td>

          	<fmt:message key="action.rcsetLicense"/>:&nbsp;



            <c4:LicenseCodeEntry code="${param.code}"

                                 namePrefix="group"

                                 displayClass="content_input"/>

          </td>

        </tr>

        <tr>

          <td>&nbsp;</td>

        </tr>

        <tr>

          <td><fmt:message  bundle="${c4p_resellerBundle}" key="msg.helpForGettingAnRClicenseCode"/></td>

        </tr>

        <tr>

          <td>

            <fmt:message  bundle="${c4p_resellerBundle}" key="msg.toPurchaseAnRCLicenseCode">

              <fmt:param><fmt:message key="msg.link.plans"/></fmt:param>

            </fmt:message>





            </td>

        </tr>

        <tr>

          <td>&nbsp;</td>

        </tr>

        <tr>

          <td align="center">

            <input type="submit" name="enterRCLicenseCodeButton" value='<fmt:message key="action.submit"/>'/>&nbsp;

            <input type="button" onClick="cancel()" value='<fmt:message key="action.cancel"/>'/>

          </td>

        </tr>

      </table>

    </html:form>

