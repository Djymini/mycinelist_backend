package com.mycinelist.mycinelist_backend.controller;

import com.mycinelist.mycinelist_backend.daos.UserDao;
import com.mycinelist.mycinelist_backend.dtos.UserDto;
import com.mycinelist.mycinelist_backend.entities.User;
import com.mycinelist.mycinelist_backend.security.JwUtil;
import com.mycinelist.mycinelist_backend.services.UserServices;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {
    private final UserDao userDao;
    private final UserServices userServices;
    private final JwUtil jwUtil;

    public UserController(UserDao userDao, UserServices userServices, JwUtil jwUtil) {
        this.userDao = userDao;
        this.userServices = userServices;
        this.jwUtil = jwUtil;
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userDao.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserbyId(@PathVariable int id) {
        return ResponseEntity.ok(userDao.findById(id));
    }

    @GetMapping("/connected")
    public ResponseEntity<UserDto> getUserByToken(@RequestHeader("Authorization") String authorizationHeader) {
        System.out.println(authorizationHeader);
        String token = authorizationHeader.substring(7);
        System.out.println(token);
        String email = jwUtil.getEmailFromToken(token);
        System.out.println(email);
        UserDto userDto = userServices.getUserByEmail(email);
        return ResponseEntity.ok(userDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User user) {
        User updateUser = userDao.update(id, user);
        return ResponseEntity.ok(updateUser);
    }

    @PostMapping
    public void saveUser(@Valid @RequestBody User user) {
        userDao.save(user);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        if (userDao.delete(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
