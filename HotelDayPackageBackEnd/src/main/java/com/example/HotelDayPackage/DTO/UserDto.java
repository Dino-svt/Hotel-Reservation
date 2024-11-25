package com.example.HotelDayPackage.DTO;

import lombok.Data;

@Data
public class UserDto {
    
    private String firstName;
    private String lastName;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String zipCode;
    private String phone;
    private String emailAddress;
    private String role;
}
