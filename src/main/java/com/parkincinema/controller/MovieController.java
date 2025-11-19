package com.parkincinema.controller;

import com.parkincinema.dao.MovieDAO;
import com.parkincinema.model.Movie;

import java.time.LocalDateTime;
import java.util.List;

public class MovieController {

    private MovieDAO movieDAO = new MovieDAO();

    // CREATE
    public int addMovie(String title, double price, String genre,
                         float rating, int duration, LocalDateTime showDate, LocalDateTime timeSlot) {

        Movie movie = new Movie();
        movie.setMovieTitle(title);
        movie.setPrice(price);
        movie.setGenre(genre);
        movie.setRating(rating);
        movie.setDuration(duration);
        movie.setShowDate(showDate);
        movie.setTimeSlot(timeSlot);

        movieDAO.addMovie(movie);
        return movie.getMovieId();
    }

    // LIST
    public List<Movie> listMovies() {
        return movieDAO.getAllMovies();
    }

    // READ
    public Movie getMovie(int id) {
        return movieDAO.getMovieById(id);
    }

    // UPDATE
    public boolean updateMovie(Movie movie) {
        return movieDAO.updateMovie(movie);
    }

    // DELETE
    public boolean deleteMovie(int id) {
        return movieDAO.deleteMovie(id);
    }
}