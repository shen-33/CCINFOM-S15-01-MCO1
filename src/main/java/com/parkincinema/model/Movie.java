package com.parkincinema.model;

import java.time.LocalDateTime;

public class Movie {
    // SQL table columns
    private int movieId;
    private String movieTitle;
    private double price;
    private String genre;
    private float rating;
    private int duration; // based on sql
    private LocalDateTime showDate;
    private LocalDateTime timeSlot;

    public Movie(int movieId, String movieTitle, double price, String genre, float rating, int duration, LocalDateTime showDate, LocalDateTime timeSlot) {
        this.movieId = movieId;
        this.movieTitle = movieTitle;
        this.price = price;
        this.genre = genre;
        this.rating = rating;
        this.duration = duration;
        this.showDate = showDate;
        this.timeSlot = timeSlot;
    }

    public Movie() {

    }

    // getters and setters
    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public LocalDateTime getShowDate() {
        return showDate;
    }

    public void setShowDate(LocalDateTime showDate) {
        this.showDate = showDate;
    }

    public LocalDateTime getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(LocalDateTime timeSlot) {
        this.timeSlot = timeSlot;
    }
}
