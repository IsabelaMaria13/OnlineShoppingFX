package com.example.onlineshoppingfx.utils.users;



import com.example.onlineshoppingfx.exceptions.UserLoadingException;
import com.example.onlineshoppingfx.model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserFile implements Serializable {
    public UserFile() {
    }

    private static final String FILE_PATH = "C:\\Users\\isabe\\IdeaProjects\\OnlineShoppingFX\\files\\users.bine";

    public static List<User> loadUsers() throws UserLoadingException {
        List<User> users = new ArrayList<>();

        try (ObjectInputStream inputStream = new ObjectInputStream(new BufferedInputStream(new FileInputStream(FILE_PATH)))) {
            users = (List<User>) inputStream.readObject();
        } catch (FileNotFoundException e) {
            throw new UserLoadingException("User file not found: " + e.getMessage());
        } catch (IOException | ClassNotFoundException e) {
            throw new UserLoadingException("Error reading user data: " + e.getMessage());
        }

        return users;
    }

    public static void saveUser(User newUser) {
        List<User> users;

        try {
            users = loadUsers();
        } catch (UserLoadingException e) {
            System.out.println("Error loading users: " + e.getMessage());
            return;
        }

        boolean userExists = users.stream().anyMatch(oldUser -> oldUser.getEmail().equals(newUser.getEmail()));
        if (!userExists) {
            users.add(newUser);
        } else {
            System.out.println("User with email " + newUser.getEmail() + " is already registered.");
        }

        try {
            saveUsersToFile(users);
        } catch (IOException e) {
            throw new RuntimeException("Failed to save users to file: " + e.getMessage(), e);
        }
    }


    public static void saveUsersToFile(List<User> users) throws IOException {
        try (ObjectOutputStream outputStream =
                     new ObjectOutputStream(new BufferedOutputStream(
                             new FileOutputStream(FILE_PATH)
                     ))) {
            outputStream.writeObject(users);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void displayUsers() {
        List<User> users;
        try {
            users = loadUsers();
        } catch (UserLoadingException e) {
            System.out.println("Error loading users: " + e.getMessage());
            return;
        }
        if (users.isEmpty()) {
            System.out.println("No users found");
        } else {
            System.out.println("List of registered users:");
            for (User user : users) {
                System.out.println(user);
            }
        }
    }
}
