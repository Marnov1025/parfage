package com.example.demo;

import com.example.demo.models.WindowManager;
import javafx.application.Application;
import javafx.stage.Stage;

public class HelloApplication extends Application {

    @Override
    public void start(Stage primaryStage) {
        WindowManager ws = new WindowManager();
        ws.hello_view(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
