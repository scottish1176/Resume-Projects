package com.techelevator.controller;


import com.techelevator.dao.UserDao;
import com.techelevator.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class UserController {
    @Autowired
    private UserDao userDao;

    @PreAuthorize("hasRole('ROLE_SYSTEM_ADMIN')")
    @DeleteMapping(value = "/delete-user/{Id}") // user id
    public boolean deleteUser (@PathVariable Long Id) {
        return userDao.deleteUser(Id);
    }

    @PreAuthorize("hasRole('ROLE_SYSTEM_ADMIN')")
    @GetMapping(value = "/get-all-users")
    public List<User> findAll(){
        return userDao.findAll();
    }


}
