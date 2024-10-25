package com.example.HotelDayPackage.ServiceImplementation;

import com.example.HotelDayPackage.dto.BookingDTO;
import com.example.HotelDayPackage.Entity.Booking;
import com.example.HotelDayPackage.Repository.BookingRepository;
import com.example.HotelDayPackage.Service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Override
    public BookingDTO createBooking(BookingDTO bookingDTO) {
        Booking booking = convertToEntity(bookingDTO);
        bookingRepository.save(booking);
        return bookingDTO;
    }

    @Override
    public void deleteBooking(Long bookingId) {
        if (bookingRepository.existsById(bookingId)) {
            bookingRepository.deleteById(bookingId);
        } else {
            throw new RuntimeException("Booking not found with id: " + bookingId);
        }
    }

    @Override
    public BookingDTO updateBooking(Long bookingId, BookingDTO bookingDTO) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found with id: " + bookingId));

        booking.setRoomId(bookingDTO.getRoomId());
        booking.setCustomerName(bookingDTO.getCustomerName());
        booking.setCustomerNIC(bookingDTO.getCustomerNIC());
        booking.setAddress(bookingDTO.getAddress());
        booking.setCheckInDate(bookingDTO.getCheckInDate());
        booking.setCheckOutDate(bookingDTO.getCheckOutDate());
        booking.setCurrentDate(bookingDTO.getCurrentDate());

        bookingRepository.save(booking);
        return bookingDTO;
    }

    @Override
    public BookingDTO viewBooking(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found with id: " + bookingId));
        return convertToDTO(booking);
    }

    @Override
    public List<BookingDTO> viewAllBookings() {
        return bookingRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private Booking convertToEntity(BookingDTO bookingDTO) {
        return new Booking(
                null, bookingDTO.getRoomId(),bookingDTO.getCustomerName(),
                bookingDTO.getCustomerNIC(), bookingDTO.getAddress(), bookingDTO.getCheckInDate(),
                bookingDTO.getCheckOutDate(), bookingDTO.getCurrentDate()
        );
    }

    private BookingDTO convertToDTO(Booking booking) {
        return new BookingDTO(
                booking.getRoomId(), booking.getCustomerName(),
                booking.getCustomerNIC(), booking.getAddress(), booking.getCheckInDate(),
                booking.getCheckOutDate(), booking.getCurrentDate()
        );
    }
}
