package com.example.HotelDayPackage.Repository;

import com.example.HotelDayPackage.Entity.RoomAvailability;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class RoomAvailabilityRepositoryTest {

    @Autowired
    private RoomAvailabilityRepository repository;

    @Test
    void testFindAvailableRoomsByDate_Positive() {
        RoomAvailability room = new RoomAvailability(1, "Deluxe", "101", "WiFi", "Available", LocalDate.now());
        repository.save(room);

        List<RoomAvailability> rooms = repository.findAvailableRoomsByDate(LocalDate.now());

        assertEquals(1, rooms.size());
    }

    @Test
    void testFindAvailableRoomsByDate_Negative() {
        List<RoomAvailability> rooms = repository.findAvailableRoomsByDate(LocalDate.now().plusDays(1));

        assertTrue(rooms.isEmpty());
}

}
