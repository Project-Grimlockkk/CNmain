package com.example.rr;

public class Property {
    private String firstName,pgPhotos,distance,gender,apName,vacancy;
    private String lastName;
    private String phoneNo;
    private String address;
    private int rentInr;
    private int depositInr;
//    private int gender;
    private String tenantType;
    private int numberOfBeds;
    private boolean electricityIncluded;
    private boolean cleaningFacility;
    private boolean internetConnectivity;
    private boolean waterSupply;
    private int apImageResource;
    public Property() {
        // Default constructor required for Firestore
    }

    // Constructor with parameters
    // String tenantType, int numberOfBeds, boolean electricityIncluded, boolean cleaningFacility, boolean internetConnectivity, boolean waterSupply
    public Property(String pgPhotos, String vacancy, int rentInr,String distance,String apName,String gender) {
        this.pgPhotos=pgPhotos;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.phoneNo = phoneNo;
        this.vacancy = vacancy;
        this.rentInr = rentInr;
//        this.depositInr = depositInr;
        this.distance=distance;
        this.apName=apName;
        this.gender=gender;
//        this.tenantType = tenantType;
//        this.numberOfBeds = numberOfBeds;
//        this.electricityIncluded = electricityIncluded;
//        this.cleaningFacility = cleaningFacility;
//        this.internetConnectivity = internetConnectivity;
//        this.waterSupply = waterSupply;
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

    public String getPgPhotos() {
        return pgPhotos;
    }

    public void setPgPhotos(String pgPhotos) {
        this.pgPhotos = pgPhotos;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getApName() {
        return apName;
    }

    public void setApName(String apName) {
        this.apName = apName;
    }

    public String getVacancy() {
        return vacancy;
    }

    public void setVacancy(String vacancy) {
        this.vacancy = vacancy;
    }

//    public String getTenantType() {
//        return tenantType;
//    }
//
//    public void setTenantType(String tenantType) {
//        this.tenantType = tenantType;
//    }
//
//    public int getNumberOfBeds() {
//        return numberOfBeds;
//    }
//
//    public void setNumberOfBeds(int numberOfBeds) {
//        this.numberOfBeds = numberOfBeds;
//    }
//
//    public boolean isElectricityIncluded() {
//        return electricityIncluded;
//    }
//
//    public void setElectricityIncluded(boolean electricityIncluded) {
//        this.electricityIncluded = electricityIncluded;
//    }
//
//    public boolean isCleaningFacility() {
//        return cleaningFacility;
//    }
//
//    public void setCleaningFacility(boolean cleaningFacility) {
//        this.cleaningFacility = cleaningFacility;
//    }
//
//    public boolean isInternetConnectivity() {
//        return internetConnectivity;
//    }
//
//    public void setInternetConnectivity(boolean internetConnectivity) {
//        this.internetConnectivity = internetConnectivity;
//    }
//
//    public boolean isWaterSupply() {
//        return waterSupply;
//    }
//
//    public void setWaterSupply(boolean waterSupply) {
//        this.waterSupply = waterSupply;
//    }
}
