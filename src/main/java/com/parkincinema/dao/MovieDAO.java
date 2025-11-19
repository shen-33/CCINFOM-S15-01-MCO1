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
                movie.setTimeSlot(timeSlot);

                allMovies.add(movie);
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
        return allMovies;
    }

    // Get a movie by its ID (Read)
    public Movie getMovieById(int id) {
        String sql = "SELECT * FROM Movie WHERE movie_id = ?";

        try (Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Movie m = new Movie();
                m.setMovieId(rs.getInt("movie_id"));
                m.setMovieTitle(rs.getString("movie_title"));
                m.setPrice(rs.getDouble("price"));
                m.setGenre(rs.getString("genre"));
                m.setRating(rs.getFloat("rating"));
                m.setDuration(rs.getInt("duration"));
                m.setShowDate(rs.getObject("showDates", LocalDateTime.class));
                m.setTimeSlot(rs.getObject("timeSlots", LocalDateTime.class));
                return m;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Update an existing movie
    public boolean updateMovie(Movie movie) {
        String sql = "UPDATE Movie SET movie_title=?, price=?, genre=?, rating=?, duration=?, showDates=?, timeSlots=? WHERE movie_id=?";

        try (Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, movie.getMovieTitle());
            stmt.setDouble(2, movie.getPrice());
            stmt.setString(3, movie.getGenre());
            stmt.setFloat(4, movie.getRating());
            stmt.setInt(5, movie.getDuration());
            stmt.setObject(6, movie.getShowDate());
            stmt.setObject(7, movie.getTimeSlot());
            stmt.setInt(8, movie.getMovieId());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete an exisiting movie 
    public boolean deleteMovie(int id) {
        String sql = "DELETE FROM Movie WHERE movie_id = ?";

        try (Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Add a new movie (CREATE)
    public void addMovie(Movie movie) {
        String sqlPrompt = "INSERT INTO Movie (movie_id, movie_title, price, genre, rating, duration, showDates, timeSlots) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DBConnection.getConnection();
            statement = connection.prepareStatement(sqlPrompt);

            statement.setInt(1, movie.getMovieId());
            statement.setString(2, movie.getMovieTitle());
            statement.setDouble(3, movie.getPrice());
            statement.setString(4, movie.getGenre());
            statement.setFloat(5, movie.getRating());
            statement.setInt(6, movie.getDuration());
            statement.setObject(7, movie.getShowDate());
            statement.setObject(8, movie.getTimeSlot());

            // update
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
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
    }
}