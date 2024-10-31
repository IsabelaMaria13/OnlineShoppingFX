package com.example.onlineshoppingfx.controller;

import com.example.onlineshoppingfx.model.User;
import com.example.onlineshoppingfx.service.RegisterUser;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class RegisterController {
    @FXML
    private TextField emailField;
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;

    @FXML
    private PasswordField passwordField;

    private final RegisterUser registerService = new RegisterUser();

    @FXML
    private void handleRegister() {
        String email = emailField.getText();
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();

        String password = passwordField.getText();
        User newUser = new User(email, firstName, lastName, password);
        RegisterUser registerMethod = new RegisterUser();
        String validationMessage = registerMethod.isUserCredentialsValid(newUser);

        if (validationMessage != null) {
            showAlert(validationMessage);
            return;
        }
        if (registerService.registerNewUser(email, firstName, lastName, password)) {
            goToMainView();
        } else {
            showAlert("User with email " + email + " is already registered.");
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Register Failed");
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void goToMainView() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/onlineshoppingfx/login-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) emailField.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Login View");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void goToLogin() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/onlineshoppingfx/login-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) emailField.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Login View");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
