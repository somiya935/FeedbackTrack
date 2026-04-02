package com.cognizant.training.feedbacktrack.model;

import com.cognizant.training.feedbacktrack.enums.NotificationType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Entity
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notificationId;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @Enumerated(EnumType.STRING)
    private NotificationType type;

    private Long sourceId; // Refers to FeedbackID or RecognitionID
    private String message;
    private boolean isRead = false;
    private LocalDateTime createdAt = LocalDateTime.now();
}
