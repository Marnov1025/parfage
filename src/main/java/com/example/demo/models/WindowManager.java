package com.example.demo.models;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class WindowManager {

    private static final String welcome = "/first-form.fxml";


    public void window_switch(String path, int height, int width) {
        try {
            FXMLLoader loader = new FXMLLoader();
            URL fxmlUrl = getClass().getResource(path);

            if (fxmlUrl == null) {
                throw new IOException("FXML файл не найден");
            }
            loader.setLocation(fxmlUrl);

            Parent root = loader.load();

            Scene scene = new Scene(root, width, height);

            URL cssUrl = getClass().getResource("/scratch.css");
            if (cssUrl != null) {
                scene.getStylesheets().add(cssUrl.toExternalForm());
            } else {
                System.err.println("CSS файл не найден");
            }

            Stage stage = new Stage();


            stage.setTitle("Парфаж");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();

        } catch (Exception e) {
            System.err.println("Ошибка при запуске приложения: " + e.getMessage());
            e.printStackTrace();

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка!");
            alert.setHeaderText("Не удалось загрузить интерфейс.");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    public void hello_view(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader();
            URL fxmlUrl = getClass().getResource(welcome);

            if (fxmlUrl == null) {
                throw new IOException("FXML файл не найден");
            }
            loader.setLocation(fxmlUrl);

            Parent root = loader.load();

            Scene scene = new Scene(root, 968, 609);

            URL cssUrl = getClass().getResource("/scratch.css");
            if (cssUrl != null) {
                scene.getStylesheets().add(cssUrl.toExternalForm());
            } else {
                System.err.println("CSS файл не найден");
            }

            primaryStage.setTitle("Парфаж");
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();

        } catch (Exception e) {
            System.err.println("Ошибка при запуске приложения: " + e.getMessage());
            e.printStackTrace();

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка!");
            alert.setHeaderText("Не удалось загрузить интерфейс.");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
}
