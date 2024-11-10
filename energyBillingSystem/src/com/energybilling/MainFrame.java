package com.energybilling;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame {
    private JTextField nameField;
    private JTextField addressField;
    private JTextField emailField;
    private JTextField customerIdField;
    private JTextField monthField;
    private JTextField yearField;
    private JTextField kWhField;
    private JTextField billIdField;
    private BillingSystem billingSystem;

    public MainFrame() {
        billingSystem = new BillingSystem();

        setTitle("Energy Billing System");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(12, 2, 10, 10));

        // Add Customer section
        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField();
        JLabel addressLabel = new JLabel("Address:");
        addressField = new JTextField();
        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField();
        JButton addCustomerButton = new JButton("Add Customer");
        addCustomerButton.addActionListener(this::handleAddCustomer);

        // Add Consumption section
        JLabel customerIdLabel = new JLabel("Customer ID:");
        customerIdField = new JTextField();
        JLabel monthLabel = new JLabel("Month:");
        monthField = new JTextField();
        JLabel yearLabel = new JLabel("Year:");
        yearField = new JTextField();
        JLabel kWhLabel = new JLabel("kWh Consumed:");
        kWhField = new JTextField();
        JButton addConsumptionButton = new JButton("Add Consumption");
        addConsumptionButton.addActionListener(this::handleAddConsumption);

        // Generate Bill section
        JButton generateBillButton = new JButton("Generate Bill");
        generateBillButton.addActionListener(this::handleGenerateBill);

        // Mark Bill as Paid section
        JLabel billIdLabel = new JLabel("Bill ID:");
        billIdField = new JTextField();
        JButton markBillAsPaidButton = new JButton("Mark Bill as Paid");
        markBillAsPaidButton.addActionListener(this::handleMarkBillAsPaid);

        // Add components to frame
        add(nameLabel);
        add(nameField);
        add(addressLabel);
        add(addressField);
        add(emailLabel);
        add(emailField);
        add(addCustomerButton);
        add(new JLabel());  // Empty label to align the button

        add(customerIdLabel);
        add(customerIdField);
        add(monthLabel);
        add(monthField);
        add(yearLabel);
        add(yearField);
        add(kWhLabel);
        add(kWhField);
        add(addConsumptionButton);
        add(new JLabel());  // Empty label to align the button

        add(generateBillButton);
        add(new JLabel());  // Empty label to align the button

        add(billIdLabel);
        add(billIdField);
        add(markBillAsPaidButton);
        add(new JLabel());  // Empty label to align the button
    }

    private void handleAddCustomer(ActionEvent event) {
        String name = nameField.getText();
        String address = addressField.getText();
        String email = emailField.getText();

        if (name.isEmpty() || address.isEmpty() || email.isEmpty()) {
            showMessage("Invalid Input", "Please enter name, address, and email.");
            return;
        }

        billingSystem.addCustomer(name, address, email);
        showMessage("Success", "Customer added successfully.");
    }

    private void handleAddConsumption(ActionEvent event) {
        try {
            int customerId = Integer.parseInt(customerIdField.getText());
            String month = monthField.getText();
            int year = Integer.parseInt(yearField.getText());
            double kWhConsumed = Double.parseDouble(kWhField.getText());

            billingSystem.addConsumption(customerId, month, year, kWhConsumed);
            showMessage("Success", "Consumption record added successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            showMessage("Error", "Failed to add consumption.");
        }
    }

    private void handleGenerateBill(ActionEvent event) {
        try {
            int customerId = Integer.parseInt(customerIdField.getText());
            String month = monthField.getText();
            int year = Integer.parseInt(yearField.getText());

            billingSystem.generateBill(customerId, month, year);
            showMessage("Success", "Bill generated successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            showMessage("Error", "Failed to generate bill.");
        }
    }

    private void handleMarkBillAsPaid(ActionEvent event) {
        try {
            int billId = Integer.parseInt(billIdField.getText());

            billingSystem.markBillAsPaid(billId);
            showMessage("Success", "Bill marked as paid.");
        } catch (Exception e) {
            e.printStackTrace();
            showMessage("Error", "Failed to mark bill as paid.");
        }
    }

    private void showMessage(String title, String message) {
        JOptionPane.showMessageDialog(this, message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);
        });
    }
}
