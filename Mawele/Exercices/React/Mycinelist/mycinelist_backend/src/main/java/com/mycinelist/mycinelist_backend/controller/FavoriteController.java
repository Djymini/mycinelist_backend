package com.mycinelist.mycinelist_backend.controller;

import com.mycinelist.mycinelist_backend.daos.FavoriteDao;
import com.mycinelist.mycinelist_backend.entities.Favorite;
import com.mycinelist.mycinelist_backend.entities.Movie;
import com.mycinelist.mycinelist_backend.entities.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/favorite")
public class FavoriteController {
    private final FavoriteDao favoriteDao;

    public FavoriteController(FavoriteDao favoriteDao) {
        this.favoriteDao = favoriteDao;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Favorite>> getAllFavorite(@PathVariable int userId) {
        return ResponseEntity.ok(favoriteDao.findByUserId(userId));
    }

    @PostMapping
    public ResponseEntity<Favorite> addMovieInFavorite(@RequestBody Favorite favorite) {
        Favorite addFavorite = favoriteDao.save(favorite);
        return ResponseEntity.status(HttpStatus.CREATED).body(addFavorite);
    }

    @DeleteMapping("/movie")
    public ResponseEntity<Void> deleteMovieInFavorite(@RequestParam int userId, int movieId) {
        if (favoriteDao.delete(userId, movieId)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/all")
    public ResponseEntity<Void> deleteAllMovieInFavorite(@RequestParam int userId) {
        if (favoriteDao.deleteAllFavorite(userId)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
