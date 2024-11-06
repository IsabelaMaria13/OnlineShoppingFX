package com.example.onlineshoppingfx.service;


import com.example.onlineshoppingfx.exceptions.UserLoadingException;
import com.example.onlineshoppingfx.model.User;
import com.example.onlineshoppingfx.utils.users.UserFile;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    public List<User> loadUsers() {
        try {
            return UserFile.loadUsers();
        } catch (UserLoadingException e) {
            System.out.println("Error loading users: " + e.getMessage());
            return new ArrayList<>();
        }
    }

}

