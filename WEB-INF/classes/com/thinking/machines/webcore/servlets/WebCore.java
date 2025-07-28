package com.thinking.machines.webcore.servlets;
import javax.servlet.http.*;
public class WebCore extends HttpServlet
{
public void doGet(HttpServletRequest request,HttpServletResponse response)
{
System.out.println("doGet of WebCore(servlet) got invoked  : ");
}
public void doPost(HttpServletRequest request,HttpServletResponse response)
{
System.out.println("doPost of WebCore(servlet) got invoked :");
}
}
