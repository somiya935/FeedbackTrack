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
public class Recognition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recognitionId;

    @ManyToOne
    @JoinColumn(name = "senderId")
    private User sender;

    @ManyToOne
    @JoinColumn(name = "targetUserId")
    private User targetUser;

    @ManyToOne
    @JoinColumn(name = "badgeId")
    private BadgeLibrary badge;

    @Column(columnDefinition = "TEXT")
    private String message;

    private LocalDateTime recognizedDate = LocalDateTime.now();
}
