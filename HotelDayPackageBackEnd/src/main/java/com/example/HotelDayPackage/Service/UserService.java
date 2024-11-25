package com.example.HotelDayPackage.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.HotelDayPackage.DTO.UserDto;
import com.example.HotelDayPackage.Entity.User;

@Service
public interface UserService {

    User createUser(UserDto userDto);
    List<User> viewAllUser();
    User viewUserByUserId(Long userId);
    User updateUser(Long userId, UserDto userDto);
    void deleteUser(Long userId);
    
}
