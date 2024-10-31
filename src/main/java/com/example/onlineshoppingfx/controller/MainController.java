package com.example.onlineshoppingfx.controller;

import com.example.onlineshoppingfx.model.Product;
import com.example.onlineshoppingfx.service.ProductService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class MainController {


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
    private final ObservableList<Product> productList = FXCollections.observableArrayList();

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
        productList.clear();
        productList.addAll(products);
        productTable.setItems(productList);
    }

    @FXML
    private void addProduct() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/onlineshoppingfx/add-product-view.fxml"));
            Parent root = loader.load();
            AddProductController addProductController = loader.getController();

            Stage stage = new Stage();
            stage.setTitle("Add New Product");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.showAndWait();

            Product newProduct = addProductController.getProduct();
            if (newProduct != null) {
                productService.addProducts(List.of(newProduct));
                showAlert(Alert.AlertType.INFORMATION, "Success", "Product added successfully.");
                loadProducts();
            }
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to open add product window: " + e.getMessage());
        }
    }

    public void deleteProduct() {
        Product selectedProduct = productTable.getSelectionModel().getSelectedItem();

        if (selectedProduct != null) {
            try {
                productService.deleteProductById(selectedProduct.getId());
                loadProducts();
            } catch (IOException e) {
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to delete product: " + e.getMessage());
            }
        } else {
            showAlert(Alert.AlertType.WARNING, "No Selection", "Please select a product to delete.");
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }


    public void showUsersList() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/onlineshoppingfx/users-view.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Users List");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.showAndWait();

        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to open users list window: " + e.getMessage());
        }
    }

    public void editProduct() {
        Product selectedProduct = productTable.getSelectionModel().getSelectedItem();

        if (selectedProduct != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/onlineshoppingfx/edit-product-view.fxml"));
                Parent root = loader.load();

                EditProductController editProductController = loader.getController();
                editProductController.setProduct(selectedProduct);

                Stage stage = new Stage();
                stage.setTitle("Edit Product");
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(new Scene(root));
                stage.showAndWait();

                productService.updateProduct(selectedProduct);
                showAlert(Alert.AlertType.INFORMATION, "Success", "Product updated successfully.");

                productTable.refresh();
            } catch (IOException e) {
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to open edit product window: " + e.getMessage());
            }
        } else {
            showAlert(Alert.AlertType.WARNING, "No Selection", "Please select a product to edit.");
        }
    }
}