package com.example.HotelDayPackage.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "FoodPackage")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter

public class FoodPack {
    @Id
    @Column(name = "day")
    private String day;
    @Column(name = "package_name")
    private String packageName;
    @Column(name = "ingredient")
    private String ingredients;
}
