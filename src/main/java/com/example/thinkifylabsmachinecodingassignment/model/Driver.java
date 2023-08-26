package com.example.thinkifylabsmachinecodingassignment.model;

public class Driver extends BaseModel {
    private String name;
    private Gender gender;
    private int age;
    private String vehicleInfo;
    private Location location;
    private DriverStatus status;

    public Driver(String name, Gender gender, int age, String vehicleInfo, Location location) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.vehicleInfo = vehicleInfo;
        this.location = location;
        this.status = DriverStatus.AVAILABLE;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getVehicleInfo() {
        return vehicleInfo;
    }

    public void setVehicleInfo(String vehicleInfo) {
        this.vehicleInfo = vehicleInfo;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public DriverStatus getStatus() {
        return status;
    }

    public void setStatus(DriverStatus status) {
        this.status = status;
    }

    public void update(Driver driver) {
        this.name = driver.getName();
        this.gender = driver.getGender();
        this.age = driver.getAge();
        this.vehicleInfo = driver.getVehicleInfo();
        this.location = driver.getLocation();
        this.status = driver.status;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "name='" + name + '\'' +
                ", gender=" + gender +
                ", age=" + age +
                ", vehicleInfo='" + vehicleInfo + '\'' +
                ", location=" + location +
                ", status=" + status +
                '}';
    }
}
