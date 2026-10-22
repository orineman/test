<LINK REL=StyleSheet HREF="x-res/style.css" TYPE="text/css">
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
  <br>
  <html:form action="changeIdentity" method="post" >
    <table width="95%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td colspan="2" class="content_title" valign="middle"><img src="x-res/ARROW.GIF">&nbsp;<fmt:message key="title.changeAccountInfo"/></td>
      </tr>
      <tr>
        <td class="shaded_header" colspan="2"><fmt:message key="title.manageProfile"/></td>
      </tr>
      <tr>
        <td class="shaded_text" colspan="2">&nbsp;</td>
      </tr>
      <tr>
        <td class="shaded_caption"><fmt:message key="field.username"/>:</td>
        <td class="shaded_text"><c:out value="${infowave_sessionState.userDTO.username}"/></td>
      </tr>
      <tr>
        <td class="shaded_caption"><fmt:message key="field.firstName"/>:</td>
        <td class="shaded_text">
          <input type="text" style="content_input" size="40" maxlength="50" name="firstName"
            value='<c:out value="${infowave_sessionState.userDTO.firstName}"/>'/>
        </td>
      </tr>
      <tr>
        <td class="shaded_caption"><fmt:message key="field.lastName"/>:</td>
        <td class="shaded_text">
          <input type="text" style="content_input" size="40" maxlength="50" name="lastName"
            value='<c:out value="${infowave_sessionState.userDTO.lastName}"/>'/>
        </td>
      </tr>
      <tr>
        <td class="shaded_caption"><fmt:message key="field.emailAddress"/>:</td>
        <td class="shaded_text">
          <input type="text" style="content_input" size="40" maxlength="64" name="email"
            value='<c:out value="${infowave_sessionState.userDTO.email}"/>'/>

        </td>
      </tr>
	<td class="shaded_caption"><fmt:message key="field.languagePreference"/></td>
        <td class="shaded_text">
	    <select size="1" name="locale">
            <c:choose>
            <c:when test="${infowave_sessionState.userDTO.localeLanguage eq \"en\"}"> 
            <option value="en" selected ><fmt:message key="field.English"/></option>
            </c:when>
            <c:otherwise>
            <option value="en" ><fmt:message key="field.English"/></option>
            </c:otherwise>
            </c:choose>

            <c:choose>
            <c:when test="${infowave_sessionState.userDTO.localeLanguage eq \"de\"}"> 
            <option value="de" selected ><fmt:message key="field.German"/></option>
            </c:when>
            <c:otherwise>
            <option value="de" ><fmt:message key="field.German"/></option>
            </c:otherwise>
            </c:choose>

            <c:choose>
            <c:when test="${infowave_sessionState.userDTO.localeLanguage eq \"fr\"}"> 
            <option value="fr" selected ><fmt:message key="field.French"/></option>
            </c:when>
            <c:otherwise>
            <option value="fr" ><fmt:message key="field.French"/></option>
            </c:otherwise>
            </c:choose>

            <c:choose>
            <c:when test="${infowave_sessionState.userDTO.localeLanguage eq \"it\"}"> 
            <option value="it" selected ><fmt:message key="field.Italian"/></option>
            </c:when>
            <c:otherwise>
            <option value="it" ><fmt:message key="field.Italian"/></option>
            </c:otherwise>
            </c:choose>

        </td>
      </tr>
      <tr>

      <tr>
        <td class="shaded_caption"></td>
        <td class="shaded_text" >
           <input type="submit" name="submit" value='<fmt:message key="action.save"/>'/></td>
          </td>
      </tr>
    </table>
  </html:form>
