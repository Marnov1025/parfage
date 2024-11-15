package com.example.demo;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

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
    void asd(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert passwordField != null : "fx:id=\"passwordField\" was not injected: check your FXML file 'autorisation-form.fxml'.";
        assert window != null : "fx:id=\"window\" was not injected: check your FXML file 'autorisation-form.fxml'.";
        assert logIn != null : "fx:id=\"logIn\" was not injected: check your FXML file 'autorisation-form.fxml'.";
        assert title != null : "fx:id=\"title\" was not injected: check your FXML file 'autorisation-form.fxml'.";
        assert loginField != null : "fx:id=\"loginField\" was not injected: check your FXML file 'autorisation-form.fxml'.";

    }
}
