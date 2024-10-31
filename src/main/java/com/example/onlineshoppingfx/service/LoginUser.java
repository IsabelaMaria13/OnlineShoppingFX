package com.example.onlineshoppingfx.service;


import com.example.onlineshoppingfx.model.User;
import com.example.onlineshoppingfx.utils.users.UserFile;

import java.io.Serializable;
import java.util.List;

public class LoginUser implements Serializable {
    public LoginUser() {
    }

    public boolean loginUser(String loginEmail, String loginPassword) {
        List<User> users = UserFile.loadUsers();
        for (User user : users) {
            if (user.getEmail().equals(loginEmail) && user.getPassword().equals(loginPassword)) {
                return true;
            }
        }
        return false;
    }
}
