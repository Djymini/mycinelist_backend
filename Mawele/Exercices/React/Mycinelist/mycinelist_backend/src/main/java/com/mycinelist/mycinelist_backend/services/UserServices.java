package com.mycinelist.mycinelist_backend.services;

import com.mycinelist.mycinelist_backend.daos.UserDao;
import com.mycinelist.mycinelist_backend.dtos.UserDto;
import com.mycinelist.mycinelist_backend.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServices {

    @Autowired
    private UserDao userDao;


    public UserDto getUserByEmail(String email) {
        System.out.println("coucou");
        User user = userDao.findByEmail(email);
        System.out.println(user);
        return new UserDto(user.getEmail(), user.getUsername(), user.getRole());
    }
}
