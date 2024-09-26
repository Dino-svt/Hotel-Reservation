package com.example.HotelDayPackage.Repository;

import com.example.HotelDayPackage.Entity.FoodPack;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FoodPackRepo extends JpaRepository<FoodPack, String> {

    Optional<FoodPack> findByDay(String day);



}
