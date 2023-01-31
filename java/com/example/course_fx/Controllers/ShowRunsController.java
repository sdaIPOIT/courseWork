package com.example.course_fx.Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.example.course_fx.Car;
import com.example.course_fx.DBClasses.DBHandler;
import com.example.course_fx.DBClasses.SceneHandler;
import com.example.course_fx.Run;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ShowRunsController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<Run, Integer> completingColumn;

    @FXML
    private Button backButton;

    @FXML
    private TableColumn<Run, String> distanceColumn;

    @FXML
    private TableColumn<Run, String> endCityColumn;

    @FXML
    private TableColumn<Run, Integer> numberColumn;

    @FXML
    private TableView<Run> runsTable;

    @FXML
    private TableColumn<Run, String> startCityColumn;

    DBHandler dbHandler = new DBHandler();
    ObservableList<Run> listRuns;

    @FXML
    void initialize() {
        SceneHandler scHandler = new SceneHandler();

        numberColumn.setCellValueFactory(new PropertyValueFactory<Run, Integer>("runId"));
        startCityColumn.setCellValueFactory(new PropertyValueFactory<Run, String>("startCity"));
        endCityColumn.setCellValueFactory(new PropertyValueFactory<Run, String>("endCity"));
        distanceColumn.setCellValueFactory(new PropertyValueFactory<Run, String>("distance"));
        completingColumn.setCellValueFactory(new PropertyValueFactory<Run, Integer>("completing"));

        listRuns = dbHandler.showRuns();
        runsTable.setItems(listRuns);

        backButton.setOnAction(event -> {
            scHandler.changeScene("/com/example/course_fx/DispatcherView.fxml", backButton);
        });
    }
}