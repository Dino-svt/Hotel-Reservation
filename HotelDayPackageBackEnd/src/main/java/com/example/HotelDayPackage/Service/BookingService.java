package com.example.HotelDayPackage.Service;

import com.example.HotelDayPackage.dto.BookingDTO;

import java.util.List;

public interface BookingService {
    BookingDTO createBooking(BookingDTO bookingDTO);
    void deleteBooking(Long bookingId);
    BookingDTO updateBooking(Long bookingId, BookingDTO bookingDTO);
    BookingDTO viewBooking(Long bookingId);
    List<BookingDTO> viewAllBookings();
}
