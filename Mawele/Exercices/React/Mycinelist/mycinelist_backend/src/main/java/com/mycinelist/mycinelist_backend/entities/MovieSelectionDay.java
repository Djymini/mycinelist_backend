package com.mycinelist.mycinelist_backend.entities;

public class MovieSelectionDay {
    private int id;
    private int movieId;
    private String date;

    public MovieSelectionDay(int id, int movieId, String date) {
        this.id = id;
        this.movieId = movieId;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public int getMovieId() {
        return movieId;
    }

    public String getDate() {
        return date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
