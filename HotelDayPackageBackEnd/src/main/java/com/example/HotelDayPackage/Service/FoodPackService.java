package com.example.HotelDayPackage.Service;

import com.example.HotelDayPackage.Entity.FoodPack;

import java.util.List;

public interface FoodPackService {
    FoodPack addFoodPack(FoodPack foodPack);

    List<FoodPack> getFoodPack();

    FoodPack updateFoodPack(FoodPack foodPack, String day);

    void deleteFoodPackById(String day);

    FoodPack getFoodPackByDay(String day);
}
