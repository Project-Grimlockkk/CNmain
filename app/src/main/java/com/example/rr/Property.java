package com.example.rr;

public class Property {
    private String firstName;
    private String lastName;
    private String phoneNo;
    private String address;
    private int rentInr;
    private int depositInr;
    public Property() {
        // Default constructor required for Firestore
    }

    // Constructor with parameters
    public Property(String firstName, String lastName, String phoneNo, String address, int rentInr, int depositInr) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNo = phoneNo;
        this.address = address;
        this.rentInr = rentInr;
        this.depositInr = depositInr;
    }

    // Getters and setters
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getRentInr() {
        return rentInr;
    }

    public void setRentInr(int rentInr) {
        this.rentInr = rentInr;
    }

    public int getDepositInr() {
        return depositInr;
    }

    public void setDepositInr(int depositInr) {
        this.depositInr = depositInr;
    }
}
