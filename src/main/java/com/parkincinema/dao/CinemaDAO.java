package com.parkincinema.dao;

import com.parkincinema.dbconnection.DBConnection;
import com.parkincinema.model.Cinema;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CinemaDAO {

    // getting all cinemas (READ)
    public List<Cinema> getAllCinemas () {
        List<Cinema> allCinemas = new ArrayList<>();
        String sqlPrompt = "SELECT * FROM Cinema";

        // required resources
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;

        try {
            connection = DBConnection.getConnection();
            statement = connection.prepareStatement(sqlPrompt);
            result = statement.executeQuery();

            while (result.next()) {
                // Instantiate cinema object
                Cinema cinema = new Cinema();
                // set attributes to the cinema object
                cinema.setCinemaId(result.getInt("cinema_id"));
                cinema.setName(result.getString("name"));
                cinema.setLocation(result.getString("location"));
                cinema.setTotalSlots(result.getInt("total_slots"));

                allCinemas.add(cinema);
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

        return allCinemas;
    }

    public Cinema getCinemaById (int id) {
        Cinema cinema = null;
        String sqlPrompt = "SELECT * FROM Cinema WHERE cinema_id = ?";

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;

        try {
            connection = DBConnection.getConnection();
            statement = connection.prepareStatement(sqlPrompt);

            // Set the ID parameter
            statement.setInt(1, id);

            result = statement.executeQuery();

            if (result.next()) {
                cinema = new Cinema();
                cinema.setCinemaId(result.getInt("cinema_id"));
                cinema.setName(result.getString("name"));
                cinema.setLocation(result.getString("location"));
                cinema.setTotalSlots(result.getInt("total_slots"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // close all
            try {
                if (result != null) result.close();
            } catch (SQLException e)
            { e.printStackTrace(); }

            try {
                if (statement != null)
                    statement.close(); }
            catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (connection != null)
                    connection.close(); }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return cinema;
    }
}