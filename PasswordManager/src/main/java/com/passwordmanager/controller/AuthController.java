// AuthController.java
package main.java.com.passwordmanager.controller;

import main.java.com.passwordmanager.model.User;

public class AuthController {
    private User[] users; // Placeholder for user data (in a real app, this could be fetched from a database)

    public AuthController() {
        // Initialize some sample users (in a real app, users would be fetched from a database)
        users = new User[]{
            new User(1,"user1", "password1"),
            new User(2,"user2", "password2"),
            new User(3,"user3", "password3")
        };
    }

    public User authenticateUser(String username, String password) {
        // Search for the user with the given username
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                // Found a user with the given username, check if the password matches
                if (user.getPassword().equals(password)) {
                    // Authentication successful, return the user object
                    return user;
                } else {
                    // Password mismatch, return null with an error message
                    return new User(-1,"Error", "Password incorrect");
                }
            }
        }
        // No user found with the given username, return null with an error message
        return new User(-1,"Error", "User not found");
    }
}
