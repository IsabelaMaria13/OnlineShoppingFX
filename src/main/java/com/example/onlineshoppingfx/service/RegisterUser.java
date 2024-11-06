package com.example.onlineshoppingfx.service;

import com.example.onlineshoppingfx.exceptions.InvalidDataException;
import com.example.onlineshoppingfx.exceptions.UserLoadingException;
import com.example.onlineshoppingfx.model.User;
import com.example.onlineshoppingfx.utils.users.UserFile;

import java.io.Serializable;
import java.util.List;

public class RegisterUser implements Serializable {
    public RegisterUser() {
    }

    public boolean registerNewUser(String email, String firstName, String lastName, String password, User.Role role) throws InvalidDataException {
        if (role == User.Role.ADMIN && !email.endsWith("@admin.com")) {
            throw new InvalidDataException("Only emails ending with @admin.com can be assigned the Admin role.");
        }
        User newUser = new User(email, firstName, lastName, password, role);
        String validationMessage = isUserCredentialsValid(newUser);
        if (validationMessage != null) {
            throw new InvalidDataException(validationMessage);
        }
        if (!isUserAlreadyRegistered(newUser)) {
            try {
                UserFile.saveUser(newUser);
            } catch (Exception e) {
                throw new InvalidDataException("An error occurred while saving the user: " + e.getMessage());
            }
            return true;
        } else {
            throw new InvalidDataException("User with email " + email + " is already registered.");
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
        List<User> users;

        try {
            users = UserFile.loadUsers();
        } catch (UserLoadingException e) {
            System.out.println("Error loading users: " + e.getMessage());
            return false;
        }
        return users.stream()
                .anyMatch(existingUser -> existingUser.getEmail().equals(newUser.getEmail()));

    }
}
