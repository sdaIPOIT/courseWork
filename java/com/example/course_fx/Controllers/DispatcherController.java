package com.example.course_fx.Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import com.example.course_fx.Car;
import com.example.course_fx.DBClasses.DBHandler;
import com.example.course_fx.DBClasses.SceneHandler;
import com.example.course_fx.Driver;
import com.example.course_fx.Run;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class DispatcherController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addCarButton;

    @FXML
    private Label messageLabel;

    @FXML
    private Button addDriverButton;

    @FXML
    private Button addRunButton;

    @FXML
    private TextField ageField;

    @FXML
    private Button backButton;

    @FXML
    private TextField carModelField;

    @FXML
    private TextField distanceField;

    @FXML
    private Button distributeButton;

    @FXML
    private TextField driverPasswordField;

    @FXML
    private TextField endCityField;

    @FXML
    private TextField productionYearField;

    @FXML
    private Button scrapCarButton;

    @FXML
    private Button showCarsButton;

    @FXML
    private Button showDriversButton;

    @FXML
    private Button showRunsButton;

    @FXML
    private TextField startCityField;

    @FXML
    private Button suspendDriverButton;

    @FXML
    private TextField usernameField;


    @FXML
    void initialize() {
        DBHandler dbHandler = new DBHandler();
        SceneHandler scHandler = new SceneHandler();

        addDriverButton.setOnAction(event -> {
            addNewDriver(dbHandler, scHandler);
        });
        addCarButton.setOnAction(event -> {
            addNewCar(dbHandler, scHandler);
        });
        addRunButton.setOnAction(event -> {
            addNewRun(dbHandler, scHandler);
        });
        showRunsButton.setOnAction(event ->{
            scHandler.changeScene("/com/example/course_fx/ShowRunsView.fxml", showRunsButton);
        });
        showCarsButton.setOnAction(event -> {
            scHandler.changeScene("/com/example/course_fx/ShowView.fxml", showCarsButton);
        });

        showDriversButton.setOnAction(event -> {
            scHandler.changeScene("/com/example/course_fx/ShowDriversView.fxml", showDriversButton);
        });

        backButton.setOnAction(event -> {
            scHandler.changeScene("/com/example/course_fx/SignIn.fxml", backButton);
        });

        distributeButton.setOnAction(event -> {
            scHandler.changeScene("/com/example/course_fx/ChooseDriverView.fxml", distributeButton);
        });

        suspendDriverButton.setOnAction(event -> {
            scHandler.changeScene("/com/example/course_fx/DeleteDriverView.fxml", suspendDriverButton);
        });

        scrapCarButton.setOnAction(event -> {
            scHandler.changeScene("/com/example/course_fx/ChooseCarView.fxml", scrapCarButton);
        });
    }

    private void addNewDriver(DBHandler dbHandler, SceneHandler scHandler){
        String name = usernameField.getText().trim();
        String age = ageField.getText().trim();
        String password = driverPasswordField.getText().trim();

        if (password.length() < 8) {
            messageLabel.setText("Error, short password!");
        }
        else if (scHandler.checkString(age) == false){
            messageLabel.setText("Error, invalid age, enter only numbers!");
        }
        else {
            dbHandler.addDriver(new Driver(name, age, 0, 0, password));
            messageLabel.setText("Driver added!");
        }
    }

    private void addNewCar(DBHandler dbHandler, SceneHandler scHandler) {
        String carModel = carModelField.getText().trim();
        String productionYear = productionYearField.getText().trim();
        if (scHandler.checkString(productionYear) == false){
            messageLabel.setText("Error, invalid production year, enter only numbers!");
        } else {
            dbHandler.addCar(new Car(carModel, productionYear, 1));
            messageLabel.setText("Car added!");
        }
    }

    private void addNewRun(DBHandler dbHandler, SceneHandler scHandler){
        String startCity = startCityField.getText().trim();
        String endCity = endCityField.getText().trim();
        String distance = distanceField.getText().trim();
        if (scHandler.checkString(distance) == false){
            messageLabel.setText("Error, invalid distance, enter only numbers!");
        } else {
            dbHandler.addRun(new Run(startCity, endCity, distance, 0));
            messageLabel.setText("Run added!");
        }
    }
}