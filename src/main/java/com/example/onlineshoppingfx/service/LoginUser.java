package com.example.onlineshoppingfx.service;


import com.example.onlineshoppingfx.model.User;
import com.example.onlineshoppingfx.utils.users.UserFile;

import java.io.Serializable;
import java.util.List;
import java.util.Scanner;

public class LoginUser implements Serializable {
    public LoginUser() {
    }

    public boolean loginUser(String loginEmail, String loginPassword) {
        List<User> users = UserFile.loadUsers();

        for (User user : users) {
            if (user.getEmail().equals(loginEmail) && user.getPassword().equals(loginPassword)) {
                System.out.println("Login successful for user: " + loginEmail);
                return true;
            }
        }
        System.out.println("Invalid email or password.");
        return false;
    }
}
