package com.example.HotelDayPackage.Repository;

import com.example.HotelDayPackage.Entity.RoomAvailability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface RoomAvailabilityRepository extends JpaRepository<RoomAvailability, Integer> {

    @Query("SELECT r FROM RoomAvailability r WHERE r.booking_date = :booking_date AND r.status != 'Booking'")
    List<RoomAvailability> findAvailableRoomsByDate(@Param("booking_date") LocalDate booking_date);
}