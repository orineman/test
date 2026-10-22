<%@ taglib uri="/WEB-INF/tlds/c4.tld" prefix="c4" %>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt" %>
<%@ taglib prefix="c" uri="/WEB-INF/tlds/c.tld" %>


<%-- retrieve the resource in the request parameter 'resource' --%>
<c4:Resource name="help/${param.page}" localized="true"/>

