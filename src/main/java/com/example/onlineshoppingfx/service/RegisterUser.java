package com.example.onlineshoppingfx.service;


import com.example.onlineshoppingfx.model.User;
import com.example.onlineshoppingfx.utils.users.UserFile;

import java.io.Serializable;
import java.util.List;
import java.util.Scanner;

public class RegisterUser implements Serializable {
    public RegisterUser() {
    }

    public boolean registerNewUser(String email, String firstName, String lastName, String password) {

        User newUser = new User(email, firstName, lastName, password);

        if (!isUserAlreadyRegistered(newUser)) {
            UserFile.saveUser(newUser);
            System.out.println("User " + email + " registered successfully.");
            return true;
        } else {
            System.out.println("User with email " + email + " is already registered.");
            return false;
        }
    }

    private boolean isUserAlreadyRegistered(User newUser) {
        List<User> users = UserFile.loadUsers();
        return users.stream()
                .anyMatch(existingUser -> existingUser.getEmail().equals(newUser.getEmail()));
    }

}
