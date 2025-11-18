package com.parkincinema.dao;

import com.parkincinema.dbconnection.DBConnection;
import com.parkincinema.model.Movie;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MovieDAO {

    // Getting all movies (READ)
    public List<Movie> getAllMovies() {
        List<Movie> allMovies = new ArrayList<>();
        String sqlPrompt = "SELECT * FROM Movie";

        // required resources
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;

        try {
            connection = DBConnection.getConnection();
            statement = connection.prepareStatement(sqlPrompt);
            result = statement.executeQuery();

            while (result.next()) {
                // Instantiate movie object
                Movie movie = new Movie();
                // set attributes to the movie object
                movie.setMovieId(result.getInt("movie_id"));
                movie.setMovieTitle(result.getString("movie_title"));
                movie.setPrice(result.getDouble("price"));
                movie.setGenre(result.getString("genre"));
                movie.setRating(result.getFloat("rating"));
                movie.setDuration(result.getInt("duration"));

                // get showDate and timeSlot since it is in LocalDateTime object
                LocalDateTime showDate = result.getObject("showDates", LocalDateTime.class);
                LocalDateTime timeSlot = result.getObject("timeSlots", LocalDateTime.class);
                // set both to movie object
                movie.setShowDate(showDate);

                allMovies.add(movie);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // closing result
            try {
                if (result != null) result.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            // closing statement
            try {
                if (statement != null) statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            // closing connection
            try {
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return allMovies;
    }
}