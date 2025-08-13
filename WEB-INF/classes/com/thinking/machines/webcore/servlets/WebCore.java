package com.thinking.machines.webcore.servlets;
import com.thinking.machines.webcore.model.*;
import com.thinking.machines.webcore.pojo.*;
import com.thinking.machines.webcore.annotations.*;
import java.lang.reflect.*;
import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
public class WebCore extends HttpServlet
{
public static void forwardRequest(String forwardPath,WebCoreModel webCoreModel,HttpServletResponse response,HttpServletRequest request)
{
PrintWriter pw;
String servicePath=forwardPath;
try
{
response.setContentType("text/plain");
pw=response.getWriter();
Service service=webCoreModel.services.get(forwardPath);

if(service==null) {
RequestDispatcher requestDispatcher=request.getRequestDispatcher(forwardPath);
requestDispatcher.forward(request,response);
return ;
}



Class clss=service.getServiceClass();
Method method=null;
Object obj=clss.newInstance();

if(service.getInjectApplicationScope())
{
ApplicationScope applicationScope=new ApplicationScope();
applicationScope.setServletContext(request.getServletContext());
method=clss.getMethod("setApplicationScope",ApplicationScope.class);
method.invoke(obj,applicationScope);
}else if(service.getInjectSessionScope())
{
SessionScope sessionScope=new SessionScope();
sessionScope.setHttpSession(request.getSession());
method=clss.getMethod("setSessionScope",SessionScope.class);
method.invoke(obj,sessionScope);
}else if(service.getInjectRequestScope())
{
RequestScope requestScope=new RequestScope();
requestScope.setHttpServletRequest(request);
method=clss.getMethod("setRequestScope",RequestScope.class);
method.invoke(obj,requestScope);
}


method=service.getService();


if(method.getReturnType().getName().equalsIgnoreCase("void")==false) pw.print(method.invoke(obj));
else{
method.invoke(obj);
if(service.getForwardPath().length()!=0 && servicePath.equalsIgnoreCase(service.getForwardPath())==false)forwardRequest(service.getForwardPath(),webCoreModel,response,request);
}

}catch(Exception exception)
{
System.out.println("Exception in forwarding request : "+exception);
}
}


/**************doGet start form there ********************/
public void doGet(HttpServletRequest request,HttpServletResponse response)
{
String dataToReturn="";
PrintWriter pw;
try
{
response.setContentType("text/plain");
pw=response.getWriter();


WebCoreModel webCoreModel=(WebCoreModel)getServletContext().getAttribute("APPLICATION_RELATED_DATASTRUCTURE");

String contextPath=request.getContextPath()+"/testingphase"; 
String uri=request.getRequestURI(); 
String pathToService=uri.substring(contextPath.length());

Service service=(Service)webCoreModel.services.get(pathToService);

if(service==null){
response.sendError(HttpServletResponse.SC_NOT_FOUND);
return ;
}




Class clss=service.getServiceClass();

Object obj=clss.newInstance();

Method method=null;

if(service.getInjectApplicationScope())
{
ApplicationScope applicationScope=new ApplicationScope();
applicationScope.setServletContext(request.getServletContext());
method=clss.getMethod("setApplicationScope",ApplicationScope.class);
method.invoke(obj,applicationScope);
}
if(service.getInjectSessionScope())
{
SessionScope sessionScope=new SessionScope();
sessionScope.setHttpSession(request.getSession());
method=clss.getMethod("setSessionScope",SessionScope.class);
method.invoke(obj,sessionScope);
}
if(service.getInjectRequestScope())
{
RequestScope requestScope=new RequestScope();
requestScope.setHttpServletRequest(request);
method=clss.getMethod("setRequestScope",RequestScope.class);
method.invoke(obj,requestScope);
}



if(service.getAutoWired())
{
for(Field field : clss.getDeclaredFields()) if(field.isAnnotationPresent(AutoWired.class))
System.out.println(((AutoWired)field.getAnnotation(AutoWired.class)).value());
} // start from there 



method=service.getService();


if(service.getClassRequestType().equalsIgnoreCase("Get"))
{
// control comes here , it means Get annotation is applied on class 
// now we will check , whether service of this class returning something or not 
if(method.getReturnType().getName().equalsIgnoreCase("void")==false) {
pw.print(method.invoke(obj));
}
else {
method.invoke(obj);
if(service.getForwardPath().length()!=0 && service.getForwardPath().equalsIgnoreCase(pathToService)==false)forwardRequest(service.getForwardPath(),webCoreModel,response,request);
}

}else if(service.getClassRequestType().equalsIgnoreCase("Post")) 
{
response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
return ;
}else{
// here control comes , it means on class ,request type annotation is not applied , so there is
//probablity of having request type annotation on method itself, we will check for it now
if(service.getServiceRequestType().equalsIgnoreCase("get"))
{
if(method.getReturnType().getName().equalsIgnoreCase("void")==false){
pw.print(method.invoke(obj));
}else{
method.invoke(obj);
if(service.getForwardPath().length()!=0 && service.getForwardPath().equalsIgnoreCase(pathToService)==false) forwardRequest(service.getForwardPath(),webCoreModel,response,request);
}

}else if(service.getServiceRequestType().equalsIgnoreCase("post"))
{
response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
return;
}else
{
// control comes here , it means that neither on class and nor on method ,  request type annotation
// is applied , so we can invoke this method
if(method.getReturnType().getName().equalsIgnoreCase("void")==false){
pw.print(method.invoke(obj));
}else
{
method.invoke(obj);
if(service.getForwardPath().length()!=0 && service.getForwardPath().equalsIgnoreCase(pathToService)==false) forwardRequest(service.getForwardPath(),webCoreModel,response,request);
}
}
}
}catch(Exception exception)
{
System.out.println("Web Core doGet Exception "+exception);
}
}



public void doPost(HttpServletRequest request,HttpServletResponse response)
{
String dataToReturn="";
PrintWriter pw;
try
{
response.setContentType("text/plain");
pw=response.getWriter();


WebCoreModel webCoreModel=(WebCoreModel)getServletContext().getAttribute("APPLICATION_RELATED_DATASTRUCTURE");

String contextPath=request.getContextPath()+"/testingphase"; 
String uri=request.getRequestURI(); 
String pathToService=uri.substring(contextPath.length());

Service service=(Service)webCoreModel.services.get(pathToService);

if(service==null){
response.sendError(HttpServletResponse.SC_NOT_FOUND);
return ;
}

Class clss=service.getServiceClass();

Object obj=clss.newInstance();

Method method=null;



if(service.getInjectApplicationScope())
{
ApplicationScope applicationScope=new ApplicationScope();
applicationScope.setServletContext(request.getServletContext());
method=clss.getMethod("setApplicationScope",ApplicationScope.class);
method.invoke(obj,applicationScope);
}
if(service.getInjectSessionScope())
{
SessionScope sessionScope=new SessionScope();
sessionScope.setHttpSession(request.getSession());
method=clss.getMethod("setSessionScope",SessionScope.class);
method.invoke(obj,sessionScope);
}
if(service.getInjectRequestScope())
{
RequestScope requestScope=new RequestScope();
requestScope.setHttpServletRequest(request);
method=clss.getMethod("setRequestScope",RequestScope.class);
method.invoke(obj,requestScope);
}


method=service.getService();


if(service.getClassRequestType().equalsIgnoreCase("Post"))
{
// control comes here , it means Get annotation is applied on class 
// now we will check , whether service of this class returning something or not 
if(method.getReturnType().getName().equalsIgnoreCase("void")==false) {
pw.print(method.invoke(obj));
}
else {
method.invoke(obj);
if(service.getForwardPath().length()!=0 && service.getForwardPath().equalsIgnoreCase(pathToService)==false)forwardRequest(service.getForwardPath(),webCoreModel,response,request);
}

}else if(service.getClassRequestType().equalsIgnoreCase("Get")) 
{
response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
return ;
}else{
// here control comes , it means on class ,request type annotation is not applied , so there is
//probablity of having request type annotation on method itself, we will check for it now
if(service.getServiceRequestType().equalsIgnoreCase("Post"))
{
if(method.getReturnType().getName().equalsIgnoreCase("void")==false){
pw.print(method.invoke(obj));
}else{
method.invoke(obj);
if(service.getForwardPath().length()!=0 && service.getForwardPath().equalsIgnoreCase(pathToService)==false) forwardRequest(service.getForwardPath(),webCoreModel,response,request);
}

}else if(service.getServiceRequestType().equalsIgnoreCase("Get"))
{
response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
return;
}else
{
// control comes here , it means that neither on class and nor on method ,  request type annotation
// is applied , so we can invoke this method
if(method.getReturnType().getName().equalsIgnoreCase("void")==false){
pw.print(method.invoke(obj));
}else
{
method.invoke(obj);
if(service.getForwardPath().length()!=0 && service.getForwardPath().equalsIgnoreCase(pathToService)==false) forwardRequest(service.getForwardPath(),webCoreModel,response,request);
}
}
}
}catch(Exception exception)
{
System.out.println(exception);
}
}




}
