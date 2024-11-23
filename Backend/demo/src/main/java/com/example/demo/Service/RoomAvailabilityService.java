package com.example.demo.Service;

import java.time.LocalDate;
import java.util.List;

import com.example.demo.Model.RoomAvailability;
import org.springframework.stereotype.Service;

@Service
public interface RoomAvailabilityService {

    List<RoomAvailability> getAllDetails();

    RoomAvailability saveDetails(RoomAvailability roomAvailability);

    List<RoomAvailability> getAllDetailsByDate(LocalDate booking_date);

    RoomAvailability updateRoomAvailability(RoomAvailability roomAvailability);

    String deleteRoom(Integer room_id);
}