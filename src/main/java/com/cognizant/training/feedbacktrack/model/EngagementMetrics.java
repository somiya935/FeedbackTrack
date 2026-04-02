package com.cognizant.training.feedbacktrack.model;

import com.cognizant.training.feedbacktrack.enums.Scope;
import com.cognizant.training.feedbacktrack.enums.TargetType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Entity
public class EngagementMetrics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long metricId;

    @Column(nullable = false)
    private Long targetId; // UserID or DepartmentID based on scope

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TargetType targetType;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Scope scope;

    private Integer feedbackCount;
    private Integer recognitionPoints;
    private Double engagementScore; // Calculated metric

    @Column(nullable = false, updatable = false)
    private LocalDateTime generatedDate = LocalDateTime.now();
}
