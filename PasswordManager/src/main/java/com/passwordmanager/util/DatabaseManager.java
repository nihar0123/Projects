package main.java.com.passwordmanager.util;

import main.java.com.passwordmanager.model.Password;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseManager {
    private static final String URL = "jdbc:mysql://localhost:3306/passwordManager";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "system@!2*1@";

    public boolean savePassword(Password password) {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String query = "INSERT INTO passwords (website, username, password) VALUES (?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, password.getWebsite());
                statement.setString(2, password.getUsername());
                statement.setString(3, password.getPassword());
                int rowsInserted = statement.executeUpdate();
                return rowsInserted > 0;
            }
        } catch (SQLException e) {
            System.err.println("Failed to save password: " + e.getMessage());
            return false;
        }
    }
}
