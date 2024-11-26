package com.Hotel.Reservention.Hotel.Reservention.Service.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Hotel.Reservention.Hotel.Reservention.Dto.UserDto;
import com.Hotel.Reservention.Hotel.Reservention.Entity.User;
import com.Hotel.Reservention.Hotel.Reservention.Entity.User.Role;
import com.Hotel.Reservention.Hotel.Reservention.Repository.UserRepository;
import com.Hotel.Reservention.Hotel.Reservention.Service.UserService;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(UserDto userDto) {

        Role role;
        
        if("USER".equalsIgnoreCase(userDto.getRole())){
            role = Role.USER;
        }else{
            role = Role.ADMINISTRATOR;
        }
        
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setAddress1(userDto.getAddress1());
        user.setAddress2(userDto.getAddress2());
        user.setCity(userDto.getCity());
        user.setState(userDto.getState());
        user.setZipCode(userDto.getZipCode());
        user.setPhone(userDto.getPhone());
        user.setEmailAddress(userDto.getEmailAddress());
        user.setRole(role);

        return userRepository.save(user);
    }

    @Override
    public List<User> viewAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User viewUserByUserId(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public User updateUser(Long userId, UserDto userDto) {

        Role role;
        
        if("USER".equalsIgnoreCase(userDto.getRole())){
            role = Role.USER;
        }else{
            role = Role.ADMINISTRATOR;
        }

        User updateUser = userRepository.findById(userId).orElse(null);

        if(updateUser == null){
            return null;
        }else{

        updateUser.setFirstName(userDto.getFirstName());
        updateUser.setLastName(userDto.getLastName());
        updateUser.setAddress1(userDto.getAddress1());
        updateUser.setAddress2(userDto.getAddress2());
        updateUser.setCity(userDto.getCity());
        updateUser.setState(userDto.getState());
        updateUser.setZipCode(userDto.getZipCode());
        updateUser.setPhone(userDto.getPhone());
        updateUser.setEmailAddress(userDto.getEmailAddress());
        updateUser.setRole(role);
        }
        return userRepository.save(updateUser);
    }

    @Override
    public void deleteUser(Long userId) {

        userRepository.deleteById(userId);
    }
    
}
