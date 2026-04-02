package com.cognizant.training.feedbacktrack.model;

import com.cognizant.training.feedbacktrack.enums.FeedbackAction;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Entity
public class FeedbackReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    @OneToOne
    @JoinColumn(name = "feedbackId")
    private Feedback feedback;

    @OneToOne
    @JoinColumn(name = "reviewerId")
    private User reviewer;

    @Enumerated(EnumType.STRING)
    private FeedbackAction actionTaken; // e.g., Acknowledged, Resolved

    private String managerNotes;

    private LocalDateTime reviewDate = LocalDateTime.now();
}
