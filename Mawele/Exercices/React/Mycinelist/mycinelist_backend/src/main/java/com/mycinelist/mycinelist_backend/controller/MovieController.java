package com.mycinelist.mycinelist_backend.controller;

import com.mycinelist.mycinelist_backend.daos.MovieDao;
import com.mycinelist.mycinelist_backend.entities.Movie;
import com.mycinelist.mycinelist_backend.entities.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieController {
    private final MovieDao movieDao;

    public MovieController(MovieDao movieDao) {
        this.movieDao = movieDao;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Movie>> getAllUsers() {
        return ResponseEntity.ok(movieDao.findAll());
    }

    @GetMapping("/id")
    public ResponseEntity<Movie> getMoviebyId(@PathVariable int id) {
        return ResponseEntity.ok(movieDao.findById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Movie>> getMovieBySearch(@RequestParam String query) {
        return ResponseEntity.ok(movieDao.findBySearch(query));
    }

    @PostMapping
    public ResponseEntity<Movie> createProduct(@RequestBody Movie movie) {
        Movie createdMovie = movieDao.save(movie);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMovie);
    }
}
