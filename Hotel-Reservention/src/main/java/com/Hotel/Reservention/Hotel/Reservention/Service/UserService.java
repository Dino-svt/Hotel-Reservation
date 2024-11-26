package com.Hotel.Reservention.Hotel.Reservention.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.Hotel.Reservention.Hotel.Reservention.Dto.UserDto;
import com.Hotel.Reservention.Hotel.Reservention.Entity.User;

@Service
public interface UserService {

    User createUser(UserDto userDto);
    List<User> viewAllUser();
    User viewUserByUserId(Long userId);
    User updateUser(Long userId, UserDto userDto);
    void deleteUser(Long userId);
    
}
