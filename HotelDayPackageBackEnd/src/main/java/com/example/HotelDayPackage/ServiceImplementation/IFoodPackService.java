package com.example.HotelDayPackage.ServiceImplementation;

import com.example.HotelDayPackage.Entity.FoodPack;

import java.util.List;

public interface IFoodPackService {
    FoodPack addFoodPack(FoodPack foodPack);

    List<FoodPack> getFoodPack();

    FoodPack updateFoodPack(FoodPack foodPack, String day);

    void deleteFoodPackById(String day);

    FoodPack getFoodPackByDay(String day);
}
