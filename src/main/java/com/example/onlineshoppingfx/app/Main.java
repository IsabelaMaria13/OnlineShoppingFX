package com.example.onlineshoppingfx.app;


import com.example.onlineshoppingfx.service.ProductService;

public class Main {
    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        RegisterUser registerUser = new RegisterUser();
//        registerUser.registerNewUser();
//
//        LoginUser.loginUser(scanner);
//
//        scanner.close();

        ProductService productService = new ProductService();
        productService.manageProducts();
    }
}
