package com.example.course_fx.Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.example.course_fx.DBClasses.Const;
import com.example.course_fx.DBClasses.DBHandler;
import com.example.course_fx.DBClasses.SceneHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class DriverViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label ageLabel;

    @FXML
    private Button backButton;

    @FXML
    private Label carIdLabel;

    @FXML
    private Button completeButton;

    @FXML
    private Label nameLabel;

    @FXML
    private Button noButton;

    @FXML
    private Button notCompleteButton;

    @FXML
    private Label runIdLabel;

    @FXML
    private Button yesButton;

    DBHandler dbHandler = new DBHandler();

    String age = dbHandler.getDriverAge();
    String carId = dbHandler.getDriverCarId();
    String runId = dbHandler.getDriverRunId();

    @FXML
    void initialize() {
        SceneHandler scHandler = new SceneHandler();
        nameLabel.setText(Const.ENTERED_DRIVER_USERNAME);
        ageLabel.setText(age);
        carIdLabel.setText(carId);
        runIdLabel.setText(runId);

        backButton.setOnAction(event ->{
            scHandler.changeScene("/com/example/course_fx/SignIn.fxml", backButton);
        });

        completeButton.setOnAction(event -> {
            dbHandler.completeRun();
        });

        notCompleteButton.setOnAction(event -> {
            dbHandler.notCompleteRun();
        });

        yesButton.setOnAction(event -> {
            dbHandler.updateCarConditionToBad();
        });

        noButton.setOnAction(event -> {
            dbHandler.updateCarConditionToGood();
        });
    }
}