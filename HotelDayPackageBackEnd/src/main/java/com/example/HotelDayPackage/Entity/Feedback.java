package com.example.HotelDayPackage.Entity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "feedback")
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Auto-incremented  ID

    @Column(nullable = false, unique = true)
    private String userNic; // User NIC (National Identity Card)

    @Column(nullable = false)
    private String userName; // User's name

    @Column(nullable = false, unique = true)
    private String userEmail; // User's email

    @Column(nullable = false)
    private String userNumber;

    @Column(length = 500)
    private String comment; // Feedback comment

    private int rating; // Rating given by the user (1-5)

    private boolean isAdminViewable; // Whether the feedback is viewable by admin
}
