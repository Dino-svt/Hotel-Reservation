package com.example.HotelDayPackage.ServiceImplementation;

import com.example.HotelDayPackage.dto.BookingDTO;
import com.example.HotelDayPackage.dto.RoomDTO;
import com.example.HotelDayPackage.Entity.Room;
import com.example.HotelDayPackage.exception.ResourceNotFoundException;
import com.example.HotelDayPackage.exception.RoomAlreadyBookedException;
import com.example.HotelDayPackage.Repository.RoomRepository;
import com.example.HotelDayPackage.Service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Override
    public RoomDTO getRoomById(String roomId) {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found with id: " + roomId));
        return new RoomDTO(room.getRoomId(), room.getRoomType(), room.getRoomNumber(), room.getRoomFacilities(),
                room.getStatus(), room.getBookingDate(), room.getTodayDate());
    }

    @Override
    public RoomDTO bookRoom(String roomId, BookingDTO bookingDTO) {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found with id: " + roomId));

        if (!room.getStatus().equals("available")) {
            throw new RoomAlreadyBookedException("Room is already booked!");
        }

        room.setStatus("booked");
        room.setBookingDate(bookingDTO.getBookingDate());
        roomRepository.save(room);

        return new RoomDTO(room.getRoomId(), room.getRoomType(), room.getRoomNumber(), room.getRoomFacilities(),
                room.getStatus(), room.getBookingDate(), room.getTodayDate());
    }
}
