package com.example.rr;

public class RoomDetailsClass {

    private String apName, rentInr, Vaccancy, distance, pgPhotos,apGender,address,phoneNo,electricity,cleaningFacility,waterSupply,pgOwner, deposit;

    public String getApName() {
        return apName;
    }
    public void setApName(String apName) {
        this.apName = apName;
    }

    public void setRentInr(String rentInr) {
        this.rentInr = rentInr;
    }

    public void setVaccancy(String vaccancy) {
        this.Vaccancy = vaccancy;
    }
    public void setDistance(String distance) {
        this.distance = distance;
    }
    public void setPgPhotos(String pgPhotos) {
        this.pgPhotos = pgPhotos;
    }
    public void setGender(String apGender) {
        this.apGender = apGender;
    }

    public String getRentInr() {
        return rentInr;
    }

    public String getVaccancy() {
        return Vaccancy;
    }

    public String getDistance() {
        return distance;
    }

    public String getPgPhotos() {
        return pgPhotos;
    }

    public String getGender() {
        return apGender;
    }

    public RoomDetailsClass(String apName, String rentInr, String vaccancy, String distance, String pgPhotos, String apGender,String address, String phoneNo,String electricity, String waterSupply, String cleaningFacility, String pgOwner, String deposit) {
        this.apName = apName;
        this.rentInr = rentInr;
        this.Vaccancy = vaccancy;
        this.distance = distance;
        this.pgPhotos = pgPhotos;
        this.apGender = apGender;
        this.address=address;
        this.phoneNo=phoneNo;
        this.electricity=electricity;
        this.waterSupply=waterSupply;
        this.cleaningFacility=cleaningFacility;
        this.pgOwner=pgOwner;
        this.deposit=deposit;

    }
    public RoomDetailsClass(){

    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getElectricity() {
        return electricity;
    }

    public void setElectricity(String electricity) {
        this.electricity = electricity;
    }

    public String getCleaningFacility() {
        return cleaningFacility;
    }

    public void setCleaningFacility(String cleaningFacility) {
        this.cleaningFacility = cleaningFacility;
    }

    public String getWaterSupply() {
        return waterSupply;
    }

    public void setWaterSupply(String waterSupply) {
        this.waterSupply = waterSupply;
    }

    public String getPgOwner() {
        return pgOwner;
    }

    public void setPgOwner(String pgOwner) {
        this.pgOwner = pgOwner;
    }

    public String getDeposit() {
        return deposit;
    }

    public void setDeposit(String deposit) {
        this.deposit = deposit;
    }
}
