<%
if(request.getScheme().equals("http"))
{
    response.sendRedirect("https://llg.ll2go.com/index.jsp");
}
else
{
  ServletContext ctx = request.getSession().getServletContext();
  response.sendRedirect("/protected/showMainPage.do");
  //response.sendRedirect("/showLoginPage.do?action=protected");
  /*
  RequestDispatcher ds =
              ctx.getRequestDispatcher("/protected/showMainPage.do");
      ds.forward(request,response);
      */
}
%>
