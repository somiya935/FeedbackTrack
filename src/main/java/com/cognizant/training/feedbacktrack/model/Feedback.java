package com.cognizant.training.feedbacktrack.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Entity
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long feedbackId;

    @ManyToOne
    @JoinColumn(name = "senderId")
    private User sender;

    @ManyToOne
    @JoinColumn(name = "targetUserId")
    private User targetUser;

    @ManyToOne
    @JoinColumn(name = "categoryId")
    private FeedbackCategory category;

    @Column(columnDefinition = "TEXT")
    private String comments;

    @Column(columnDefinition = "boolean default false")
    private boolean isAnonymous;

    private LocalDateTime submittedDate=LocalDateTime.now();
}
