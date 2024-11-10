package main.java.com.passwordmanager.view;

import main.java.com.passwordmanager.model.Password;
import main.java.com.passwordmanager.util.DatabaseManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainView extends JFrame {
    private final String MASTER_PASSWORD = "masterkey"; // Set your master password here
    private JButton addPasswordButton;

    public MainView() {
        setTitle("Password Manager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 250);
        setLocationRelativeTo(null); // Center the window

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 1, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        addPasswordButton = new JButton("Add Password");
        addPasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Prompt for master password before displaying Add Password functionality
                String inputPassword = JOptionPane.showInputDialog(MainView.this, "Enter Master Password:");
                if (inputPassword != null && inputPassword.equals(MASTER_PASSWORD)) {
                    // Master password is correct, display Add New Password view
                    displayAddPasswordView();
                } else {
                    // Incorrect master password, display error message
                    JOptionPane.showMessageDialog(MainView.this, "Incorrect Master Password", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        buttonPanel.add(addPasswordButton);

        JButton viewPasswordsButton = new JButton("View Passwords");
        viewPasswordsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle view passwords button click event
            }
        });
        buttonPanel.add(viewPasswordsButton);

        panel.add(buttonPanel, BorderLayout.CENTER);

        ImageIcon icon = new ImageIcon("resources/images/logo.png");
        JLabel logoLabel = new JLabel(icon);
        panel.add(logoLabel, BorderLayout.NORTH);

        add(panel);
    }

    private void displayAddPasswordView() {
        PasswordView passwordView = new PasswordView();
        passwordView.setSaveListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle save password event
                Password password = passwordView.createPassword();
                
                // Save the password data to the database
                DatabaseManager dbManager = new DatabaseManager();
                boolean saved = dbManager.savePassword(password);
                
                // Show success or failure message
                if (saved) {
                    JOptionPane.showMessageDialog(MainView.this, "Password saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(MainView.this, "Failed to save password.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                
                // Close the password view
                passwordView.dispose();
            }
        });
        passwordView.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MainView().setVisible(true);
            }
        });
    }
}
