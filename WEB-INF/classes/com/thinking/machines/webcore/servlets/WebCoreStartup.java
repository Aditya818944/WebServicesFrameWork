package com.thinking.machines.webcore.servlets;
import java.util.*;
import java.io.*;
import java.lang.reflect.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.thinking.machines.webcore.annotations.*;
import com.thinking.machines.webcore.model.*;
import com.thinking.machines.webcore.pojo.*;
public class WebCoreStartup extends HttpServlet
{
public static void startupServicesExecuter(Map<Integer,Service> startupServices,ServletContext servletContext)
{
try
{
Class clss;
Method method;
List<Service> list=new ArrayList<>();

startupServices.forEach((k,service)->{
list.add(service);
});

for(Service service : list)
{
clss=service.getServiceClass();
Object obj=clss.newInstance();


if(service.getInjectApplicationScope())
{
ApplicationScope applicationScope=new ApplicationScope();
applicationScope.setServletContext(servletContext);
method=clss.getMethod("setApplicationScope",ApplicationScope.class);
method.invoke(obj,applicationScope);
}

method=service.getService();
if(method.getReturnType().getName().equalsIgnoreCase("void")==false) {
System.out.println("Service return type should be [void] ");
break;
}else if(method.getParameterTypes().length!=0){
System.out.println("Service should not have any parameters :");
break;
}else method.invoke(obj);
}
System.out.println("All startups methods are executed , without any error :");
}catch(Exception exception)
{
System.out.println("Exception in startupServiceExecuter "+exception);
}
}
public void init()
{
/***********Datastructure to store services ***********/
WebCoreModel webCoreModel=new WebCoreModel();
/*****************************************************/
Map<Integer,Service> treeMap=new TreeMap<>();
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
Class clss=Class.forName(str);
if(!clss.isAnnotationPresent(Path.class)) continue;
Path classPath=(Path)clss.getAnnotation(Path.class);

String requestType="";

if(clss.isAnnotationPresent(Get.class)) requestType="Get";
if(clss.isAnnotationPresent(Post.class)) requestType="Post";



boolean autoWired=false;


for(Field field : clss.getDeclaredFields())
{
if(field.isAnnotationPresent(AutoWired.class))
{
autoWired=true;
break;
}
}




Service service=null;
for(Method method : clss.getMethods())
{


if(!method.isAnnotationPresent(Path.class)) continue; 
Path methodPath=method.getAnnotation(Path.class);

String methodRequestType=""; 
if(method.isAnnotationPresent(Get.class)) methodRequestType="Get";
else  if(method.isAnnotationPresent(Post.class)) methodRequestType="Post";


service=new Service();
service.setServiceClass(clss);
service.setPath(classPath.value()+methodPath.value());


service.setAutoWired(autoWired);
                                           
service.setInjectApplicationDirectory(clss.isAnnotationPresent(InjectApplicationDirectory.class));
service.setInjectApplicationScope(clss.isAnnotationPresent(InjectApplicationScope.class));
service.setInjectSessionScope(clss.isAnnotationPresent(InjectSessionScope.class));
service.setInjectRequestScope(clss.isAnnotationPresent(InjectRequestScope.class));



if(method.isAnnotationPresent(Forward.class)) service.setForwardPath(((Forward)method.getAnnotation(Forward.class)).value());
else service.setForwardPath(null);

if(method.isAnnotationPresent(Onstartup.class)){ 
service.setRunOnStartup(true);
service.setPriority(((Onstartup)method.getAnnotation(Onstartup.class)).value());
treeMap.put(service.getPriority(),service);
}
else {
service.setRunOnStartup(false);
service.setPriority(-1);
}

service.setService(method);
service.setClassRequestType(requestType);
service.setServiceRequestType(methodRequestType);
webCoreModel.services.put(service.getPath(),service);
}
}
}
}
/***********Now putting , our webCoreModel into application scope *****************/
getServletContext().setAttribute("APPLICATION_RELATED_DATASTRUCTURE",webCoreModel);
/**********************************************************************************/
System.out.println("- - - - - - - - - \n");
}
startupServicesExecuter(treeMap,getServletContext());
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