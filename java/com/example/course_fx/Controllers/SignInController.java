package com.example.course_fx.Controllers;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.example.course_fx.DBClasses.Const;
import com.example.course_fx.DBClasses.DBHandler;
import com.example.course_fx.DBClasses.SceneHandler;
import com.example.course_fx.Driver;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class SignInController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label errorLabel;

    @FXML
    private Button signInButton;

    @FXML
    private TextField usernameField;

    @FXML
    void initialize() {
        SceneHandler scHandler = new SceneHandler();
        DBHandler dbHandler = new DBHandler();

        signInButton.setOnAction(event -> {

            String usernameText = usernameField.getText().trim();
            String passwordText = passwordField.getText().trim();

            if (!usernameText.equals("") && !passwordText.equals("")) {
                try {
                    signInDriver (usernameText, passwordText, dbHandler, scHandler);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            else if(!usernameText.equals("") || usernameText.equals("") || !passwordText.equals("") || passwordText.equals("")){
                errorLabel.setText("Invalid username or password");
            }
            if(!usernameText.equals("") && !passwordText.equals("") && usernameText.equals("dispatcher") && passwordText.equals("dispatcher")){
                scHandler.changeScene("/com/example/course_fx/DispatcherView.fxml", signInButton);
            }
        });
    }

    private void signInDriver(String usernameText, String passwordText, DBHandler dbHandler, SceneHandler scHandler) throws SQLException {
        Driver driver = new Driver();
        driver.setName(usernameText);
        driver.setPassword(passwordText);
        Const.ENTERED_DRIVER_USERNAME = usernameText;
        Const.ENTERED_DRIVER_PASSWORD = passwordText;
        ResultSet result = dbHandler.getDriver(driver);

        int counter = 0;

        while (result.next()){
            counter++;
        }

        if (counter > 0){
            scHandler.changeScene("/com/example/course_fx/DriverView.fxml", signInButton);
        }

        errorLabel.setText("Invalid username or password");
    }
}