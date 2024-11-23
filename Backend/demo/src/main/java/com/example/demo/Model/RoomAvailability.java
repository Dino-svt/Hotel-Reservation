package com.example.demo.Model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "Availability")
@NoArgsConstructor
@AllArgsConstructor
public class RoomAvailability {
    @Id
    @Column(name = "ROOM_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int room_id;

    @Column(name = "ROOM_TYPE")
    private String room_type;

    @Column(name = "ROOM_NUMBER")
    private String room_number;

    @Column(name = "ROOM_FACILITIES")
    private String room_facilities;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "BOOKING_DATE")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate booking_date;

    public RoomAvailability(int i, String deluxe, String available, LocalDate now) {
    }

    // Builder Class for RoomAvailability
    public static class Builder {
        private int room_id;
        private String room_type;
        private String room_number;
        private String room_facilities;
        private String status;
        private LocalDate booking_date;

        public Builder withRoomId(int room_id) {
            this.room_id = room_id;
            return this;
        }

        public Builder withRoomType(String room_type) {
            this.room_type = room_type;
            return this;
        }

        public Builder withRoomNumber(String room_number) {
            this.room_number = room_number;
            return this;
        }

        public Builder withRoomFacilities(String room_facilities) {
            this.room_facilities = room_facilities;
            return this;
        }

        public Builder withStatus(String status) {
            this.status = status;
            return this;
        }

        public Builder withBookingDate(LocalDate booking_date) {
            this.booking_date = booking_date;
            return this;
        }

        public RoomAvailability build() {
            RoomAvailability roomAvailability = new RoomAvailability();
            roomAvailability.room_id = this.room_id;
            roomAvailability.room_type = this.room_type;
            roomAvailability.room_number = this.room_number;
            roomAvailability.room_facilities = this.room_facilities;
            roomAvailability.status = this.status;
            roomAvailability.booking_date = this.booking_date;
            return roomAvailability;
        }
    }
}