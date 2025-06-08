package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.print.DocFlavor;
import java.io.File;
import java.io.IOException;
import java.net.URL;



public class HelloApplication extends Application{
    private static final String FIRST_FORM = "C:\\Users\\Сергей\\IdeaProjects\\parfage\\src\\main\\resources\\first-form.fxml";

    private static final String ABSOLUTE_PATH = new File("./first-form.fxml").getAbsolutePath();



    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(FIRST_FORM));

            Parent root = loader.load(new File(ABSOLUTE_PATH).toURI().toURL());

            Scene scene = new Scene(root, 800, 600);
            scene.getStylesheets().add(getClass().getResource("scratch.css").toExternalForm());

            primaryStage.setTitle("Парфаж");
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
        }

        catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка!");
            alert.setHeaderText("Не удалось загрузить интерфейс.");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
        URL url = getClass().getResource(FIRST_FORM);
        System.out.println("FXML: " + url);
    }

    public static void main(String[] args) {
        launch(args);
    }
}