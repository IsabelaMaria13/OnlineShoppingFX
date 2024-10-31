package com.example.onlineshoppingfx.service;


import com.example.onlineshoppingfx.model.User;
import com.example.onlineshoppingfx.utils.users.UserFile;

import java.io.Serializable;
import java.util.List;

public class RegisterUser implements Serializable {
    public RegisterUser() {
    }

    public boolean registerNewUser(String email, String firstName, String lastName, String password) {

        User newUser = new User(email, firstName, lastName, password);

        if (!isUserAlreadyRegistered(newUser)) {
            UserFile.saveUser(newUser);
            return true;
        } else {
            return false;
        }
    }
    public String isUserCredentialsValid(User newUser) {
        if (newUser.getEmail() == null || newUser.getEmail().isEmpty() || !newUser.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            return "Invalid email format.";
        }
        if (newUser.getFirstName() == null || newUser.getFirstName().isEmpty()) {
            return "First name cannot be empty.";
        }
        if (newUser.getLastName() == null || newUser.getLastName().isEmpty()) {
            return "Last name cannot be empty.";
        }
        if (newUser.getPassword() == null || newUser.getPassword().isEmpty()) {
            return "Password cannot be empty.";
        }
        return null;
    }

    private boolean isUserAlreadyRegistered(User newUser) {
        List<User> users = UserFile.loadUsers();
        return users.stream()
                .anyMatch(existingUser -> existingUser.getEmail().equals(newUser.getEmail()));
    }

}
