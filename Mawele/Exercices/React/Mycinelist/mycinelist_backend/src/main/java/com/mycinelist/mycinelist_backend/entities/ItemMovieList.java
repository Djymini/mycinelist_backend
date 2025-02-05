package com.mycinelist.mycinelist_backend.entities;

public class ItemMovieList {
    private int listId;
    private int movieId;

    public ItemMovieList(int listId, int movieId) {
        this.listId = listId;
        this.movieId = movieId;
    }

    public int getMovieId() {
        return movieId;
    }

    public int getListId() {
        return listId;
    }

    public void setListId(int listId) {
        this.listId = listId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }
}
