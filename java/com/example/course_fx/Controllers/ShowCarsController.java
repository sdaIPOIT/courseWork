package com.example.course_fx.Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import com.example.course_fx.Car;
import com.example.course_fx.DBClasses.DBHandler;
import com.example.course_fx.DBClasses.SceneHandler;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ShowCarsController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backButton;

    @FXML
    private TableColumn<Car, String> carModelColumn;

    @FXML
    private TableView<Car> carsTable;

    @FXML
    private TableColumn<Car, Integer> conditionColumn;

    @FXML
    private TableColumn<Car, Integer> numberColumn;

    @FXML
    private TableColumn<Car, String> productionYearColumn;
    DBHandler dbHandler = new DBHandler();
    ObservableList<Car> listCars;

    @FXML
    public void initialize() {
        SceneHandler scHandler = new SceneHandler();

        numberColumn.setCellValueFactory(new PropertyValueFactory<Car, Integer>("carId"));
        carModelColumn.setCellValueFactory(new PropertyValueFactory<Car, String>("carModel"));
        productionYearColumn.setCellValueFactory(new PropertyValueFactory<Car, String>("productionYear"));
        conditionColumn.setCellValueFactory(new PropertyValueFactory<Car, Integer>("conditionOfCar"));

        listCars = dbHandler.showCars();
        carsTable.setItems(listCars);

        backButton.setOnAction(event -> {
            scHandler.changeScene("/com/example/course_fx/DispatcherView.fxml", backButton);
        });
    }
}