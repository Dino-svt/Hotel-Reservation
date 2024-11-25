package com.example.HotelDayPackage.ServiceImplementation;
import com.example.HotelDayPackage.Entity.Feedback;
import com.example.HotelDayPackage.Repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;

    @Autowired
    public FeedbackService(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    public Feedback addFeedback(Feedback feedback) {
        return feedbackRepository.save(feedback);
    }

    public Optional<Feedback> getFeedback(Long id) {
        return feedbackRepository.findById(id);
    }

    public List<Feedback> getUserFeedbackByNic(String userNic) {
        return feedbackRepository.findByUserNic(userNic);
    }

    public Feedback updateFeedback(Long id, Feedback newFeedback) {
        return feedbackRepository.findById(id).map(feedback -> {

            Feedback updatedFeedback = Feedback.builder()
                    .id(feedback.getId())
                    .userNic(feedback.getUserNic())
                    .userName(feedback.getUserName())
                    .userEmail(feedback.getUserEmail())
                    .userNumber(feedback.getUserNumber())
                    .comment(newFeedback.getComment())
                    .rating(newFeedback.getRating())
                    .isAdminViewable(feedback.isAdminViewable())
                    .build();

            return feedbackRepository.save(updatedFeedback);
        }).orElse(null);
    }

    public void deleteFeedback(Long id) {
        feedbackRepository.deleteById(id);
    }

    public List<Feedback> getAllFeedback() {
        return feedbackRepository.findAll();

    }

    public List<Feedback> AllFeedback() {
        return feedbackRepository.findAll();
    }
}
