package com.example.HotelDayPackage.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Data
public class RoomAvailabilityDTO {
    private int room_id;
    private String room_type;
    private String room_number;
    private String room_facilities;
    private String status;
    private LocalDate booking_date;
}
