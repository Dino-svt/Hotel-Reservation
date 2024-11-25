package com.example.HotelDayPackage.ControllerTests;

import com.example.HotelDayPackage.Controller.RoomAvailabilityController;
import com.example.HotelDayPackage.Entity.RoomAvailability;
import com.example.HotelDayPackage.Service.RoomAvailabilityService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RoomAvailabilityControllerTest {

    @Mock
    private RoomAvailabilityService roomAvailabilityService;

    @InjectMocks
    private RoomAvailabilityController roomAvailabilityController;

    public RoomAvailabilityControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllDetails() {
        List<RoomAvailability> mockRoomList = new ArrayList<>();
        mockRoomList.add(new RoomAvailability(1, "Deluxe", "101", "WiFi, TV", "Available", LocalDate.now()));

        when(roomAvailabilityService.getAllDetails()).thenReturn(mockRoomList);

        List<RoomAvailability> result = roomAvailabilityController.getAllDetails();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Deluxe", result.get(0).getRoom_type());
        verify(roomAvailabilityService, times(1)).getAllDetails();
    }

    @Test
    void testSaveDetails() {
        RoomAvailability mockRoom = new RoomAvailability(1, "Deluxe", "101", "WiFi, TV", "Available", LocalDate.now());
        when(roomAvailabilityService.saveDetails(mockRoom)).thenReturn(mockRoom);

        RoomAvailability result = roomAvailabilityController.saveDetails(mockRoom);

        assertNotNull(result);
        assertEquals("Deluxe", result.getRoom_type());
        verify(roomAvailabilityService, times(1)).saveDetails(mockRoom);
    }

    @Test
    void testGetAllDetailsByDate() {
        LocalDate date = LocalDate.now();
        List<RoomAvailability> mockRoomList = new ArrayList<>();
        mockRoomList.add(new RoomAvailability(1, "Deluxe", "101", "WiFi, TV", "Available", date));

        when(roomAvailabilityService.getAllDetailsByDate(date)).thenReturn(mockRoomList);

        ResponseEntity<?> response = roomAvailabilityController.getAllDetailsByDate(date);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        verify(roomAvailabilityService, times(1)).getAllDetailsByDate(date);
    }

    @Test
    void testDeleteRoomDetails() {
        Integer roomId = 1;
        when(roomAvailabilityService.deleteRoom(roomId)).thenReturn("Room with ID " + roomId + " deleted successfully.");

        String result = roomAvailabilityController.deleteRoomDetails(roomId);

        assertEquals("Room with ID 1 deleted successfully.", result);
        verify(roomAvailabilityService, times(1)).deleteRoom(roomId);
    }
}
