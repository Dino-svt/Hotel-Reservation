package com.example.HotelDayPackage.ServiceTests;

import com.example.HotelDayPackage.Entity.RoomAvailability;
import com.example.HotelDayPackage.Repository.RoomAvailabilityRepository;
import com.example.HotelDayPackage.ServiceImplementation.RoomAvailabilityServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RoomAvailabilityServiceImplTest {

    @Mock
    private RoomAvailabilityRepository roomAvailabilityRepository;

    @InjectMocks
    private RoomAvailabilityServiceImpl roomAvailabilityServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllDetails() {
        List<RoomAvailability> mockRoomList = new ArrayList<>();
        mockRoomList.add(new RoomAvailability(1, "Deluxe", "101", "WiFi, TV", "Available", LocalDate.now()));

        when(roomAvailabilityRepository.findAll()).thenReturn(mockRoomList);

        List<RoomAvailability> result = roomAvailabilityServiceImpl.getAllDetails();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Deluxe", result.get(0).getRoom_type());
        verify(roomAvailabilityRepository, times(1)).findAll();
    }

    @Test
    void testSaveDetails() {
        RoomAvailability mockRoom = new RoomAvailability(1, "Deluxe", "101", "WiFi, TV", "Available", LocalDate.now());
        when(roomAvailabilityRepository.save(any(RoomAvailability.class))).thenReturn(mockRoom);

        RoomAvailability result = roomAvailabilityServiceImpl.saveDetails(mockRoom);

        assertNotNull(result);
        assertEquals("Deluxe", result.getRoom_type());
        verify(roomAvailabilityRepository, times(1)).save(any(RoomAvailability.class));
    }

    @Test
    void testGetAllDetailsByDate() {
        LocalDate date = LocalDate.now();
        List<RoomAvailability> mockRoomList = new ArrayList<>();
        mockRoomList.add(new RoomAvailability(1, "Deluxe", "101", "WiFi, TV", "Available", date));

        when(roomAvailabilityRepository.findAvailableRoomsByDate(date)).thenReturn(mockRoomList);

        List<RoomAvailability> result = roomAvailabilityServiceImpl.getAllDetailsByDate(date);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Deluxe", result.get(0).getRoom_type());
        verify(roomAvailabilityRepository, times(1)).findAvailableRoomsByDate(date);
    }

    @Test
    void testUpdateRoomAvailability() {
        RoomAvailability mockExistingRoom = new RoomAvailability(1, "Deluxe", "101", "WiFi, TV", "Available", LocalDate.now());
        RoomAvailability mockUpdatedRoom = new RoomAvailability(1, "Suite", "101", "WiFi, TV, Pool", "Available", LocalDate.now());

        when(roomAvailabilityRepository.findById(1)).thenReturn(Optional.of(mockExistingRoom));
        when(roomAvailabilityRepository.save(any(RoomAvailability.class))).thenReturn(mockUpdatedRoom);

        RoomAvailability result = roomAvailabilityServiceImpl.updateRoomAvailability(mockUpdatedRoom);

        assertNotNull(result);
        assertEquals("Suite", result.getRoom_type());
        verify(roomAvailabilityRepository, times(1)).findById(1);
        verify(roomAvailabilityRepository, times(1)).save(any(RoomAvailability.class));
    }

    @Test
    void testDeleteRoom() {
        Integer roomId = 1;
        when(roomAvailabilityRepository.existsById(roomId)).thenReturn(true);
        doNothing().when(roomAvailabilityRepository).deleteById(roomId);

        String result = roomAvailabilityServiceImpl.deleteRoom(roomId);

        assertEquals("Room with ID 1 deleted successfully.", result);
        verify(roomAvailabilityRepository, times(1)).existsById(roomId);
        verify(roomAvailabilityRepository, times(1)).deleteById(roomId);
    }
}
