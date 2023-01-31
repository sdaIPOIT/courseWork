package com.example.course_fx;

public class Driver {
    private int driverId;
    private String name;
    private String age;
    private int runId;
    private int carId;
    private String password;

    public Driver (int driverId, String name, String age, int runId, int carId, String password){
        this.driverId = driverId;
        this.name = name;
        this.age = age;
        this.carId = carId;
        this.runId = runId;
        this.password = password;
    }
    public Driver (String name, String age, int carId, int runId, String password){
        this.name = name;
        this.age = age;
        this.carId = carId;
        this.runId = runId;
        this.password = password;
    }

    public Driver() {
    }

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public int getCarId() {
        return carId;
    }

    public int getRunId() {
        return runId;
    }

    public String getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRunId(int runId) {
        this.runId = runId;
    }
}