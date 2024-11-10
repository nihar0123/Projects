package com.energybilling;
public class Consumption {
    private int id;
    private int customerId;
    private String month;
    private int year;
    private double kWhConsumed;

    public Consumption(int id, int customerId, String month, int year, double kWhConsumed) {
        this.id = id;
        this.customerId = customerId;
        this.month = month;
        this.year = year;
        this.kWhConsumed = kWhConsumed;
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

    public double getKWhConsumed() {
        return kWhConsumed;
    }
}

