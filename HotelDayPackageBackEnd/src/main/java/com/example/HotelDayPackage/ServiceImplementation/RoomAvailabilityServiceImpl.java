package com.example.HotelDayPackage.ServiceImplementation;

import com.example.HotelDayPackage.Entity.RoomAvailability;
import com.example.HotelDayPackage.Repository.RoomAvailabilityRepository;
import com.example.HotelDayPackage.Service.RoomAvailabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RoomAvailabilityServiceImpl implements RoomAvailabilityService {

    @Autowired
    private RoomAvailabilityRepository roomAvailabilityRepository;

    @Override
    public List<RoomAvailability> getAllDetails() {
        return roomAvailabilityRepository.findAll();
    }

    @Override
    public RoomAvailability saveDetails(RoomAvailability roomAvailability) {
        // Using Builder pattern to create RoomAvailability instance
        RoomAvailability newRoom = new RoomAvailability.Builder()
                .withRoomType(roomAvailability.getRoom_type())
                .withRoomNumber(roomAvailability.getRoom_number())
                .withRoomFacilities(roomAvailability.getRoom_facilities())
                .withStatus(roomAvailability.getStatus())
                .withBookingDate(roomAvailability.getBooking_date())
                .build();

        return roomAvailabilityRepository.save(newRoom);
    }

    @Override
    public List<RoomAvailability> getAllDetailsByDate(LocalDate booking_date) {
        List<RoomAvailability> availableRooms = roomAvailabilityRepository.findAvailableRoomsByDate(booking_date);

        if (availableRooms.isEmpty()) {
            System.out.println("No rooms available on: " + booking_date);
            return null;  // or you can return an empty list or handle this in the controller
        }
        return availableRooms;
    }


    @Override
    public RoomAvailability updateRoomAvailability(RoomAvailability roomAvailability) {
        RoomAvailability existingRoomAvailability = roomAvailabilityRepository.findById(roomAvailability.getRoom_id()).orElse(null);

        if (existingRoomAvailability != null) {
            // Using Builder pattern to update fields
            RoomAvailability updatedRoom = new RoomAvailability.Builder()
                    .withRoomId(existingRoomAvailability.getRoom_id())
                    .withRoomType(roomAvailability.getRoom_type())
                    .withRoomNumber(roomAvailability.getRoom_number())
                    .withRoomFacilities(roomAvailability.getRoom_facilities())
                    .withStatus(roomAvailability.getStatus())
                    .withBookingDate(existingRoomAvailability.getBooking_date())  // Keeping original booking date
                    .build();

            roomAvailabilityRepository.save(updatedRoom);
            System.out.println("Room with ID " + updatedRoom.getRoom_id() + " updated successfully.");
            return updatedRoom;
        }

        System.out.println("Room update failed. Room not found with ID: " + roomAvailability.getRoom_id());
        return null;
    }

    @Override
    public String deleteRoom(Integer room_id) {
        if (roomAvailabilityRepository.existsById(room_id)) {
            roomAvailabilityRepository.deleteById(room_id);
            return "Room with ID " + room_id + " deleted successfully.";
        } else {
            return "Room with ID " + room_id + " does not exist!";
        }
    }
}