package com.example.rr;

import com.google.protobuf.Internal;

public class inputData {

    String firstName, lastName, phoneNumber, tenantType, description;
    Integer Rent, Deposit, beds;
    Boolean electricity, cleaning, internetSupply, waterSupply;

    public inputData() {
    }

    public inputData(String firstName, String lastName, String phoneNumber, String tenantType, String description, Integer Rent, Integer Deposit, Integer beds, Boolean electricity, Boolean cleaning, Boolean internetSupply, Boolean waterSupply){
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.tenantType = tenantType;
        this.description = description;
        this.Rent = Rent;
        this.Deposit = Deposit;
        this.beds = beds;
        this.electricity = electricity;
        this.cleaning = cleaning;
        this.internetSupply = internetSupply;
        this.waterSupply = waterSupply;
    }

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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getTenantType() {
        return tenantType;
    }

    public void setTenantType(String tenantType) {
        this.tenantType = tenantType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getRent() {
        return Rent;
    }

    public void setRent(Integer rent) {
        Rent = rent;
    }

    public Integer getDeposit() {
        return Deposit;
    }

    public void setDeposit(Integer deposit) {
        Deposit = deposit;
    }

    public Integer getBeds() {
        return beds;
    }

    public void setBeds(Integer beds) {
        this.beds = beds;
    }

    public Boolean getElectricity() {
        return electricity;
    }

    public void setElectricity(Boolean electricity) {
        this.electricity = electricity;
    }

    public Boolean getCleaning() {
        return cleaning;
    }

    public void setCleaning(Boolean cleaning) {
        this.cleaning = cleaning;
    }

    public Boolean getInternetSupply() {
        return internetSupply;
    }

    public void setInternetSupply(Boolean internetSupply) {
        this.internetSupply = internetSupply;
    }

    public Boolean getWaterSupply() {
        return waterSupply;
    }

    public void setWaterSupply(Boolean waterSupply) {
        this.waterSupply = waterSupply;
    }
}
