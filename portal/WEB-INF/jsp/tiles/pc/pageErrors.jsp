



<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c" %>

<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt" %>



<c:if test="${not empty requestScope.c4p_errors}">



<table width="100%" border="0" align="center">

  <tr>

    <td width="1%" align="left"><img src="x-res/ERROR.GIF" border="0"/></td>

    <td>

      <table border="0">

        <c:forEach items="${requestScope.c4p_errors}" var="error">

          <tr>

            <td align="left">

              <span class="content_error_heading">

               <fmt:message key="${error.messageKey}">

               <fmt:param value="${error.firstField}"/>

               <fmt:param value="${error.secondField}"/>

               <fmt:param value="${error.thirdField}"/>

               <fmt:param value="${error.fourthField}"/>

               <fmt:param value="${error.fifthField}"/>

               <fmt:param value="${error.sixthField}"/>

               <fmt:param value="${error.seventhField}"/>

               </fmt:message>

               </span>

             </td>

           </tr>

        </c:forEach>

      </table>

    </td>

  </tr>

</table>





</c:if>



