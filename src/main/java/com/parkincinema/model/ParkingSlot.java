package com.parkincinema.model;

public class ParkingSlot {

    private int slotId;
    private int slotNo;
    private int status;
    private int cinemaId; // FK

    public ParkingSlot() {

    }

    public ParkingSlot(int slotId, int slotNo, int status, int cinemaId) {
        this.slotId = slotId;
        this.slotNo = slotNo;
        this.status = status;
        this.cinemaId = cinemaId;
    }

    // getters and setters
    public int getSlotId() {
        return slotId;
    }

    public void setSlotId(int slotId) {
        this.slotId = slotId;
    }

    public int getSlotNo() {
        return slotNo;
    }

    public void setSlotNo(int slotNo) {
        this.slotNo = slotNo;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(int cinemaId) {
        this.cinemaId = cinemaId;
    }
}
