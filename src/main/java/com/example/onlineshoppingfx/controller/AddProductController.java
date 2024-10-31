package com.example.onlineshoppingfx.controller;

import com.example.onlineshoppingfx.model.Product;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddProductController {

    @FXML
    private TextField idField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField priceField;

    @FXML
    private TextField stockField;

    private Product product;

    @FXML
    private void handleAddProduct() {
        try {
            int id = Integer.parseInt(idField.getText());
            String name = nameField.getText();
            double price = Double.parseDouble(priceField.getText());
            int stock = Integer.parseInt(stockField.getText());

            product = new Product(id, name, price, stock);
            ((Stage) idField.getScene().getWindow()).close();
        } catch (NumberFormatException e) {
            showAlert("Invalid input. Please check the data format.");
        }
    }

    public Product getProduct() {
        return product;
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Input Error");
        alert.setContentText(message);
        alert.showAndWait();
    }
}
