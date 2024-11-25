package com.example.HotelDayPackage.Service;

import com.example.HotelDayPackage.Entity.Booking;

import java.util.List;

public interface BookingService {
    Booking createBooking(Booking booking, Integer roomId);

    void cancelBooking(Long bookingId);

    List<Booking> getAllBookings();

    Booking updateBooking(Long bookingId, Booking updatedBooking);
}
