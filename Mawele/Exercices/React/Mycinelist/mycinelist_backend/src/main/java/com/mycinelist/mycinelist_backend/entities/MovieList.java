package com.mycinelist.mycinelist_backend.entities;

public class MovieList {
    private int userId;
    private int id;
    private String name;

    public MovieList(int userId, int id, String name) {
        this.userId = userId;
        this.id = id;
        this.name = name;
    }

    public int getUserId() {
        return userId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
