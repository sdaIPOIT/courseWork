package com.example.course_fx.Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.example.course_fx.DBClasses.DBHandler;
import com.example.course_fx.DBClasses.SceneHandler;
import com.example.course_fx.Driver;
import com.example.course_fx.Run;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ShowDriversController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<Driver, String> ageColumn;

    @FXML
    private Button backButton;

    @FXML
    private TableColumn<Driver, Integer> carIdColumn;

    @FXML
    private TableView<Driver> driversTable;

    @FXML
    private TableColumn<Driver, Integer> numberColumn;

    @FXML
    private TableColumn<Driver, String> passwordColumn;

    @FXML
    private TableColumn<Driver, Integer> runIdColumn;

    @FXML
    private TableColumn<Driver, String> usernameColumn;
    DBHandler dbHandler = new DBHandler();
    ObservableList<Driver> listDrivers;


    @FXML
    void initialize() {
        SceneHandler scHandler = new SceneHandler();

        numberColumn.setCellValueFactory(new PropertyValueFactory<Driver, Integer>("driverId"));
        usernameColumn.setCellValueFactory(new PropertyValueFactory<Driver, String>("name"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<Driver, String>("age"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<Driver, String>("password"));
        runIdColumn.setCellValueFactory(new PropertyValueFactory<Driver, Integer>("runId"));
        carIdColumn.setCellValueFactory(new PropertyValueFactory<Driver, Integer>("carId"));

        listDrivers = dbHandler.showDrivers();
        driversTable.setItems(listDrivers);

        backButton.setOnAction(event -> {
            scHandler.changeScene("/com/example/course_fx/DispatcherView.fxml", backButton);
        });
    }
}