package com.example.HotelDayPackage.Service;
import java.util.List;

import com.example.HotelDayPackage.Entity.Booking;

public interface BookingService {
    Booking createBooking(Booking booking, Integer roomId);

    void cancelBooking(Long bookingId);

    List<Booking> getAllBookings();

    Booking updateBooking(Long bookingId, Booking updatedBooking);
}
