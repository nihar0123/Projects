package main.java.com.passwordmanager.controller;

import main.java.com.passwordmanager.model.Password;
import main.java.com.passwordmanager.model.User;
import main.java.com.passwordmanager.view.PasswordView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PasswordController {
    private PasswordView passwordView;
    private User currentUser; // Hold the reference to the authenticated user

    public PasswordController(User currentUser) {
        this.currentUser = currentUser;
        this.passwordView = new PasswordView();
        initSaveListener();
    }
    public PasswordController(){
        this.passwordView = new PasswordView();
        initSaveListener();
    }

    private void initSaveListener() {
        passwordView.setSaveListener(e -> savePassword());
    }

    public void managePasswords() {
        passwordView.setVisible(true);
    }

    private void savePassword() {
        Password password = passwordView.createPassword();
        if (password != null) {
            // Set the user_id for the password
            password.setUserId(currentUser.getId()); // Using getId() from User class

            // Implement password management logic, such as saving the password to the database
            if (saveToDatabase(password)) {
                System.out.println("Password saved successfully!");
            } else {
                System.out.println("Failed to save password.");
            }
        }
    }

    private boolean saveToDatabase(Password password) {
        // Example implementation of saving password to a database
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/passwordManager", "username", "password");
            String query = "INSERT INTO passwords (user_id, website, username, password) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, password.getUserId());
            preparedStatement.setString(2, password.getWebsite());
            preparedStatement.setString(3, password.getUsername());
            preparedStatement.setString(4, password.getPassword());
            int rowsAffected = preparedStatement.executeUpdate();
            connection.close();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
