package bobby.project.testing.phase;
import com.thinking.machines.webcore.annotations.*;
@Path("/StudentManager")
public class StudentManager
{
@Path("/addStudent")
public void addStudent()
{
System.out.println("Student is added : ");
}
@Path("/updateStudent")
public void updateStudent()
{
System.out.println("Student is updated : ");
}
@Path("/deleteStudent")
public void deleteStudent()
{
System.out.println("Student is deleted : ");
}
}