package com.example.demo;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.example.demo.models.WindowManager;
import com.example.demo.models.animations.Shake;
import com.example.demo.models.database.DBHandler;
import com.example.demo.models.database.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class AuthorisationFormController {

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

    protected final static String fxmlPath = "/authorisation-form.fxml";

    protected final static int fxmlHeight = 609;
    protected final static int fxmlWidth = 968;

    @FXML
    void initialize() {
        rewindButton.setOnAction(event -> {
            rewindButton.getScene().getWindow().hide();

            WindowManager ws = new WindowManager();
            ws.window_switch(FirstFormController.fxmlPath, FirstFormController.fxmlHeight, FirstFormController.fxmlWidth);
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

    protected void loginUser(String loginText, String passwordText) throws SQLException {
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

            WindowManager ws = new WindowManager();
            ws.window_switch(MainMenuController.fxmlPath, MainMenuController.fxmlHeight, MainMenuController.fxmlWidth);
        }
        else {
            Shake userLoginAnim = new Shake(loginField);
            Shake userPasswordAnim = new Shake(passwordField);
            userLoginAnim.playAnim();
            userPasswordAnim.playAnim();
        }
    }
}
