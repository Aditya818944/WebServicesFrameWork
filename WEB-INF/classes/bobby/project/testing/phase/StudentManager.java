package bobby.project.testing.phase;
import com.thinking.machines.webcore.annotations.*;
@Path("/StudentManager")
@Get
public class StudentManager
{
@AutoWired("abc")
private String name;
@AutoWired("def")
private Integer rollNumber;
@AutoWired("fgh")
private String addres;
@AutoWired("ijk")
private Object obj;
@AutoWired("lmn")
private DepartmentManager dept;



@Path("/studentDSLoading")
@Onstartup(2)
public void studentDSLoading()
{
System.out.println("student ds loading :");
}

@Path("/addStudent")
@Forward("/StudentManager/updateStudent")
public void addStudent()
{
System.out.println("Student is added : ");
}
@Path("/updateStudent")
@Forward("/StudentManager/updateStudent")
public void updateStudent()
{
System.out.println("Student is updated : ");
}
@Path("/deleteStudent")
@Forward("/DepartmentManager/addDepartment")
public void deleteStudent()
{
System.out.println("Student is deleted : ");
}
}