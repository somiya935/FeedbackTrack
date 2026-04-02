package com.cognizant.training.feedbacktrack.controller;

import com.cognizant.training.feedbacktrack.dto.CreateFeedbackRequest;
import com.cognizant.training.feedbacktrack.model.Feedback;
import com.cognizant.training.feedbacktrack.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @PostMapping("/create")
    public ResponseEntity<Feedback> createNewFeedback(@RequestBody CreateFeedbackRequest request){
        Feedback savedFeedback=feedbackService.createNewFeedback(request);
        return new ResponseEntity<>(savedFeedback, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")    // get a particular feedback
    public ResponseEntity<Feedback> getFeedbackById(@PathVariable("id") Long id){
        return ResponseEntity.ok(feedbackService.getFeedbackById(id));
    }

    @DeleteMapping("{id}") //delete a particular feedback with id
    public ResponseEntity<Feedback> deleteFeedbackById(@PathVariable("id") Long id, @RequestParam("userId") Long userId){
        //Get the userId from security or jwt in the backend(look at old role-based repo)
        Feedback feedback=feedbackService.deleteFeedbackById(id, userId);
        return ResponseEntity.ok(feedback);
    }

    @GetMapping("/manager/{id}") // feedbacks for all employees where,this guy is the manager
    public ResponseEntity<List<Feedback>> getManagerFeedbacks(@PathVariable("id") Long id){
        //Get the userId from security or jwt in the backend(look at old role-based repo)
        List<Feedback> feedbacks=feedbackService.getManagerFeedbacks(id);
        return ResponseEntity.ok(feedbacks);
    }
    @PutMapping("/{id}/visibility") //togger the annonymous thing of the feedback
    public ResponseEntity<Feedback> changeFeedbackVisibility(@PathVariable("id") Long id, @RequestParam("userId") Long userId){
        //Get the userId from security or jwt in the backend(look at old role-based repo)
        Feedback feedback = feedbackService.changeFeedbackVisibility(id, userId);
        return ResponseEntity.ok(feedback);
    }
}
