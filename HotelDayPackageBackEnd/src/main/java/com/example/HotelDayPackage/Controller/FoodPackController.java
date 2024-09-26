package com.example.HotelDayPackage.Controller;

import com.example.HotelDayPackage.Entity.FoodPack;
import com.example.HotelDayPackage.ServiceImplementation.IFoodPackService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("http://localhost:3000/")
@RestController
@RequestMapping("/fp")
@RequiredArgsConstructor
public class FoodPackController {
    private final IFoodPackService foodPackService;
    @GetMapping("/gfp")
    public ResponseEntity<List<FoodPack>> getFoodPack(){
        return new ResponseEntity<>(foodPackService.getFoodPack(), HttpStatus.OK);
    }

    @PostMapping("/afp")
    public FoodPack addFoodPack (@RequestBody FoodPack foodPack){
        return foodPackService.addFoodPack(foodPack);
    }

    @PutMapping("/ufp/{day}")
    public FoodPack updateFoodPack(@RequestBody FoodPack foodPack, @PathVariable String day){
        return foodPackService.updateFoodPack(foodPack, day);
    }

    @DeleteMapping("/dfp/{day}")
    public void deleteFoodPackById(@PathVariable String day){
        foodPackService.deleteFoodPackById(day);
    }


}
