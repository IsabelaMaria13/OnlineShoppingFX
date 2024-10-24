package com.example.onlineshoppingfx.service;



import com.example.onlineshoppingfx.model.Product;
import com.example.onlineshoppingfx.utils.products.ProductsFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ProductService {
    public List<Product> loadProducts() {
        return ProductsFile.loadProducts();
    }

    public void addProducts(List<Product> newProducts) throws IOException {
        ProductsFile.saveProductsToFile(newProducts);
    }

    public void deleteProductById(int productId) throws IOException {
        List<Product> products = ProductsFile.loadProducts();
        products.removeIf(product -> product.getId() == productId);
        ProductsFile.saveAllProductsToFile(products);
    }

}

