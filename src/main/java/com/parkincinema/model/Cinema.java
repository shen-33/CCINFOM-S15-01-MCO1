package com.parkincinema.model;

public class Cinema {

    private int cinemaId;
    private String name;
    private String location;
    private int totalSlots;

    public Cinema(int cinemaId, String name, String location, int totalSlots) {
        this.cinemaId = cinemaId;
        this.name = name;
        this.location = location;
        this.totalSlots = totalSlots;
    }

    // getters and setters
    public int getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(int cinemaId) {
        this.cinemaId = cinemaId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getTotalSlots() {
        return totalSlots;
    }

    public void setTotalSlots(int totalSlots) {
        this.totalSlots = totalSlots;
    }
}
