package com.parkincinema.dao;

import com.parkincinema.dbconnection.DBConnection;
import com.parkincinema.model.ParkingSlot;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

public class ParkingSlotDAO {

    // READ
    public List<ParkingSlot> getAvailableSlotsByCinema (int cinemaId) {
        List<ParkingSlot> availableSlots = new ArrayList<>();
        String sqlPrompt = "SELECT * FROM ParkingSlot WHERE Cinema_cinema_id = ? and status = 0";

        // required resources
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;

        try {
            connection = DBConnection.getConnection();
            statement = connection.prepareStatement(sqlPrompt);
            statement.setInt(1, cinemaId); // setting the parameter
            result = statement.executeQuery();

            while (result.next()) {
                // Instantiate slot object
                ParkingSlot slot = new ParkingSlot();
                // set attributes to the slot object
                slot.setSlotId(result.getInt("slot_id"));
                slot.setCinemaId(result.getInt("Cinema_cinema_id"));
                slot.setSlotNo(result.getInt("slot_no"));
                slot.setStatus(result.getInt("status"));

                availableSlots.add(slot);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // closing result
            try {
                if (result != null)
                    result.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            // closing statement
            try {
                if (statement != null)
                    statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            // closing connection
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return availableSlots;
    }
}