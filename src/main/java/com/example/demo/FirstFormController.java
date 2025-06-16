package com.example.demo;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import com.example.demo.models.WindowManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
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

    protected static final String fxmlPath = "/first-form.fxml";

    protected final static int fxmlHeight = 609;
    protected final static int fxmlWidth = 968;

    @FXML
    void initialize() {
        Autorisation.setOnAction(event -> {
            Autorisation.getScene().getWindow().hide();

            WindowManager ws = new WindowManager();
            ws.window_switch(AuthorisationFormController.fxmlPath, AuthorisationFormController.fxmlHeight, AuthorisationFormController.fxmlWidth);
        });

        Registration.setOnAction(event -> {
            Registration.getScene().getWindow().hide();

            WindowManager ws = new WindowManager();
            ws.window_switch(RegistrationFormController.fxmlPath, RegistrationFormController.fxmlHeight, RegistrationFormController.fxmlWidth);
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
