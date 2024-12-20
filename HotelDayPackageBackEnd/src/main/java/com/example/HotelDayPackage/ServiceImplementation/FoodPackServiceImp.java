package com.example.HotelDayPackage.ServiceImplementation;

import com.example.HotelDayPackage.Entity.FoodPack;
import com.example.HotelDayPackage.Repository.FoodPackRepo;
import com.example.HotelDayPackage.Exception.FoodPackAlreadyExistsException;
import com.example.HotelDayPackage.Exception.FoodPackNotFoundException;
import com.example.HotelDayPackage.Service.FoodPackService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class FoodPackServiceImp implements FoodPackService {

    private final FoodPackRepo foodPackRepo;

    @Override
    public List<FoodPack> getFoodPack() {
        return foodPackRepo.findAll();
    }

    @Override
    public FoodPack addFoodPack(FoodPack foodPack) {
        if (FoodPackAlreadyExists(foodPack.getDay())){
            throw new FoodPackAlreadyExistsException(foodPack.getDay()+ "already exists");
        }
        return foodPackRepo.save(foodPack);
    }

    private boolean FoodPackAlreadyExists(String day) {
        return foodPackRepo.findByDay(day).isPresent();
    }


    @Override
    public FoodPack updateFoodPack(FoodPack foodPack, String day) {
        return foodPackRepo.findByDay(day).map(fp ->{
            fp.setDay(foodPack.getDay());
            fp.setPackageName(foodPack.getPackageName());
            fp.setIngredients(foodPack.getIngredients());
            return foodPackRepo.save(fp);
        }).orElseThrow(()->new FoodPackNotFoundException("Sorry, this food package could not found."));
    }

    @Override
    public void deleteFoodPackById(String day) {
        if(!foodPackRepo.existsById(day)){
            throw new FoodPackNotFoundException("Sorry, Food package not found.");
        }foodPackRepo.deleteById(day);
    }

    @Override
    public FoodPack getFoodPackByDay(String day) {
        return foodPackRepo.findByDay(day).orElseThrow(()-> new FoodPackNotFoundException("Sorry, no food pack found with that day :"+day));
    }
}
