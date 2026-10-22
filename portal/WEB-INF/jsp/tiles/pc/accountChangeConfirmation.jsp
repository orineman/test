<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/tlds/fmt.tld" %>

<LINK REL=StyleSheet HREF="x-res/style.css" TYPE="text/css">
<div class="content_title" align="center">
<br>
<fmt:message>
  <c:out value="${requestScope.c4p_msg}"/>
</fmt:message>
</div>