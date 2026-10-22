<%
  ServletContext ctx = request.getSession().getServletContext();
  response.sendRedirect("/RAssist/showLoginPage.do?action=protected");
%>

