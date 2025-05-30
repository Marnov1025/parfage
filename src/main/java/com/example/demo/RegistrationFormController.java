package com.example.demo;

import java.io.IOException;
import java.util.Objects;

import com.example.demo.models.WindowSwitch;
import com.example.demo.models.database.DBHandler;
import com.example.demo.models.database.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

public class RegistrationFormController extends AutorisationFormController {

    public Button registration;

    @FXML
    private PasswordField passwordReg;

    @FXML
    private TextField logInReg;

    @FXML
    private TextField userNameReg;

    @FXML
    private Button rewindButton;

    @FXML
    private AnchorPane window;

    @FXML
    private Label title;

    @FXML
    void button(ActionEvent event) {

    }

    @FXML
    void initialize() {

        rewindButton.setOnAction(event -> {
            rewindButton.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("first-form.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            Parent root = loader.getRoot();
            root.getStylesheets().addAll(Objects.requireNonNull(this.getClass().getResource("scratch.css")).toExternalForm());
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
        });

        registration.setOnAction(event -> {

            regNewUser();

        });

    }

    private void regNewUser() {
        DBHandler handler = new DBHandler();

        String username = userNameReg.getText();
        String login = logInReg.getText();
        String password = passwordReg.getText();

        User user = new User(login, password, username);

        handler.regUser(user);

        registration.getScene().getWindow().hide();

        WindowSwitch ws = new WindowSwitch();
        ws.main_menu();
    }
}
