package com.parkincinema.dao;

import com.parkincinema.dbconnection.DBConnection;
import java.sql.*;
import java.util.*;

public class ReportDAO {
    
    public Map<String, Object> generateSalesReport(int month, int year) {
        Map<String, Object> report = new HashMap<>();
        String sql = "SELECT SUM(total_amount) as total_sales, COUNT(*) as total_bookings " +
                    "FROM Booking WHERE MONTH(date) = ? AND YEAR(date) = ? AND order_status = 1";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, month);
            stmt.setInt(2, year);
            
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                report.put("total_sales", rs.getDouble("total_sales"));
                report.put("total_bookings", rs.getInt("total_bookings"));
                report.put("month", month);
                report.put("year", year);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return report;
    }

    public List<Map<String, Object>> generateSlotOccupancy(int month, int year) {
        List<Map<String, Object>> occupancy = new ArrayList<>();
        String sql = "SELECT c.name as cinema_name, " +
                    "COUNT(DISTINCT b.ParkingSlot_slot_id) as occupied_slots, " +
                    "c.total_slots as total_slots " +
                    "FROM Booking b " +
                    "JOIN Cinema c ON b.Cinema_cinema_id = c.cinema_id " +
                    "WHERE MONTH(b.date) = ? AND YEAR(b.date) = ? AND b.order_status = 1 " +
                    "GROUP BY c.cinema_id";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, month);
            stmt.setInt(2, year);
            
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                row.put("cinema_name", rs.getString("cinema_name"));
                row.put("occupied_slots", rs.getInt("occupied_slots"));
                row.put("total_slots", rs.getInt("total_slots"));
                occupancy.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return occupancy;
    }

    public List<Map<String, Object>> generateBookingPattern(int month, int year) {
        List<Map<String, Object>> patterns = new ArrayList<>();
        String sql = "SELECT m.movie_title, COUNT(b.booking_id) as booking_count " +
                    "FROM Booking b " +
                    "JOIN Movie m ON b.Movie_movie_id = m.movie_id " +
                    "WHERE MONTH(b.date) = ? AND YEAR(b.date) = ? AND b.order_status = 1 " +
                    "GROUP BY m.movie_id " +
                    "ORDER BY booking_count DESC";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, month);
            stmt.setInt(2, year);
            
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                row.put("movie_title", rs.getString("movie_title"));
                row.put("booking_count", rs.getInt("booking_count"));
                patterns.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patterns;
    }
}