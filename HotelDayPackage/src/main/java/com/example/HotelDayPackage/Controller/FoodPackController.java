package com.example.HotelDayPackage.Controller;

import com.example.HotelDayPackage.Entity.FoodPack;
import com.example.HotelDayPackage.Service.IFoodPackService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/FoodPackage")
@RequiredArgsConstructor
public class FoodPackController {
    private final IFoodPackService foodPackService;
    @GetMapping("/getFoodPack")
    public ResponseEntity<List<FoodPack>> getFoodPack(){
        return new ResponseEntity<>(foodPackService.getFoodPack(), HttpStatus.FOUND);
    }

    @PostMapping("/addFoodPack")
    public FoodPack addFoodPack (@RequestBody FoodPack foodPack){
        return foodPackService.addFoodPack(foodPack);
    }

    @PutMapping("/updateFoodPack/{day}")
    public FoodPack updateFoodPack(@RequestBody FoodPack foodPack, @PathVariable String day){
        return foodPackService.updateFoodPack(foodPack, day);
    }

    @DeleteMapping("/deleteFoodPack/{day}")
    public void deleteFoodPackById(@PathVariable String day){
        foodPackService.deleteFoodPackById(day);
    }


}
