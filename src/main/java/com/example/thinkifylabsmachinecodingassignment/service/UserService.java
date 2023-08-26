package com.example.thinkifylabsmachinecodingassignment.service;

import com.example.thinkifylabsmachinecodingassignment.model.User;
import com.example.thinkifylabsmachinecodingassignment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public int registerUser(User user) {
        return userRepository.save(user);
    }

    public int updateUser(User user) {
        return userRepository.update(user);
    }
}
