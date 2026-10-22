<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>



    <table class="siongtable" cellspacing="0" cellpadding="0">

        <tr>
          <td width="350" bgcolor="#e8e8e8" class="style4"><p class="style4">
            <a class="content_link" href="/RAssist/protected/SearchForCompany.do?nextaction=listallcompanies"><fmt:message key="companymenu.listitem.listall"/></a>
          </p></td>
        </tr>
        <tr>
          <td width="350" bgcolor="#e8e8e8" class="style4"><p class="style4">
            <a class="content_link" href="/RAssist/protected/SetType.do?type=locatecompany"><fmt:message key="companymenu.listitem.locate"/></a>
          </p></td>
        </tr>
        <tr>
          <td width="350" bgcolor="#e8e8e8" class="style4"><p class="style4">
            <a href="/RAssist/protected/SetType.do?type=editcompany" class="content_link" ><fmt:message key="companymenu.listitem.edit"/></a>
          </p></td>
        </tr>
        <tr>
          <td width="350" bgcolor="#e8e8e8" class="style4"><p class="style4">
            <a class="content_link" href="/RAssist/protected/SetType.do?type=addcompany"><fmt:message key="companymenu.listitem.add"/></a>
          </p></td>
        </tr>
        <tr>
          <td width="350" bgcolor="#e8e8e8" class="style4"><p class="style4">
            <a class="content_link" href="/RAssist/protected/SetType.do?type=deletecompany"><fmt:message key="companymenu.listitem.delete"/></a>
          </p></td>
        </tr>
        <tr>
          <td width="350" bgcolor="#e8e8e8" class="style4"><p class="style4">
            &nbsp;
          </p></td>
        </tr>
   </table>


