package com.example.onlineshoppingfx.controller;

import com.example.onlineshoppingfx.model.User;
import com.example.onlineshoppingfx.service.UserService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.util.List;

public class UsersController {
    @FXML
    private TableView<User> userTable;

    @FXML
    private TableColumn<User, String> emailColumn;

    @FXML
    private TableColumn<User, String> firstNameColumn;

    @FXML
    private TableColumn<User, String> lastNameColumn;

    private final UserService userService = new UserService();
    private final ObservableList<User> userList = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        loadUsers();
    }

    @FXML
    private void loadUsers() {
        List<User> users = userService.loadUsers();
        userList.setAll(users);
        userTable.setItems(userList);
    }

    public void back(ActionEvent actionEvent) {
        ((Stage) userTable.getScene().getWindow()).close();
    }
}
