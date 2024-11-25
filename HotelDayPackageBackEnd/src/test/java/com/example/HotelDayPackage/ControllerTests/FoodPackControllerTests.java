package com.example.HotelDayPackage.ControllerTests;

import com.example.HotelDayPackage.Controller.FoodPackController;
import com.example.HotelDayPackage.Entity.FoodPack;
import com.example.HotelDayPackage.Exception.FoodPackAlreadyExistsException;
import com.example.HotelDayPackage.Exception.FoodPackNotFoundException;
import com.example.HotelDayPackage.Service.FoodPackService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FoodPackControllerTests {

    @InjectMocks
    private FoodPackController foodPackController;

    @Mock
    private FoodPackService foodPackService;

    private FoodPack foodPack;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        foodPack = new FoodPack("Monday", "Package A", "Bread, Rice");
    }

    @Test
    void getFoodPackPositive() {
        when(foodPackService.getFoodPack()).thenReturn(Arrays.asList(foodPack));

        ResponseEntity<List<FoodPack>> response = foodPackController.getFoodPack();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
    }

    @Test
    void getFoodPackNegativeEmptyList() {
        when(foodPackService.getFoodPack()).thenReturn(Arrays.asList());

        ResponseEntity<List<FoodPack>> response = foodPackController.getFoodPack();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().isEmpty());
    }


    @Test
    void addFoodPackPositive() {
        when(foodPackService.addFoodPack(any(FoodPack.class))).thenReturn(foodPack);

        FoodPack result = foodPackController.addFoodPack(foodPack);

        assertNotNull(result);
        assertEquals("Monday", result.getDay());
    }

    @Test
    void addFoodPackNegativeAlreadyExists() {
        when(foodPackService.addFoodPack(any(FoodPack.class))).thenThrow(new FoodPackAlreadyExistsException("Monday already exists"));

        assertThrows(FoodPackAlreadyExistsException.class, () -> foodPackController.addFoodPack(foodPack));
    }


    @Test
    void updateFoodPackPositive() {
        when(foodPackService.updateFoodPack(any(FoodPack.class), anyString())).thenReturn(foodPack);

        FoodPack result = foodPackController.updateFoodPack(foodPack, "Monday");

        assertNotNull(result);
        assertEquals("Monday", result.getDay());
    }

    @Test
    void updateFoodPackNegativeNotFound() {
        when(foodPackService.updateFoodPack(any(FoodPack.class), anyString()))
                .thenThrow(new FoodPackNotFoundException("FoodPack not found"));

        assertThrows(FoodPackNotFoundException.class, () -> foodPackController.updateFoodPack(foodPack, "Monday"));
    }


    @Test
    void deleteFoodPackPositive() {
        doNothing().when(foodPackService).deleteFoodPackById("Monday");

        foodPackController.deleteFoodPackById("Monday");

        verify(foodPackService, times(1)).deleteFoodPackById("Monday");
    }

    @Test
    void deleteFoodPackNegativeNotFound() {
        doThrow(new FoodPackNotFoundException("FoodPack not found"))
                .when(foodPackService).deleteFoodPackById(anyString());

        assertThrows(FoodPackNotFoundException.class, () -> foodPackController.deleteFoodPackById("Monday"));
    }


    @Test
    void testGetFoodPackByDayPositive() {
        when(foodPackService.getFoodPackByDay("Monday")).thenReturn(foodPack);

        FoodPack foundFoodPack = foodPackController.getFoodPackByDay("Monday");

        assertNotNull(foundFoodPack);
        assertEquals("Monday", foundFoodPack.getDay());
        assertEquals("Package A", foundFoodPack.getPackageName());
        assertEquals("Bread, Rice", foundFoodPack.getIngredients());
        verify(foodPackService, times(1)).getFoodPackByDay("Monday");
    }

    @Test
    void testGetFoodPackByDayNegativeNotFound() {
        when(foodPackService.getFoodPackByDay("Monday"))
                .thenThrow(new FoodPackNotFoundException("FoodPack not found for day: Monday"));

        FoodPackNotFoundException exception = assertThrows(FoodPackNotFoundException.class,
                () -> foodPackController.getFoodPackByDay("Monday"));

        assertEquals("FoodPack not found for day: Monday", exception.getMessage());
        verify(foodPackService, times(1)).getFoodPackByDay("Monday");
    }

}
