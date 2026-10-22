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

      <html:form action="/protected/getLicenseCode" method="post">

      <input type="hidden" name="did" value='<c:out value="${param.did}"/>' >

      <input type="hidden" name="expired" value='<c:out value="${requestScope.expired}"/>' >





      <table class="shaded_text" width="100%" border="0" cellspacing="0" cellpadding="0">

        <tr>

          <td>

            <c:if test="${requestScope.expired == false}">

              <b><font color="blue">

                <fmt:message key="msg.licenseHasNotExpiredWarning"/>

              </font></b>

            </c:if>

          </td>

        </tr>

        <tr>

          <td>&nbsp;</td>

        </tr>

        <tr>

          <td><fmt:message key="msg.pleaseEnterUnusedLicense"/></td>

        </tr>

        <tr>

          <td>

          	<fmt:message key="action.setLicense"/>:&nbsp;



            <c4:LicenseCodeEntry code="${param.code}"

                                 namePrefix="group"

                                 displayClass="content_input"/>

          </td>

        </tr>

        <tr>

          <td>&nbsp;</td>

        </tr>

        <tr>

          <td><fmt:message  bundle="${c4p_resellerBundle}" key="msg.helpForGettingAlicenseCode"/></td>

        </tr>

        <tr>

          <td>

            <fmt:message  bundle="${c4p_resellerBundle}" key="msg.toPurchaseALicenseCode">

              <fmt:param value="${infowave_sessionState.reseller.expiryUrl}"/>

            </fmt:message>





            </td>

        </tr>

        <tr>

          <td>&nbsp;</td>

        </tr>

        <tr>

          <td align="center">

            <input type="submit" name="enterLicenseCodeButton" value='<fmt:message key="action.submit"/>'/>&nbsp;

            <input type="button" onClick="cancel()" value='<fmt:message key="action.cancel"/>'/>

          </td>

        </tr>

      </table>

    </html:form>

