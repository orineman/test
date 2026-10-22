<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/tlds/c4.tld" prefix="c4" %>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt" %>
<%@ taglib prefix="c" uri="/WEB-INF/tlds/c.tld" %>

<TABLE id="Table11" align="center" cellSpacing="0" cellPadding="0" width="95%" border="0">
						<tr>
							<td background='x-res/GRADIENT_2.GIF' class=toolbar_heading align="center">
								<h1>
                  <fmt:message key="msg.welcome"/>&nbsp;
                  <c:if test="${infowave_sessionState.userDTO != null}">
                    <c:out value="${infowave_sessionState.userDTO.username}"/>
                  </c:if>
                </h1>
							</td>
						</tr>
</table>
<c4:Resource name="welcome.htm" localized="true"/>
