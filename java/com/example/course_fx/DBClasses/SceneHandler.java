package com.example.course_fx.DBClasses;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;

public class SceneHandler {
    public void changeScene(String window, Button button) {

        URL fxmlLocation = getClass().getResource(window);
        FXMLLoader loader = new FXMLLoader(fxmlLocation);

        try {
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Parent root = loader.getRoot();
        button.getScene().setRoot(root);
    }

    public boolean checkString(String str){
        for (int i = 0; i < str.length(); i++){
            if (str.charAt(i) < 48 || str.charAt(i) > 57){
                return false;
            }
        }
        return  true;
    }
}