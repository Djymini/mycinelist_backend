package com.mycinelist.mycinelist_backend.entities;

import java.util.List;

public class TmdbResponse {
    private int page;
    private List<MovieOfTMDB> results;
    private int total_pages;
    private int total_results;

    public TmdbResponse(int page, List<MovieOfTMDB> results, int total_pages, int total_results) {
        this.page = page;
        this.results = results;
        this.total_pages = total_pages;
        this.total_results = total_results;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<MovieOfTMDB> getResults() {
        return results;
    }

    public void setResults(List<MovieOfTMDB> results) {
        this.results = results;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public int getTotal_results() {
        return total_results;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }
}
