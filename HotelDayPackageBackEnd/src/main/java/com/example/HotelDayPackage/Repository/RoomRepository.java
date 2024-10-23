package com.example.HotelDayPackage.Repository;

import com.example.HotelDayPackage.Entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, String> {
}
