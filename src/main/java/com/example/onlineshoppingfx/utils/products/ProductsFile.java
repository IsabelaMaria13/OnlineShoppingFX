package com.example.onlineshoppingfx.utils.products;


import com.example.onlineshoppingfx.model.Product;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProductsFile {
    private static final String FILE_PATH = "C:\\Users\\isabe\\IdeaProjects\\OnlineShoppingFX\\files\\products.txt";

    public static List<Product> loadProducts() {
        List<Product> products = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    int id = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    double price = Double.parseDouble(parts[2]);
                    int stock = Integer.parseInt(parts[3]);
                    products.add(new Product(id, name, price, stock));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return products;
    }


    public static void saveProductsToFile(List<Product> newProducts) {
        List<Product> existingProducts = loadProducts();

        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(FILE_PATH, true)))) {
            for (Product newProduct : newProducts) {
                boolean exists = existingProducts.stream().anyMatch(p -> p.getId() == newProduct.getId());
                if (!exists) {
                    writer.println(newProduct.getId() + "," + newProduct.getName() + "," + newProduct.getPrice() + "," + newProduct.getStock());
                } else {
                    System.out.println("Product with ID " + newProduct.getId() + " already exists. Skipping.");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateProduct(Product updatedProduct) throws IOException {
        List<Product> products = ProductsFile.loadProducts();
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == updatedProduct.getId()) {
                products.set(i, updatedProduct);
                break;
            }
        }

        ProductsFile.saveAllProductsToFile(products);
    }


    public static void saveAllProductsToFile(List<Product> products) {
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(FILE_PATH, false)))) {
            for (Product product : products) {
                writer.println(product.getId() + "," + product.getName() + "," + product.getPrice() + "," + product.getStock());
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to save products to file: " + e.getMessage(), e);
        }
    }

}
