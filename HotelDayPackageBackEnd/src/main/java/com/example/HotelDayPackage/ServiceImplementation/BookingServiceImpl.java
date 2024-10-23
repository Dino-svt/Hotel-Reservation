package com.example.HotelDayPackage.ServiceImplementation;

import com.example.HotelDayPackage.dto.BookingDTO;
import com.example.HotelDayPackage.Entity.Booking;
import com.example.HotelDayPackage.Repository.BookingRepository;
import com.example.HotelDayPackage.Service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Override
    public BookingDTO createBooking(BookingDTO bookingDTO) {
        Booking booking = new Booking();
        booking.setRoomId(bookingDTO.getRoomId());
        booking.setBookingDate(bookingDTO.getBookingDate());
        booking.setCustomerName(bookingDTO.getCustomerName());

        bookingRepository.save(booking);
        return bookingDTO;
    }
}
