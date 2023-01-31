package com.example.course_fx.DBClasses;

import com.example.course_fx.Car;
import com.example.course_fx.Driver;
import com.example.course_fx.Run;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class DBHandler extends Configs{
    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;
        Class.forName("com.mysql.cj.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);

        return dbConnection;
    }

    public void addDriver (Driver driver) {
        String insert = "INSERT INTO " + Const.DRIVER_TABLE + " (" + Const.DRIVER_NAME + "," + Const.DRIVER_AGE + ","
                + Const.DRIVER_RUN_ID + "," + Const.DRIVER_CAR_ID + "," + Const.DRIVER_PASSWORD + ")" + " VALUES(?,?,?,?,?)";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);

            prSt.setString(1, driver.getName());
            prSt.setString(2, driver.getAge());
            prSt.setInt(3, 0);
            prSt.setInt(4, 0);
            prSt.setString(5, driver.getPassword());

            prSt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void addCar(Car car){
        String insert = "INSERT INTO " + Const.CAR_TABLE + " (" + Const.CAR_MODEL + "," + Const.CAR_PRODUCTION_YEAR + "," + Const.CAR_CONDITION + ")" + " VALUES(?,?,?)";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);

            prSt.setString(1, car.getCarModel());
            prSt.setString(2, car.getProductionYear());
            prSt.setInt(3, 1);

            prSt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void addRun (Run run){
        String insert = "INSERT INTO " + Const.RUN_TABLE + " (" + Const.RUN_START + "," + Const.RUN_END + "," + Const.RUN_DISTANCE + "," + Const.RUN_COMPLETING + ")" + " VALUES(?,?,?,?)";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);

            prSt.setString(1, run.getStartCity());
            prSt.setString(2, run.getEndCity());
            prSt.setString(3, run.getDistance());
            prSt.setInt(4, 0);

            prSt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public ObservableList<Car> showCars(){

        String getCars = "SELECT * FROM cars";
        ObservableList<Car> carList = FXCollections.observableArrayList();

        try{
            PreparedStatement prSt = getDbConnection().prepareStatement(getCars);
            ResultSet resSet = prSt.executeQuery();

            while (resSet.next()){
                carList.add(new Car(Integer.parseInt(resSet.getString(Const.CAR_ID)), resSet.getString(Const.CAR_MODEL),
                        resSet.getString(Const.CAR_PRODUCTION_YEAR), Integer.parseInt(resSet.getString(Const.CAR_CONDITION))));
            }
        } catch (Exception e) {

        }
        return carList;
    }

    public ObservableList<Run> showRuns(){

        String getRuns = "SELECT * FROM runs";
        ObservableList<Run> runList = FXCollections.observableArrayList();

        try{
            PreparedStatement prSt = getDbConnection().prepareStatement(getRuns);
            ResultSet resSet = prSt.executeQuery();

            while (resSet.next()){
                runList.add(new Run(Integer.parseInt(resSet.getString(Const.RUN_ID)), resSet.getString(Const.RUN_START),
                        resSet.getString(Const.RUN_END), resSet.getString(Const.RUN_DISTANCE), Integer.parseInt(resSet.getString(Const.RUN_COMPLETING))));
            }
        } catch (Exception e) {

        }
        return runList;
    }

    public ObservableList<Driver> showDrivers(){

        String getDrivers = "SELECT * FROM drivers";
        ObservableList<Driver> driverList = FXCollections.observableArrayList();

        try{
            PreparedStatement prSt = getDbConnection().prepareStatement(getDrivers);
            ResultSet resSet = prSt.executeQuery();

            while (resSet.next()){
                driverList.add(new Driver(Integer.parseInt(resSet.getString(Const.DRIVER_ID)), resSet.getString(Const.DRIVER_NAME),
                        resSet.getString(Const.DRIVER_AGE), Integer.parseInt(resSet.getString(Const.DRIVER_RUN_ID)),
                        Integer.parseInt(resSet.getString(Const.DRIVER_CAR_ID)), resSet.getString(Const.DRIVER_PASSWORD)));
            }
        } catch (Exception e) {

        }
        return driverList;
    }

    public ResultSet getDriver (Driver driver){
        ResultSet resSet = null;

        String select = "SELECT * FROM " + Const.DRIVER_TABLE + " WHERE " +
                Const.DRIVER_NAME + "=? AND " + Const.DRIVER_PASSWORD + "=?";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);

            prSt.setString(1, driver.getName());
            prSt.setString(2, driver.getPassword());

            resSet = prSt.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return resSet;
    }

    public void distributeCarsAndRuns (String carId, String runId, String driverId){
        String edit = "UPDATE drivers SET run_id='" + runId + "',car_id='" + carId +"' WHERE driver_id='" + driverId + "' ";

        try{
            PreparedStatement prSt = getDbConnection().prepareStatement(edit);

            prSt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteDriver (String driverId){
        String delete = "DELETE FROM drivers WHERE driver_id = ?";

        try{
            PreparedStatement prSt = getDbConnection().prepareStatement(delete);

            prSt.setString(1, driverId);
            prSt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteCar (String carId){
        String delete = "DELETE FROM cars WHERE car_id = ?";

        try{
            PreparedStatement prSt = getDbConnection().prepareStatement(delete);

            prSt.setString(1, carId);
            prSt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void editDriversCar (String carId){
        String edit = "UPDATE drivers SET car_id='0' WHERE car_id='" + carId + "' ";

        try{
            PreparedStatement prSt = getDbConnection().prepareStatement(edit);

            prSt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public String getDriverId (){
        String selectId = "SELECT driver_id FROM drivers WHERE name='" + Const.ENTERED_DRIVER_USERNAME + "' AND password='" + Const.ENTERED_DRIVER_PASSWORD + "'";

        ResultSet resSet = null;
        String id = "";

        try {
            Statement statement = getDbConnection().createStatement();
            resSet = statement.executeQuery(selectId);
            resSet.next();
            id = resSet.getString(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        Const.ENTERED_DRIVER_ID = id;

        return id;
    }

    public String getDriverAge (){
        String selectAge = "SELECT age FROM drivers WHERE name='" + Const.ENTERED_DRIVER_USERNAME + "' AND password='" + Const.ENTERED_DRIVER_PASSWORD + "'";

        ResultSet resSet = null;
        String age = "";

        try {
            Statement statement = getDbConnection().createStatement();
            resSet = statement.executeQuery(selectAge);
            resSet.next();
            age = resSet.getString(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return age;
    }

    public String getDriverCarId (){
        String selectId = "SELECT car_id FROM drivers WHERE name='" + Const.ENTERED_DRIVER_USERNAME + "' AND password='" + Const.ENTERED_DRIVER_PASSWORD + "'";

        ResultSet resSet = null;
        String id = "";

        try {
            Statement statement = getDbConnection().createStatement();
            resSet = statement.executeQuery(selectId);
            resSet.next();
            id = resSet.getString(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        Const.ENTERED_DRIVER_CAR_ID = id;

        return id;
    }

    public String getDriverRunId (){
        String selectId = "SELECT run_id FROM drivers WHERE name='" + Const.ENTERED_DRIVER_USERNAME + "' AND password='" + Const.ENTERED_DRIVER_PASSWORD + "'";

        ResultSet resSet = null;
        String id = "";

        try {
            Statement statement = getDbConnection().createStatement();
            resSet = statement.executeQuery(selectId);
            resSet.next();
            id = resSet.getString(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        Const.ENTERED_DRIVER_RUN_ID = id;
        return id;
    }

    public void updateCarConditionToBad(){
        String updateCondition = "UPDATE cars SET condition_of_car='0' WHERE car_id='" + Const.ENTERED_DRIVER_CAR_ID + "'";

        try{
            PreparedStatement prSt = getDbConnection().prepareStatement(updateCondition);

            prSt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateCarConditionToGood(){
        String updateCondition = "UPDATE cars SET condition_of_car='1' WHERE car_id='" + Const.ENTERED_DRIVER_CAR_ID + "'";

        try{
            PreparedStatement prSt = getDbConnection().prepareStatement(updateCondition);

            prSt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void completeRun(){
        String deleteRun = "UPDATE runs SET completing='1' WHERE run_id='" + Const.ENTERED_DRIVER_RUN_ID + "'";

        try{
            PreparedStatement prSt = getDbConnection().prepareStatement(deleteRun);

            prSt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void notCompleteRun(){
        String deleteRun = "UPDATE runs SET completing='0' WHERE run_id='" + Const.ENTERED_DRIVER_RUN_ID + "'";

        try{
            PreparedStatement prSt = getDbConnection().prepareStatement(deleteRun);

            prSt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}