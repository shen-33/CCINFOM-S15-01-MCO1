package com.parkincinema.view;

import com.parkincinema.controller.BookingController;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.swing.*;
import java.awt.*;

public class BookingWindow extends JFrame {

    private JTextField customerIdField, movieIdField, cinemaIdField, slotIdField, passengersField;
    private JTextField dateField, timeField;
    
    public BookingWindow() {
        setTitle("Book a Ticket");
        setSize(450, 500);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(10, 2, 8, 8));

        customerIdField = new JTextField();
        movieIdField = new JTextField();
        cinemaIdField = new JTextField();
        slotIdField = new JTextField();
        passengersField = new JTextField();
        dateField = new JTextField("YYYY-MM-DD");
        timeField = new JTextField("YYYY-MM-DDTHH:MM");

        JButton bookBtn = new JButton("Confirm Booking");
        JButton backBtn = new JButton("Back");

        panel.add(new JLabel("Customer ID:"));
        panel.add(customerIdField);

        panel.add(new JLabel("Movie ID:"));
        panel.add(movieIdField);

        panel.add(new JLabel("Cinema ID:"));
        panel.add(cinemaIdField);

        panel.add(new JLabel("Slot ID:"));
        panel.add(slotIdField);

        panel.add(new JLabel("Passengers:"));
        panel.add(passengersField);

        panel.add(new JLabel("Booking Date:"));
        panel.add(dateField);

        panel.add(new JLabel("Time Slot:"));
        panel.add(timeField);

        panel.add(bookBtn);
        panel.add(backBtn);

        bookBtn.addActionListener(e -> handleBooking());
        backBtn.addActionListener(e -> dispose());

        add(panel);
        setVisible(true);
    }

    private void handleBooking() {
        try {
            int customerId = Integer.parseInt(customerIdField.getText());
            int movieId = Integer.parseInt(movieIdField.getText());
            int cinemaId = Integer.parseInt(cinemaIdField.getText());
            int slotId = Integer.parseInt(slotIdField.getText());
            int passengers = Integer.parseInt(passengersField.getText());
            LocalDate date = LocalDate.parse(dateField.getText());
            LocalDateTime timeSlot = LocalDateTime.parse(timeField.getText());

            BookingController controller = new BookingController();
            int bookingId = controller.bookTicket(customerId, movieId, cinemaId, slotId, 
                                                passengers, date, timeSlot);
            
            if (bookingId != -1) {
                JOptionPane.showMessageDialog(this, "Booking confirmed! ID: " + bookingId);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Booking failed. Kindly double check your inputs.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }
}
