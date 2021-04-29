package com.simran.models;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class User
{
    String id;
    String name;
    Gender gender;
    int age;
    List<Vehicle> vehicles;

    public String getId() {
        return id;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }


    public User(String name, Gender gender, int age) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.vehicles = new LinkedList<>();
    }


    public void addVehicle(String name, String regNo, int seats)
    {
        Vehicle vehicle = new Vehicle(name,regNo,seats);
        this.vehicles.add(vehicle);
    }

    public String getName()
    {
        return name;
    }
}
