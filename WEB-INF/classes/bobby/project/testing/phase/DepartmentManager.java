package bobby.project.testing.phase;
import com.thinking.machines.webcore.annotations.*;
@Path("/DepartmentManager")
public class DepartmentManager 
{
@Path("/addDepartment")
public void addDepartment()
{
System.out.println("Department is added :");
}
@Path("/updateDepartment")
public void updateDepartment()
{
System.out.println("Department is updated :");
}
@Path("/deleteDepartment")
public void deleteDepartment()
{
System.out.println("Department is deleted :");
}
}