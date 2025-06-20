package com.saitechie.controller;

import com.saitechie.entity.User;
import com.saitechie.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public User addUser(@RequestBody User user){
        log.info("Add User method called "+user);
        return userService.addUser(user);
    }

    @GetMapping("/{userId}")
    public User getUser(@PathVariable int userId){
        return userService.getUser(userId);
    }

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @PutMapping("/{userId}/{amount}")
    public User updateAccountBalance(@PathVariable int userId, @PathVariable double amount){
        return userService.updateAccountStatus(userId, amount);
    }

    @DeleteMapping ("/delete/{userId}")
    public String deleteUser(@PathVariable int userId){
        userService.deleteUser(userId);
        return "User with User ID "+userId+" Deleted Successfully";
    }

}
