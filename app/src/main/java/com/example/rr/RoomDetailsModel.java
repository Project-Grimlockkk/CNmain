package com.example.rr;

public class RoomDetailsModel {
    int mainImg, vacancyImg, distanceImg;
    String apName, distance, vacancy, price, gender;

    private int apImageResource;

    public RoomDetailsModel(int apImageResource, int vacancyImg, int distanceImg, String apName, String distance, String vacancy, String price, String gender){
        this.apImageResource = apImageResource;
        this.vacancyImg=vacancyImg;
        this.distanceImg=distanceImg;
        this.apName=apName;
        this.distance=distance;
        this.vacancy=vacancy;
        this.price=price;
        this.gender=gender;
    }

    public int getApImageResource() {
        return apImageResource;
    }

}
