package com.example.HotelDayPackage.Service;

public class FoodPackAlreadyExistsException extends RuntimeException{
    public FoodPackAlreadyExistsException(String message){
        super(message);
    }
}
