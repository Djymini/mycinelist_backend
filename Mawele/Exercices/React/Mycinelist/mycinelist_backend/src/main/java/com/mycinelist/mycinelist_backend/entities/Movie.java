package com.mycinelist.mycinelist_backend.entities;

import java.time.LocalDate;

public class Movie {
    private int id;
    private String name;
    private int runtime;
    private String releaseDate;
    private String posterPath;

    public Movie(int id, String name, int runtime, String releaseDate, String posterPath) {
        this.id = id;
        this.name = name;
        this.runtime = runtime;
        this.releaseDate = releaseDate;
        this.posterPath = posterPath;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getRuntime() {
        return runtime;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }
}
