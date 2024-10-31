package com.example.onlineshoppingfx.controller;

import com.example.onlineshoppingfx.service.LoginUser;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    private final LoginUser loginService = new LoginUser();

    @FXML
    private void handleLogin() {
        String email = emailField.getText();
        String password = passwordField.getText();

        if (loginService.loginUser(email, password)) {
            goToMainView();
        } else {
            showAlert();
        }
    }

    private void showAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Login Failed");
        alert.setContentText("Invalid email or password.");
        alert.showAndWait();
    }

    private void goToMainView() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/onlineshoppingfx/main-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) emailField.getScene().getWindow(); // Obține scena curentă
            stage.setScene(scene);
            stage.setTitle("Main View");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void goToRegister() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/onlineshoppingfx/register-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) emailField.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Register View");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}