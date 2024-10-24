package com.example.onlineshoppingfx.utils.users;



import com.example.onlineshoppingfx.model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserFile implements Serializable {
    public UserFile() {
    }

    private static final String FILE_PATH = "C:\\Users\\isabe\\IdeaProjects\\OnlineShoppingFX\\files\\users.bine";

    public static List<User> loadUsers() {
        List<User> users = new ArrayList<>();

        try (ObjectInputStream inputStream = new ObjectInputStream(new BufferedInputStream(
                new FileInputStream(FILE_PATH)
        ))) {
            users = (List<User>) inputStream.readObject();
        } catch (FileNotFoundException e) {
            try {
                throw new FileNotFoundException(e.getMessage());
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return users;
    }

    public static void saveUser(User newUser) {
        List<User> users = loadUsers();
        boolean userExists = users.stream().anyMatch(oldUser -> oldUser.getEmail().equals(newUser.getEmail()));
        if (!userExists) {
            users.add(newUser);
        } else {
            System.out.println("User with email " + newUser.getEmail() + " is already registered.");
        }
        try {
            saveUsersToFile(users);
        } catch (IOException e) {
            throw new RuntimeException(e);
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
        List<User> users = loadUsers();
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
