package com.example.HotelDayPackage.ControllerTests;

import com.example.HotelDayPackage.Controller.BookingController;
import com.example.HotelDayPackage.Entity.Booking;
import com.example.HotelDayPackage.Service.BookingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookingController.class)
class BookingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookingService bookingService;

    private Booking booking;

    @BeforeEach
    void setUp() {
        booking = new Booking();
        booking.setId(1L);
        booking.setCustomerName("John Doe");
        booking.setAddress("123 Street");
        booking.setNic("123456789V");
        booking.setBookingDate(LocalDate.now().plusDays(1));
        booking.setCreatedDate(LocalDate.now());
    }

    @Test
    void testCreateBooking() throws Exception {
        when(bookingService.createBooking(Mockito.any(Booking.class), Mockito.anyInt())).thenReturn(booking);

        mockMvc.perform(post("/bookings/create/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"customerName\":\"John Doe\",\"address\":\"123 Street\",\"nic\":\"123456789V\"}")
                        .param("roomId", "1"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.customerName").value("John Doe"));
    }

    @Test
    void testCancelBooking() throws Exception {
        Mockito.doNothing().when(bookingService).cancelBooking(1L);

        mockMvc.perform(delete("/bookings/cancel/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Booking cancelled successfully"));
    }

    @Test
    void testGetAllBookings() throws Exception {
        when(bookingService.getAllBookings()).thenReturn(Collections.singletonList(booking));

        mockMvc.perform(get("/bookings/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].customerName").value("John Doe"));
    }

    @Test
    void testUpdateBooking() throws Exception {
        when(bookingService.updateBooking(Mockito.anyLong(), Mockito.any(Booking.class))).thenReturn(booking);

        mockMvc.perform(put("/bookings/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"customerName\":\"John Doe Updated\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customerName").value("John Doe"));
    }
}
