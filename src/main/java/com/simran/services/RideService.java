package com.simran.services;

import com.simran.exceptions.NoRideAvailableException;
import com.simran.exceptions.UserNotFoundException;
import com.simran.models.Location;
import com.simran.models.Ride;
import com.simran.models.RideType;
import com.simran.strategies.IRideSelectionStrategy;
import com.simran.strategies.RideSelectionStrategyFactory;

import java.time.LocalDateTime;
import java.util.*;

public class RideService
{
    Map<String, Ride> rideMap;
    Map<String, List<String>> ridesOfferedByUserId;
    Map<String, List<String>> ridesTakenByUserId;
    private RideSelectionStrategyFactory rideSelectionStrategyFactory;

    private UserService userService;

    public RideService(UserService userService,RideSelectionStrategyFactory rideSelectionStrategyFactory)
    {
        this.rideMap = new HashMap<>();
        this.ridesOfferedByUserId = new HashMap<>();
        this.ridesTakenByUserId = new HashMap<>();
        this.userService = userService;
        this.rideSelectionStrategyFactory = rideSelectionStrategyFactory;
    }

   public String offerRide(String userId, String vehicleId, int availableSeats, Location origin, Location destination, LocalDateTime startTime, int duration)
    {
        Ride ride = new Ride(userId,vehicleId,availableSeats,origin,destination,startTime,duration);
        rideMap.put(ride.getId(),ride);
        if(!ridesOfferedByUserId.containsKey(userId))
        {
           ridesOfferedByUserId.put(userId,new ArrayList<>());
        }
        List<String> rideIds = ridesOfferedByUserId.get(userId);
        rideIds.add(ride.getId());
        ridesOfferedByUserId.put(userId,rideIds);
        return ride.getId();
    }

   public Ride selectRide(String userId, Location origin, Location destination, int requiredSeats, RideType rideType)
    {
        List<Ride> rides = new LinkedList<>();
        Set<String> keys = this.rideMap.keySet();
        for(String key: (Iterable<String>) keys)
        {
            Ride ride = rideMap.get(key);
            if(ride.getOrigin().equals(origin) && ride.getDestination().equals(destination)
                    && ride.getAvailableSeats() >= requiredSeats)
            {
                rides.add(ride);
            }
        }

        if(rides.isEmpty())
        {
            throw new NoRideAvailableException();
        }

        IRideSelectionStrategy rideSelectionStrategy = this.rideSelectionStrategyFactory.selectStrategy(rideType);
        Ride ride = rideSelectionStrategy.getRide(rides);
        if(!ridesTakenByUserId.containsKey(userId))
        {
            ridesTakenByUserId.put(userId,new ArrayList<>());
        }
        List<String> rideIds = ridesTakenByUserId.get(userId);
        rideIds.add(ride.getId());
        ridesTakenByUserId.put(userId,rideIds);
        this.rideMap.get(ride.getId()).addPassenger(userId);
        return ride;
    }

    public List<String> getRidesTakenByUserId(String userId)
    {
        if(!ridesTakenByUserId.containsKey(userId))
        {
            throw new UserNotFoundException();
        }
        List<String> rideIds = this.ridesTakenByUserId.get(userId);
        return rideIds;
    }

    public List<String> getRidesOfferedByUserId(String userId)
    {
        if(!ridesOfferedByUserId.containsKey(userId))
        {
            throw new UserNotFoundException();
        }
        List<String> rideIds = this.ridesOfferedByUserId.get(userId);
        return rideIds;
    }

}
