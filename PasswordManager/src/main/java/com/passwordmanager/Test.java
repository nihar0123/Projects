// Test.java
package main.java.com.passwordmanager;

import main.java.com.passwordmanager.controller.AuthController;
import main.java.com.passwordmanager.controller.PasswordController;
import main.java.com.passwordmanager.model.Password;
import main.java.com.passwordmanager.model.User;
import main.java.com.passwordmanager.view.LoginView;
import main.java.com.passwordmanager.view.MainView;
import main.java.com.passwordmanager.view.PasswordView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Test {
    public static void main(String[] args) {
        // Create instances of controllers
        AuthController authController = new AuthController();
        PasswordController passwordController = new PasswordController();

        // Create a sample user
        User user = new User(1,"user1", "password1");

        // Test authentication
        User authenticatedUser = authController.authenticateUser("user1", "password1");
        if (authenticatedUser != null) {
            System.out.println("User authenticated: " + authenticatedUser.getUsername());
        } else {
            System.out.println("Authentication failed.");
        }

        // Test password management
        PasswordView passwordView = new PasswordView();
        passwordView.setSaveListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Password newPassword = (Password) e.getSource();
                System.out.println("New password added:");
                System.out.println("Website: " + newPassword.getWebsite());
                System.out.println("Username: " + newPassword.getUsername());
                System.out.println("Password: " + newPassword.getPassword());
            }
        });
        passwordController.managePasswords();
    }
}
