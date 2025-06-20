package com.saitechie.service;

import com.saitechie.entity.User;
import com.saitechie.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserService {
    @Autowired
    private UserRepository repository;

    public User addUser(User user){
        log.info("addUser method from service "+user.toString());
        return repository.save(user);
    }

    public User getUser(int userId){
         return repository.findById(userId).
                 orElseThrow(() -> new RuntimeException("User not found with Id "+userId));
    }

    public List<User> getAllUsers(){
        List<User> allUsers = repository.findAll();
        return allUsers;
    }

    public User updateAccountStatus (int userId, double usedAmount){
        User userDetailsFromDB = getUser(userId);
        log.info("User current Available Balance is "+userDetailsFromDB.getAvailableAmount());
        userDetailsFromDB.setAvailableAmount(userDetailsFromDB.getAvailableAmount() - usedAmount);
        User user = repository.save(userDetailsFromDB);
        log.info("After Deducting the Amount "+usedAmount +" , The User Current Available Balance is : "+userDetailsFromDB.getAvailableAmount());
        return user;
    }

    public void deleteUser(int userId){
        repository.deleteById(userId);
        log.info("User deleted successfully");
    }
}

