package com.example.HotelDayPackage.ServiceImplementation;

public class FoodPackAlreadyExistsException extends RuntimeException{
    public FoodPackAlreadyExistsException(String message){
        super(message);
    }
}
