package com.example.rr;

public class contactModel {
    int img, occupancy;
    String tenant, rate, distance;

    public contactModel(int img, String tenant, String rate, String distance){
        this.tenant = tenant;
        this.img = img;
        this.rate = rate;
        this.distance = distance;
    }
}
