package com.simran.strategies;

import com.simran.models.Ride;

import java.util.List;

public class EarliestRideSelectionStrategy implements IRideSelectionStrategy{

    @Override
    public Ride getRide(List<Ride> rides) {
        return rides.get(0);
    }
}
