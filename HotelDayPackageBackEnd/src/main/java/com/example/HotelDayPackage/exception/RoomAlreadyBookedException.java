package com.example.HotelDayPackage.exception;

public class RoomAlreadyBookedException extends RuntimeException {
    public RoomAlreadyBookedException(String message) {
        super(message);
    }
}
