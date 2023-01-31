package com.example.course_fx;

public class Car {
    int carId;
    private String carModel;
    private String productionYear;
    private int conditionOfCar;

    public Car(int carId, String carModel, String productionYear, int conditionOfCar){
        this.carId = carId;
        this.carModel = carModel;
        this.productionYear = productionYear;
        this.conditionOfCar = conditionOfCar;
    }

    public Car(String carModel, String productionYear, int conditionOfCar){
        this.carModel = carModel;
        this.productionYear = productionYear;
        this.conditionOfCar = conditionOfCar;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(String productionYear) {
        this.productionYear = productionYear;
    }

    public int getConditionOfCar() {
        return conditionOfCar;
    }

    public void setConditionOfCar(int conditionOfCar) {
        this.conditionOfCar = conditionOfCar;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }
}