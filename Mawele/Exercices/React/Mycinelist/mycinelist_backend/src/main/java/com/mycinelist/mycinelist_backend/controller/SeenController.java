package com.mycinelist.mycinelist_backend.controller;

import com.mycinelist.mycinelist_backend.daos.SeenDao;
import com.mycinelist.mycinelist_backend.entities.Seen;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/seen")
public class SeenController {
    private final SeenDao seenDao;

    public SeenController(SeenDao seenDao) {
        this.seenDao = seenDao;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Seen>> getAllSeen(@PathVariable int userId) {
        return ResponseEntity.ok(seenDao.findByUserId(userId));
    }

    @PostMapping
    public ResponseEntity<Seen> addMovieInSeen(@RequestBody Seen seen) {
        Seen addSeen = seenDao.save(seen);
        return ResponseEntity.status(HttpStatus.CREATED).body(addSeen);
    }

    @DeleteMapping("/movie")
    public ResponseEntity<Void> deleteMovieInSeen(@RequestParam int userId, int movieId) {
        if (seenDao.delete(userId, movieId)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/all")
    public ResponseEntity<Void> deleteAllMovieInSeen(@RequestParam int userId) {
        if (seenDao.deleteAllSeen(userId)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
