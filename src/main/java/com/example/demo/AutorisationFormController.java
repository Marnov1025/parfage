package com.example.demo;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

import com.example.demo.models.WindowSwitch;
import com.example.demo.models.animations.Shake;
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
import com.example.demo.models.WindowSwitch;

public class AutorisationFormController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private PasswordField passwordField;

    @FXML
    private AnchorPane window;

    @FXML
    private Button logIn;

    @FXML
    private Label title;

    @FXML
    private TextField loginField;

    @FXML
    private Button rewindButton;

    @FXML
    void asd(ActionEvent event) {

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

        logIn.setOnAction(event -> {
            String loginText = loginField.getText().trim();
            String passwordText = passwordField.getText().trim();

            if(!loginText.equals("") && !passwordText.equals("")) {
                try {
                    loginUser(loginText, passwordText);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            else {
                System.out.println("Error");
            }
        });


    }

    private void loginUser(String loginText, String passwordText) throws SQLException {
        DBHandler dbHandler = new DBHandler();
        User user = new User();
        user.setUsername(loginText);
        user.setPassword(passwordText);
        ResultSet result = dbHandler.getUser(user);

        int counter = 0;

        while(result.next()) {
            counter++;
        }

        if (counter >= 1) {
            logIn.getScene().getWindow().hide();

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
        else {
            Shake userLoginAnim = new Shake(loginField);
            Shake userPasswordAnim = new Shake(passwordField);
            userLoginAnim.playAnim();
            userPasswordAnim.playAnim();
        }
    }

//    public void openNewScene(String window) {
//
//    }
}
