package com.example.onlineshoppingfx.service;



import com.example.onlineshoppingfx.model.User;
import com.example.onlineshoppingfx.utils.users.UserFile;

import java.io.Serializable;
import java.util.List;
import java.util.Scanner;

public class RegisterUser implements Serializable {
    public RegisterUser() {
    }

    public void registerNewUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Register User");

        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("First Name: ");
        String firstName = scanner.nextLine();
        System.out.print("Last Name: ");
        String lastName = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        User newUser = new User(email, firstName, lastName, password);

        if (!isUserAlreadyRegistered(newUser)) {
            UserFile.saveUser(newUser);
            System.out.println("User " + email + " registered successfully.");
        } else {
            System.out.println("User with email " + email + " is already registered.");
        }
    }

    private boolean isUserAlreadyRegistered(User newUser) {
        List<User> users = UserFile.loadUsers();
        return users.stream()
                .anyMatch(existingUser -> existingUser.getEmail().equals(newUser.getEmail()));
    }

}
