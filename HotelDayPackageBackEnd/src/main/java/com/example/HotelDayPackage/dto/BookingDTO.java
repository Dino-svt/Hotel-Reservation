package com.example.HotelDayPackage.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingDTO {
    private String roomId;
    private String customerName;
    private String customerNIC;
    private String address;
    private String checkInDate;
    private String checkOutDate;
    private String currentDate;
}
