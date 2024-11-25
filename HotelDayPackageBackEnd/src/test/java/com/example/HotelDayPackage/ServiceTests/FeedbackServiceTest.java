package com.example.HotelDayPackage.Service;


import com.example.HotelDayPackage.Entity.Feedback;
import com.example.HotelDayPackage.Repository.FeedbackRepository;
import com.example.HotelDayPackage.ServiceImplementation.FeedbackService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class FeedbackServiceTest {

    @Mock
    private FeedbackRepository feedbackRepository;

    @InjectMocks
    private FeedbackService feedbackService;

    private Feedback feedback;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        feedback = new Feedback(1L, "123456789", "John Doe", "john.doe@example.com","07178978901", "Great service", 5, true);
    }

    @Test
    public void testAddFeedback() {
        when(feedbackRepository.save(feedback)).thenReturn(feedback);

        Feedback savedFeedback = feedbackService.addFeedback(feedback);

        assertNotNull(savedFeedback);
        assertEquals("123456789", savedFeedback.getUserNic());
        assertEquals("John Doe", savedFeedback.getUserName());
        assertEquals("john.doe@example.com", savedFeedback.getUserEmail());
        assertEquals("Great service", savedFeedback.getComment());
        assertEquals(5, savedFeedback.getRating());
    }

    @Test
    public void testUpdateFeedback() {
        // Arrange
        Feedback existingFeedback = new Feedback(1L, "123456789", "John Doe", "john.doe@example.com","07178978901","Original comment", 3, true);
        Feedback updatedFeedback = new Feedback(1L, "123456789", "John Doe", "john.doe@example.com","07178978901", "Updated comment", 4, true);

        // Mock the repository to return the existing feedback when findById is called
        when(feedbackRepository.findById(1L)).thenReturn(Optional.of(existingFeedback));

        // Update the existingFeedback to match the updatedFeedback fields
        existingFeedback.setComment(updatedFeedback.getComment());
        existingFeedback.setRating(updatedFeedback.getRating());

        // Mock the repository to save the updated feedback
        when(feedbackRepository.save(existingFeedback)).thenReturn(existingFeedback); // Save the updated object

        // Act
        Feedback result = feedbackService.updateFeedback(1L, updatedFeedback);

        // Assert
        assertNotNull(result);
        assertEquals("Updated comment", result.getComment()); // Ensure comment is updated
        assertEquals(4, result.getRating()); // Ensure rating is updated

        // Verify that save was called with the updated feedback
        verify(feedbackRepository).save(existingFeedback);
    }

    @Test
    public void testDeleteFeedback() {
        doNothing().when(feedbackRepository).deleteById(1L);

        feedbackService.deleteFeedback(1L);

        verify(feedbackRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testGetUserFeedbackByNic() {
        when(feedbackRepository.findByUserNic("123456789")).thenReturn(List.of(feedback));

        List<Feedback> feedbackList = feedbackService.getUserFeedbackByNic("123456789");

        assertNotNull(feedbackList);
        assertEquals(1, feedbackList.size());
        assertEquals("123456789", feedbackList.get(0).getUserNic());
    }
}
