package com.simran.models;

import com.simran.exceptions.SeatNotAvailableException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Ride
{
    String id;
    String userId;
    String vehicleId;
    int availableSeats;
    List<String> passengers;
    Location origin;
    Location destination;
    LocalDateTime startTime;
    int duration;

    @Override
    public String toString() {
        return "Ride{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", vehicleId='" + vehicleId + '\'' +
                ", availableSeats=" + availableSeats +
                ", passengers=" + passengers +
                ", origin=" + origin +
                ", destination=" + destination +
                ", startTime=" + startTime +
                ", duration=" + duration +
                '}';
    }


    public String getId() {
        return id;
    }

    public void addPassenger(String passenger)
    {
        if(this.availableSeats==0)
        {
            throw new SeatNotAvailableException();
        }
        this.passengers.add(passenger);
        this.availableSeats--;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public Location getOrigin() {
        return origin;
    }

    public Location getDestination() {
        return destination;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public int getDuration() {
        return duration;
    }


    public Ride(String userId, String vehicleId, int availableSeats, Location origin, Location destination, LocalDateTime startTime, int duration)
    {
        this.id = UUID.randomUUID().toString();
        this.userId = userId;
        this.vehicleId = vehicleId;
        this.availableSeats = availableSeats;
        this.origin = origin;
        this.destination = destination;
        this.startTime = startTime;
        this.duration = duration;
        this.passengers = new ArrayList<>();
    }


}
