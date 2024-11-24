package com.example.demo;

import java.net.URL;
import java.util.ResourceBundle;

import com.example.demo.models.database.DBHandler;
import com.example.demo.models.database.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class RegistrationFormController {

    public Button registration;

    @FXML
    private PasswordField passwordReg;

    @FXML
    private TextField logInReg;

    @FXML
    private TextField userNameReg;

    @FXML
    private AnchorPane window;

    @FXML
    private Label title;

    @FXML
    void button(ActionEvent event) {

    }

    @FXML
    void initialize() {

        registration.setOnAction(event -> {

            loginNewUser();

        });

    }

    private void loginNewUser() {
        DBHandler handler = new DBHandler();

        String username = userNameReg.getText();
        String login = logInReg.getText();
        String password = passwordReg.getText();

        User user = new User(login, password, username);

        handler.regUser(user);
    }


}
