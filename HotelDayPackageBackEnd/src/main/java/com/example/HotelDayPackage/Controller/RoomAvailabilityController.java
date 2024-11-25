package com.example.HotelDayPackage.Controller;

import com.example.HotelDayPackage.Entity.RoomAvailability;
import com.example.HotelDayPackage.Service.RoomAvailabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/rooms")
@CrossOrigin(origins = "http://localhost:3000")
public class RoomAvailabilityController {

    @Autowired
    private RoomAvailabilityService roomAvailabilityService;

    // Get all room details
    @GetMapping("/viewroom")
    public List<RoomAvailability> getAllDetails() {
        return roomAvailabilityService.getAllDetails();
    }

    // Save room details
    @PostMapping("/saveroom")
    public RoomAvailability saveDetails(@RequestBody RoomAvailability roomAvailability) {
        return roomAvailabilityService.saveDetails(roomAvailability);
    }


    // Get room details by date
    @GetMapping("/viewroombydate/{booking_date}")
    public ResponseEntity<?> getAllDetailsByDate(@PathVariable("booking_date") LocalDate booking_date) {
        List<RoomAvailability> availableRooms = roomAvailabilityService.getAllDetailsByDate(booking_date);

        if (availableRooms == null || availableRooms.isEmpty()) {
            // Return a message when no rooms are available for the booking date
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Any Available Rooms for this " +booking_date+ " Date");
        }
        return ResponseEntity.ok(availableRooms);
    }


    // Update room details
    @PutMapping("/updateroom")
    public RoomAvailability updateDetails(@RequestBody RoomAvailability roomAvailability) {
        return roomAvailabilityService.updateRoomAvailability(roomAvailability);
    }

    // Delete room details
    @DeleteMapping("/deleteroom/{room_id}")
    public String deleteRoomDetails(@PathVariable Integer room_id) {
        return roomAvailabilityService.deleteRoom(room_id);
    }
}