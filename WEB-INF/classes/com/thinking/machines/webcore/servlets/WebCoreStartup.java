package com.thinking.machines.webcore.servlets;
import javax.servlet.*;
import javax.servlet.http.*;
public class WebCoreStartup extends HttpServlet
{
public void init()
{
String service_package_prefix=getServletConfig().getInitParameter("SERVICE_PACKAGE_PREFIX");













System.out.println("********************************************\n");
System.out.println("All is well bro , everything is executed without error ");
System.out.println("********************************************\n");
}
public void doGet(HttpServletRequest request,HttpServletResponse response)
{
System.out.println("doGet of WebCoreStartup (startup servlet)  got invoked  : ");
}
public void doPost(HttpServletRequest request,HttpServletResponse response)
{
System.out.println("doPost of WebCoreStartup (startup servlet) got invoked  : ");
}
}