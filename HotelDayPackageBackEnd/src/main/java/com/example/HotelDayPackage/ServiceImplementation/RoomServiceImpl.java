package com.example.HotelDayPackage.ServiceImplementation;

import com.example.HotelDayPackage.dto.BookingDTO;
import com.example.HotelDayPackage.dto.RoomDTO;
import com.example.HotelDayPackage.Entity.Room;
import com.example.HotelDayPackage.exception.ResourceNotFoundException;
import com.example.HotelDayPackage.exception.RoomAlreadyBookedException;
import com.example.HotelDayPackage.Repository.RoomRepository;
import com.example.HotelDayPackage.Service.RoomService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    // Constructor-based dependency injection
    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public RoomDTO getRoomById(String roomId) {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found with id: " + roomId));
        return new RoomDTO(room.getRoomId(), room.getRoomType(), room.getRoomNumber(), room.getRoomFacilities(),
                room.getStatus(), room.getCheckInDate(), room.getTodayDate());
    }

    @Transactional  // Ensures atomic transaction for room booking
    @Override
    public RoomDTO bookRoom(String roomId, BookingDTO bookingDTO) {
        // Fetch room
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found with id: " + roomId));

        // Check availability
        if (!room.getStatus().equals("available")) {
            throw new RoomAlreadyBookedException("Room is already booked!");
        }

        // Update status to 'booked' and save booking date
        room.setStatus("booked");
        room.setCheckInDate(bookingDTO.getCheckInDate());
        roomRepository.save(room);

        // Return updated room DTO
        return new RoomDTO(room.getRoomId(), room.getRoomType(), room.getRoomNumber(), room.getRoomFacilities(),
                room.getStatus(), room.getCheckInDate(), room.getTodayDate());
    }

    @Override
    public RoomDTO addRoom(RoomDTO roomDTO) {
        Room room = new Room();
        room.setRoomId(roomDTO.getRoomId());
        room.setRoomType(roomDTO.getRoomType());
        room.setRoomNumber(roomDTO.getRoomNumber());
        room.setRoomFacilities(roomDTO.getRoomFacilities());
        room.setStatus("available");  // Default status as "available"
        room.setCheckInDate(null);    // Initially, no booking
        room.setTodayDate(roomDTO.getTodayDate());

        roomRepository.save(room);

        return roomDTO;  // Return saved room DTO
    }
}
