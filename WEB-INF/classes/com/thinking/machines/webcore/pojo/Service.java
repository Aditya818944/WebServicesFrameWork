package com.thinking.machines.webcore.pojo;
import java.lang.reflect.*;
public class Service 
{
private Class serviceClass;
private String path;
private Method service;
public void setServiceClass(Class serviceClass)
{
this.serviceClass=serviceClass;
}
public Class getServiceClass()
{
return this.serviceClass;
}
public void setPath(String path)
{
this.path=path;
}
public String getPath()
{
return this.path;
}
public void setService(Method service)
{
this.service=service;
}
public Method getService()
{
return this.service;
}
}