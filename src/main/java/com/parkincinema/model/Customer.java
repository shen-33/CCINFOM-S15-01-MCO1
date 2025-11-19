package com.parkincinema.model;

public class Customer {

    private int customerId;
    private String lastName;
    private String firstName;
    private String phoneNo;
    private String carPlate;

    public Customer() {

    }

    public Customer(int customerId, String lastName, String firstName, String phoneNo, String carPlate) {
        this.customerId = customerId;
        this.lastName = lastName;
        this.firstName = firstName;
        this.phoneNo = phoneNo;
        this.carPlate = carPlate;
    }

    // getters and setters

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getCarPlate() {
        return carPlate;
    }

    public void setCarPlate(String carPlate) {
        this.carPlate = carPlate;
    }
}
