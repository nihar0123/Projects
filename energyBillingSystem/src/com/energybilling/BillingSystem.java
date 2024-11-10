package com.energybilling;
import java.sql.*;
import java.util.Scanner;

public class BillingSystem {
    private DatabaseManager dbManager;

    public BillingSystem() {
        dbManager = new DatabaseManager();
    }

    public void addCustomer(String name, String address, String email) {
        String query = "INSERT INTO customers (name, address, email) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = dbManager.getConnection().prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.setString(2, address);
            stmt.setString(3, email);
            stmt.executeUpdate();
            System.out.println("Customer added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addConsumption(int customerId, String month, int year, double kWhConsumed) {
        String query = "INSERT INTO consumption (customer_id, month, year, kWh_consumed) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = dbManager.getConnection().prepareStatement(query)) {
            stmt.setInt(1, customerId);
            stmt.setString(2, month);
            stmt.setInt(3, year);
            stmt.setDouble(4, kWhConsumed);
            stmt.executeUpdate();
            System.out.println("Consumption record added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void generateBill(int customerId, String month, int year) {
        String query = "SELECT kWh_consumed FROM consumption WHERE customer_id = ? AND month = ? AND year = ?";
        try (PreparedStatement stmt = dbManager.getConnection().prepareStatement(query)) {
            stmt.setInt(1, customerId);
            stmt.setString(2, month);
            stmt.setInt(3, year);
            ResultSet rs = stmt.executeQuery();
            double totalKWh = 0;
            while (rs.next()) {
                totalKWh += rs.getDouble("kWh_consumed");
            }
            double ratePerKWh = 0.12; // Example rate
            double totalAmount = totalKWh * ratePerKWh;
            addBill(customerId, month, year, totalAmount);
            System.out.println("Bill generated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addBill(int customerId, String month, int year, double totalAmount) {
        String query = "INSERT INTO bills (customer_id, month, year, total_amount, paid) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = dbManager.getConnection().prepareStatement(query)) {
            stmt.setInt(1, customerId);
            stmt.setString(2, month);
            stmt.setInt(3, year);
            stmt.setDouble(4, totalAmount);
            stmt.setBoolean(5, false);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void markBillAsPaid(int billId) {
        String query = "UPDATE bills SET paid = ? WHERE id = ?";
        try (PreparedStatement stmt = dbManager.getConnection().prepareStatement(query)) {
            stmt.setBoolean(1, true);
            stmt.setInt(2, billId);
            stmt.executeUpdate();
            System.out.println("Bill marked as paid.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        BillingSystem system = new BillingSystem();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("1. Add Customer");
            System.out.println("2. Add Consumption");
            System.out.println("3. Generate Bill");
            System.out.println("4. Mark Bill as Paid");
            System.out.println("5. Exit");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter address: ");
                    String address = scanner.nextLine();
                    System.out.print("Enter email: ");
                    String email = scanner.nextLine();
                    system.addCustomer(name, address, email);
                    break;
                case 2:
                    System.out.print("Enter customer ID: ");
                    int customerId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter month: ");
                    String month = scanner.nextLine();
                    System.out.print("Enter year: ");
                    int year = scanner.nextInt();
                    System.out.print("Enter kWh consumed: ");
                    double kWhConsumed = scanner.nextDouble();
                    system.addConsumption(customerId, month, year, kWhConsumed);
                    break;
                case 3:
                    System.out.print("Enter customer ID: ");
                    customerId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter month: ");
                    month = scanner.nextLine();
                    System.out.print("Enter year: ");
                    year = scanner.nextInt();
                    system.generateBill(customerId, month, year);
                    break;
                case 4:
                    System.out.print("Enter bill ID: ");
                    int billId = scanner.nextInt();
                    system.markBillAsPaid(billId);
                    break;
                case 5:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();
        system.dbManager.closeConnection();
    }
}
