package com.cognizant.training.feedbacktrack.repository;


import com.cognizant.training.feedbacktrack.model.FeedbackCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackCategoryRepository extends JpaRepository<FeedbackCategory,Long> {
}
