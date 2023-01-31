package com.example.course_fx.Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.example.course_fx.DBClasses.DBHandler;
import com.example.course_fx.DBClasses.SceneHandler;
import com.example.course_fx.Driver;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class EditDriverController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button editButton;

    @FXML
    private TextField carIdField;

    @FXML
    private Label numLabel;

    @FXML
    private Label setNumLabel;

    @FXML
    private TextField runIdField;

    @FXML
    private Button backButton;

    @FXML
    private TableColumn<Driver, Integer> numberColumn;

    @FXML
    private TableColumn<Driver, String> passwordColumn;

    @FXML
    private TableColumn<Driver, Integer> runIdColumn;

    @FXML
    private TableColumn<Driver, String> usernameColumn;

    @FXML
    private TableColumn<Driver, String> ageColumn;

    @FXML
    private TableColumn<Driver, Integer> carIdColumn;

    @FXML
    private TableView<Driver> driversTable;

    DBHandler dbHandler = new DBHandler();
    ObservableList<Driver> listDrivers;

    int index = -1;

    @FXML
    void getSelected (MouseEvent event){
        index = driversTable.getSelectionModel().getSelectedIndex();

        if (index <= -1){
            return;
        }

        setNumLabel.setText(numberColumn.getCellData(index).toString());
        carIdField.setText(carIdColumn.getCellData(index).toString());
        runIdField.setText(runIdColumn.getCellData(index).toString());
    }

    @FXML
    void initialize() {
        SceneHandler scHandler = new SceneHandler();

        numberColumn.setCellValueFactory(new PropertyValueFactory<Driver, Integer>("driverId"));
        usernameColumn.setCellValueFactory(new PropertyValueFactory<Driver, String>("name"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<Driver, String>("age"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<Driver, String>("password"));
        carIdColumn.setCellValueFactory(new PropertyValueFactory<Driver, Integer>("carId"));
        runIdColumn.setCellValueFactory(new PropertyValueFactory<Driver, Integer>("runId"));

        listDrivers = dbHandler.showDrivers();
        driversTable.setItems(listDrivers);

        backButton.setOnAction(event -> {
            scHandler.changeScene("/com/example/course_fx/DispatcherView.fxml", backButton);
        });

        editButton.setOnAction(event -> {
            distribute(dbHandler);
            updateTable();
        });
    }

    private void distribute (DBHandler dbHandler){
        String driverId = setNumLabel.getText().trim();
        String carId = carIdField.getText().trim();
        String runId = runIdField.getText().trim();

        dbHandler.distributeCarsAndRuns(carId, runId, driverId);
    }

    private void updateTable (){
        numberColumn.setCellValueFactory(new PropertyValueFactory<Driver, Integer>("driverId"));
        usernameColumn.setCellValueFactory(new PropertyValueFactory<Driver, String>("name"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<Driver, String>("age"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<Driver, String>("password"));
        carIdColumn.setCellValueFactory(new PropertyValueFactory<Driver, Integer>("carId"));
        runIdColumn.setCellValueFactory(new PropertyValueFactory<Driver, Integer>("runId"));

        listDrivers = dbHandler.showDrivers();
        driversTable.setItems(listDrivers);
    }
}