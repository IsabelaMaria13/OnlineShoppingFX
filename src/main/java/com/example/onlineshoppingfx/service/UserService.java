package com.example.onlineshoppingfx.service;


import com.example.onlineshoppingfx.model.User;
import com.example.onlineshoppingfx.utils.users.UserFile;

import java.util.List;

public class UserService {
    public List<User> loadUsers() {
        return UserFile.loadUsers();
    }

}

