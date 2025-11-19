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

    // Read slot by ID
    public ParkingSlot getSlotById(int slotId) {
        String sql = "SELECT * FROM ParkingSlot WHERE slot_id = ?";

        try (Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, slotId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                ParkingSlot slot = new ParkingSlot();
                slot.setSlotId(rs.getInt("slot_id"));
                slot.setSlotNo(rs.getInt("slot_no"));
                slot.setStatus(rs.getInt("status"));
                slot.setCinemaId(rs.getInt("Cinema_cinema_id"));
                return slot;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // Update the status of the slot
    public boolean updateSlotStatus(int slotId, int status) {
        String sql = "UPDATE ParkingSlot SET status=? WHERE slot_id=?";

        try (Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, status);
            stmt.setInt(2, slotId);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}