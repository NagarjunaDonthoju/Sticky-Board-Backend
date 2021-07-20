package com.board.boardbackend.service;

import com.board.boardbackend.exception.ResourceNotFoundException;
import com.board.boardbackend.model.User;
import com.board.boardbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUserById(String uid) throws ResourceNotFoundException {

        return userRepository.findById(uid)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this uid: " + uid));

    }

    public User createUser(User user){

        final Date DATE = new Date();
        user.setCreatedAt(DATE);
        user.setUpdatedAt(DATE);
        return userRepository.save(user);

    }

    public User updateUser(User userDetails, String uid) throws ResourceNotFoundException{

        User user = getUserById(uid);
        user.setFirstName(userDetails.getFirstName());
        user.setLastName(userDetails.getLastName());
        user.setUpdatedAt(new Date());
        return this.userRepository.save(user);

    }
}
