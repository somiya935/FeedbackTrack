package com.cognizant.training.feedbacktrack.service;

import com.cognizant.training.feedbacktrack.model.FeedbackCategory;
import com.cognizant.training.feedbacktrack.repository.FeedbackCategoryRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor @NoArgsConstructor
public class FeedbackCategoryService {

    @Autowired
    private FeedbackCategoryRepository feedbackCategoryRepository;

    public List<FeedbackCategory> findAllCategories() {
        return feedbackCategoryRepository.findAll();
    }
}
