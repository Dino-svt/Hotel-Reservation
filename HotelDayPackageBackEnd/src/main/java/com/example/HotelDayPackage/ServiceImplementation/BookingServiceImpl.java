package com.example.HotelDayPackage.ServiceImplementation;

import com.example.HotelDayPackage.Entity.Booking;
import com.example.HotelDayPackage.Entity.RoomAvailability;
import com.example.HotelDayPackage.Repository.BookingRepository;
import com.example.HotelDayPackage.Repository.RoomAvailabilityRepository;
import com.example.HotelDayPackage.Service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private RoomAvailabilityRepository roomAvailabilityRepository;

    @Override
    public Booking createBooking(Booking booking, Integer roomId) {
        // Find the room by ID
        RoomAvailability room = roomAvailabilityRepository.findById(roomId)
                .orElseThrow(() -> new RuntimeException("Room with ID " + roomId + " not found"));

        // Check if the room is already booked
        if ("Booked".equalsIgnoreCase(room.getStatus())) {
            throw new RuntimeException("Room with ID " + roomId + " is already booked");
        }

        // Set the booking details
        booking.setRoom(room);
        booking.setCreatedDate(LocalDate.now());

        // Change the room status to 'Booked'
        room.setStatus("Booked");
        roomAvailabilityRepository.save(room);

        // Save the booking
        return bookingRepository.save(booking);
    }

    @Override
    public void cancelBooking(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking with ID " + bookingId + " not found"));

        // Get the associated room and set its status back to 'Available'
        RoomAvailability room = booking.getRoom();
        room.setStatus("Available");
        roomAvailabilityRepository.save(room);

        // Delete the booking
        bookingRepository.deleteById(bookingId);
    }

    @Override
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    @Override
    public Booking updateBooking(Long bookingId, Booking updatedBooking) {
        Booking existingBooking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking with ID " + bookingId + " not found"));

        // Update fields if they are provided in the updatedBooking object
        if (updatedBooking.getCustomerName() != null) {
            existingBooking.setCustomerName(updatedBooking.getCustomerName());
        }
        if (updatedBooking.getAddress() != null) {
            existingBooking.setAddress(updatedBooking.getAddress());
        }
        if (updatedBooking.getNic() != null) {
            existingBooking.setNic(updatedBooking.getNic());
        }
        if (updatedBooking.getBookingDate() != null) {
            existingBooking.setBookingDate(updatedBooking.getBookingDate());
        }

        // Save the updated booking
        return bookingRepository.save(existingBooking);

    }
}
