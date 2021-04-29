package com.simran.models;

import java.util.UUID;

public class Vehicle
{

    String id;
    String name;
    String regNo;
    int seats;

    public String getId() {
        return id;
    }


    public String getName() {
        return name;
    }


    public Vehicle(String name, String regNo, int seats)
    {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.regNo = regNo;
        this.seats = seats;
    }

}
