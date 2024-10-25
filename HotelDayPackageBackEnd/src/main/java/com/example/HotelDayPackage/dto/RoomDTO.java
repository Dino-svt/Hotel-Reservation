package com.example.HotelDayPackage.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomDTO {
    private String roomId;
    private String roomType;
    private int roomNumber;
    private String roomFacilities;
    private String status;
    private String checkInDate;
    private String todayDate;
}
