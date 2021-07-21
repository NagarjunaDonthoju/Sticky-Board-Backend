package com.board.boardbackend.controller;

import com.board.boardbackend.exception.ResourceNotFoundException;
import com.board.boardbackend.model.User;
import com.board.boardbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("users")
    public User createUser(@RequestBody User user, @RequestAttribute("uid") String curUID) throws Exception {

        if(curUID != null && curUID.equals(user.getUid())){
            return userService.createUser(user);
        }

        System.out.println(curUID);
        System.out.println(user.getUid());
        throw new Exception("Unauthorized access");

    }

    @GetMapping("users/{uid}")
    public ResponseEntity<User> findById(@PathVariable(value = "uid") String uid,
                                         @RequestAttribute("uid") String curUID)
            throws Exception {

        if(curUID != null && curUID.equals(uid)) {
            User user = userService.getUserById(uid);
            return ResponseEntity.ok().body(user);
        }

        throw new Exception("Unauthorized access");

    }

    @PutMapping("users/{uid}")
    public ResponseEntity<User> updateUser(@PathVariable(value = "uid") String uid,
                                           @RequestBody User userDetails,
                                           @RequestAttribute("uid") String curUID)
            throws Exception {

        if(curUID != null && curUID.equals(uid)){
            User updatedUserData = userService.updateUser(userDetails, uid);
            return ResponseEntity.ok(updatedUserData);
        }

        throw new Exception("Unauthorized access");

    }
}
