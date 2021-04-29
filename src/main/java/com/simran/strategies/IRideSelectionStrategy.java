package com.simran.strategies;

import com.simran.models.Ride;

import java.util.List;

public interface IRideSelectionStrategy
{
    Ride getRide(List<Ride> rides);
}
