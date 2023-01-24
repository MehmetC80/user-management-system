package de.memozone.usermanagementsystem.controller;


import de.memozone.usermanagementsystem.model.UserModel;
import de.memozone.usermanagementsystem.services.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
public class UserController {

    @Autowired
    private UserService userService;

    /*
    alternatively with explicit constrictor for the UserService
    without @Autowired Annotation is defining as follows:

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

     */

    // Methods

    @PostMapping("/users")
    public ResponseEntity<UserModel> saveUser(@RequestBody UserModel user) {
        userService.saveUser(user);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserModel>> getAllUser() {
        List<UserModel> user = userService.getAllUser();
        return ResponseEntity.ok(user);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserModel> getSingleUser(@PathVariable("id") Long id) {
        UserModel user = null;
        user = userService.getUserById(id);
        return ResponseEntity.ok(user);

    }


    @DeleteMapping("/users/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteUser(@PathVariable("id") Long id) {

        boolean deleted = false;
        deleted = userService.deleteUser(id);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", deleted);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UserModel> updateUser(@PathVariable("id") Long id,@RequestBody UserModel user){

        user= userService.updateUser(id,user);

        return ResponseEntity.ok(user);

    }



}
