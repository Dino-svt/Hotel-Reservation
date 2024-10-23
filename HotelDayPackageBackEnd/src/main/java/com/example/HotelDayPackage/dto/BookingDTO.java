package com.example.HotelDayPackage.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingDTO {
    private String roomId;
    private String bookingDate;
    private String customerName;
}
