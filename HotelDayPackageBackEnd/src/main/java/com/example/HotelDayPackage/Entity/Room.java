package com.example.HotelDayPackage.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Room {
    @Id
    private String roomId;
    private String roomType;
    private int roomNumber;
    private String roomFacilities;
    private String status; // "available", "booked"
    private String checkInDate;
    private String todayDate;
}
