package com.cognizant.training.feedbacktrack.service;

import com.cognizant.training.feedbacktrack.dto.CreateFeedbackRequest;
import com.cognizant.training.feedbacktrack.enums.Role;
import com.cognizant.training.feedbacktrack.exceptions.FeedbackCategoryNotFoundException;
import com.cognizant.training.feedbacktrack.exceptions.FeedbackNotFoundException;
import com.cognizant.training.feedbacktrack.exceptions.UnauthorizedAccessException;
import com.cognizant.training.feedbacktrack.exceptions.UserNotFoundException;
import com.cognizant.training.feedbacktrack.model.Feedback;
import com.cognizant.training.feedbacktrack.model.User;
import com.cognizant.training.feedbacktrack.repository.FeedbackCategoryRepository;
import com.cognizant.training.feedbacktrack.repository.FeedbackRepository;
import com.cognizant.training.feedbacktrack.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor @NoArgsConstructor
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FeedbackCategoryRepository feedbackCategoryRepository;

    public Feedback createNewFeedback(CreateFeedbackRequest request){
        Feedback feedback=new Feedback();
        feedback.setSender(userRepository.findById(request.getSenderId())
                .orElseThrow(()->new UserNotFoundException("User not found with id: "+request.getSenderId())));
        feedback.setTargetUser(userRepository.findById(request.getTargetUserId())
                .orElseThrow(() -> new UserNotFoundException("Target user not found")));
        feedback.setCategory(feedbackCategoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new FeedbackCategoryNotFoundException("Category not found")));
        feedback.setComments(request.getComments());
        feedback.setAnonymous(request.isAnonymous());
        feedback.setSubmittedDate(LocalDateTime.now());
        return feedbackRepository.save(feedback);
    }

    public Feedback getFeedbackById(Long id) {
        return feedbackRepository.findById(id).orElseThrow(()->new FeedbackNotFoundException("Feedback not found with the ID"+id));
    }

    public Feedback deleteFeedbackById(Long feedbackId, Long currentUserId) {
        Feedback feedback = feedbackRepository.findById(feedbackId)
                .orElseThrow(() -> new FeedbackNotFoundException("Feedback not found with ID: " + feedbackId));

        if (!feedback.getSender().getUserId().equals(currentUserId)) {
            // Throw a 403 Forbidden error if they aren't the owner
            throw new UnauthorizedAccessException("You do not have permission to delete this feedback.");
        }
        feedbackRepository.delete(feedback);
        return feedback;
    }

    public List<Feedback> getManagerFeedbacks(Long userId) {
        User manager = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("Manager not found"));
        if (!manager.getRole().equals(Role.MANAGER)) {
            throw new UnauthorizedAccessException("Access Denied: You are not a Manager.");
        }
        List<Feedback> teamFeedback = feedbackRepository.findAllByTargetUser_Manager_UserId(userId);
        return teamFeedback;
    }

    public Feedback changeFeedbackVisibility(Long id, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("Manager not found"));
        Feedback feedback = feedbackRepository.findById(id)
                .orElseThrow(() -> new FeedbackNotFoundException("Feedback not found with ID: " + id));
        if (!feedback.getSender().getUserId().equals(user.getUserId())) {
            throw new UnauthorizedAccessException("You are not authorized to change this feedback's visibility.");
        }
        feedback.setAnonymous(!feedback.isAnonymous());
        return feedbackRepository.save(feedback);
    }
}
