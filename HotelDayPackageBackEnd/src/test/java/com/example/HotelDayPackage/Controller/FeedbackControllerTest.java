package com.example.HotelDayPackage.Controller;

import com.example.HotelDayPackage.Entity.Feedback;
import com.example.HotelDayPackage.Service.FeedbackService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class FeedbackControllerTest {

    private MockMvc mockMvc;

    @Mock
    private FeedbackService feedbackService;

    @InjectMocks
    private FeedbackController feedbackController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(feedbackController).build();
    }

    @Test
    public void testAddFeedback() throws Exception {
        Feedback feedback = new Feedback(1L, "123456789", "John Doe", "john.doe@example.com","07178978901" ,"Great service", 5, true);
        when(feedbackService.addFeedback(any(Feedback.class))).thenReturn(feedback);

        mockMvc.perform(post("/api/feedback/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userNic\":\"123456789\",\"userName\":\"John Doe\",\"userEmail\":\"john.doe@example.com\",\"userNumber\":\"07178978901\"\"comment\":\"Great service\",\"rating\":5}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userNic").value("123456789"))
                .andExpect(jsonPath("$.userName").value("John Doe"))
                .andExpect(jsonPath("$.userEmail").value("john.doe@example.com"))
                .andExpect(jsonPath("$.userNumber").value("07178978901"));

    }

    @Test
    public void testUpdateFeedback() throws Exception {
        Feedback updatedFeedback = new Feedback(1L, "123456789", "John Doe", "john.doe@example.com","07178978901", "Updated comment", 4, true);
        when(feedbackService.updateFeedback(eq(1L), any(Feedback.class))).thenReturn(updatedFeedback);

        mockMvc.perform(put("/api/feedback/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"comment\":\"Updated comment\",\"rating\":4}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.comment").value("Updated comment"))
                .andExpect(jsonPath("$.rating").value(4));
    }

    @Test
    public void testDeleteFeedback() throws Exception {
        // Mock the service method to do nothing
        doNothing().when(feedbackService).deleteFeedback(1L);

        // Perform the DELETE request with the correct path variable name (case-sensitive)
        mockMvc.perform(delete("/api/feedback/delete/{id}", 1L))
                .andExpect(status().isNoContent());

    }


    @Test
    public void testGetAllFeedback() throws Exception {
        Feedback feedback1 = new Feedback(1L, "123456789", "John Doe", "john.doe@example.com","07178978901" ,"Great service", 5, true);
        Feedback feedback2 = new Feedback(2L, "987654321", "Jane Doe", "jane.doe@example.com","07178978901","Good service", 4, true);
        List<Feedback> feedbackList = Arrays.asList(feedback1, feedback2);
        when(feedbackService.getAllFeedback()).thenReturn(feedbackList);

        mockMvc.perform(get("/api/feedback/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].userNic").value("123456789"))
                .andExpect(jsonPath("$[1].userNic").value("987654321"));
    }

    @Test
    public void testGetUserFeedbackByNic() throws Exception {
        Feedback feedback1 = new Feedback(1L, "123456789", "John Doe", "john.doe@example.com","07178978901","Great service", 5, true);
        when(feedbackService.getUserFeedbackByNic("123456789")).thenReturn(List.of(feedback1));

        mockMvc.perform(get("/api/feedback/user/nic/123456789"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].userNic").value("123456789"))
                .andExpect(jsonPath("$[0].userName").value("John Doe"));
    }

    @Test
    public void testGetAllAdminViewableFeedback() throws Exception {
        Feedback feedback1 = new Feedback(1L, "123456789", "John Doe", "john.doe@example.com","07178978901", "Great service", 5, true);
        Feedback feedback2 = new Feedback(2L, "987654321", "Jane Doe", "jane.doe@example.com","07178978901","Good service", 4, true);
        List<Feedback> feedbackList = Arrays.asList(feedback1, feedback2);
        when(feedbackService.AllFeedback()).thenReturn(feedbackList);

        mockMvc.perform(get("/api/feedback/user/view/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].userNic").value("123456789"))
                .andExpect(jsonPath("$[1].userNic").value("987654321"));
    }
}
