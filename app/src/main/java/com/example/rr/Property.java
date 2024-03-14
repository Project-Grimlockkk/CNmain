package com.example.rr;

public class Property {
    private String firstName;
    private String lastName;
    private String phoneNo;
    private String address;
    private int rentInr;
    private int depositInr;
    private String tenantType;
    private int numberOfBeds;
    private boolean electricityIncluded;
    private boolean cleaningFacility;
    private boolean internetConnectivity;
    private boolean waterSupply;
    public Property() {
        // Default constructor required for Firestore
    }

    // Constructor with parameters
    public Property(String firstName, String lastName, String phoneNo, String address, int rentInr, int depositInr, String tenantType, int numberOfBeds, boolean electricityIncluded, boolean cleaningFacility, boolean internetConnectivity, boolean waterSupply) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNo = phoneNo;
        this.address = address;
        this.rentInr = rentInr;
        this.depositInr = depositInr;
        this.tenantType = tenantType;
        this.numberOfBeds = numberOfBeds;
        this.electricityIncluded = electricityIncluded;
        this.cleaningFacility = cleaningFacility;
        this.internetConnectivity = internetConnectivity;
        this.waterSupply = waterSupply;
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

    public String getTenantType() {
        return tenantType;
    }

    public void setTenantType(String tenantType) {
        this.tenantType = tenantType;
    }

    public int getNumberOfBeds() {
        return numberOfBeds;
    }

    public void setNumberOfBeds(int numberOfBeds) {
        this.numberOfBeds = numberOfBeds;
    }

    public boolean isElectricityIncluded() {
        return electricityIncluded;
    }

    public void setElectricityIncluded(boolean electricityIncluded) {
        this.electricityIncluded = electricityIncluded;
    }

    public boolean isCleaningFacility() {
        return cleaningFacility;
    }

    public void setCleaningFacility(boolean cleaningFacility) {
        this.cleaningFacility = cleaningFacility;
    }

    public boolean isInternetConnectivity() {
        return internetConnectivity;
    }

    public void setInternetConnectivity(boolean internetConnectivity) {
        this.internetConnectivity = internetConnectivity;
    }

    public boolean isWaterSupply() {
        return waterSupply;
    }

    public void setWaterSupply(boolean waterSupply) {
        this.waterSupply = waterSupply;
    }
}
