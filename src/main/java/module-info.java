module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires com.google.gson;
    requires java.sql;
    requires java.desktop;
    exports com.example.demo.models;


    opens com.example.demo to javafx.fxml;
    exports com.example.demo;
    exports com.example.demo.models.database;
}