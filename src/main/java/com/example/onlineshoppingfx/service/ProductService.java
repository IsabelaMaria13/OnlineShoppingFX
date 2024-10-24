package com.example.onlineshoppingfx.service;



import com.example.onlineshoppingfx.model.Product;
import com.example.onlineshoppingfx.utils.products.ProductsFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductService {
    public void manageProducts() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nProduct Management");
            System.out.println("1. Load Products");
            System.out.println("2. Display Products");
            System.out.println("3. Add New Products");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    loadProducts();
                    break;

                case 2:
                    ProductsFile.displayProducts();
                    break;

                case 3:
                    addProduct();
                    break;

                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public void loadProducts() {
        List<Product> loadedProducts = ProductsFile.loadProducts();
        System.out.println(loadedProducts.size() + " products loaded.");
    }

    public void addProduct() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Product ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Product Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Product Price: ");
        double price = scanner.nextDouble();
        System.out.print("Enter Product Stock: ");
        int stock = scanner.nextInt();

        Product newProduct = new Product(id, name, price, stock);
        List<Product> newProducts = new ArrayList<>();
        newProducts.add(newProduct);

        try {
            ProductsFile.saveProductsToFile(newProducts);
            System.out.println("Product added successfully.");
        } catch (IOException e) {
            System.err.println("Failed to save product: " + e.getMessage());
        }
    }
}

