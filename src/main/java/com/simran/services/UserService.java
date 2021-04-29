package com.simran.services;

import com.simran.exceptions.UserNotFoundException;
import com.simran.exceptions.VehicleNotFoundException;
import com.simran.models.Gender;
import com.simran.models.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class UserService
{
    Map<String,User> userMap;

    public UserService() {
        this.userMap = new HashMap<>();
    }

    public String registerUser(String name, Gender gender, int age)
    {
      User user = new User(name, gender, age);
      userMap.put(user.getId(),user);
      return user.getId();
    }


    public void registerVehicle(String userId,String vehicleName,String regNo,int seats)
    {
        User user = this.userMap.get(userId);
        user.addVehicle(vehicleName,regNo,seats);
    }

    public String getUserId(String name)
    {
        Set<String> keys = this.userMap.keySet();
        for(String key: (Iterable<String>) keys)
        {
            User user = userMap.get(key);
            if(user.getName().equals(name))
            {
                return user.getId();
            }
        }

        throw new UserNotFoundException();
    }

    public String getVehicleId(String userId, String vehicleName)
    {
        User user = this.userMap.get(userId);
        Optional<String> vehicleId = user.getVehicles().stream().filter(vehicle -> vehicle.getName().equals(vehicleName)).map(vehicle -> vehicle.getId()).findFirst();
        if(Optional.of(vehicleId).isPresent())
        {
            return vehicleId.get();
        }else
        {
            throw new VehicleNotFoundException();
        }
    }

}
