package com.simran.strategies;

import com.simran.models.RideType;

import java.util.HashMap;
import java.util.Map;

public class RideSelectionStrategyFactory
{

    private Map<RideType,IRideSelectionStrategy> rideSelectionStrategyMap;

   public RideSelectionStrategyFactory()
   {
         rideSelectionStrategyMap = new HashMap<>();
         rideSelectionStrategyMap.put(RideType.earliest,new EarliestRideSelectionStrategy());
         rideSelectionStrategyMap.put(RideType.fastest,new FastestRideSelectionStrategy());
   }
   public IRideSelectionStrategy selectStrategy(RideType rideType)
    {
        return  rideSelectionStrategyMap.get(rideType);
    }
}
