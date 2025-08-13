package com.thinking.machines.webcore.appservlets;
import javax.servlet.*;
import javax.servlet.http.*;
public class CheckerServlet extends HttpServlet
{
public void doGet(HttpServletRequest request,HttpServletResponse response)
{
ServletContext servletContext=request.getServletContext();
String str=(String)servletContext.getAttribute("Attribute");
System.out.println("CheckerServlet , take out value from ApplicationScope , which is : "+str);
System.out.println("doGet of checker got invoked ");
}
public void doPost(HttpServletRequest request,HttpServletResponse response)
{
System.out.println("doPost of checker got invoked ");
}
}