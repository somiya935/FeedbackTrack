package com.cognizant.training.feedbacktrack.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Data
public class CreateFeedbackRequest {
    @NotEmpty
    private Long senderId;
    @NotEmpty
    private Long targetUserId;
    @NotEmpty
    private Long categoryId;
    private String comments;
    private boolean isAnonymous;
}
