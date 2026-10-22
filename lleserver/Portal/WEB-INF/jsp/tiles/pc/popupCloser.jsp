<%@ page contentType="text/html; charset=UTF-8" %>
<!-- http://www.google.ca/search?q=cache:WdSg6vzgpa8J:www.webreference.com/js/tutorial1/opener.html+javascript+open+window+opener&hl=en&ie=UTF-8 -->
<%@ taglib prefix="c" uri="/WEB-INF/tlds/c.tld" %>
<html>
 <head>
<script type="text/javascript" language="JavaScript">

<!--
function bustOut(){
// Refresh the opener window if we were passed a page in teh request
 <c:if test="${not empty requestScope.page}">
    opener.location.replace('<c:out value="${requestScope.page}"/>');
 </c:if>

// close the popup
this.close();
}
// -->
</script>
</head>

<body onLoad="bustOut();">
should be closed
</body>


</html>

