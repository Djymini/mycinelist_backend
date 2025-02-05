package com.mycinelist.mycinelist_backend.controller;

import com.mycinelist.mycinelist_backend.daos.ItemMovieListDao;
import com.mycinelist.mycinelist_backend.entities.Favorite;
import com.mycinelist.mycinelist_backend.entities.ItemMovieList;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/list")
public class ItemMovieListController {
    private final ItemMovieListDao itemMovieListDao;

    public ItemMovieListController(ItemMovieListDao itemMovieListDao) {
        this.itemMovieListDao = itemMovieListDao;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<ItemMovieList>> getAllFavorite(@PathVariable int userId) {
        return ResponseEntity.ok(itemMovieListDao.findByUserId(userId));
    }

    @PostMapping
    public ResponseEntity<ItemMovieList> addMovieInList(@RequestBody ItemMovieList itemMovieList) {
        ItemMovieList addList = itemMovieListDao.save(itemMovieList);
        return ResponseEntity.status(HttpStatus.CREATED).body(addList);
    }

    @DeleteMapping("/list")
    public ResponseEntity<Void> deleteMovieInFavorite(@RequestParam int listId, int movieId) {
        if (itemMovieListDao.delete(listId, movieId)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
