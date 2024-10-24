package com.example.onlineshoppingfx.controller;

import com.example.onlineshoppingfx.model.Product;
import com.example.onlineshoppingfx.repository.ProductRepository;
import com.example.onlineshoppingfx.service.ProductService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

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
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Add New Product");
        dialog.setHeaderText("Enter Product Details");

        dialog.setContentText("Enter Product ID:");
        Optional<String> result = dialog.showAndWait();
        if (!result.isPresent()) {
            return;
        }
        int id = Integer.parseInt(result.get());

        dialog.setContentText("Enter Product Name:");
        result = dialog.showAndWait();
        if (!result.isPresent()) {
            return;
        }
        String name = result.get();

        dialog.setContentText("Enter Product Price:");
        result = dialog.showAndWait();
        if (!result.isPresent()) {
            return;
        }
        double price = Double.parseDouble(result.get());

        dialog.setContentText("Enter Product Stock:");
        result = dialog.showAndWait();
        if (!result.isPresent()) {
            return;
        }
        int stock = Integer.parseInt(result.get());

        Product newProduct = new Product(id, name, price, stock);
        List<Product> newProducts = List.of(newProduct);
        try {
            productService.addProducts(newProducts);
            showAlert(Alert.AlertType.INFORMATION, "Success", "Product added successfully.");
            loadProducts();
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to add product: " + e.getMessage());
        }
    }

    public void deleteProduct(ActionEvent actionEvent) {
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


}