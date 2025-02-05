package com.mycinelist.mycinelist_backend.controller;

import com.mycinelist.mycinelist_backend.daos.MovieListDao;
import com.mycinelist.mycinelist_backend.entities.MovieList;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/list")
public class MovieListController {
    private final MovieListDao movieListDao;

    public MovieListController(MovieListDao movieListDao) {
        this.movieListDao = movieListDao;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<MovieList>> getAllList(@PathVariable int userId) {
        return ResponseEntity.ok(movieListDao.findByUserId(userId));
    }

    @PostMapping
    public ResponseEntity<MovieList> addList(@RequestBody MovieList movieList) {
        MovieList addList = movieListDao.save(movieList);
        return ResponseEntity.status(HttpStatus.CREATED).body(addList);
    }

    @DeleteMapping("/list")
    public ResponseEntity<Void> deleteList(@RequestParam int userId, int id) {
        if (movieListDao.delete(userId, id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
