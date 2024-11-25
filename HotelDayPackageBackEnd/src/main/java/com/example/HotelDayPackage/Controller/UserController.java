package com.example.HotelDayPackage.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.HotelDayPackage.DTO.UserDto;
import com.example.HotelDayPackage.Entity.User;
import com.example.HotelDayPackage.Service.UserService;

@RestController
@RequestMapping("user")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/postData")
    public ResponseEntity<String> createUser(@RequestBody UserDto userDto) {
        userService.createUser(userDto);
        return ResponseEntity.ok("Registration Successful");
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<User>> viewAllUser() {
        List<User> users = userService.viewAllUser();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> viewUserByUserId(@PathVariable Long userId){
        User user = userService.viewUserByUserId(userId);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<String> updateUser(@PathVariable Long userId, @RequestBody UserDto userDto) {
        userService.updateUser(userId, userDto);
        return ResponseEntity.ok("Registration Updated Successfully");
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteRegistration(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok("Registration Deleted Successfully");
}
}