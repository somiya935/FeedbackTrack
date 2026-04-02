package com.cognizant.training.feedbacktrack.controller;

import com.cognizant.training.feedbacktrack.model.FeedbackCategory;
import com.cognizant.training.feedbacktrack.service.FeedbackCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/feedback_categories")
public class FeedbackCategoryController {

    @Autowired
    private FeedbackCategoryService feedbackCategoryService;

    @GetMapping("/all")  //get all feedbacks
    public ResponseEntity<List<FeedbackCategory>> findAllCategories(){
        List<FeedbackCategory> cats= feedbackCategoryService.findAllCategories();
        return new ResponseEntity<>(cats, HttpStatus.FOUND);
    }

}
