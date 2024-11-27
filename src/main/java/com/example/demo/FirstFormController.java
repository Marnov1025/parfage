package com.example.demo;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class FirstFormController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button Registration;

    @FXML
    private Button Autorisation;

    @FXML
    private AnchorPane window;

    @FXML
    private Label title;



    @FXML
    void initialize() {
        Autorisation.setOnAction(event -> {
            Autorisation.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("autorisation-form.fxml"));

            form_create(loader);
        });

        Registration.setOnAction(event -> {
            Registration.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("registration-form.fxml"));

            form_create(loader);
        });
    }

    private void form_create(FXMLLoader loader) {
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
