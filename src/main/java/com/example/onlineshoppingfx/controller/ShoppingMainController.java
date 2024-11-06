package com.example.onlineshoppingfx.controller;

import com.example.onlineshoppingfx.model.Product;
import com.example.onlineshoppingfx.service.ProductService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ShoppingMainController {

    @FXML
    public TableColumn<Product, Integer> stockColumn;
    @FXML
    private TableView<Product> productTable;
    @FXML
    private TableColumn<Product, Integer> idColumn;
    @FXML
    private TableColumn<Product, String> nameColumn;
    @FXML
    private TableColumn<Product, Double> priceColumn;

    private final ProductService productService = new ProductService();
    private final List<Product> cart = new ArrayList<>();

    @FXML
    private void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        stockColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));

        loadProducts();
    }

    @FXML
    private void loadProducts() {
        List<Product> products = productService.loadProducts();
        productTable.getItems().setAll(products);
    }

    @FXML
    private void addProduct() {
        try {
            Product selectedProduct = productTable.getSelectionModel().getSelectedItem();
            if (selectedProduct != null) {
                if (selectedProduct.getStock() > 0) {
                    cart.add(selectedProduct);
                    selectedProduct.setStock(selectedProduct.getStock() - 1);
                    productService.updateProduct(selectedProduct);
                    showAlert(Alert.AlertType.INFORMATION, "Product added", "The product has been added to your cart.");
                }
            } else {
                showAlert(Alert.AlertType.WARNING, "No selection", "Please select a product to add to your cart.");
            }
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", "An error occurred while adding the product to the cart: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void viewCart() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/onlineshoppingfx/cart-view.fxml"));
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Shopping Cart");
            stage.setScene(new Scene(fxmlLoader.load()));

            CartController cartController = fxmlLoader.getController();
            cartController.setCartItems(cart);

            stage.show();
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to open cart view: " + e.getMessage());
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}