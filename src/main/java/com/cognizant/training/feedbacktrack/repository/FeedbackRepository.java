package com.cognizant.training.feedbacktrack.repository;

import com.cognizant.training.feedbacktrack.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    List<Feedback> findAllByTargetUser_Manager_UserId(Long managerId);
}
