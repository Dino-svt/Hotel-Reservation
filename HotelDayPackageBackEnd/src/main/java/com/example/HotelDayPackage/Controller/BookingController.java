package com.example.HotelDayPackage.Controller;

import com.example.HotelDayPackage.dto.BookingDTO;
import com.example.HotelDayPackage.Service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping
    public BookingDTO createBooking(@RequestBody BookingDTO bookingDTO) {
        return bookingService.createBooking(bookingDTO);
    }
}
