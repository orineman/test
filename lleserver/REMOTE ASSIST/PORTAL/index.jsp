<%
  ServletContext ctx = request.getSession().getServletContext();
  //response.sendRedirect("/protected/showMainPage.do");
  response.sendRedirect("/RAssist/showLoginPage.do?action=protected");
  /*
  RequestDispatcher ds =
              ctx.getRequestDispatcher("/protected/showMainPage.do");
      ds.forward(request,response);
      */
%>