<br>
<form>
  <table width="95%" border="0" cellpadding="0" cellspacing="0">
    <tr class="shaded_header">
      <td colspan="2"><fmt:message key="msg.yourPCs"/></td>
      <td colspan="7" align="right">
      </td>
    </tr>
    <tr align="center" class="shaded_heading">
    <td><fmt:message key="field.pcName"/></td>
    <td><fmt:message key="field.enabled"/></td>
    <td><fmt:message key="field.softwareVersion"/></td>
    <td><fmt:message key="field.license"/></td>
    <td><fmt:message key="field.expiryDate"/></td>
    <td>&nbsp;</td>
    <td><fmt:message key="msg.settings"/></td>


      <td colspan="2" align="center"><fmt:message key="msg.pcLicenses"/></td>
    </tr>
    <c:forEach items="${infowave_sessionState.userDesktops}" var="desktop">


    <tr align="center" class="shaded_text">
      <td><c:out value="${desktop.name}"/></td>
      <td>
      <c:choose>
        <c:when test="${desktop.visible}">
          <fmt:message key="msg.yes"/>
        </c:when>
        <c:when test="${!desktop.visible}">
          <fmt:message key="msg.no"/>
       </c:when>
     </c:choose>
      </td>
      <td>
      <c:choose>
        <c:when test="${desktop.softwareVersion != null}">
          <c:out value="${desktop.softwareVersion}"/>
        </c:when>
        <c:otherwise>
          <fmt:message key="msg.notAvailableAbbreviation"/>
        </c:otherwise>
      </c:choose>
      </td>
      <td>
      <c:choose>
        <c:when test="${desktop.licenseSerialNumber == null}">
        <fmt:message key="msg.unsubscribed"/>
        </c:when>
        <c:when test="${desktop.licenseSerialNumber != null}">
          <fmt:message key="msg.subscribed"/>
       </c:when>
      </c:choose>
      </td>
      <td>
        <c:choose>
          <c:when test="${desktop.expiryDate == null}">
            &nbsp;
          </c:when>
          <c:when test="${desktop.expiryDate != null}">
            <fmt:formatDate value="${desktop.expiryDate}"/>
          </c:when>
        </c:choose>
     </td>
      <td>&nbsp;</td>
      <td>
        <c:url value="/forward.do" var="editDesktopUrl">
  	      <c:param name="page" value="editDesktop"/>
          <c:param name="did" value="${desktop.did}"/>
          <c:param name="visible" value="${desktop.visible}"/>
		  <c:param name="licensed" value="${desktop.licensed}"/>
          <c:param name="name" value="${desktop.name}"/>
        </c:url>
        <c:url value="/protected/showEnterLicenseCodePage.do" var="enterLicenseCodeUrl">
          <c:param name="did" value="${desktop.did}"/>
        </c:url>
        <c:url value="/protected/forward.do" var="unlicenseDeskopUrl">
          <c:param name="page" value="unlicenseDesktop"/>
          <c:param name="did" value="${desktop.did}"/>
        </c:url>

        <input type="button" value='<fmt:message key="action.editDesktop"/>'
           onClick="devEdit = window.open('<c:out value="${editDesktopUrl}"/>','Edit','scrollbars=no,resizable=no,width=350,height=120');if (devEdit.opener == null) devEdit.opener = self;return false">
      </td>
      <td>
        <input type="button" value="<fmt:message key="action.setLicense"/>" onClick="devReg = window.open('<c:out value="${enterLicenseCodeUrl}"/>','License','scrollbars=no,resizable=no,width=400,height=220');if (devReg.opener == null) devReg.opener = self;return false">
      </td>
        <td>
        <c:choose>
          <c:when test="${!desktop.licensed}">
            &nbsp;
          </c:when>
          <c:when test="${desktop.licensed}">
          <input type="button" value='<fmt:message key="action.unlicense"/>' onClick="devReg = window.open('<c:out value="${unlicenseDeskopUrl}"/>','Unlicense','scrollbars=no,resizable=no,width=400,height=180');if (devReg.opener == null) devReg.opener = self;return false">
          </c:when>
        </c:choose>

        </td>
    </tr>
   </c:forEach>

  </table>
</form>


