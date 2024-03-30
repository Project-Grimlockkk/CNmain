package com.example.rr;

public class RoomDetailsClass {

    private String apName, rentInr, Vaccancy, distance, pgPhotos,gender;

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
    public void setGender(String gender) {
        this.gender = gender;
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
        return gender;
    }

    public RoomDetailsClass(String apName, String rentInr, String vaccancy, String distance, String pgPhotos, String gender) {
        this.apName = apName;
        this.rentInr = rentInr;
        this.Vaccancy = vaccancy;
        this.distance = distance;
        this.pgPhotos = pgPhotos;
        this.gender = gender;
    }
    public RoomDetailsClass(){

    }
}
