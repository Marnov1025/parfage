module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires com.google.gson;
    exports com.example.demo.models;


    opens com.example.demo to javafx.fxml;
    exports com.example.demo;
}