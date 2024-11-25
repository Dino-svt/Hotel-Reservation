package com.example.HotelDayPackage.Repository;
import com.example.HotelDayPackage.Entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    List<Feedback> findByUserNic(String userNic);
    List<Feedback> findByIsAdminViewableTrue();
}
