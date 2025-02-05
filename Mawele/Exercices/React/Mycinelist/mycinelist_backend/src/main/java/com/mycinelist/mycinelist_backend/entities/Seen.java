package com.mycinelist.mycinelist_backend.entities;

public class Seen {
    private int userId;
    private int movieId;
    private String like;
    private String recommendation;
    private String commentary;

    public Seen(int userId, int movieId, String like, String recommendation, String commentary) {
        this.userId = userId;
        this.movieId = movieId;
        this.like = like;
        this.recommendation = recommendation;
        this.commentary = commentary;
    }

    public int getUserId() {
        return userId;
    }

    public int getMovieId() {
        return movieId;
    }

    public String getLike() {
        return like;
    }

    public String getRecommendation() {
        return recommendation;
    }

    public String getCommentary() {
        return commentary;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public void setLike(String like) {
        this.like = like;
    }

    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }

    public void setCommentary(String commentary) {
        this.commentary = commentary;
    }
}
