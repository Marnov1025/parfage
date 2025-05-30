package com.example.demo.models;

import com.example.demo.FirstFormController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class WindowSwitch {
    String auth = "authorisation-form.fxml";
    String main = "main-menu-form.fxml";
    String welcome = "first-form";

    public void main_menu() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("main-menu-form.fxml"));

        try {
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Parent root = loader.getRoot();
        root.getStylesheets().addAll(Objects.requireNonNull(this.getClass().getResource("scratch.css")).toExternalForm());
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
