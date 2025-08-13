package com.thinking.machines.webcore.pojo;
import javax.servlet.*;
public class ApplicationScope 
{
private ServletContext servletContext;

public void setAttribute(String attribute,Object value)
{
this.servletContext.setAttribute(attribute,value);
}
public Object getAttribute(String attribute)
{
return this.servletContext.getAttribute(attribute);
}

public void setServletContext(ServletContext servletContext)
{
this.servletContext=servletContext;
}
public ServletContext getServletContext()
{
return this.servletContext;
}
}