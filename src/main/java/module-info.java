module com.example.onlineshoppingfx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.onlineshoppingfx to javafx.fxml;
    exports com.example.onlineshoppingfx.controller;
    exports com.example.onlineshoppingfx.service;
    exports com.example.onlineshoppingfx.model;
    opens com.example.onlineshoppingfx.controller to javafx.fxml;
}