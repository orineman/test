<%@ taglib prefix="c" uri="/WEB-INF/tlds/c.tld" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/tlds/fmt.tld" %>
<%@ taglib prefix="bean" uri="/WEB-INF/tlds/struts-bean.tld" %>



<TABLE class="header_bar" cellspacing="0" cellpadding="0" width="100%" id="HeaderBar">
  <TR>
    <td colspan="3" width="100%">
    <!-- header -->
      <table class="header_bar_light" cellSpacing="0" cellPadding="0" width="100%">
        <tr class="header_bar_dark">
          <td class="header_bar_light" width="160" ><img src="x-res/logo.gif" border="0" height=50></td>
          <td align="left" valign="bottom"><img src="x-res/watermark.gif" ></td>
          <%--                                           --%>
          <%-- A list of display supported display modes --%>
          <%--                                           --%>
        </tr>
        <tr>
          <td align="center" valign="middle" background='x-res/GRADIENT_1.GIF'>
<!--            <a href=<c:out value="${infowave_sessionState.reseller.expiryUrl}"/> target="_blank"><img src="x-res/BUYNOW.GIF" width="99" height="13" border="0"></a> -->
		&nbsp;
		</td>
          <!-- The page tabs here -->
          <td align="left" valign="bottom" background='x-res/GRADIENT_1.GIF'>
            <table class="tabstrip" cellpadding="0" cellspacing="0" border="0">
              <tr>
                <%-- Desktop tab --%>

                <c:if test="${infowave_sessionState.inDesktopSection}">
                  <td><img src="x-res/tableft_s.gif"></td>
                  <td class="tabstrip_sel">
                    <fmt:message key="title.myPCs"/>
                  </td>
                </c:if>
                <c:if test="${not infowave_sessionState.inDesktopSection}">
                  <td><img src="x-res/tableft_u.gif"></td>
                  <td class="tabstrip_unsel">
                    <a href="/protected/showMainPage.do"><fmt:message key="title.myPCs"/></a>
                  </td>
                </c:if>
                <%-- Tab separator --%>
                <c:choose>
                  <%-- Desktop selected --%>
                  <c:when test="${infowave_sessionState.inDesktopSection}">
                    <td><img src='x-res/tabdiv_su.gif'></td>
                  </c:when>
                  <%--  Account selected--%>
                  <c:when test="${infowave_sessionState.inAccountSection}">
                    <td><img src='x-res/tabdiv_us.gif'></td>
                  </c:when>
                  <%-- Neither is selected --%>
                  <c:otherwise>
                   <td><img src='x-res/tabdiv_uu.gif'></td>
                  </c:otherwise>
                </c:choose>

                <%-- Account tab --%>
                <c:if test="${infowave_sessionState.inAccountSection}">
                  <td class="tabstrip_sel">
                    <fmt:message key="title.myAccount"/>
                  </td>
                </c:if>
                <c:if test="${not infowave_sessionState.inAccountSection}">
                  <td class="tabstrip_unsel">
                    <a class="tabstrip" href="/protected/showAccountManagementPage.do"><fmt:message key="title.myAccount"/></a>
                  </td>
                </c:if>

                <%-- Tab separator --%>
                <c:choose>
                  <%-- Desktop selected --%>
                  <c:when test="${infowave_sessionState.inAccountSection}">
                    <td><img src='x-res/tabdiv_su.gif'></td>
                  </c:when>
                  <%--  Account selected--%>
                  <c:when test="${infowave_sessionState.inHelpSection}">
                    <td><img src='x-res/tabdiv_us.gif'></td>
                  </c:when>
                  <%-- Neither is selected --%>
                  <c:otherwise>
                    <td><img src='x-res/tabdiv_uu.gif'></td>
                  </c:otherwise>
                </c:choose>

                <%-- tab div --%>
                <c:if test="${infowave_sessionState.inHelpSection}">
                  <td class="tabstrip_sel">
                    <fmt:message key="title.help"/>
                  </td>
                  <td><img src="x-res/tabright_s.gif" border="0"></td>
                </c:if>
                <c:if test="${not infowave_sessionState.inHelpSection}">
                  <td class="tabstrip_unsel">
                    <a class="tabstrip" href="/help.do?page=main.htm"><fmt:message key="title.help"/></a>
                  </td>
                  <td><img src="x-res/tabright_u.gif" border="0"></td>
                </c:if>
				<TD background='x-res/GRADIENT_1.GIF'>&nbsp;</TD>


              </tr>
            </table>
            <%-- If we have a user in the session, show a logout link --%>
          </td>
            <c:if test="${infowave_sessionState.userDTO != null}">
              <td colspan="4" align="right" valign="middle" background='x-res/GRADIENT_1.GIF'>
                <a href="/logout.do" class="tabstrip"><fmt:message key="action.logout"/>&nbsp;
                    <c:out value="${infowave_sessionState.userDTO.username}"/>
                  </a>&nbsp;&nbsp;&nbsp;
              </td>
            </c:if>
        </tr>
      </table>
    </td>
  </tr>
</table>


<%--
<table bgcolor="#C0C0C0" cellspacing="2" cellpadding="2" border="0" width="100%">
  <tr>
    <td align="center" > Hello &nbsp

      <c:out value="${infowave_sessionState.userDTO.firstName}"/>&nbsp
    <c:out value="${infowave_sessionState.userDTO.lastName}"/>
&nbsp &nbsp<a href="/showLoginPage.do?action=logout">(logout)</a>
--%>
    <%--
    <bean:write name="infowave_userDTO" property="username"/> </td>
    --%>
