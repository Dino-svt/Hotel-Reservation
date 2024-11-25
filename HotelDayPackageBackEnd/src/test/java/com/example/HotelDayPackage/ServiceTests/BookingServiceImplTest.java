package com.example.HotelDayPackage.ServiceTests;

import com.example.HotelDayPackage.Entity.Booking;
import com.example.HotelDayPackage.Entity.RoomAvailability;
import com.example.HotelDayPackage.Repository.BookingRepository;
import com.example.HotelDayPackage.Repository.RoomAvailabilityRepository;
import com.example.HotelDayPackage.ServiceImplementation.BookingServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookingServiceImplTest {

    @InjectMocks
    private BookingServiceImpl bookingService;

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private RoomAvailabilityRepository roomAvailabilityRepository;

    private Booking booking;
    private RoomAvailability room;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        room = new RoomAvailability();
        room.setRoom_id(1);;
        room.setStatus("Available");

        booking = new Booking();
        booking.setId(1L);
        booking.setCustomerName("John Doe");
        booking.setAddress("123 Street");
        booking.setNic("123456789V");
        booking.setBookingDate(LocalDate.now().plusDays(1));
        booking.setCreatedDate(LocalDate.now());
        booking.setRoom(room);
    }

    @Test
    void testCreateBooking() {
        when(roomAvailabilityRepository.findById(1)).thenReturn(Optional.of(room));
        when(bookingRepository.save(any(Booking.class))).thenReturn(booking);

        Booking createdBooking = bookingService.createBooking(booking, 1);

        assertNotNull(createdBooking);
        assertEquals("John Doe", createdBooking.getCustomerName());
        verify(roomAvailabilityRepository, times(1)).save(room);
        verify(bookingRepository, times(1)).save(booking);
    }

    @Test
    void testCancelBooking() {
        when(bookingRepository.findById(1L)).thenReturn(Optional.of(booking));

        bookingService.cancelBooking(1L);

        assertEquals("Available", room.getStatus());
        verify(roomAvailabilityRepository, times(1)).save(room);
        verify(bookingRepository, times(1)).deleteById(1L);
    }

    @Test
    void testGetAllBookings() {
        when(bookingRepository.findAll()).thenReturn(Collections.singletonList(booking));

        assertEquals(1, bookingService.getAllBookings().size());
        verify(bookingRepository, times(1)).findAll();
    }

    @Test
    void testUpdateBooking() {
        when(bookingRepository.findById(1L)).thenReturn(Optional.of(booking));
        when(bookingRepository.save(any(Booking.class))).thenReturn(booking);

        Booking updatedBooking = new Booking();
        updatedBooking.setCustomerName("Jane Doe");

        Booking result = bookingService.updateBooking(1L, updatedBooking);

        assertEquals("Jane Doe", result.getCustomerName());
        verify(bookingRepository, times(1)).save(booking);
    }
}
