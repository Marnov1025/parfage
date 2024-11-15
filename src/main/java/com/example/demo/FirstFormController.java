package com.example.demo;

import java.net.URL;
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
        assert Registration != null : "fx:id=\"Registration\" was not injected: check your FXML file 'first-form.fxml'.";
        assert Autorisation != null : "fx:id=\"Autorisation\" was not injected: check your FXML file 'first-form.fxml'.";
        assert window != null : "fx:id=\"window\" was not injected: check your FXML file 'first-form.fxml'.";
        assert title != null : "fx:id=\"title\" was not injected: check your FXML file 'first-form.fxml'.";
    }

    public void autorisation(MouseEvent mouseEvent){
        try {
            // Загружаем новую форму из FXML файла
            FXMLLoader loader = new FXMLLoader(getClass().getResource("autorisation-form.fxml"));
            Parent autorisationForm = loader.load();
            autorisationForm.getStylesheets().addAll(this.getClass().getResource("scratch.css").toExternalForm());

            // Создаем новое окно
            Stage autorisationStage = new Stage();
            autorisationStage.setTitle("Авторизация");
            autorisationStage.initModality(Modality.WINDOW_MODAL); // Модальное окно
            autorisationStage.setScene(new Scene(autorisationForm, 968, 609));

            // Показываем новое окно
            autorisationStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void registration(MouseEvent mouseEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("registration-form.fxml"));
            Parent registrationForm = loader.load();
            registrationForm.getStylesheets().addAll(this.getClass().getResource("scratch.css").toExternalForm());

            Stage registrationStage = new Stage();
            registrationStage.setTitle("Регистрация");
            registrationStage.initModality(Modality.WINDOW_MODAL);
            registrationStage.setScene(new Scene(registrationForm, 968, 609));

            registrationStage.show();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
