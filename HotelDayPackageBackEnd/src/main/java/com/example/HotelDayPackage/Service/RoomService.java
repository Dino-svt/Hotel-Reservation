package com.example.HotelDayPackage.Service;

import com.example.HotelDayPackage.dto.BookingDTO;
import com.example.HotelDayPackage.dto.RoomDTO;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomService {
    RoomDTO getRoomById(String roomId);
    RoomDTO bookRoom(String roomId, BookingDTO bookingDTO);
}
