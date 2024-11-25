package com.example.HotelDayPackage.Controller;

import com.example.HotelDayPackage.Entity.Feedback;
import com.example.HotelDayPackage.ServiceImplementation.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/feedback")
@CrossOrigin(origins = "http://localhost:3000")
public class FeedbackController {

    private final FeedbackService feedbackService;

    @Autowired
    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @PostMapping("/add")
    public Feedback addFeedback(@RequestBody Feedback feedback) {
        Feedback newFeedback = Feedback.builder()
                .userNic(feedback.getUserNic())
                .userName(feedback.getUserName())
                .userEmail(feedback.getUserEmail())
                .userNumber(feedback.getUserNumber())
                .comment(feedback.getComment())
                .rating(feedback.getRating())
                .isAdminViewable(true) // Default to true for admin view
                .build();
        return feedbackService.addFeedback(newFeedback);
    }


    @PutMapping("/update/{id}")
    public Feedback updateFeedback(@PathVariable Long id, @RequestBody Feedback feedback) {
        return feedbackService.updateFeedback(id, feedback);
    }


    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFeedback(@PathVariable Long id) {
        feedbackService.deleteFeedback(id);
    }


    @GetMapping("/user/nic/{nic}")
    public List<Feedback> getUserFeedback(@PathVariable String nic) {
        return feedbackService.getUserFeedbackByNic(nic);
    }


    @GetMapping("/all")
    public List<Feedback> getAllFeedback() {
        return feedbackService.getAllFeedback();}


    @GetMapping("/user/view/all")
    public List<Feedback> AllFeedback() {

        return feedbackService.AllFeedback();
    }

}





