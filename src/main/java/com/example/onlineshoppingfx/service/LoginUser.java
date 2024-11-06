package com.example.onlineshoppingfx.service;


import com.example.onlineshoppingfx.exceptions.UserLoadingException;
import com.example.onlineshoppingfx.model.User;
import com.example.onlineshoppingfx.utils.users.UserFile;

import java.io.Serializable;
import java.util.List;

public class LoginUser implements Serializable {
    public LoginUser() {
    }

    public User loginUser(String loginEmail, String loginPassword) {
        List<User> users;
        try {
            users = UserFile.loadUsers();
        } catch (UserLoadingException e) {
            System.out.println("Error loading users: " + e.getMessage());
            return null;
        }

        for (User user : users) {
            if (user.getEmail().equals(loginEmail) && user.getPassword().equals(loginPassword)) {
                return user;
            }
        }
        return null;
    }
}
