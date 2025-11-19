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
}
