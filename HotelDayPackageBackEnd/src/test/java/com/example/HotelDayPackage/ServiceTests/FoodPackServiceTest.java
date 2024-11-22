package com.example.HotelDayPackage.ServiceTests;

import com.example.HotelDayPackage.Entity.FoodPack;
import com.example.HotelDayPackage.Exception.FoodPackAlreadyExistsException;
import com.example.HotelDayPackage.Exception.FoodPackNotFoundException;
import com.example.HotelDayPackage.Repository.FoodPackRepo;
import com.example.HotelDayPackage.Service.FoodPackService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class FoodPackServiceTest {

    @InjectMocks
    private FoodPackService foodPackService;

    @Mock
    private FoodPackRepo foodPackRepo;

    private FoodPack foodPack;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        foodPack = new FoodPack("Monday", "Package A", "Bread, Milk");
    }

    @Test
    void addFoodPackPositive() {
        when(foodPackRepo.findByDay(foodPack.getDay())).thenReturn(Optional.empty());
        when(foodPackRepo.save(foodPack)).thenReturn(foodPack);

        FoodPack result = foodPackService.addFoodPack(foodPack);

        assertNotNull(result);
        assertEquals("Monday", result.getDay());
        verify(foodPackRepo, times(1)).save(foodPack);
    }

    @Test
    void addFoodPackNegativeAlreadyExists() {
        when(foodPackRepo.findByDay(foodPack.getDay())).thenReturn(Optional.of(foodPack));

        assertThrows(FoodPackAlreadyExistsException.class, () -> foodPackService.addFoodPack(foodPack));

        verify(foodPackRepo, never()).save(any(FoodPack.class));
    }


    @Test
    void updateFoodPackPositive() {
        when(foodPackRepo.findByDay("Monday")).thenReturn(Optional.of(foodPack));

        FoodPack updated = foodPackService.updateFoodPack(foodPack, "Monday");

        assertNotNull(updated);
        assertEquals("Monday", updated.getDay());
        verify(foodPackRepo, times(1)).save(foodPack);
    }

    @Test
    void updateFoodPackNegativeNotFound() {
        when(foodPackRepo.findByDay(anyString())).thenReturn(Optional.empty());

        assertThrows(FoodPackNotFoundException.class, () -> foodPackService.updateFoodPack(foodPack, "Monday"));

        verify(foodPackRepo, never()).save(any(FoodPack.class));
    }


    @Test
    void deleteFoodPack_Positive() {
        when(foodPackRepo.existsById("Monday")).thenReturn(true);
        doNothing().when(foodPackRepo).deleteById("Monday");

        foodPackService.deleteFoodPackById("Monday");

        verify(foodPackRepo, times(1)).deleteById("Monday");
    }

    @Test
    void deleteFoodPack_Negative_NotFound() {
        when(foodPackRepo.existsById(anyString())).thenReturn(false);

        assertThrows(FoodPackNotFoundException.class, () -> foodPackService.deleteFoodPackById("Monday"));

        verify(foodPackRepo, never()).deleteById(anyString());
    }

}
