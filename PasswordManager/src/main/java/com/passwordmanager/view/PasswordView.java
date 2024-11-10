package main.java.com.passwordmanager.view;

import main.java.com.passwordmanager.model.Password;
import main.java.com.passwordmanager.controller.PasswordController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PasswordView extends JFrame {
    private JTextField websiteField;
    private JTextField usernameField;
    private JTextField passwordField;
    private ActionListener saveListener;

    public PasswordView() {
        setTitle("Add Password");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 250);
        setLocationRelativeTo(null); // Center the window

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(4, 1, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("Add New Password");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        formPanel.add(titleLabel);

        JLabel websiteLabel = new JLabel("Website:");
        websiteField = new JTextField();
        formPanel.add(websiteLabel);
        formPanel.add(websiteField);

        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();
        formPanel.add(usernameLabel);
        formPanel.add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JTextField();
        formPanel.add(passwordLabel);
        formPanel.add(passwordField);

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String website = websiteField.getText();
                String username = usernameField.getText();
                String password = passwordField.getText();
                if (saveListener != null) {
                    saveListener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, website + "/" + username + "/" + password));
                }
            }
        });
        formPanel.add(saveButton);

        panel.add(formPanel, BorderLayout.CENTER);

        add(panel);
        setVisible(true);
    }

    public void setSaveListener(ActionListener listener) {
        this.saveListener = listener;
    }

    // Method to create a Password object based on user input
    public Password createPassword() {
        String website = websiteField.getText();
        String username = usernameField.getText();
        String password = passwordField.getText();
        return new Password(website, username, password);
    }
}
