package com.example.HotelDayPackage.Controller;

import com.example.HotelDayPackage.dto.BookingDTO;
import com.example.HotelDayPackage.dto.RoomDTO;
import com.example.HotelDayPackage.Service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    private final RoomService roomService;

    // Constructor-based dependency injection
    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/{roomId}")
    public ResponseEntity<RoomDTO> getRoomById(@PathVariable String roomId) {
        RoomDTO roomDTO = roomService.getRoomById(roomId);
        return ResponseEntity.ok(roomDTO);  // Return 200 OK with room details
    }

    @PostMapping("/{roomId}/book")
    public ResponseEntity<RoomDTO> bookRoom(@PathVariable String roomId, @RequestBody BookingDTO bookingDTO) {
        RoomDTO roomDTO = roomService.bookRoom(roomId, bookingDTO);
        return ResponseEntity.ok(roomDTO);  // Return 200 OK with updated room status
    }

    @PostMapping("/add")
    public ResponseEntity<RoomDTO> addRoom(@RequestBody RoomDTO roomDTO) {
        RoomDTO createdRoom = roomService.addRoom(roomDTO);
        return ResponseEntity.ok(createdRoom);  // Return 200 OK with added room details
    }
}
