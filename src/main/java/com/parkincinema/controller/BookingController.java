package com.parkincinema.controller;

import com.parkincinema.dao.*;
import com.parkincinema.model.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class BookingController {

    private BookingDAO bookingDAO = new BookingDAO();
    private MovieDAO movieDAO = new MovieDAO();
    private CustomerDAO customerDAO = new CustomerDAO();
    private ParkingSlotDAO slotDAO = new ParkingSlotDAO();

    // Book a ticket for the cinema
    public int bookTicket(int customerId, int movieId, int cinemaId, int slotId,
                          int passengersNum, LocalDate date, LocalDateTime timeSlot) {

        // ----- Error Handlings -----

        if (customerDAO.getCustomerById(customerId) == null) {
            System.out.println("Customer does not exist!");
            return -1;
        }

        Movie movie = movieDAO.getMovieById(movieId);
        if (movie == null) {
            System.out.println("Movie does not exist!");
            return -1;
        }

        ParkingSlot slot = slotDAO.getSlotById(slotId);
        if (slot == null || slot.getStatus() == 1) {
            System.out.println("Slot is already taken!");
            return -1;
        }

        if (bookingDAO.customerAlreadyBooked(customerId, timeSlot)) {
            System.out.println("Customer already booked something at the same timeslot!");
            return -1;
        }

        // Compute total amount
        double total = movie.getPrice() * passengersNum;

        // Create new booking
        Booking booking = new Booking();
        booking.setPassengersNum(passengersNum);
        booking.setDate(date);
        booking.setTimeSlot(timeSlot);
        booking.setTotalAmount(total);
        booking.setOrderStatus(1); // booked

        booking.setMovieId(movieId);
        booking.setCustomerId(customerId);
        booking.setCinemaId(cinemaId);
        booking.setSlotId(slotId);

        int id = bookingDAO.createBooking(booking);

        // Update parking slot to reserved
        slotDAO.updateSlotStatus(slotId, 1);

        return id;
    }


    // Cancel an existing booking
    public boolean cancelBooking(int bookingId) {
        Booking b = bookingDAO.getBookingById(bookingId);
        if (b == null) return false;

        // Set order status to cancelled
        bookingDAO.cancelBooking(bookingId);

        // Free slot
        slotDAO.updateSlotStatus(b.getSlotId(), 0);

        return true;
    }
}
