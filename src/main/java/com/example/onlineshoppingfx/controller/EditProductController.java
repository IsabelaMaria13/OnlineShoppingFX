package com.example.onlineshoppingfx.controller;

import com.example.onlineshoppingfx.model.Product;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditProductController {
    @FXML
    private TextField idField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField priceField;

    @FXML
    private TextField stockField;

    private Product product;

    public void setProduct(Product product) {
        this.product = product;

        idField.setText(String.valueOf(product.getId()));
        nameField.setText(product.getName());
        priceField.setText(String.valueOf(product.getPrice()));
        stockField.setText(String.valueOf(product.getStock()));
    }

    @FXML
    private void handleSave() {
        try {
            product.setName(nameField.getText());
            product.setPrice(Double.parseDouble(priceField.getText()));
            product.setStock(Integer.parseInt(stockField.getText()));

            ((Stage) idField.getScene().getWindow()).close();
        } catch (NumberFormatException e) {
            showAlert("Invalid input. Please check the data format.");
        }
    }


    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Input Error");
        alert.setContentText(message);
        alert.showAndWait();
    }
}
