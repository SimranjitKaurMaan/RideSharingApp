import com.simran.models.*;
import com.simran.services.RideService;
import com.simran.services.UserService;
import com.simran.strategies.RideSelectionStrategyFactory;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.List;

public class RideServiceTest
{
    @Test
    public void defaultTest()
    {
        UserService userService = new UserService();
        String user1 = userService.registerUser("A", Gender.male,20);
        String user2 = userService.registerUser("B", Gender.male,30);
        String user3 = userService.registerUser("C", Gender.male,40);
        String user4 = userService.registerUser("D", Gender.male,50);
        userService.registerVehicle(user1,"V1","V11",4);
        userService.registerVehicle(user2,"V2","V22",2);
        RideService rideService = new RideService(userService, new RideSelectionStrategyFactory());
        rideService.offerRide("A","V1",3,new Location(0,0),new Location(10,10), LocalDateTime.now(),2);
        rideService.offerRide("B","V2",2,new Location(0,0),new Location(10,10), LocalDateTime.now(),2);
        Ride ride1 = rideService.selectRide("C",new Location(0,0),new Location(10,10),1, RideType.earliest);
        System.out.println(ride1);
        Ride ride2 = rideService.selectRide("D",new Location(0,0),new Location(10,10),2,RideType.fastest);
        System.out.println(ride2);
        Ride ride3 = rideService.selectRide("D",new Location(0,0),new Location(10,10),2,RideType.earliest);
        System.out.println(ride3);
        List<String> rideIds = rideService.getRidesOfferedByUserId(user1);
        System.out.println(rideIds);
        System.out.println(rideService.getRidesOfferedByUserId(user2));
        System.out.println(rideService.getRidesTakenByUserId(user3));
        System.out.println(rideService.getRidesTakenByUserId(user4));
    }
}
