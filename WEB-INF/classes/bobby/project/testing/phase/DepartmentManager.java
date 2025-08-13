package bobby.project.testing.phase;
import com.thinking.machines.webcore.annotations.*;
import com.thinking.machines.webcore.pojo.*;
@Path("/DepartmentManager")
@Get
@InjectApplicationScope
public class DepartmentManager 
{
@AutoWired("pqr")
private StudentManager studentManager;
private ApplicationScope applicationScope;
public void setApplicationScope(ApplicationScope applicationScope)
{
this.applicationScope=applicationScope;
}
@Path("/departmentDSLoading")
@Onstartup(3)
public void departmentDSLoading()
{
System.out.println("department ds loading :");
}
@Path("/addDepartment")
@Forward("/checker")
public void addDepartment()
{
applicationScope.setAttribute("Attribute","KuchTouBaakiHai");
System.out.println("Department is added :");
System.out.println("Department is added ");
}
@Path("/updateDepartment")
public String updateDepartment()
{
System.out.println("Department is updated :");
return "Department is updated ";
}
@Path("/deleteDepartment")
public String deleteDepartment()
{
System.out.println("Department is deleted :");
return "Department is deleted ";
}
}