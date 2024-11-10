package com.energybilling;

public class Bill {
    private int id;
    private int customerId;
    private String month;
    private int year;
    private double totalAmount;
    private boolean paid;

    public Bill(int id, int customerId, String month, int year, double totalAmount, boolean paid) {
        this.id = id;
        this.customerId = customerId;
        this.month = month;
        this.year = year;
        this.totalAmount = totalAmount;
        this.paid = paid;
    }

    public int getId() {
        return id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public boolean isPaid() {
        return paid;
    }
}

