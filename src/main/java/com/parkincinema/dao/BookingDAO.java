package com.parkincinema.dao;

import com.parkincinema.dbconnection.DBConnection;
import com.parkincinema.model.Booking;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class BookingDAO {

    // inserts new booking record and returns the generated booking id
    public int createBooking(Booking booking) {
        String sqlPrompt = "INSERT INTO Booking (" +
                "passengers_num, total_amount, time_slot, date, order_status, " +
                "Movie_movie_id, Customer_customer_id, Cinema_cinema_id, ParkingSlot_slot_id) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)"; // columns for the insert statement

        int generatedBookingId = -1;

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet generatedKeys = null;

        try {
            connection = DBConnection.getConnection();
            statement = connection.prepareStatement(sqlPrompt, Statement.RETURN_GENERATED_KEYS);

            // set 9 parameters
            statement.setInt(1, booking.getPassengersNum());
            statement.setDouble(2, booking.getTotalAmount());
            statement.setObject(3, booking.getTimeSlot());
            statement.setObject(4, booking.getDate());
            statement.setInt(5, booking.getOrderStatus());

            // FKs
            statement.setInt(6, booking.getMovieId());
            statement.setInt(7, booking.getCustomerId());
            statement.setInt(8, booking.getCinemaId());
            statement.setInt(9, booking.getSlotId());

            int affectedRows = statement.executeUpdate();

            if (affectedRows > 0) {
                generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    generatedBookingId = generatedKeys.getInt(1); // gets the new id
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Closing resources
            try {
                if (generatedKeys != null)
                    generatedKeys.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (statement != null)
                    statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return generatedBookingId;
    }

    // get booking record by id (READ)
    public Booking getBookingById(int id) {
        Booking booking = null;
        String sqlPrompt = "SELECT * FROM Booking WHERE booking_id = ?";

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;

        try {
            connection = DBConnection.getConnection();
            statement = connection.prepareStatement(sqlPrompt);
            statement.setInt(1, id);

            result = statement.executeQuery();

            if (result.next()) {
                booking = new Booking();
                // set parameters
                booking.setBookingId(result.getInt("booking_id"));
                booking.setPassengersNum(result.getInt("passengers_num"));
                booking.setTotalAmount(result.getDouble("total_amount"));
                booking.setTimeSlot(result.getObject("time_slot", LocalDateTime.class));
                booking.setDate(result.getObject("date", LocalDate.class));
                booking.setOrderStatus(result.getInt("order_status"));

                // FKs
                booking.setMovieId(result.getInt("Movie_movie_id"));
                booking.setCustomerId(result.getInt("Customer_customer_id"));
                booking.setCinemaId(result.getInt("Cinema_cinema_id"));
                booking.setSlotId(result.getInt("ParkingSlot_slot_id"));
            }
        } catch (SQLException e) {

            e.printStackTrace();
        } finally {
            try {
                if (result != null)
                    result.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (statement != null)
                    statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return booking;
    }
}