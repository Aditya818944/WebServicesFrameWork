package bobby.car.service;
import com.thinking.machines.webcore.annotations.*;
@Path("/CarService")
public class CarService
{
@Path("/carDSLoading")
@Onstartup(1)
public void carDSLoading()
{
System.out.println("car ds loading :");
}
@Path("/serviceCar")
@Get
public void serviceCar()
{
System.out.println("Car serviced : ");
}
@Path("/washCar")
@Post
public void washCar()
{
System.out.println("Car washed : ");
}
@Path("/runCar")
public void runCar()
{
System.out.println("Car runned :");
}
}