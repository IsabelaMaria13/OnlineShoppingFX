package com.example.onlineshoppingfx.service;



import com.example.onlineshoppingfx.model.Product;
import com.example.onlineshoppingfx.utils.products.ProductFile;

import java.io.IOException;
import java.util.List;

public class ProductService {
    public List<Product> loadProducts() {
        return ProductFile.loadProducts();
    }

    public void addProducts(List<Product> newProducts) throws IOException {
        ProductFile.saveProductsToFile(newProducts);
    }

    public void deleteProductById(int productId) throws IOException {
        List<Product> products = ProductFile.loadProducts();
        products.removeIf(product -> product.getId() == productId);
        ProductFile.saveAllProductsToFile(products);
    }

    public void updateProduct(Product product) throws IOException {
        ProductFile.updateProduct(product);
    }

}

