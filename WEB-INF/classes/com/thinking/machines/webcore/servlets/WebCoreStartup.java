package com.thinking.machines.webcore.servlets;
import java.util.*;
import java.io.*;
import java.lang.reflect.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class WebCoreStartup extends HttpServlet
{
public void init()
{
String mainPathString="c:"+File.separator+"tomcat9"+File.separator+"webapps"+File.separator+"webcore"+File.separator+"WEB-INF"+File.separator+"classes"+File.separator;

String servicePackagePrefix=getServletConfig().getInitParameter("SERVICE_PACKAGE_PREFIX");
try
{
File servicePackagePrefixDirectory=new File(mainPathString+servicePackagePrefix);

if(servicePackagePrefixDirectory.isFile()==false && servicePackagePrefixDirectory.isDirectory()==false) System.out.println("Given service package prefix : ["+servicePackagePrefix+"] is invalid ");
Stack<File> directories=new Stack<>();
directories.push(servicePackagePrefixDirectory);


while(directories.isEmpty()==false)
{
File file=directories.pop();
System.out.println("\n\n- - - - - - - - - \n");
System.out.println(file.getName());
for(File directory : file.listFiles())
{
if(directory.isDirectory()) directories.push(directory);
else{
String str=directory.getPath().substring(directory.getPath().indexOf(servicePackagePrefix));
if(str.endsWith(".class")){
str=str.substring(0,str.indexOf(".class")).replace(File.separator,".");
/*************Start from there ***************************
Class clss=Class.forName(str);
System.out.println(clss.getName());
Object obj=clss.newInstance();
**********************************************************/
}

}
}
System.out.println("- - - - - - - - - \n");
}

 

    
}
catch(Exception exception)
{
System.out.println(exception);
}
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