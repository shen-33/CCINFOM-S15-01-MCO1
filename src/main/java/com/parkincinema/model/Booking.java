package com.parkincinema.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Booking {

    private int bookingId;
    private int passengersNum;
    private double totalAmount;
    private LocalDateTime timeSlot;
    private LocalDate date;
    private int orderStatus;

    // FK
    private int movieId;
    private int customerId;
    private int cinemaId;
    private int slotId;

    public Booking (){

    }

    public Booking(int bookingId, int passengersNum, double totalAmount, LocalDateTime timeSlot, LocalDate date, int orderStatus, int movieId, int customerId, int cinemaId, int slotId) {
        this.bookingId = bookingId;
        this.passengersNum = passengersNum;
        this.totalAmount = totalAmount;
        this.timeSlot = timeSlot;
        this.date = date;
        this.orderStatus = orderStatus;
        this.movieId = movieId;
        this.customerId = customerId;
        this.cinemaId = cinemaId;
        this.slotId = slotId;
    }

    // getters and setters

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getPassengersNum() {
        return passengersNum;
    }

    public void setPassengersNum(int passengersNum) {
        this.passengersNum = passengersNum;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalDateTime getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(LocalDateTime timeSlot) {
        this.timeSlot = timeSlot;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(int cinemaId) {
        this.cinemaId = cinemaId;
    }

    public int getSlotId() {
        return slotId;
    }

    public void setSlotId(int slotId) {
        this.slotId = slotId;
    }
}
